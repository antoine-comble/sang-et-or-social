package com.zenika.rclens.social.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private Long id;

    private String username;

    private String email;

    private String token;

    private long expiresIn;
}
