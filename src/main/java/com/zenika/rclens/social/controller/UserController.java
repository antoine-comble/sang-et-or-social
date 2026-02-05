package com.zenika.rclens.social.controller;

import com.zenika.rclens.social.core.UserService;
import com.zenika.rclens.social.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600)
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{id}")
    public Optional<User> findById(@PathVariable Long id) {
        return this.userService.findById(id);
    }

    @GetMapping("/user/search")
    public Optional<User> findByUsername(@RequestParam(required = false, name = "username") String username, @RequestParam(required = false) String email) {
        if (StringUtils.hasText(username)) {
            return this.userService.findByUsername(username);
        }
        if (StringUtils.hasText(email)) {
            return this.userService.findByEmail(email);
        }
        return Optional.empty();
    }

    @PostMapping("/user")
    public User save(final @RequestBody  User user) {
        return userService.save(user);
    }

    @GetMapping("/users/all")
    public List<User> findAll() {
        return this.userService.findAll();
    }

    @GetMapping("/users/me")
    public ResponseEntity<User> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        return ResponseEntity.ok(currentUser);
    }
}
