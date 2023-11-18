package com.example.floud.dto.response.user;

import com.example.floud.dto.request.hashtag.HashtagDto;
import com.example.floud.entity.Hashtag;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
public class MainResponseDto {
    private Long user_id;
    private Long memoir_id;
    private String title;
    private int backColor;
    private int continueDate;
    private List<HashtagDto> hashtagList;
}
