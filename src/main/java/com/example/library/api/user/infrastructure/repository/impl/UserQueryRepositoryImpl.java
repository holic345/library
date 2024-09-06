package com.example.library.api.user.infrastructure.repository.impl;

import com.example.library.api.user.domain.mapper.UserMapper;
import com.example.library.api.user.domain.model.User;
import com.example.library.api.user.domain.repository.UserQueryRepository;
import com.example.library.api.user.infrastructure.repository.UserJpaRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserQueryRepositoryImpl implements UserQueryRepository {

    private final UserJpaRepository _userJpaRepository;

    @Override
    public Optional<User> findByKey(Long key) {
        return _userJpaRepository.findById(key).map(UserMapper.INSTANCE::toDomain);
    }
}
