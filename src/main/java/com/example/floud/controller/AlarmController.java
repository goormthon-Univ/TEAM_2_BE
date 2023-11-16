package com.example.floud.controller;

import com.example.floud.dto.SuccessResponse;
import com.example.floud.dto.response.alarm.AlarmListResponseDto;
import com.example.floud.exception.Success;
import com.example.floud.service.AlarmService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class AlarmController {
    private final AlarmService alarmService;
    @GetMapping("/notifications/{user_id}")
    public  SuccessResponse<List<AlarmListResponseDto>> getAlarm(
            @PathVariable("user_id")Long user_id){
        return SuccessResponse.success(Success.GET_MY_ALARM_SUCCESS,alarmService.getAlarm(user_id));
    }
}
