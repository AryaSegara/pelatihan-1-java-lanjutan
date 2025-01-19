package com.pelatihan.pelatihan.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pelatihan.pelatihan.dto.GenericResponse;
import com.pelatihan.pelatihan.dto.RegisterUserDto;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/users")
public class UsersController {
    
    @PostMapping("/register")
    public ResponseEntity<GenericResponse<Object>> register(
                                                            @RequestBody RegisterUserDto dto) {
        return null;
    }

}
