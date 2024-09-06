package com.example.library.api.user.domain.repository;

import com.example.library.api.user.domain.model.User;
import java.util.Optional;

public interface UserQueryRepository {

    Optional<User> findByKey(Long key);
}
