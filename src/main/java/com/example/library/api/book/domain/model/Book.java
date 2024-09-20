package com.example.library.api.book.domain.model;

import com.example.library.api.book.infrastructure.persistence.entity.enums.BookStatus;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class Book {

    private Long key;

    private String title;

    private String writer;

    private BookStatus status;

    private LocalDateTime createdDate;

    public boolean isDelete() {
        return this.status == BookStatus.DELETE;
    }
}
