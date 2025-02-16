package com.pelatihan.pelatihan.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pelatihan.pelatihan.dto.RegisterUserDto;
import com.pelatihan.pelatihan.dto.RoleDto;
import com.pelatihan.pelatihan.model.Role;
import com.pelatihan.pelatihan.model.UserRole;
import com.pelatihan.pelatihan.model.Users;
import com.pelatihan.pelatihan.repository.RoleRepository;
import com.pelatihan.pelatihan.repository.UserRoleRepository;
import com.pelatihan.pelatihan.repository.UsersRepository;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private final UsersRepository usersRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;

    UserServiceImpl(UsersRepository usersRepository,
                    RoleRepository roleRepository,
                    UserRoleRepository userRoleRepository){

        this.usersRepository = usersRepository;
        this.roleRepository = roleRepository;
        this.userRoleRepository = userRoleRepository;
    }


    // register users
    @Transactional
    @Override
    public void register(RegisterUserDto dto){
        Users users = new Users();
        users.setId(dto.getId());
        users.setUsername(dto.getUsername());
        users.setPassword(dto.getPassword());
        users.setStatus(true);
        users.setCreatedDate(LocalDate.now());
        users.setUpdateDate(LocalDate.now());

        users = usersRepository.save(users);

        for(RoleDto roleDto : dto.getRoles()){
            Role role = roleRepository.findById(roleDto.getId()).orElse(null);

            UserRole userRole = new UserRole();
            userRole.setUsers(users);
            userRole.setRole(role);

            userRoleRepository.save(userRole);
        }
    }


}
