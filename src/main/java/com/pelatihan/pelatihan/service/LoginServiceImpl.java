package com.pelatihan.pelatihan.service;

import java.util.Optional;

import org.checkerframework.checker.units.qual.t;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.pelatihan.pelatihan.dto.LoginDto;
import com.pelatihan.pelatihan.dto.UserDetailDto;
import com.pelatihan.pelatihan.model.Users;
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
        Optional<Users> optionalUsers = usersRepository.findByUsername(dto.getUsername());

        if(optionalUsers.isPresent()){

        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username atau Password Salah");
        }
        return null;
    }


    
}
