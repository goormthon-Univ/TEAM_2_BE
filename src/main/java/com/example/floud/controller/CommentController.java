package com.example.floud.controller;

import com.example.floud.dto.SuccessResponse;
import com.example.floud.dto.request.CommentSaveRequestDto;
import com.example.floud.exception.Success;
import com.example.floud.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RequiredArgsConstructor
@Controller
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("")
    public SuccessResponse<Long> saveComment(
                                             @RequestBody CommentSaveRequestDto requestDto){
        return SuccessResponse.success(Success.SAVE_COMMENT_SUCCESS, commentService.saveComment(requestDto));
    }
}
