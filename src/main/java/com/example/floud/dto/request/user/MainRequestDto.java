package com.example.floud.dto.request.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class MainRequestDto {
    private LocalDateTime nowTime;
}
