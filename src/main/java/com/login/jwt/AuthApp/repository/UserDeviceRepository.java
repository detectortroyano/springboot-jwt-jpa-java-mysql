package com.login.jwt.AuthApp.repository;

import com.login.jwt.AuthApp.model.UserDevice;
import com.login.jwt.AuthApp.model.token.RefreshToken;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDeviceRepository extends JpaRepository<UserDevice, Long> {

    @Override
    Optional<UserDevice> findById(Long id);

    Optional<UserDevice> findByRefreshToken(RefreshToken refreshToken);

    Optional<UserDevice> findByUserId(Long userId);
}
