package com.example.library.api.book.infrastructure.repository.impl;

import com.example.library.api.book.domain.mapper.BookMapper;
import com.example.library.api.book.domain.model.Book;
import com.example.library.api.book.domain.repository.BookQueryRepository;
import com.example.library.api.book.infrastructure.repository.BookJpaRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BookQueryRepositoryImpl implements BookQueryRepository {

    private final BookJpaRepository _bookJpaRepository;

    @Override
    public Optional<Book> findByKey(Long key) {
        return _bookJpaRepository.findById(key).map(BookMapper.INSTANCE::toDomain);
    }
}
