package com.example.floud.dto.request.comment;

import com.example.floud.entity.Comment;
import com.example.floud.entity.Memoir;
import com.example.floud.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class CommentSaveRequestDto {
    private Long memoir_id;
    private Long user_id;
    private LocalDateTime createdAt;
    private String content;
    private Long parent_id;


    public Comment toEntity(Users users, Memoir memoir){
        return Comment.builder()
                .content(content)
                .parent_id(parent_id)
                .createdAt(createdAt)
                .memoir(memoir)
                .users(users)
                .build();

    }

}
