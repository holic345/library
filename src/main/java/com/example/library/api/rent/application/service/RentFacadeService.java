package com.example.library.api.rent.application.service;

import com.example.library.api.book.domain.model.Book;
import com.example.library.api.book.domain.service.BookQueryService;
import com.example.library.api.book.domain.service.BookService;
import com.example.library.api.book.infrastructure.persistence.entity.enums.BookStatus;
import com.example.library.api.rent.application.command.CreateCommand;
import com.example.library.api.rent.application.usecase.RentUseCase;
import com.example.library.api.rent.domain.model.Rent;
import com.example.library.api.rent.domain.service.RentQueryService;
import com.example.library.api.rent.domain.service.RentService;
import com.example.library.api.rent.presentation.dto.CreateResponse;
import com.example.library.api.user.domain.model.User;
import com.example.library.api.user.domain.service.UserQueryService;
import com.example.library.api.user.domain.service.UserService;
import com.example.library.global.exception.list.CustomBadRequestException;
import com.example.library.global.exception.status.StatusCodeBadRequestVO;
import jakarta.transaction.Transactional;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RentFacadeService implements RentUseCase {

    private final RentService      _rentService;
    private final RentQueryService _rentQueryService;

    private final UserService      _userService;
    private final UserQueryService _userQueryService;

    private final BookService      _bookService;
    private final BookQueryService _bookQueryService;

    @Override
    @Transactional
    public CreateResponse createRent(final CreateCommand createCommand) {

        // 유저 검증
        User user = _userQueryService.findByKey(createCommand.userKey());

        // 도서 검증
        Book book = _bookQueryService.findByKey(createCommand.bookKey());

        if (book.isDelete()) {
            throw new IllegalArgumentException("삭제된 도서 입니다.");
        }

        // 대여 정보 생성
        Rent rent = _rentService.create(createCommand);

        return new CreateResponse(rent.getKey());
    }
}
