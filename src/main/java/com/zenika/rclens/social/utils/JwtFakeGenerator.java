package com.zenika.rclens.social.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.List;

public class JwtFakeGenerator {

    static void main(String[] args) {
        // 1. Secret key (must be ≥ 32 bytes for HS256)
        String secret = "my-very-long-super-secure-secret-key-123456";
        Key key = Keys.hmacShaKeyFor(secret.getBytes());

        // 2. Build token manually
        String token = Jwts.builder()
                .setSubject("admin")                 // fake username
                .claim("roles", List.of("ROLE_ADMIN")) // fake roles
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600_000L * 24 * 365)) // 1 year
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        System.out.println("Fake JWT Token: " + token);
    }
}
