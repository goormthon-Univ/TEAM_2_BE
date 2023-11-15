package com.example.floud.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MemoirRequestDto {
    private String title;
    private String memoirKeep;
    private String memoirProblem;
    private String memoirTry;
}
