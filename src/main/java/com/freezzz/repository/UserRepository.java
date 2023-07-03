package com.freezzz.repository;

import com.freezzz.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByLogin(String login);

    Optional<User> findByEmail(String email);
}
