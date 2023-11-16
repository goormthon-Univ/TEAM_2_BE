package com.example.floud.controller;

import com.example.floud.dto.SuccessResponse;
import com.example.floud.dto.request.MemoirRequestDto;
import com.example.floud.dto.response.MemoirResponseDto;
import com.example.floud.entity.Memoir;
import com.example.floud.exception.Success;
import com.example.floud.service.MemoirService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/memoir")
public class MemoirController {

    private final MemoirService memoirService;

    @Autowired
    public MemoirController(MemoirService memoirService) {
        this.memoirService = memoirService;
    }

    @PostMapping
    public SuccessResponse<MemoirResponseDto> createMemoir(@RequestBody MemoirRequestDto requestDto) {
        return SuccessResponse.success(Success.SAVE_MEMOIR_SUCCESS, memoirService.createMemoir(requestDto));
    }
}
