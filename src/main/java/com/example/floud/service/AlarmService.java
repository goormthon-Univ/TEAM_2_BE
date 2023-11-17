package com.example.floud.service;

import com.example.floud.dto.request.alarm.AlarmSaveRequestDto;
import com.example.floud.dto.response.alarm.AlarmListResponseDto;
import com.example.floud.entity.*;
import com.example.floud.repository.AlarmRepository;
import com.example.floud.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AlarmService {
    private final AlarmRepository alarmRepository;
    private final UserRepository userRepository;
    @Transactional
    public void saveAlarmComment(User user, Memoir memoir, Comment comment){
        AlarmSaveRequestDto requestDto = new AlarmSaveRequestDto();
        alarmRepository.save(requestDto.toEntityComment(user,memoir,comment));
    }

    @Transactional
    public void saveAlarmLike(User user, Memoir memoir, MemoirLike memoirLike){
        AlarmSaveRequestDto requestDto = new AlarmSaveRequestDto();
        alarmRepository.save(requestDto.toEntityLike(user,memoir,memoirLike));
    }

    @Transactional
    public List<AlarmListResponseDto> getAlarm(Long user_id){
        userRepository.findById(user_id)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원 정보가 존재하지 않습니다. user_id = " + user_id));

        List<Alarm> alarmList = alarmRepository.findTop30ByUserIdOrderByAlarmDateDesc(user_id);
        List<AlarmListResponseDto> alarmDtoList = new ArrayList<>();

        for (Alarm alarm : alarmList) {
            AlarmListResponseDto alarmDto = new AlarmListResponseDto();
            alarmDto.setAlarm_id(alarm.getAlarm_id());

            if (alarm.getComment() != null) {
                alarmDto.setComment_id(alarm.getComment().getComment_id());
                alarmDto.setContent(alarm.getComment().getContent());
                alarmDto.setAlarmTime(alarm.getComment().getCreatedAt());
            }

            if (alarm.getMemoirLike() != null) {
                alarmDto.setMemoir_like_id(alarm.getMemoirLike().getMemoir_like_id());
                alarmDto.setMemoir_id(alarm.getMemoirLike().getMemoir().getId());
                alarmDto.setTitle(alarm.getMemoirLike().getMemoir().getTitle());
                alarmDto.setAlarmTime(alarm.getMemoirLike().getLikeDate());
            }

            alarmDtoList.add(alarmDto);
        }

        alarmDtoList.sort(Comparator.comparing(AlarmListResponseDto::getAlarmTime).reversed());
        return alarmDtoList;
    }

}
