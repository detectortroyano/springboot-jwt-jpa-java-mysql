package com.login.jwt.AuthApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.login.jwt.AuthApp.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
