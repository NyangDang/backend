package com.sparta.nyangdangback.user.repository;

import com.sparta.nyangdangback.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByKakaoId(Long id);
    boolean existsByusername(String username);
}


