package com.zenika.rclens.social;

import com.zenika.rclens.social.core.MatchService;
import com.zenika.rclens.social.core.PlayerService;
import com.zenika.rclens.social.core.RatingService;
import com.zenika.rclens.social.core.UserService;
import com.zenika.rclens.social.model.Match;
import com.zenika.rclens.social.model.Player;
import com.zenika.rclens.social.model.Rating;
import com.zenika.rclens.social.model.User;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class RatingTests {

    private final RatingService ratingService;

    private final MatchService matchService;

    private final UserService userService;

    private final PlayerService playerService;

    private Match match;

    private User user;

    private Player player;

    @Autowired
    public RatingTests(RatingService ratingService, MatchService matchService, PlayerService playerService, UserService userService) {
        this.ratingService = ratingService;
        this.matchService = matchService;
        this.playerService = playerService;
        this.userService = userService;
    }

    @BeforeEach
    public void setUp() {
        // Match
        match = new Match();
        match.setDate(LocalDate.now());
        match.setHome(true);
        match.setCompetition("Ligue 1");
        match.setOpponent("Lille");
        match.setScoreLens(3);
        match.setScoreOpponent(0);
        match = this.matchService.save(match);

        // Player
        player = new Player();
        player.setFirstName("Eric");
        player.setLastName("Cantona");
        player.setNumber(7);
        player.setPosition("Attaquant");
        player = this.playerService.save(player);

        // User
        user = new User();
        user.setCreationDate(LocalDate.now());
        user.setUsername("Toto le Lensois");
        user.setEmail("toto.lelensois@gmail.com");
        user.setEnabled(true);
        user = this.userService.save(user);

        // Rating
        Rating rating = new Rating();
        rating.setUser(user);
        rating.setMatch(match);
        rating.setPlayer(player);
        rating.setRating(6);
        this.ratingService.save(rating);
    }

    @Test
    @Transactional
    public void testFindRatingByUserAndMatchAndPlayer_existing() {
        Optional<User> expectedUser = this.userService.findById(user.getId());
        Optional<Match> expectedMatch = this.matchService.findById(match.getId());
        Optional<Player> expectedPlayer = this.playerService.findById(player.getId());

        assertThat(expectedUser).isNotEmpty();
        assertThat(expectedMatch).isNotEmpty();
        assertThat(expectedPlayer).isNotEmpty();

        final Optional<Rating> rating = this.ratingService.findByUserAndMatchAndPlayer(expectedUser.orElseGet(null), expectedMatch.orElseGet(null), expectedPlayer.orElseGet(null));
        assertThat(rating).isNotEmpty();
        assertThat(rating.orElseGet(null).getRating()).isEqualTo(6);
        assertThat(rating.orElseGet(null).getPlayer().getFirstName()).isEqualTo("Eric");
        assertThat(rating.orElseGet(null).getPlayer().getLastName()).isEqualTo("Cantona");
        assertThat(rating.orElseGet(null).getPlayer().getNumber()).isEqualTo(7);
    }


}
