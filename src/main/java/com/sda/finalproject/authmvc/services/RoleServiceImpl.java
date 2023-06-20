package com.sda.finalproject.authmvc.services;

import com.sda.finalproject.authmvc.entities.RoleEntity;
import com.sda.finalproject.authmvc.repositories.RoleRepository;

public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public RoleEntity findByName(String name) throws Exception {
        return roleRepository.findByName(name)
                .orElseThrow(() -> new Exception("Role does not exist"));
    }
}
