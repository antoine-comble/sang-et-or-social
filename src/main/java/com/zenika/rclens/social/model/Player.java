package com.zenika.rclens.social.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Player {
    @Id
    @GeneratedValue
    private Long id;

    private String firstName;

    private String lastName;

    private String position;

    private int number;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "rating_ig")
    private List<Rating> ratings;

    public Player(Long id, String firstName, String lastName, int number, String position) {
        this.number = number;
        this.position = position;
        this.lastName = lastName;
        this.firstName = firstName;
        this.id = id;
    }

    public Player() {
    }
}
