package com.example.floud.dto.response.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class LoginResponseDto {
    private Long user_id;
    private String username;
}
