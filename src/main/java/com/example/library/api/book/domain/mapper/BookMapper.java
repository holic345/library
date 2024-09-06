package com.example.library.api.book.domain.mapper;

import com.example.library.api.book.domain.model.Book;
import com.example.library.api.book.infrastructure.persistence.entity.BookEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    Book toDomain(BookEntity entity);

    BookEntity toEntity(Book domain);
}
