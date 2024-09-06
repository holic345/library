package com.example.library.api.book.domain.service;

import com.example.library.api.book.domain.model.Book;
import com.example.library.api.book.domain.repository.BookQueryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookQueryService {

    private final BookQueryRepository _bookQueryRepository;

    public Book findByKey(final Long key) {
        return _bookQueryRepository.findByKey(key)
            .orElseThrow(EntityNotFoundException::new);
    }
}
