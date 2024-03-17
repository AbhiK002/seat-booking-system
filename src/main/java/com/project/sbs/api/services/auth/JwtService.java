package com.project.sbs.api.services.auth;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {
    @Value("${jwtservice.secret.key}")
    private String jwtGenKey;

    public String generateToken(Integer userId) {
        return Jwts.builder()
                .header().add("userId", userId)
                .and()
                .expiration(new Date(System.currentTimeMillis() + 20 * 1000)) // 12 hours expiration
                .signWith(SignatureAlgorithm.HS256, jwtGenKey)
                .compact();
    }

    public boolean isTokenValid(String token) {
        Jwt<?,?> jwt;

        try {
            jwt = Jwts.parser()
                    .setSigningKey(jwtGenKey)
                    .build()
                    .parse(token);

            return true;
        }
        catch (JwtException e) {
            // jwt corrupted or expired
            return false;
        }
    }

    public Integer getIdFromToken(String token) {
        Jwt<?,?> jwt;

        try {
            jwt = Jwts.parser()
                    .setSigningKey(jwtGenKey)
                    .build()
                    .parse(token);

            return (Integer) jwt.getHeader().get("userId");
        }
        catch (JwtException e) {
            // jwt corrupted or expired
            return null;
        }
    }
}
