package com.example.library.api.rent.application.mapper;

import com.example.library.api.rent.application.command.CreateCommand;
import com.example.library.api.rent.presentation.dto.CreateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RentApplicationMapper {

    RentApplicationMapper INSTANCE = Mappers.getMapper(RentApplicationMapper.class);

    CreateCommand toCreateCommand(CreateRequest request);
}
