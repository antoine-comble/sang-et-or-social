package com.zenika.rclens.social.core;

import com.zenika.rclens.social.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    Optional<Player> findByFirstNameAndLastName(final String firstName, final String lastName);
}
