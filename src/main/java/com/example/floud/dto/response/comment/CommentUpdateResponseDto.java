package com.example.floud.dto.response.comment;


import lombok.Builder;
import lombok.Getter;

@Getter
public class CommentUpdateResponseDto {
    private Long comment_id;
    private String content;

    public CommentUpdateResponseDto(){}

    @Builder
    public CommentUpdateResponseDto(Long comment_id, String content){
        this.comment_id =comment_id;
        this.content = content;
    }
}
