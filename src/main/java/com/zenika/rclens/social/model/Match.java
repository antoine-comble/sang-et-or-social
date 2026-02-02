package com.zenika.rclens.social.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class Match {
    @Id
    @GeneratedValue
    private Long id;

    private String opponent;

    private LocalDate date;

    private boolean home;

    @Column(nullable = true)
    private Integer scoreLens;

    @Column(nullable = true)
    private Integer scoreOpponent;

    private String competition;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "match_id")
    private List<Rating> ratings;
}
