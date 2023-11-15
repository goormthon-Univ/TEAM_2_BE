package com.example.floud.service;

import com.example.floud.dto.JwtToken;
import com.example.floud.dto.UserFormDto;
import com.example.floud.entity.User;
import com.example.floud.repository.UserRepository;
import com.example.floud.util.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;


    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public ResponseEntity<?> signup(UserFormDto userFormDto) {
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

    public JwtToken login(String loginId, String rawPassword) {
        // 사용자 정보 조회
        User user = userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

        // 비밀번호 검증
        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
        }

        // 토큰 생성 및 인증
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginId, rawPassword);
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        // JWT 토큰 생성
        JwtToken token = jwtProvider.generateToken(authentication);
        return token;
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
