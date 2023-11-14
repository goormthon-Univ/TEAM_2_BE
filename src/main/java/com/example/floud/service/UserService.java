package com.example.floud.service;

import com.example.floud.dto.ResponseDto;
import com.example.floud.dto.UserFormDto;
import com.example.floud.entity.User;
import com.example.floud.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public ResponseEntity<?> signup(UserFormDto userFormDto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = User.builder()
                .loginId(userFormDto.getLoginId())
                .password(passwordEncoder.encode(userFormDto.getPassword()))
                .username(userFormDto.getUsername())
                .email(userFormDto.getEmail())
                .phone(userFormDto.getPhone())
                .birth(userFormDto.getBirth())
                .build();
        isPhoneAlreadyRegistered(userFormDto.getPhone());
        isEmailAlreadyRegistered(user.getEmail());
        userRepository.save(user);
        return ResponseEntity.ok().body(user);
    }

    private boolean isPhoneAlreadyRegistered(String phone) {
        Optional<User> user = userRepository.findByPhone(phone);
        return user.isPresent();
    }

    private boolean isEmailAlreadyRegistered(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.isPresent();
    }




}
