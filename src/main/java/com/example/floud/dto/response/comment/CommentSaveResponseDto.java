package com.example.floud.dto.response.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter //for 데이터 직렬화
@Builder
public class CommentSaveResponseDto {
    private Long comment_id;
    private Long parent_id;
}
