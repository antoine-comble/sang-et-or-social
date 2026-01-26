package com.zenika.rclens.social.core;

import com.zenika.rclens.social.model.Match;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MatchService {

    private final MatchRepository matchRepository;

    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public Optional<Match> findByDate(final LocalDate date) {
        return this.matchRepository.findByDate(date);
    }

    public Optional<Match> findByOpponent(final String opponent, final boolean home) {
        return this.matchRepository.findByOpponentAndHome(opponent, home);
    }

    @Transactional
    public Match save(Match match) {
        return this.matchRepository.save(match);
    }

    public Optional<Match> findById(long matchId) {
        return this.matchRepository.findById(matchId);
    }

    public List<Match> findAll() {
        return this.matchRepository.findAll();
    }
}
