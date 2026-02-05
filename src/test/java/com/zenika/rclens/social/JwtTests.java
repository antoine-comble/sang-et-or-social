package com.zenika.rclens.social;

import com.zenika.rclens.social.model.User;
import com.zenika.rclens.social.tech.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class JwtTests {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    private final JwtService jwtService;

    @Autowired
    public JwtTests(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Test
    public void testJwtServices_parseClaims() {
        final String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMSIsImlhdCI6MTc3MDE5NjM3NiwiZXhwIjoxNzcwMTk5OTc2fQ.rrnaBbLgLllLH0XxVmI-_NNcA4aFxDwlnRyHL-2bnDI";
        Claims body = Jwts.parser().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
        //assertThat(body.getExpiration()).isEqualTo(jwtExpiration);
    }

    @Test
    public void testBuildToken() {
        User user = new User();
        user.setUsername("user1");
        user.setEmail("user1@test.com");
        user.setPassword("password");
        String token = jwtService.generateToken(user);
        assertThat(token).isNotBlank();
    }
}
