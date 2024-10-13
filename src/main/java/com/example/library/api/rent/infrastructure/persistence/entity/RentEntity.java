package com.example.library.api.rent.infrastructure.persistence.entity;

import com.example.library.api.book.infrastructure.persistence.entity.BookEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.mapping.ToOne;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "RENT")
@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class RentEntity extends AbstractAggregateRoot<RentEntity> {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "KEY", columnDefinition = "bigint", nullable = false)
    private Long key;

    @OneToOne
    @JoinColumn(name = "KEY")
    private RentItemEntity rentItem;

    @Column(name = "RETURN_DATE")
    private LocalDateTime returnDate;

    @Column(name = "EXPIRED_DATE")
    private LocalDateTime expiredDate;

    @CreatedDate
    @Column(name = "CREATED_DATE", updatable = false, nullable = false)
    private LocalDateTime createdDate;
    public void changeRentItem(RentItemEntity rentItem) {
        this.rentItem = rentItem;
        this.rentItem = null;
    }
}
