package com.example.floud.dto.request;

import com.example.floud.entity.Memoir;
import com.example.floud.entity.MemoirLike;
import com.example.floud.entity.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LikeSaveRequestDto {
    private Long user_id;
    private Long memoir_id;

    public MemoirLike toEntity(User user, Memoir memoir){
        return MemoirLike.builder()
                .user(user)
                .memoir(memoir)
                .build();
    }
}
