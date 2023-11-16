package com.example.floud.dto.request.like;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LikeMemoirListRequestDto {
    private LocalDate likeDate;
}
