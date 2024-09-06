package com.example.library.api.user.domain.service;

import com.example.library.api.user.domain.model.User;
import com.example.library.api.user.domain.repository.UserQueryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserQueryService {

    private final UserQueryRepository _userQueryRepository;

    public User findByKey(final Long key) {
        return _userQueryRepository.findByKey(key)
            .orElseThrow(EntityNotFoundException::new);
    }
}
