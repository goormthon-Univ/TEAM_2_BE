package com.example.floud.dto.response.memoir;

import com.example.floud.entity.Comment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class MemoirAnonymousResponseDto {
    private Long user_id;
    private String title;
    private String place;
    private String memoirKeep;
    private String memoirProblem;
    private String memoirTry;
    private int commentCount;
    private int likeCount;
    private List<Comment> commentList;
    private LocalDateTime createdAt;
}
