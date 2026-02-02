package com.zenika.rclens.social.tech;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.List;

@Component
public class JwtUtils {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private int jwtExpirationMs;

    private Key key;

    /**
     * Initializes the key after the class is instantiated and the jwtSecret is injected,
     * preventing the repeated creation of the key and enhancing performance
     */
    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Generate JWT token
     *
     * @param username
     * @return
     */
    public String generateToken(String username) {
        return Jwts.builder().setSubject(username).setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs)).signWith(key, SignatureAlgorithm.HS256).compact();
    }

    /**
     * Get Authentication from JWT token
     *
     * @param token
     * @return
     */
    public Authentication getAuthenticationFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(key).build().parseClaimsJws(token).getBody();

        String username = claims.getSubject();

        List<String> roles = claims.get("roles", List.class);
        List authorities = roles.stream().map(SimpleGrantedAuthority::new).toList();

        return new UsernamePasswordAuthenticationToken(username, null, authorities);
    }

    public UsernamePasswordAuthenticationToken getUsernamePasswordAuthenticationTokenFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(key).build().parseClaimsJws(token).getBody();

        String username = claims.getSubject();

        List<String> roles = claims.get("roles", List.class);
        List authorities = roles.stream().map(SimpleGrantedAuthority::new).toList();

        return new UsernamePasswordAuthenticationToken(username, null, authorities);
    }


    /**
     * Get username from JWT token
     *
     * @param token
     * @return
     */
    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()   // ✅ parser, not builder
                .setSigningKey(key)           // must match the key used to sign
                .build().parseClaimsJws(token)        // returns Jws<Claims>
                .getBody();                   // extract claims

        return claims.getSubject();
    }

    /**
     * Validate JWT token
     *
     * @param token
     * @return
     */
    public boolean validateJwtToken(String token) {
        try {
            Jwts.parser().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (SecurityException e) {
            System.out.println("Invalid JWT signature: " + e.getMessage());
        } catch (MalformedJwtException e) {
            System.out.println("Invalid JWT token: " + e.getMessage());
        } catch (ExpiredJwtException e) {
            System.out.println("JWT token is expired: " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.out.println("JWT token is unsupported: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("JWT claims string is empty: " + e.getMessage());
        }
        return false;
    }
}

