package com.zenika.rclens.social.core;

import com.zenika.rclens.social.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

    Optional<Match> findByDate(final LocalDate date);

    Optional<Match> findByOpponentAndHome(final String opponent, final boolean home);

}
