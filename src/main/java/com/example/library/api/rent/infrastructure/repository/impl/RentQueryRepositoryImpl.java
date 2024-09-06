package com.example.library.api.rent.infrastructure.repository.impl;

import com.example.library.api.rent.domain.repository.RentQueryRepository;
import com.example.library.api.rent.infrastructure.repository.RentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RentQueryRepositoryImpl implements RentQueryRepository {

    private final RentJpaRepository _rentJpaRepository;
}
