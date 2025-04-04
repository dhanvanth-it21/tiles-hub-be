package com.trustrace.tiles_hub_be.template;

import com.trustrace.tiles_hub_be.model.collections.Actor.Supplier;
import com.trustrace.tiles_hub_be.model.collections.tiles_list.Order;
import com.trustrace.tiles_hub_be.model.collections.tiles_list.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrderTemplate {

    @Autowired
    private MongoTemplate mongoTemplate;

    public Order save(Order order) {
        return mongoTemplate.save(order);
    }

    public Optional<Order> findById(String id) {
        return Optional.ofNullable(mongoTemplate.findById(id, Order.class));
    }

    public boolean existsById(String id) {
        return mongoTemplate.exists(new Query(Criteria.where("_id").is(id)), Order.class);
    }

    public void deleteById(String id) {
        mongoTemplate.remove(new Query(Criteria.where("_id").is(id)), Order.class);
    }



    public Page<Order> findAll(int page, int size, String sortBy, String sortDirection, String search, OrderStatus orderStatus) {
        Sort.Direction direction = sortDirection.toUpperCase().equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));

        Query query = new Query();
        if(search == null || search.equals("")) {
            //nothing to do
        } else {
            query.addCriteria(new Criteria().orOperator(
                    Criteria.where("shopName").regex(search, "i"),
                    Criteria.where("status").regex(search, "i"),
                    Criteria.where("orderId").regex(search, "i")
            ));
        }

        if(orderStatus != null && (orderStatus == OrderStatus.PENDING || orderStatus == OrderStatus.DISPATCHED || orderStatus == OrderStatus.PICKING)) {
            query.addCriteria(Criteria.where("status").is(orderStatus));
        }
        long total = mongoTemplate.count(query, Order.class);

        query.with(pageable);

        List<Order> orders = mongoTemplate.find(query, Order.class);
        return new PageImpl<>(orders, pageable, total);
    }

    public List<Order> searchOrders(String search) {
        Query query = new Query();
        query.addCriteria(new Criteria().orOperator(
                Criteria.where("shopName").regex(search, "i"),
                Criteria.where("orderId").regex(search, "i")
        ));
        return mongoTemplate.find(query, Order.class);
    }

    public Optional<Order> findByOrderId(String givenId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("orderId").is(givenId));
        return Optional.ofNullable(mongoTemplate.findOne(query, Order.class));
    }

    public int getTotalPendingOrders() {
        Query query = new Query();
        query.addCriteria(Criteria.where("status").is(OrderStatus.PENDING));
        return (int) mongoTemplate.count(query, Order.class);
    }

    public int getTotalOrders() {
        return (int) mongoTemplate.count(new Query(), Order.class);
    }

    public int getTotalPickingOrders() {
        Query query = new Query();
        query.addCriteria(Criteria.where("status").is(OrderStatus.PICKING));
        return (int) mongoTemplate.count(query, Order.class);
    }
}
