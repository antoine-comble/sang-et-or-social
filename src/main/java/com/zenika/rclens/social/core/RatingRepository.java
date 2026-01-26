package com.zenika.rclens.social.core;

import com.zenika.rclens.social.model.Match;
import com.zenika.rclens.social.model.Player;
import com.zenika.rclens.social.model.Rating;
import com.zenika.rclens.social.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    Optional<Rating> findByUserAndMatchAndPlayer(final User user, final Match match, final Player player);

    List<Rating> findByMatch(final Long matchId);

    List<Rating> findByPlayer(final Long playerId);
}
