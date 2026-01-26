package com.zenika.rclens.social.core;

import com.zenika.rclens.social.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolesRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(final String name);
}
