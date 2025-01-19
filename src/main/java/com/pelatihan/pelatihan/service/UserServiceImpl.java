package com.pelatihan.pelatihan.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.pelatihan.pelatihan.dto.RegisterUserDto;
import com.pelatihan.pelatihan.model.Users;

@Service
public class UserServiceImpl implements UserService{

    @Override
    public void register(RegisterUserDto dto){
        Users users = new Users();
        users.setId(dto.getId());
        users.setUsername(dto.getUsername());
        users.setPassword(dto.getPassword());
        users.setStatus(true);
        users.setCreatedDate(LocalDate.now());
        users.setUpdateDate(LocalDate.now());
    }
}
