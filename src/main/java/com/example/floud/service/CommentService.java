package com.example.floud.service;

import com.example.floud.dto.request.CommentSaveRequestDto;
import com.example.floud.dto.request.CommentUpdateRequestDto;
import com.example.floud.dto.response.CommentSaveResponseDto;
import com.example.floud.dto.response.CommentUpdateResponseDto;
import com.example.floud.entity.Comment;
import com.example.floud.entity.Memoir;
import com.example.floud.entity.User;
import com.example.floud.repository.CommentRepository;
import com.example.floud.repository.MemoirRepository;
import com.example.floud.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final UserRepository userRepository;
    private final MemoirRepository memoirRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public CommentSaveResponseDto saveComment(CommentSaveRequestDto requestDto){
        Long user_id = requestDto.getUser_id();

        User user = userRepository.findById(user_id)
                .orElseThrow(()-> new IllegalArgumentException("해당 회원 정보가 존재하지 않습니다. user_id = "+ user_id));

        Long memoir_id = requestDto.getMemoir_id();
        Memoir memoir = memoirRepository.findById(memoir_id)
                .orElseThrow(()-> new IllegalArgumentException("해당 게시글 정보가 존재하지 않습니다. user_id = "+ memoir_id));

        Comment newComment = commentRepository.save(requestDto.toEntity(user,memoir));

        CommentSaveResponseDto responseDto = CommentSaveResponseDto.builder()
                .comment_id(newComment.getComment_id())
                .parent_id(newComment.getParent_id())
                .build();

        return responseDto;
    }

    @Transactional
    public CommentUpdateResponseDto updateComment(Long comment_id, CommentUpdateRequestDto requestDto){
        Comment comment = commentRepository.findById(comment_id)
                .orElseThrow(()-> new IllegalArgumentException("해당 댓글이 존재하지 않습니다. comment_id = "+comment_id));
        comment.update(requestDto.getContent());

        CommentUpdateResponseDto responseDto = CommentUpdateResponseDto.builder()
                .comment_id(comment.getComment_id())
                .content(comment.getContent())
                .build();

        return responseDto;
    }
    @Transactional
    public void  deleteComment(Long comment_id){
        Comment comment = commentRepository.findById(comment_id)
                        .orElseThrow(()->new IllegalArgumentException(("해당 댓글이 존재하지 않습니다. comment_id = "+comment_id)));
        commentRepository.delete(comment);

    }
}
