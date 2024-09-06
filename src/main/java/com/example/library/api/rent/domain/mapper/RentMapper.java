package com.example.library.api.rent.domain.mapper;

import com.example.library.api.rent.application.command.CreateCommand;
import com.example.library.api.rent.domain.model.Rent;
import com.example.library.api.rent.infrastructure.persistence.entity.RentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RentMapper {

    RentMapper INSTANCE = Mappers.getMapper(RentMapper.class);

    // Domain
    Rent toDomain(RentEntity entity);

    // Entity
    RentEntity toEntity(Rent domain);
    RentEntity toEntityFromCommand(CreateCommand command);
}
