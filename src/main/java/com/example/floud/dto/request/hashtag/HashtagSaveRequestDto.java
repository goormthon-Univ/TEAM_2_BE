package com.example.floud.dto.request.hashtag;

import com.example.floud.entity.Hashtag;
import com.example.floud.entity.Memoir;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HashtagSaveRequestDto {
    private String hashtag;
    public Hashtag toHashtag(Memoir memoir){
        return Hashtag.builder()
                .memoir(memoir)
                .tagNum(1L)
                .tagContent(hashtag)
                .build();
    }
}
