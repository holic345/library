package com.example.library.api.book.infrastructure.persistence.entity.converter;

import com.example.library.api.book.infrastructure.persistence.entity.enums.BookStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.EnumSet;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * <h4> 도서 상태 enum 변환 클래스 (Entity -> DB, DB -> Entity) </h4>
 */
@Converter(autoApply = true)
public class BookStatusConverter implements AttributeConverter<BookStatus, String> {

    // Entity -> DB
    @Override
    public String convertToDatabaseColumn(BookStatus bookStatus) {
        return bookStatus.getCode();
    }

    // DB -> Entity
    @Override
    public BookStatus convertToEntityAttribute(String dbData) {
        return EnumSet.allOf(BookStatus.class).stream()
            .filter(e -> Objects.equals(e.getCode(), dbData))
            .findAny()
            .orElseThrow(NoSuchElementException::new);
    }
}
