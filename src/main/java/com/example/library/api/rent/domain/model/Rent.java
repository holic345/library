package com.example.library.api.rent.domain.model;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class Rent {

    private Long key;

    private Long userKey;

    private Long bookKey;

    private LocalDateTime createdDate;
}
