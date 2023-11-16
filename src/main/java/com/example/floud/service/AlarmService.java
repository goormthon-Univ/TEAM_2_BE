package com.example.floud.service;

import com.example.floud.dto.request.alarm.AlarmSaveRequestDto;
import com.example.floud.dto.request.comment.CommentSaveRequestDto;
import com.example.floud.dto.response.comment.CommentSaveResponseDto;
import com.example.floud.entity.*;
import com.example.floud.repository.AlarmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class AlarmService {
    private final AlarmRepository alarmRepository;

    @Transactional
    public void saveAlarmComment(User user, Memoir memoir, Comment comment){
        AlarmSaveRequestDto requestDto = new AlarmSaveRequestDto();
        Alarm newAlarm = alarmRepository.save(requestDto.toEntityComment(user,memoir,comment));
    }

    @Transactional
    public void saveAlarmLike(User user, Memoir memoir, MemoirLike memoirLike){
        AlarmSaveRequestDto requestDto = new AlarmSaveRequestDto();
        Alarm newAlarm = alarmRepository.save(requestDto.toEntityLike(user,memoir,memoirLike));
    }
}
