package com.example.library.api.user.domain.service;

import com.example.library.api.user.domain.repository.UserQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private UserQueryRepository _userQueryRepository;
}
