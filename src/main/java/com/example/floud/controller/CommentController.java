package com.example.floud.controller;


import com.example.floud.dto.SuccessResponse;
import com.example.floud.dto.request.comment.CommentSaveRequestDto;
import com.example.floud.dto.request.comment.CommentUpdateRequestDto;
import com.example.floud.dto.request.comment.MyCommentListRequestDto;
import com.example.floud.dto.response.comment.CommentSaveResponseDto;
import com.example.floud.dto.response.comment.CommentUpdateResponseDto;
import com.example.floud.dto.response.comment.MyCommentListResponseDto;

import com.example.floud.exception.Success;
import com.example.floud.service.AlarmService;
import com.example.floud.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;
    private final AlarmService alarmService;

    @PostMapping("")
    public SuccessResponse<CommentSaveResponseDto> saveComment(@RequestBody CommentSaveRequestDto requestDto){
        CommentSaveResponseDto responseDto = commentService.saveComment(requestDto);
        return SuccessResponse.success(Success.SAVE_COMMENT_SUCCESS, responseDto);
    }

    @PutMapping("/edit/{comment_id}")
    public SuccessResponse<CommentUpdateResponseDto> updateComment(
            @PathVariable("comment_id") Long comment_id,
            @RequestBody CommentUpdateRequestDto requestDto){
        return SuccessResponse.success(Success.UPDATE_COMMENT_SUCCESS, commentService.updateComment(comment_id, requestDto));
    }


    @DeleteMapping("/delete/{comment_id}")
    public SuccessResponse<?> deleteComment(@PathVariable("comment_id")Long comment_id ){
        commentService.deleteComment(comment_id);
        return SuccessResponse.success(Success.DELETE_COMMENT_SUCCESS);
    }

    @GetMapping("/memoir/{user_id}")
    public List<MyCommentListResponseDto> getMyComment(
            @PathVariable("user_id")Long user_id,
            @RequestBody MyCommentListRequestDto requestDto){
        return commentService.getMyComment(user_id,requestDto);
    }
}
