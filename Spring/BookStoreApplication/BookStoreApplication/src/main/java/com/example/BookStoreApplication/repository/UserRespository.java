package com.example.BookStoreApplication.repository;

import com.example.BookStoreApplication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRespository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
}
