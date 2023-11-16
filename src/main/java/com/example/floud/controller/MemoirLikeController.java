package com.example.floud.controller;

import com.example.floud.dto.SuccessResponse;
import com.example.floud.dto.request.like.LikeMemoirListRequestDto;
import com.example.floud.dto.request.like.LikeSaveRequestDto;
import com.example.floud.dto.response.like.LikeMemoirListResponseDto;
import com.example.floud.dto.response.like.LikeSaveResponseDto;
import com.example.floud.exception.Success;
import com.example.floud.service.MemoirLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/like")
public class MemoirLikeController {

    private final MemoirLikeService memoirLikeService;

    @PostMapping("")
    public SuccessResponse<LikeSaveResponseDto> saveMemoirLike(@RequestBody LikeSaveRequestDto requestDto){
        return SuccessResponse.success(Success.SAVE_LIKE_SUCCESS, memoirLikeService.saveLike(requestDto));
    }

    @DeleteMapping("/delete/{memoir_like_id}")
    private SuccessResponse<Long> deleteMemoirLike(@PathVariable("memoir_like_id") Long memoir_like_id){
        return SuccessResponse.success(Success.DELETE_LIKE_SUCCESS,  memoirLikeService.deleteLike(memoir_like_id) );
    }

    @GetMapping("/memoir/{user_id}")
    private SuccessResponse<List<LikeMemoirListResponseDto>> getMemoirLike(
            @PathVariable("user_id") Long user_id,
            @RequestBody LikeMemoirListRequestDto requestDto){
        return SuccessResponse.success(Success.GET_MY_LIKE_MEMOIR_SUCCESS, memoirLikeService.getMemoirLike(user_id, requestDto));
    }

}
