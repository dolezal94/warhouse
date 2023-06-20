package com.sda.finalproject.authmvc.services;

import com.sda.finalproject.authmvc.dto.RegisterDto;

public interface UserService {

    void register(RegisterDto registerDto) throws Exception;
}
