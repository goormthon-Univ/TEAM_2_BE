package com.example.floud.controller;

import com.example.floud.dto.UserFormDto;
import com.example.floud.entity.User;
import com.example.floud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
                .map(user -> ResponseEntity.ok(user))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(
            @RequestBody UserFormDto user) {
        try{
            ResponseEntity<?> registeredUser = userService.signup(user);
            return ResponseEntity.ok(registeredUser);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());  // 오류 응답
        }
    }
}
