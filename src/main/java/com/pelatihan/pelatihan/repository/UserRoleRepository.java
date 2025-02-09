package com.pelatihan.pelatihan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pelatihan.pelatihan.model.UserRole;
import com.pelatihan.pelatihan.model.Users;

public interface UserRoleRepository  extends JpaRepository <UserRole,Integer>{
    List<UserRole> findByUsers(Users users);
    
}
