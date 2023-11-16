package com.example.floud.dto.response.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyCommentListResponseDto {
    private Long memoir_id;
    private String title;
    private LocalDateTime createdAt;
    private Long comment_id;
    private String content;

}
