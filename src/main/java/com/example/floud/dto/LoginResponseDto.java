package com.example.floud.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginResponseDto {
    private String message;
    private Data data;

    public LoginResponseDto(String message, Data data) {
        this.message = message;
        this.data = data;
    }

    @Getter
    public static class Data {
        private Long userId;
        private String accessToken;
        private String refreshToken;


        public Data(Long userId, String accessToken, String refreshToken) {
            this.userId = userId;
            this.accessToken = accessToken;
            this.refreshToken = refreshToken;
        }

    }
}
