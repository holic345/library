package com.example.library.api.rent.domain.repository;

import com.example.library.api.rent.domain.model.Rent;
import com.example.library.api.rent.infrastructure.persistence.entity.RentEntity;

public interface RentRepository {

    Rent createRent(RentEntity entity);
}
