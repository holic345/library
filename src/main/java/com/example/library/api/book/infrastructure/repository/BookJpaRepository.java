package com.example.library.api.book.infrastructure.repository;

import com.example.library.api.book.infrastructure.persistence.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookJpaRepository extends JpaRepository<BookEntity, Long> {

}
