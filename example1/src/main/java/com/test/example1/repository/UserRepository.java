package com.test.example1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.example1.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByusername(String username);
}
