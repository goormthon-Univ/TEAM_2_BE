package com.example.floud.controller;

import com.example.floud.dto.SuccessResponse;
import com.example.floud.dto.request.LikeSaveRequestDto;
import com.example.floud.dto.response.LikeSaveResponseDto;
import com.example.floud.exception.Success;
import com.example.floud.service.MemoirLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping("/like")
public class MemoirLikeController {

    private final MemoirLikeService memoirLikeService;

    @PostMapping("")
    public SuccessResponse<LikeSaveResponseDto> saveMemoirLike(@RequestBody LikeSaveRequestDto requestDto){
        return SuccessResponse.success(Success.SAVE_LIKE_SUCCESS, memoirLikeService.saveLike(requestDto));
    }
}
