package com.zenika.rclens.social.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

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

    private int scoreLens;

    private int scoreOpponent;

    private String competition;
}
