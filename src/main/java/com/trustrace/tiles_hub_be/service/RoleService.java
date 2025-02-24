package com.trustrace.tiles_hub_be.service;

import com.trustrace.tiles_hub_be.dao.RoleDao;
import com.trustrace.tiles_hub_be.model.user.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    public Optional<Role> findByName(String userRole) {
        Optional<Role> role= roleDao.findByName(userRole);
        return role;
    }
}
