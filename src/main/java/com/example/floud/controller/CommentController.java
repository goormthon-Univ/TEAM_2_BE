package com.example.floud.controller;

import com.example.floud.dto.SuccessResponse;
import com.example.floud.dto.request.CommentSaveRequestDto;
import com.example.floud.dto.response.CommentSaveResponseDto;
import com.example.floud.exception.Success;
import com.example.floud.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("")
    public SuccessResponse<CommentSaveResponseDto> saveComment(@RequestBody CommentSaveRequestDto requestDto){
        return SuccessResponse.success(Success.SAVE_COMMENT_SUCCESS, commentService.saveComment(requestDto));
    }



    @DeleteMapping("/delete/{comment_id}")
    public SuccessResponse<?> deleteComment(@PathVariable("comment_id")Long comment_id ){
        commentService.deleteComment(comment_id);
        return SuccessResponse.success(Success.DELETE_COMMENT_SUCCESS);
    }
}
