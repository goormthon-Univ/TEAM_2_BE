package com.example.floud.repository;

import com.example.floud.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User>  findByEmail(String email);

//    @Override
//    Optional<User> findById(Long user_id);
//
//
//
//    Optional<User> findByPhone(String phone);
//
//    Optional<User> findByUsername(String username);
//
//    Optional<User> findByLoginId(String loginId);
//
}
