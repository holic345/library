package com.example.library.api.user.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "USER")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class UserEntity extends AbstractAggregateRoot<UserEntity> {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "KEY", columnDefinition = "bigint", nullable = false)
    private Long key;

    @Column(name = "ID", columnDefinition = "char", nullable = false)
    private String id;

    @Column(name = "PASSWORD", columnDefinition = "char", nullable = false)
    private String password;

    @Column(name = "NAME", columnDefinition = "char", nullable = false)
    private String name;

    @CreatedDate
    @Column(name = "CREATED_DATE", updatable = false, nullable = false)
    private LocalDateTime createdDate;
}
