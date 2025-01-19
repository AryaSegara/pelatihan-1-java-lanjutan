package com.pelatihan.pelatihan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pelatihan.pelatihan.model.Role;

public interface RoleRepository extends JpaRepository <Role, Integer>{
    
}
