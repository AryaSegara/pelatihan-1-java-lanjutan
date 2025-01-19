package com.pelatihan.pelatihan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pelatihan.pelatihan.model.UserRole;

public interface UserRoleRepository  extends JpaRepository <UserRole,Integer>{
    
}
