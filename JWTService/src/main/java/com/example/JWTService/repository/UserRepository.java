package com.example.JWTService.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.JWTService.entity.User;

// прописал здесь стандартные методы, в дальнейшем скорее всего использованы не буду ведь проект учебный

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);

    Optional<User> findByEmail(String email);

    Boolean existByLogin(String login);

    Boolean existByEmail(String email);
}
