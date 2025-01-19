package com.pelatihan.pelatihan.controller;

import com.pelatihan.pelatihan.dto.GenericResponse;
import com.pelatihan.pelatihan.dto.RegisterUserDto;
import com.pelatihan.pelatihan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UsersController {

  private final UserService userService;

  @Autowired
  UsersController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/register")
  public ResponseEntity<GenericResponse<Object>> register (
                                                @RequestBody RegisterUserDto dto) {

    userService.register(dto);
    return ResponseEntity.ok().body(GenericResponse.builder()
          .success(true)
          .message("Berhasil menambahkan user baru")
          .data(null)
          .build()
      );
  }


}
