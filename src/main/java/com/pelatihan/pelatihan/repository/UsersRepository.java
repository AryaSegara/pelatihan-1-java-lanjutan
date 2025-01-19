package com.pelatihan.pelatihan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pelatihan.pelatihan.model.Users;

public interface UsersRepository extends JpaRepository <Users, Integer>{
    
}
