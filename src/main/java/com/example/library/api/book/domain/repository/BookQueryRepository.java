package com.example.library.api.book.domain.repository;

import com.example.library.api.book.domain.model.Book;
import java.util.Optional;

public interface BookQueryRepository {

    Optional<Book> findByKey(Long key);
}
