//package com.example.floud.config;
//
//import com.example.floud.dto.JwtToken;
//import com.example.floud.util.JwtProvider;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@EnableConfigurationProperties(JwtToken.class)
//public class JwtConfig {
//
//    public JwtProvider jwtProvider(JwtToken jwtToken) {
//        return new JwtProvider(jwtToken.getSecret());
//    }
//}
