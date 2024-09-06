package com.example.library.api.rent.infrastructure.repository.impl;

import com.example.library.api.rent.domain.mapper.RentMapper;
import com.example.library.api.rent.domain.model.Rent;
import com.example.library.api.rent.domain.repository.RentRepository;
import com.example.library.api.rent.infrastructure.persistence.entity.RentEntity;
import com.example.library.api.rent.infrastructure.repository.RentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RentRepositoryImpl implements RentRepository {

    private final RentJpaRepository _rentJpaRepository;

    @Override
    public Rent createRent(RentEntity entity) {
        return RentMapper.INSTANCE.toDomain(_rentJpaRepository.saveAndFlush(entity));
    }
}
