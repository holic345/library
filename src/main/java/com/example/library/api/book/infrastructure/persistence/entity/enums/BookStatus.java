package com.example.library.api.book.infrastructure.persistence.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BookStatus {

    STABLE  ("B_ST_1", "정상"),
    DELETE  ("B_ST_2", "삭제");

    private final String code;
    private final String desc;
}
