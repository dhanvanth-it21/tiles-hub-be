package com.trustrace.tiles_hub_be.dao;

import com.trustrace.tiles_hub_be.model.collections.damage.DamageReport;
import com.trustrace.tiles_hub_be.template.DamageReportTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DamageReportDao {

    @Autowired
    private DamageReportTemplate damageReportTemplate;

    public DamageReport save(DamageReport damageReport) {
        return damageReportTemplate.save(damageReport);
    }

    public Optional<DamageReport> findById(String id) {
        return damageReportTemplate.findById(id);
    }

    public List<DamageReport> findAll() {
        return damageReportTemplate.findAll();
    }

    public boolean existsById(String id) {
        return damageReportTemplate.existsById(id);
    }

    public void deleteById(String id) {
        damageReportTemplate.deleteById(id);
    }
}
