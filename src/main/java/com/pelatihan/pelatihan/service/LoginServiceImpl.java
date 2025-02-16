package com.pelatihan.pelatihan.service;

import java.util.List;
import java.util.Optional;
// import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.pelatihan.pelatihan.dto.LoginDto;
import com.pelatihan.pelatihan.dto.UserDetailDto;
import com.pelatihan.pelatihan.model.UserRole;
import com.pelatihan.pelatihan.model.Users;
import com.pelatihan.pelatihan.provider.JwtProvider;
import com.pelatihan.pelatihan.repository.UserRoleRepository;
import com.pelatihan.pelatihan.repository.UsersRepository;
import com.pelatihan.pelatihan.util.PasswordUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService{

    @Autowired
    private final UsersRepository usersRepository;
    @Autowired
    private final UserRoleRepository userRoleRepository;
    @Autowired
    private final JwtProvider jwtProvider;

    public LoginServiceImpl(UsersRepository usersRepository, UserRoleRepository userRoleRepository, JwtProvider jwtProvider) {
        this.usersRepository = usersRepository;
        this.userRoleRepository = userRoleRepository;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public UserDetailDto login(LoginDto dto) {
        // System.out.println(dto);
        Optional<Users> optionalUsers = usersRepository.findByUsername(dto.getUsername());

        if(optionalUsers.isPresent()){
            Users users = optionalUsers.get();

            //  ini untuk mengecek hashing password nya atau munculin hashing passwordnya di terminalnya
            log.info(PasswordUtil.hash(dto.getPassword()));

            if(PasswordUtil.check(dto.getPassword(), users.getPassword())){

                // Generic jwt token
                List<UserRole> userRoles = userRoleRepository.findByUsers(users);
                List<String> roles = userRoles.stream()
                                                .map(userRole -> userRole.getRole().getRoleName())
                                                .toList();

                String accessToken = jwtProvider.generateToken(users.getId(), users.getUsername(),roles);


                return UserDetailDto.builder()
                                    .username(users.getUsername())
                                    .role(String.join("," ,roles))
                                    .accessToken(accessToken)      
                                    .build();
            }else{
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username atau Password Salah");
            }

        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username atau Password Salah");
        }
    }


    
}
