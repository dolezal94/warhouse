package com.sda.finalproject.authmvc.services;

import com.sda.finalproject.authmvc.entities.RoleEntity;

public interface RoleService {

    RoleEntity findByName(String name) throws Exception;
}
