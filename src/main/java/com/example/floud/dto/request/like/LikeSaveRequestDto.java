package com.example.floud.dto.request.like;

import com.example.floud.entity.Memoir;
import com.example.floud.entity.MemoirLike;
import com.example.floud.entity.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
public class LikeSaveRequestDto {
    private Long user_id;
    private Long memoir_id;
    private LocalDateTime likeDate;

    public MemoirLike toEntity(User user, Memoir memoir){
        return MemoirLike.builder()
                .user(user)
                .memoir(memoir)
                .likeDate(likeDate)
                .build();
    }
}
