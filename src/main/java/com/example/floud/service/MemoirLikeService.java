package com.example.floud.service;

import com.example.floud.dto.request.like.LikeMemoirListRequestDto;
import com.example.floud.dto.request.like.LikeSaveRequestDto;
import com.example.floud.dto.response.like.LikeMemoirListResponseDto;
import com.example.floud.dto.response.like.LikeSaveResponseDto;
import com.example.floud.entity.Memoir;
import com.example.floud.entity.MemoirLike;
import com.example.floud.entity.User;
import com.example.floud.repository.MemoirLikeRepository;
import com.example.floud.repository.MemoirRepository;
import com.example.floud.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MemoirLikeService {

    private final UserRepository userRepository;
    private final MemoirRepository memoirRepository;
    private final MemoirLikeRepository memoirLikeRepository;

    private final AlarmService alarmService;

    @Transactional
    public LikeSaveResponseDto saveLike(LikeSaveRequestDto requestDto){

        User user = userRepository.findById(requestDto.getUser_id())
                .orElseThrow(()-> new IllegalArgumentException("해당 회원 정보가 존재하지 않습니다. user_id = "+ requestDto.getUser_id()));
        Memoir memoir = memoirRepository.findById(requestDto.getMemoir_id())
                .orElseThrow(()-> new IllegalArgumentException("해당 게시글 정보가 존재하지 않습니다. user_id = "+ requestDto.getMemoir_id()));

        //좋아요
        MemoirLike newLike = memoirLikeRepository.save(requestDto.toEntity(user,memoir));
        //알람 생성
        alarmService.saveAlarmLike(user,memoir,newLike);

        return LikeSaveResponseDto.builder()
                .memori_like_id(newLike.getMemoir_like_id())
                .build();
    }

    @Transactional
    public Long deleteLike(Long memoir_like_id){
        MemoirLike memoirLike = memoirLikeRepository.findById(memoir_like_id)
                .orElseThrow(()-> new IllegalArgumentException("해당 좋아요 정보가 존재하지 않습니다. memoir_like_id = "+memoir_like_id));
        memoirLikeRepository.delete(memoirLike);
        return memoir_like_id;
    }

    @Transactional
    public List<LikeMemoirListResponseDto> getMemoirLike(Long user_id, LikeMemoirListRequestDto requestDto){

        User user = userRepository.findById(user_id)
                .orElseThrow(()-> new IllegalArgumentException("해당 회원 정보가 존재하지 않습니다. user_id = "+ user_id));

        LocalDate like_date =  requestDto.getLikeDate();
        LocalDate startDate = like_date.withDayOfMonth(1);
        LocalDate endDate = like_date.withDayOfMonth(like_date.lengthOfMonth());

        List<MemoirLike> memoirLikes = memoirLikeRepository.findByUserIdAndLikeDateBetween(user_id, startDate, endDate);
        List<LikeMemoirListResponseDto> responseDtos = memoirLikes.stream()
                .map(memoirLike -> {
                    Memoir memoir = memoirLike.getMemoir();
                    return LikeMemoirListResponseDto.builder()
                            .memoir_id(memoir.getId())
                            .title(memoir.getTitle())
                            .createdAt(memoir.getCreatedAt())
                            .memoir_like_id(memoirLike.getMemoir_like_id())
                            .build();
                })
                .collect(Collectors.toList());

        return responseDtos;
    }
}
