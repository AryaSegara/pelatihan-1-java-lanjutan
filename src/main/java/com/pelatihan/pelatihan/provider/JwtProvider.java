package com.pelatihan.pelatihan.provider;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;

import io.jsonwebtoken.*;

public class JwtProvider {
    
    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.access-token-validity}")
    private Long accessTokenValidity;

    public String generateToken(String userId, String username, String role) {

        Claims claims = Jwts.claims().setSubject(userId);
        claims.setId(userId);
        claims.put("username", username);
        claims.put("authorities", List.of(role));

        Date tokenCreateTime = new Date();
        Date tokenValidity = new Date(tokenCreateTime.getTime() + TimeUnit.MINUTES.toMillis(accessTokenValidity));

        return Jwts.builder()
                    .setClaims(claims)
                    .setExpiration(tokenValidity)
                    .signWith(SignatureAlgorithm.HS512, secretKey)
                    .compact();
    }


}
