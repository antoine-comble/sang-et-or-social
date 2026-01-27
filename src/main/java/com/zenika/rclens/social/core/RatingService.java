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

    public Optional<Rating> findByUserIdAndMatchIdAndPlayerId(final Long userId, final Long matchId, final Long playerId) {
        return this.ratingRepository.findByUserIdAndMatchIdAndPlayerId(userId, matchId, playerId);
    }

    public List<Rating> findByMatchId(final Long matchId) {
        return this.ratingRepository.findByMatchId(matchId);
    }

    public List<Rating> findByPlayerId(final Long playerId) {
        return this.ratingRepository.findByPlayerId(playerId);
    }

    @Transactional
    public Rating save(Rating rating) {
        return this.ratingRepository.save(rating);
    }

}
