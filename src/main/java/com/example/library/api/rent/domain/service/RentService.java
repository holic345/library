package com.example.library.api.rent.domain.service;

import com.example.library.api.rent.application.command.CreateCommand;
import com.example.library.api.rent.domain.mapper.RentMapper;
import com.example.library.api.rent.domain.model.Rent;
import com.example.library.api.rent.domain.repository.RentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RentService {

    private final RentRepository _rentRepository;

    @Transactional
    public Rent create(final CreateCommand createCommand) {
        return _rentRepository.createRent(RentMapper.INSTANCE.toEntityFromCommand(createCommand));
    }
}
