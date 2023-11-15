package com.example.floud.service;

import com.example.floud.dto.request.CommentSaveRequestDto;
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
    public Long saveComment(CommentSaveRequestDto requestDto){
        Long user_id = requestDto.getUser_id();
        User user = userRepository.findById(user_id)
                .orElseThrow(()-> new IllegalArgumentException("해당 회원 정보가 존재하지 않습니다. user_id = "+ user_id));

        Long memoir_id = requestDto.getMemoir_id();
        Memoir memoir = memoirRepository.findById(memoir_id)
                .orElseThrow(()-> new IllegalArgumentException("해당 게시글 정보가 존재하지 않습니다. user_id = "+ memoir_id));

        Comment newComment = commentRepository.save(requestDto.toEntity(user,memoir));
        return newComment.getComment_id();
    }
}
