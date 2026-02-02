package com.zenika.rclens.social.tech;

import com.zenika.rclens.social.core.*;
import com.zenika.rclens.social.model.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
@Profile("dev")
public class DataInitializer {

    private final UserService userService;

    private final RatingService ratingService;

    private final MatchService matchService;

    private final RolesService rolesService;

    private final PlayerService playerService;

    public DataInitializer(UserService userService, RatingService ratingService, MatchService matchService, RolesService rolesService, PlayerService playerService, PlayerService playerService1) {
        this.userService = userService;
        this.ratingService = ratingService;
        this.matchService = matchService;
        this.rolesService = rolesService;
        this.playerService = playerService;
    }

    @PostConstruct
    public void initializeSampleData() {
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
        user.setPassword("password");
        user.setEnabled(true);
        user.setRoles(List.of(roleUser, roleAdmin));
        user = userService.save(user);

        // Players
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
        match.setDate(LocalDate.of(2025, Month.SEPTEMBER, 20));
        match.setHome(true);
        match.setCompetition("Ligue 1");
        match.setOpponent("Lille");
        match.setScoreLens(3);
        match.setScoreOpponent(0);
        matchService.save(match);

        // Save ratings
        rating.setMatchId(match.getId());
        rating = ratingService.save(rating);
        rating1.setMatchId(match.getId());
        rating1 = ratingService.save(rating1);
        rating2.setMatchId(match.getId());
        rating2 = ratingService.save(rating2);

        match.setRatings(List.of(rating, rating1, rating2));
    }
}
