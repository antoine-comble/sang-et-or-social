package com.zenika.rclens.social;


import com.zenika.rclens.social.core.PlayerService;
import com.zenika.rclens.social.model.Player;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PlayerTests {
    private final PlayerService playerService;

    @Autowired
    public PlayerTests(PlayerService playerService) {
        this.playerService = playerService;
    }

    @BeforeEach
    public void setUp() {
        final Player player = new Player(null, "Eric", "Cantona", 7, "Attaquant");
        playerService.save(player);
    }

    @Test
    @Transactional
    public void testFindByFirstNameAndLastName_existing() {
        final Optional<Player> player = playerService.findByFirstNameAndLastName("Eric", "Cantona");
        assertThat(player.orElseGet(null)).isNotNull();
        assertThat(player.orElseGet(null).getId()).isNotNull();
        assertThat(player.orElseGet(null).getFirstName()).isEqualTo("Eric");
        assertThat(player.orElseGet(null).getLastName()).isEqualTo("Cantona");
        assertThat(player.orElseGet(null).getPosition()).isEqualTo("Attaquant");
        assertThat(player.orElseGet(null).getNumber()).isEqualTo(7);
    }

    @Test
    @Transactional
    public void testFindByFirstNameAndLastName_not_existing() {
        final Optional<Player> player = playerService.findByFirstNameAndLastName("David", "Ginola");
        assertThat(player).isEmpty();
    }

}
