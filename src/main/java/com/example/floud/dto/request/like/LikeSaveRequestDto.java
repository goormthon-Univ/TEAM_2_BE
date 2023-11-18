package com.example.floud.dto.request.like;

import com.example.floud.entity.Memoir;
import com.example.floud.entity.MemoirLike;
import com.example.floud.entity.Users;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class LikeSaveRequestDto {
    private Long user_id;
    private Long memoir_id;
    private LocalDateTime likeDate;

    public MemoirLike toEntity(Users users, Memoir memoir){
        return MemoirLike.builder()
                .users(users)
                .memoir(memoir)
                .likeDate(likeDate)
                .build();
    }
}
