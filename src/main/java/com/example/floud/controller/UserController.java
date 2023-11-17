package com.example.floud.controller;

import com.example.floud.dto.SuccessResponse;
import com.example.floud.dto.request.user.LoginRequestDto;
import com.example.floud.dto.request.user.MainRequestDto;
import com.example.floud.dto.request.user.SignupRequestDto;
import com.example.floud.dto.response.user.LoginResponseDto;
import com.example.floud.dto.response.user.MainResponseDto;
import com.example.floud.dto.response.user.SignupResponseDto;
import com.example.floud.exception.Success;
import com.example.floud.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    private SuccessResponse<SignupResponseDto> saveUser(@RequestBody SignupRequestDto requestDto){
        return SuccessResponse.success(Success.SAVE_USER_SUCCESS, userService.saveUser(requestDto));
    }

    @PostMapping("login")
    private SuccessResponse<LoginResponseDto> loginUser(@RequestBody LoginRequestDto requestDto){
        return SuccessResponse.success(Success.GET_USER_SUCCESS, userService.loginUser(requestDto));
    }

    @PostMapping("/main/{user_id}")
    private SuccessResponse<MainResponseDto> getMain(
            @PathVariable("user_id") Long user_id,
            @RequestBody MainRequestDto requestDto){
        return SuccessResponse.success(Success.GET_MAIN_SUCCESS, userService.getMain(user_id, requestDto));
    }


}