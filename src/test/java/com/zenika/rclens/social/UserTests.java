package com.zenika.rclens.social;


import com.zenika.rclens.social.core.RolesService;
import com.zenika.rclens.social.core.UserService;
import com.zenika.rclens.social.model.Role;
import com.zenika.rclens.social.model.User;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserTests {
    private final UserService userService;

    private final RolesService rolesService;

    @Autowired
    public UserTests(UserService userService, RolesService rolesService) {
        this.userService = userService;
        this.rolesService = rolesService;
    }

    @BeforeEach
    public void setUp() {
        // Roles
        Role roleUser = new Role();
        roleUser.setName("user");
        Role roleAdmin = new Role();
        roleAdmin.setName("admin");
        Role roleModerateur = new Role();
        roleModerateur.setName("moderateur");
        rolesService.save(roleUser);
        rolesService.save(roleAdmin);
        rolesService.save(roleModerateur);

        //
        User user = new User();
        user.setCreationDate(LocalDate.now());
        user.setUsername("Toto le Lensois");
        user.setEmail("toto.lelensois@gmail.com");
        user.setEnabled(true);
        user.setRoles(List.of(roleUser, roleAdmin));
        userService.save(user);
    }

    @Test
    @Transactional
    public void testFindByUserName_existing_username() {
        final Optional<User> user1 = userService.findByUsername("Toto le Lensois");
        assertThat(user1.orElseGet(null)).isNotNull();
        assertThat(user1.orElseGet(null).getId()).isNotNull();
        assertThat(user1.orElseGet(null).getUsername()).isEqualTo("Toto le Lensois");
        assertThat(user1.orElseGet(null).getEmail()).isEqualTo("toto.lelensois@gmail.com");
        assertThat(user1.orElseGet(null).isEnabled()).isTrue();
        assertThat(user1.orElseGet(null).getRoles()).isNotNull();
        assertThat(user1.orElseGet(null).getRoles().size()).isEqualTo(2);
    }

    @Test
    @Transactional
    public void testFindByUserName_not_existing_username() {
        final Optional<User> user1 = userService.findByUsername("username");
        assertThat(user1).isEmpty();
    }

}
