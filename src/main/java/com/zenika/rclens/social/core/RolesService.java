package com.zenika.rclens.social.core;

import com.zenika.rclens.social.model.Role;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolesService {

    private final RolesRepository rolesRepository;

    public RolesService(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    public Optional<Role> findByName(final String name) {
        return this.rolesRepository.findByName(name);
    }

    @Transactional
    public Role save(Role role) {
        return this.rolesRepository.save(role);
    }

    public List<Role> findAll() {
        return this.rolesRepository.findAll();
    }
}
