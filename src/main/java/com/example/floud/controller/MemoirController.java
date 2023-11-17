package com.example.floud.controller;

import com.example.floud.dto.SuccessResponse;
import com.example.floud.dto.request.hashtag.HashtagSaveRequestDto;
import com.example.floud.dto.request.memoir.MemoirCreateRequestDto;
import com.example.floud.dto.request.memoir.MemoirUpdateRequestDto;
import com.example.floud.dto.response.memoir.MemoirAnonymousResponseDto;
import com.example.floud.dto.response.memoir.MemoirCreateResponseDto;
import com.example.floud.dto.response.memoir.MemoirGetOneResponseDto;
import com.example.floud.dto.response.memoir.MemoirUpdateResponseDto;
import com.example.floud.exception.Success;
import com.example.floud.service.HashtagService;
import com.example.floud.service.MemoirService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor

@RestController
@RequestMapping("/memoir")
public class MemoirController {

    private final MemoirService memoirService;
    private final HashtagService hashtagService;


    @PostMapping
    public SuccessResponse<MemoirCreateResponseDto> createMemoir(@RequestBody MemoirCreateRequestDto requestDto) {
        MemoirCreateResponseDto responseDto = memoirService.createMemoir(requestDto);
        hashtagService.createHashtag(requestDto, responseDto.getMemoir_id());
        return SuccessResponse.success(Success.SAVE_MEMOIR_SUCCESS, responseDto);
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

    @GetMapping("/anonymous/{user_id}")
    public SuccessResponse<MemoirAnonymousResponseDto> getAnonymousMemoir(@PathVariable("user_id") Long user_id) {
        return SuccessResponse.success(Success.GET_ANONYMOUS_MEMOIR_SUCCESS, memoirService.getAnonymousMemoir(user_id));
    }
}
