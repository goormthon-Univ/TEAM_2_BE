package com.example.floud.controller;

import com.example.floud.entity.User;
import com.example.floud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    private UserService userService;

    // 유저 ID로 사용자를 조회
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(
            @PathVariable Long id) {
        return userService.getUserById(id)
                .map(user-> ResponseEntity.ok(user))
                .orElse(ResponseEntity.notFound().build());
    }

}
