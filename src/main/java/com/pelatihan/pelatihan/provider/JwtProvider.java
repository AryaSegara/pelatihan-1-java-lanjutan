package com.pelatihan.pelatihan.provider;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JwtProvider {
    
    @Value("${jwt.secret.key}")
    private String secretKey;

    @Value("${jwt.token.validity}")
    private Long accessTokenValidity;

    public String generateToken(Integer userId, String username, List<String> role) {

        Claims claims = Jwts.claims().setSubject(String.valueOf(userId));
        claims.setId(String.valueOf(userId));
        claims.put("username", username);
        claims.put("authorities", role);

        Date tokenCreateTime = new Date();
        Date tokenValidity = new Date(tokenCreateTime.getTime() + TimeUnit.MINUTES.toMillis(accessTokenValidity));

        return Jwts.builder()
                    .setClaims(claims)
                    .setExpiration(tokenValidity)
                    .signWith(SignatureAlgorithm.HS512, secretKey)
                    .compact();
    }


}
