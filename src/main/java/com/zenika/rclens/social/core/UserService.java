package com.zenika.rclens.social.core;

import com.zenika.rclens.social.model.User;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findByUsername(final String username) {
        return this.userRepository.findByUsername(username);
    }

    public Optional<User> findByEmail(final String username) {
        return this.userRepository.findByEmail(username);
    }

    @Transactional
    public User save(User user) {
        user.setCreationDate(LocalDate.now());
        return this.userRepository.save(user);
    }

    public Optional<User> findById(final long userId) {
        return this.userRepository.findById(userId);
    }
}
