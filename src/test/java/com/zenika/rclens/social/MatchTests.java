package com.zenika.rclens.social;

import com.zenika.rclens.social.core.MatchService;
import com.zenika.rclens.social.model.Match;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MatchTests {

    private final MatchService matchService;

    @Autowired
    public MatchTests(MatchService matchService) {
        this.matchService = matchService;
    }

    @BeforeEach
    public void setUp() {
        Match match = new Match();
        match.setDate(LocalDate.now());
        match.setHome(true);
        match.setCompetition("Ligue 1");
        match.setOpponent("Lille");
        match.setScoreLens(3);
        match.setScoreOpponent(0);
        matchService.save(match);
    }

    @Test
    @Transactional
    public void testFindMatchByDate_existing() {
        final Optional<Match> match = this.matchService.findByDate(LocalDate.now());
        assertThat(match).isNotEmpty();
        assertThat(match.orElseGet(null)).isNotNull();
        assertThat(match.orElseGet(null).getOpponent()).isEqualTo("Lille");
        assertThat(match.orElseGet(null).getScoreOpponent()).isEqualTo(0);
        assertThat(match.orElseGet(null).getScoreLens()).isEqualTo(3);
    }

    @Test
    @Transactional
    public void testFindMatchByOpponent_homeTrue() {
        final Optional<Match> match = this.matchService.findByOpponent("Lille", true);
        assertThat(match).isNotEmpty();
        assertThat(match.orElseGet(null)).isNotNull();
        assertThat(match.orElseGet(null).getOpponent()).isEqualTo("Lille");
        assertThat(match.orElseGet(null).getScoreOpponent()).isEqualTo(0);
        assertThat(match.orElseGet(null).getScoreLens()).isEqualTo(3);
    }

    @Test
    @Transactional
    public void testFindMatchByOpponent_homeFalse_not_existing() {
        final Optional<Match> match = this.matchService.findByOpponent("Lille", false);
        assertThat(match).isEmpty();
    }

    @Test
    @Transactional
    public void testFindMatchByOpponent_opponent_not_existing() {
        final Optional<Match> match = this.matchService.findByOpponent("Lyon", false);
        assertThat(match).isEmpty();
    }

}
