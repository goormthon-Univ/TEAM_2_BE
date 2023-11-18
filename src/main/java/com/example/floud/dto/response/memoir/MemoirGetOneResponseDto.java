package com.example.floud.dto.response.memoir;

import com.example.floud.entity.Comment;
import com.example.floud.entity.MemoirLike;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class MemoirGetOneResponseDto {
    private Long user_id;
    private String title;
    private String place;
    private String memoirKeep;
    private String memoirProblem;
    private String memoirTry;
    private List<Comment> commentList;
    private int commentCount;
    private int likeCount;
    private List<MemoirLike> memoirLikeList;
    private LocalDateTime createdAt;
    private String hashtag1;
    private String hashtag2;
    private String hashtag3;

}
