package com.zenika.rclens.social.core;

import com.zenika.rclens.social.model.Player;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Optional<Player> findByFirstNameAndLastName(final String firstName, final String lastName) {
        return this.playerRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    @Transactional
    public Player save(Player player) {
        return this.playerRepository.save(player);
    }

    public Optional<Player> findById(final Long playerId) {
        return this.playerRepository.findById(playerId);
    }

    public List<Player> findAll() {
        return this.playerRepository.findAll();
    }
}
