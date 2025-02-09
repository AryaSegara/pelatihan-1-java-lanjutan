package com.pelatihan.pelatihan.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pelatihan.pelatihan.model.Users;

public interface UsersRepository extends JpaRepository <Users, Integer>{
    Optional<Users> findByUsername(String username);
}
