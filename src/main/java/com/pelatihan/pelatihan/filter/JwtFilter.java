package com.pelatihan.pelatihan.filter;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.pelatihan.pelatihan.dto.UserCredentialsDto;
import com.pelatihan.pelatihan.provider.JwtProvider;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private final JwtProvider jwtProvider;

    public JwtFilter(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }
    
    @Override
    protected void doFilterInternal(HttpServletRequest request , 
                                    HttpServletResponse response,
                                    FilterChain filterChain)  throws IOException, ServletException{
        
        try {
            String accessToken = jwtProvider.resolveToken(request);
            if(accessToken == null){
                filterChain.doFilter(request, response);
                return;
            }


            Claims claims = jwtProvider.resolveClaims(request);
            if(claims != null && jwtProvider.validateClaims(claims)){
                String username = claims.getSubject();
                List<String> roles = (List<String>) claims.get("authorities");


                UserCredentialsDto credentialPayload = new UserCredentialsDto();
                credentialPayload.setUserId(Integer.valueOf(claims.getId()));
                credentialPayload.setUsername(claims.get("username").toString());


                Authentication authentication  = new UsernamePasswordAuthenticationToken(
                    username,
                    credentialPayload, 
                    roles.stream().map(aLong -> new SimpleGrantedAuthority(String.valueOf(aLong)))
                                    .toList());

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        } catch (Exception e) {
            log.info(e.getMessage());
        }

        filterChain.doFilter(request, response);
        
    }
}
