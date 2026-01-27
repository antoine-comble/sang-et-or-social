package com.zenika.rclens.social;

import com.zenika.rclens.social.core.*;
import com.zenika.rclens.social.model.*;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.text.PlainDocument;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MatchTests {

    private final MatchService matchService;

    @Autowired
    private RatingService ratingService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private UserService userService;
    @Autowired
    private RolesService rolesService;

    @Autowired
    public MatchTests(MatchService matchService) {
        this.matchService = matchService;
    }

    @BeforeEach
    public void setUp() {
        // Roles
        Role roleUser = new Role();
        roleUser.setName("user");
        roleUser = rolesService.save(roleUser);
        Role roleAdmin = new Role();
        roleAdmin.setName("admin");
        roleAdmin = rolesService.save(roleAdmin);
        // User
        User user = new User();
        user.setCreationDate(LocalDate.now());
        user.setUsername("toto-le-lensois");
        user.setEmail("toto.lelensois@gmail.com");
        user.setEnabled(true);
        user.setRoles(List.of(roleUser, roleAdmin));
        user = userService.save(user);

        Player player1 = new Player(null, "Eric", "Cantona", 7, "Attaquant");
        player1 = playerService.save(player1);
        Player player2 = new Player(null, "Yoann", "Lachor", 3, "Défenseur");
        player2 = playerService.save(player2);
        Player player3 = new Player(null, "Eric", "Sikora", 2, "Défenseur");
        player3 = playerService.save(player3);
        // Note player1
        Rating rating = new Rating();
        rating.setUserId(user.getId());
        rating.setPlayerId(player1.getId());
        rating.setRating(8);
        // Note player3
        Rating rating2 = new Rating();
        rating2.setUserId(user.getId());
        rating2.setPlayerId(player3.getId());
        rating2.setRating(7);
        // Note player2
        Rating rating1 = new Rating();
        rating1.setUserId(user.getId());
        rating1.setPlayerId(player2.getId());
        rating1.setRating(6);
        // Match
        Match match = new Match();
        match.setDate(LocalDate.now());
        match.setHome(true);
        match.setCompetition("Ligue 1");
        match.setOpponent("Lille");
        match.setScoreLens(3);
        match.setScoreOpponent(0);
        matchService.save(match);

        // save ratings
        rating.setMatchId(match.getId());
        rating = ratingService.save(rating);
        rating1.setMatchId(match.getId());
        rating1 = ratingService.save(rating1);
        rating2.setMatchId(match.getId());
        rating2 = ratingService.save(rating2);

        match.setRatings(List.of(rating, rating1, rating2));
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

    @Test
    @Transactional
    public void testFindMatchById() {
        Optional<Match> match = this.matchService.findByDate(LocalDate.now());
        assertThat(match).isNotEmpty();
        assertThat(match.orElseGet(null)).isNotNull();
        assertThat(match.orElseGet(null).getRatings().size()).isEqualTo(3);
    }

}
