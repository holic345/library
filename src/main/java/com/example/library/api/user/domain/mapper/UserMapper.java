package com.example.library.api.user.domain.mapper;

import com.example.library.api.user.domain.model.User;
import com.example.library.api.user.infrastructure.persistence.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toDomain(UserEntity entity);

    UserEntity toEntity(User domain);
}
