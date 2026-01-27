package com.zenika.rclens.social.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "ratings")
@IdClass(Rating.PrimaryKey.class)
public class Rating {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Id
    @Column(name = "match_id")
    private Long matchId;

    @Id
    @Column(name = "player_id")
    private Long playerId;

    @Column(name = "rating")
    @ColumnDefault("0")
    private int rating;

    static class PrimaryKey implements Serializable {
        private Long userId;
        private Long matchId;
        private Long playerId;
    }
}
