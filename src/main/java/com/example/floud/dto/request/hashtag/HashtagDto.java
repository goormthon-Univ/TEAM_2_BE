package com.example.floud.dto.request.hashtag;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class HashtagDto {
    private String tagContent;
    private Long tagNum;
}