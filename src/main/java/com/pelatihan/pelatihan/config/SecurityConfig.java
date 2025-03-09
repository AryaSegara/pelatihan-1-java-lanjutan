package com.pelatihan.pelatihan.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.pelatihan.pelatihan.filter.JwtFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private final JwtFilter jwtFilter;
    
    public SecurityConfig(JwtFilter jwtFilter){
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> 
                            auth.requestMatchers("/login",
                                            "/email/send",
                                            // "/users/register",
                                            "/api-docs/**",
                                            "/swagger-ui/**",
                                            "/swagger-ui.html")
                            .permitAll()
                            // .requestMatchers("/admin/**").hasAuthority("Admin")
                            .anyRequest()
                            .authenticated())

                .addFilterAfter(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}
