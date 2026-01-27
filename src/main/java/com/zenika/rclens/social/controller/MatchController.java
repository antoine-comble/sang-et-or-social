package com.zenika.rclens.social.controller;

import com.zenika.rclens.social.core.MatchDateComparator;
import com.zenika.rclens.social.core.MatchService;
import com.zenika.rclens.social.model.Match;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600)
public class MatchController {

    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping("/match/date/{date}")
    public Optional<Match> findByDate(@PathVariable final LocalDate date) {
        return this.matchService.findByDate(date);
    }

    @GetMapping("/match/{id}")
    public Optional<Match> findById(@PathVariable final Long id) {
        return this.matchService.findById(id);
    }

    @GetMapping("/match/opponent/{opponent}/{home}")
    public Optional<Match> findByOpponent(@PathVariable final String opponent, @PathVariable final boolean home) {
        return this.matchService.findByOpponent(opponent, home);
    }

    @GetMapping("/match/all")
    public List<Match> findAll(@RequestParam(required = false) final String order) {
        final List<Match> all = this.matchService.findAll();
        if (StringUtils.hasText(order) && order.equals("DESC")) {
            return all.stream().sorted(Comparator.comparing(Match::getDate)).toList().reversed();
        } else {
            return all.stream().sorted(Comparator.comparing(Match::getDate)).toList();
        }
    }

    @PostMapping("/match")
    public Match save(@RequestBody final Match match) {
        return this.matchService.save(match);
    }
}
