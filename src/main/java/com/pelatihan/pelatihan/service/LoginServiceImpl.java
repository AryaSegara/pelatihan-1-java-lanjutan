package com.pelatihan.pelatihan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pelatihan.pelatihan.dto.LoginDto;
import com.pelatihan.pelatihan.dto.UserDetailDto;
import com.pelatihan.pelatihan.repository.UsersRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService{

    private final UsersRepository usersRepository;

    @Autowired
    public LoginServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetailDto login(LoginDto dto) {
        return null;
    }


    
}
