package com.example.library.api.user.infrastructure.repository;

import com.example.library.api.user.infrastructure.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {


}
