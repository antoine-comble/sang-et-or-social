package com.zenika.rclens.social.core;

import com.zenika.rclens.social.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(final String username);

    Optional<User> findByEmail(final String email);

    Optional<User> findByUsernameAndPassword(final String username, final String password);
}
