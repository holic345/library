package com.example.library.api.rent.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "RENT_ITEM")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class RentItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "KEY", columnDefinition = "bigint", nullable = false)
    private Long key;

    @Column(name = "USER_KEY", columnDefinition = "bigint", nullable = false)
    private Long userKey;

    @Column(name = "BOOK_KEY", columnDefinition = "bigint", nullable = false)
    private Long bookKey;

    @Column(name = "RENT_PRICE", columnDefinition = "int")
    private Integer rentPrice;

    @CreatedDate
    @Column(name = "CREATED_DATE", updatable = false, nullable = false)
    private LocalDateTime createdDate;

    @OneToOne(mappedBy = "rentItem", cascade = CascadeType.ALL, orphanRemoval = true)
    private RentEntity rent;
}
