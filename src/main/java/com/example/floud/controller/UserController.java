package com.example.floud.controller;

import com.example.floud.dto.JwtToken;
import com.example.floud.dto.LoginFormDto;
import com.example.floud.dto.LoginResponseDto;
import com.example.floud.dto.UserFormDto;
import com.example.floud.entity.User;
import com.example.floud.repository.UserRepository;
import com.example.floud.service.UserService;
import com.example.floud.util.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    private UserService userService;

    private UserRepository userRepository;

    private JwtProvider jwtProvider;

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
        try {
            ResponseEntity<?> registeredUser = userService.signup(user);
            return ResponseEntity.ok(registeredUser);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());  // 오류 응답
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginFormDto loginFormDto) {
        LoginResponseDto.Data data = userService.login(loginFormDto.getLoginId(), loginFormDto.getPassword());
        Long userId = data.getUserId();
        LoginResponseDto.Data responseData = new LoginResponseDto.Data(userId, data.getAccessToken(), data.getRefreshToken());
        LoginResponseDto response = new LoginResponseDto("로그인 성공", responseData);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}