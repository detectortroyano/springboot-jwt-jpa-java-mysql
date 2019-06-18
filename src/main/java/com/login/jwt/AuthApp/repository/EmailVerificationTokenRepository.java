package com.login.jwt.AuthApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.login.jwt.AuthApp.model.token.EmailVerificationToken;

import java.util.Optional;

public interface EmailVerificationTokenRepository extends JpaRepository<EmailVerificationToken, Long> {

    Optional<EmailVerificationToken> findByToken(String token);
}
