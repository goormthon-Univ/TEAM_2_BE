package com.example.floud.dto.response.like;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class LikeMemoirListResponseDto {
    private Long memoir_id;
    private String title;
    private LocalDateTime created_at;
    private Long memoir_like_id;
}