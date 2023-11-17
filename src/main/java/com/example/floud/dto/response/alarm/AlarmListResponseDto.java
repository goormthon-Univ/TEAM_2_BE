package com.example.floud.dto.response.alarm;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
public class AlarmListResponseDto {
    private Long alarm_id;
    private Long memoir_id;
    private String title;
    private Long comment_id;
    private String content;
    private Long memoir_like_id;
    private LocalDateTime alarmTime;
}
