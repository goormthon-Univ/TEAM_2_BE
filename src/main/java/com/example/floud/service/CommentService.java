package com.example.floud.service;


import com.example.floud.dto.request.comment.CommentSaveRequestDto;
import com.example.floud.dto.request.comment.CommentUpdateRequestDto;
import com.example.floud.dto.request.comment.MyCommentListRequestDto;
import com.example.floud.dto.response.comment.CommentSaveResponseDto;
import com.example.floud.dto.response.comment.CommentUpdateResponseDto;
import com.example.floud.dto.response.comment.MyCommentListResponseDto;
import com.example.floud.entity.Comment;
import com.example.floud.entity.Memoir;


import com.example.floud.entity.User;
import com.example.floud.repository.CommentRepository;
import com.example.floud.repository.MemoirRepository;
import com.example.floud.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class CommentService {
    private final UserRepository userRepository;
    private final MemoirRepository memoirRepository;
    private final CommentRepository commentRepository;
    private final AlarmService alarmService;
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

        //알람생성
        alarmService.saveAlarmComment(user,memoir,newComment);

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


    @Transactional
    public List<MyCommentListResponseDto> getMyComment(Long user_id, MyCommentListRequestDto requestDto) {

        User user = userRepository.findById(user_id)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원 정보가 존재하지 않습니다. user_id = " + user_id));

        //한달기준
        LocalDate created_at = requestDto.getCreatedAt().toLocalDate();
        LocalDateTime startDate = created_at.withDayOfMonth(1).atStartOfDay();
        LocalDateTime endDate = created_at.withDayOfMonth(created_at.lengthOfMonth()).atTime(LocalTime.MAX);

        //사용자가 쓴 댓글 찾기
        List<Comment> comments = commentRepository.findByUser_Id(user_id);
        List<MyCommentListResponseDto> responseDtos = null;
        if (!comments.isEmpty()) {
            responseDtos = comments.stream()
                    .filter(comment -> {
                        LocalDateTime memoirCreatedAt = comment.getMemoir().getCreatedAt();
                        return memoirCreatedAt.isAfter(startDate) && memoirCreatedAt.isBefore(endDate);
                    })
                    .map(comment -> {
                        Memoir memoir = comment.getMemoir();
                        return MyCommentListResponseDto.builder()
                                .memoir_id(memoir.getId())
                                .title(memoir.getTitle())
                                .createdAt(memoir.getCreatedAt())
                                .comment_id(comment.getComment_id())
                                .content(comment.getContent())
                                .build();
                    })
                    .collect(Collectors.toList());
        }

        return responseDtos;

    }
}
