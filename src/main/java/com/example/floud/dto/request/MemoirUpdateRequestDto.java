package com.example.floud.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemoirUpdateRequestDto {
    private String title;
    private String place;
    private String memoirKeep;
    private String memoirProblem;
    private String memoirTry;
}
