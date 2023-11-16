package com.example.floud.controller;

import com.example.floud.dto.SuccessResponse;
import com.example.floud.dto.request.MemoirCreateRequestDto;
import com.example.floud.dto.request.MemoirUpdateRequestDto;
import com.example.floud.dto.response.MemoirCreateResponseDto;
import com.example.floud.dto.response.MemoirUpdateResponseDto;
import com.example.floud.exception.Success;
import com.example.floud.service.MemoirService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/memoir")
public class MemoirController {

    private final MemoirService memoirService;

    @Autowired
    public MemoirController(MemoirService memoirService) {
        this.memoirService = memoirService;
    }

    @PostMapping
    public SuccessResponse<MemoirCreateResponseDto> createMemoir(@RequestBody MemoirCreateRequestDto requestDto) {
        return SuccessResponse.success(Success.SAVE_MEMOIR_SUCCESS, memoirService.createMemoir(requestDto));
    }

    @PatchMapping("/edit/{memoir_id}")
    public SuccessResponse<MemoirUpdateResponseDto> updateMemoir(@RequestBody MemoirUpdateRequestDto requestDto,
                                                                 @PathVariable Long memoir_id) {
        return SuccessResponse.success(Success.UPDATE_MEMOIR_SUCCESS, memoirService.updateMemoir(memoir_id, requestDto));
    }

    @DeleteMapping("/delete/{memoir_id}")
    public SuccessResponse<?> deleteMemoir(@PathVariable Long memoir_id) {
        memoirService.deleteMemoir(memoir_id);
        return SuccessResponse.success(Success.DELETE_MEMOIR_SUCCESS);
    }
}
