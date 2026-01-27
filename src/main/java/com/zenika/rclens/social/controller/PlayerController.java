package com.zenika.rclens.social.controller;

import com.zenika.rclens.social.core.PlayerService;
import com.zenika.rclens.social.model.Match;
import com.zenika.rclens.social.model.Player;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600)
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/joueur/{id}")
    public Optional<Player> findById(@PathVariable final Long id) {
        return this.playerService.findById(id);
    }

    @GetMapping("/joueur/all")
    public List<Player> findAll() {
        return this.playerService.findAll();
    }

    @PostMapping("/joueur")
    public Player player(@RequestBody final Player player) {
        return this.playerService.save(player);
    }
}
