package com.example.library.api.rent.presentation.controller;

import com.example.library.api.rent.application.usecase.RentUseCase;
import com.example.library.api.rent.application.mapper.RentApplicationMapper;
import com.example.library.api.rent.presentation.dto.CreateRequest;
import com.example.library.api.rent.presentation.dto.CreateResponse;
import com.example.library.global.domain.LibraryResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("rent")
public class RentController {

    private final RentUseCase _rentUseCase;

    @PostMapping("create")
    public LibraryResponse<CreateResponse> createRent(@RequestBody @Valid CreateRequest request) {
        return LibraryResponse.ok(_rentUseCase.createRent(RentApplicationMapper.INSTANCE.toCreateCommand(request)));
    }
}
