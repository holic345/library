package com.example.library.api.rent.infrastructure.repository;

import com.example.library.api.rent.infrastructure.persistence.entity.RentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentJpaRepository extends JpaRepository<RentEntity, Long> {

}
