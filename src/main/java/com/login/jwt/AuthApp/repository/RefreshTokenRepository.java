package com.login.jwt.AuthApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.login.jwt.AuthApp.model.token.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    @Override
    Optional<RefreshToken> findById(Long id);

    Optional<RefreshToken> findByToken(String token);

}
