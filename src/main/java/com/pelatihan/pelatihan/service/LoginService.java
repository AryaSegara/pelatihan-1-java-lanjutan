package com.pelatihan.pelatihan.service;

import com.pelatihan.pelatihan.dto.LoginDto;
import com.pelatihan.pelatihan.dto.UserDetailDto;

public interface LoginService {
    UserDetailDto login(LoginDto dto);
}
