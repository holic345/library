package com.example.library.api.book.infrastructure.persistence.entity;

import com.example.library.api.book.infrastructure.persistence.entity.enums.BookStatus;
import com.example.library.api.rent.infrastructure.persistence.entity.RentEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "BOOK", indexes = @Index(name = "UQ_001", unique = true, columnList = "productNum"))
@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class BookEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "KEY", columnDefinition = "bigint", nullable = false)
    private Long key;

    @Column(name = "PRODUCT_NUM", columnDefinition = "char", nullable = false)
    private String productNum;

    @Column(name = "TITLE", length = 50, nullable = false)
    private String title;

    @Column(name = "WRITER", columnDefinition = "char", nullable = false)
    private String writer;

    @Column(name = "STATUS", nullable = false)
    private BookStatus status;

    @CreatedDate
    @Column(name = "CREATED_DATE", updatable = false, nullable = false)
    private LocalDateTime createdDate;
}
