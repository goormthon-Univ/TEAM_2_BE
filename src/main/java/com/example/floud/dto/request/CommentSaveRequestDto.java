package com.example.floud.dto.request;

import com.example.floud.entity.Comment;
import com.example.floud.entity.Memoir;
import com.example.floud.entity.User;
import com.example.floud.service.CommentService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
public class CommentSaveRequestDto {
    private Long memoir_id;
    private Long user_id;
    private String content;
    private Long parent_id;


    public Comment toEntity(User user, Memoir memoir){
        return Comment.builder()
                .content(content)
                .parent_id(parent_id)
                .memoir(memoir)
                .user(user)
                .build();

    }
}
