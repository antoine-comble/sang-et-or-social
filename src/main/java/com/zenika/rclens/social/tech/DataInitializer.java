package com.zenika.rclens.social.tech;

import com.zenika.rclens.social.core.*;
import com.zenika.rclens.social.model.*;
import jakarta.annotation.PostConstruct;
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

    public DataInitializer(UserService userService, RatingService ratingService) {
        this.userService = userService;
        this.ratingService = ratingService;
    }

    @PostConstruct
    public void initializeSampleData() {
        // Roles
        final Role roleUser = new Role();
        roleUser.setName("user");
        final Role roleAdmin = new Role();
        roleAdmin.setName("admin");

        // Match
        final Match match = new Match();
        match.setDate(LocalDate.of(2025, Month.SEPTEMBER, 20));
        match.setHome(true);
        match.setCompetition("Ligue 1");
        match.setOpponent("Lille");
        match.setScoreLens(3);
        match.setScoreOpponent(0);

        // Player
        final Player player = new Player();
        player.setFirstName("Eric");
        player.setLastName("Cantona");
        player.setNumber(7);
        player.setPosition("Attaquant");

        // User
        final User user = new User();
        user.setCreationDate(LocalDate.now());
        user.setUsername("toto-le-lensois");
        user.setEmail("toto.lelensois@gmail.com");
        user.setEnabled(true);
        user.setRoles(List.of(roleUser, roleAdmin));

        // User 2
        final User user2 = new User();
        user2.setCreationDate(LocalDate.now());
        user2.setUsername("sang");
        user2.setEmail("lens@gmail.com");
        user2.setEnabled(true);
        //user2.setRoles(List.of(roleUser));
        this.userService.save(user2);

        // Rating
        final Rating rating = new Rating();
        rating.setUser(user);
        rating.setMatch(match);
        rating.setPlayer(player);
        rating.setRating(6);
        this.ratingService.save(rating);
    }
}
