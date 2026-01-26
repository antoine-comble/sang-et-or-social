package com.zenika.rclens.social.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
        name = "PLAYERS_RATINGS",
        uniqueConstraints =
        @UniqueConstraint(columnNames = {"id", "match_id", "player_id"})
)
@Getter
@Setter
public class Rating {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "match_id")
    private Match match;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "player_id")
    private Player player;

    private int rating;

}
