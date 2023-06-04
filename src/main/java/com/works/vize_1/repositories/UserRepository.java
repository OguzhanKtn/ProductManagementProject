package com.works.vize_1.repositories;

import com.works.vize_1.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailEqualsAndPasswordEquals(String email, String password);


}