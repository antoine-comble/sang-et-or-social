package com.zenika.rclens.social.controller;

import com.zenika.rclens.social.model.UserDTO;
import com.zenika.rclens.social.model.User;
import com.zenika.rclens.social.security.AuthenticationService;
import com.zenika.rclens.social.tech.AuthConstants;
import com.zenika.rclens.social.tech.JwtService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    private final SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> authenticate(@RequestBody User user) {
        final User authenticatedUser = authenticationService.authenticate(user);

        final String jwtToken = jwtService.generateToken(authenticatedUser);

        final UserDTO userDTO = new UserDTO();
        userDTO.setToken(jwtToken);
        userDTO.setExpiresIn(jwtService.getExpirationTime());
        userDTO.setEmail(authenticatedUser.getEmail());
        userDTO.setUsername(authenticatedUser.getUsername());
        userDTO.setId(authenticatedUser.getId());

        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/logout")
    public void logout(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
        final Cookie cookie = new Cookie(AuthConstants.TOKEN_COOKIE_NAME, "");
        cookie.setMaxAge(0);
        this.logoutHandler.logout(request, response, authentication);
    }
}
