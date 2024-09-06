package com.example.library.global.config.database;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * <h4> Spring Data JPA 설정 클래스 </h4>
 */
@EnableJpaAuditing(auditorAwareRef = "auditorAwareConfig")
@Configuration
@RequiredArgsConstructor
public class JPAConfig {

    private final EntityManager entityManager;

    /**
     * <h5> QueryDsl JPAQueryFactory를 빈으로 등록 </h5>
     */
    @Bean
    public JPAQueryFactory jpaQueryFactory() { return new JPAQueryFactory(entityManager); }
}
