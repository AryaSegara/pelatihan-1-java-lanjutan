package com.pelatihan.pelatihan.provider;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.jsonwebtoken.*;

public class JwtProvider {
    
    public String generateToken(String userId, String email, String role) {

        Claims claims = Jwts.claims().setSubject(userId);
        claims.setId(userId);
        claims.put("email", email);
        claims.put("authorities", List.of(role));

        Date tokenCreateTime = new Date();
        Date tokenValidity = new Date(tokenCreateTime.getTime() + TimeUnit.MINUTES.toMillis(accessTokenValidity);

        return Jwts.builder()
                    .setClaims(claims)
                    .setExpiration(tokenValidity)
                    .signWith(SignatureAlgorithm.HS512, secretKey)
                    .compact();
    }
    
}
