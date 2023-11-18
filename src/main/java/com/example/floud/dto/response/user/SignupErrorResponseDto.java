package com.example.floud.dto.response.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class SignupErrorResponseDto {
    private int code;
    private String message;

    public SignupErrorResponseDto(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
