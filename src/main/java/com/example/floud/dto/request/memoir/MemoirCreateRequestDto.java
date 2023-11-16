package com.example.floud.dto.request.memoir;


import com.example.floud.entity.Memoir;
import com.example.floud.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemoirCreateRequestDto {
    private Long user_id;
    private String title;
    private String place;
    private String memoirKeep;
    private String memoirProblem;
    private String memoirTry;

    public Memoir toMemoir(User user) {
        return Memoir.builder()
                .title(title)
                .place(place)
                .memoirKeep(memoirKeep)
                .memoirProblem(memoirProblem)
                .memoirTry(memoirTry)
                .user(user)
                .build();
    }
}
