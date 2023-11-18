package com.example.floud.dto.request.memoir;


import com.example.floud.entity.Memoir;
import com.example.floud.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
@AllArgsConstructor
public class MemoirCreateRequestDto {
    private Long user_id;
    private String title;
    private String place;
    private LocalDateTime createdAt;
    private String memoirKeep;
    private String memoirProblem;
    private String memoirTry;
    private Long backColor;
    private String hashtag1;
    private String hashtag2;
    private String hashtag3;

    public Memoir toMemoir(Users users) {
        return Memoir.builder()
                .title(title)
                .place(place)
                .createdAt(createdAt)
                .memoirKeep(memoirKeep)
                .memoirProblem(memoirProblem)
                .memoirTry(memoirTry)
                .users(users)
                .build();
    }


}
