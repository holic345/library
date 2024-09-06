package com.example.library.api.user.domain.model;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class User {

    private Long key;

    private String id;

    private String name;

    private String password;

    private LocalDateTime createdDate;
}
