package com.example.enetrevfinal.service;

import com.example.enetrevfinal.dao.RoleDao;
import com.example.enetrevfinal.entity.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    public Role createNewRole(Role role) {
        return roleDao.save(role);
    }
}
