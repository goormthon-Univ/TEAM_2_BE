package com.example.floud.controller;

import com.example.floud.dto.SuccessResponse;
import com.example.floud.dto.request.like.LikeMemoirListRequestDto;
import com.example.floud.dto.request.memoir.MemoirAnonymousRequestDto;
import com.example.floud.dto.request.memoir.MemoirCreateRequestDto;
import com.example.floud.dto.request.memoir.MemoirUpdateRequestDto;
import com.example.floud.dto.response.like.LikeMemoirListResponseDto;
import com.example.floud.dto.response.memoir.MemoirAnonymousResponseDto;
import com.example.floud.dto.response.memoir.MemoirCreateResponseDto;
import com.example.floud.dto.response.memoir.MemoirGetOneResponseDto;
import com.example.floud.dto.response.memoir.MemoirUpdateResponseDto;
import com.example.floud.exception.Success;
import com.example.floud.service.MemoirService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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


    @GetMapping("/{memoir_id}")
    public SuccessResponse<MemoirGetOneResponseDto> getOneMemoir(@PathVariable Long memoir_id) {
        return SuccessResponse.success(Success.GET_ONE_MEMOIR_SUCCESS, memoirService.getOneMemoir(memoir_id));
    }

    @PostMapping("/anonymous")
    public SuccessResponse<MemoirAnonymousResponseDto> getAnonymousMemoir(@RequestBody MemoirAnonymousRequestDto requestDto) {
        return SuccessResponse.success(Success.GET_ANONYMOUS_MEMOIR_SUCCESS, memoirService.getAnonymousMemoir(requestDto.getUser_id()));
    }
}
