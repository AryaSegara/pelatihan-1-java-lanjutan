package com.pelatihan.pelatihan.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.pelatihan.pelatihan.dto.LoginDto;
import com.pelatihan.pelatihan.dto.UserDetailDto;
import com.pelatihan.pelatihan.model.UserRole;
import com.pelatihan.pelatihan.model.Users;
import com.pelatihan.pelatihan.repository.UserRoleRepository;
import com.pelatihan.pelatihan.repository.UsersRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService{

    private final UsersRepository usersRepository;
    private final UserRoleRepository userRoleRepository;

    @Autowired
    public LoginServiceImpl(UsersRepository usersRepository, UserRoleRepository userRoleRepository) {
        this.usersRepository = usersRepository;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public UserDetailDto login(LoginDto dto) {
        // System.out.println(dto);
        Optional<Users> optionalUsers = usersRepository.findByUsername(dto.getUsername());

        if(optionalUsers.isPresent()){
            Users users = optionalUsers.get();
            if(dto.getPassword().equals(users.getPassword())){
                // Generic jwt token


                List<UserRole> userRoles = userRoleRepository.findByUsers(users);

                return UserDetailDto.builder()
                                    .username(users.getUsername())
                                    .role(userRoles.stream()
                                                    .map(userRole -> userRole.getRole().getRoleName())
                                                    .collect(Collectors.joining(",")))
                                                    
                                    .build();
            }else{
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username atau Password Salah");
            }

        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username atau Password Salah");
        }
    }


    
}
