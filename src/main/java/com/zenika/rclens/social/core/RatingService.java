package com.zenika.rclens.social.core;

import com.zenika.rclens.social.model.Match;
import com.zenika.rclens.social.model.Player;
import com.zenika.rclens.social.model.Rating;
import com.zenika.rclens.social.model.User;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;

    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public Optional<Rating> findByUserAndMatchAndPlayer(final User user, final Match match, final Player player) {
        return this.ratingRepository.findByUserAndMatchAndPlayer(user, match, player);
    }

    public List<Rating> findByMatch(final Long matchId) {
        return this.ratingRepository.findByMatch(matchId);
    }

    public List<Rating> findByPlayer(final Long playerId) {
        return this.ratingRepository.findByPlayer(playerId);
    }

    @Transactional
    public Rating save(Rating rating) {
        return this.ratingRepository.save(rating);
    }

}
