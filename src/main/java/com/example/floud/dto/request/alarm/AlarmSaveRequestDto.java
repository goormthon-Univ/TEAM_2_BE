package com.example.floud.dto.request.alarm;

import com.example.floud.entity.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.PrePersist;
import java.time.LocalDateTime;

@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AlarmSaveRequestDto {
    private LocalDateTime alarmDate;

    public Alarm toEntityComment(User user, Memoir memoir, Comment comment){
        alarmDate = LocalDateTime.now();
        return Alarm.builder()
                .user(user)
                .memoir(memoir)
                .comment(comment)
                .alarmDate(alarmDate)
                .memoirLike(null)
                .build();

    }
    public Alarm toEntityLike(User user, Memoir memoir, MemoirLike memoirLike){
        alarmDate = LocalDateTime.now();
        return Alarm.builder()
                .user(user)
                .memoir(memoir)
                .comment(null)
                .alarmDate(alarmDate)
                .memoirLike(memoirLike)
                .build();

    }
}
