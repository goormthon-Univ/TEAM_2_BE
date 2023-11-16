package com.example.floud.service;

import com.example.floud.dto.request.like.LikeSaveRequestDto;
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

@RequiredArgsConstructor
@Service
public class MemoirLikeService {

    private final UserRepository userRepository;
    private final MemoirRepository memoirRepository;
    private final MemoirLikeRepository memoirLikeRepository;

    @Transactional
    public LikeSaveResponseDto saveLike(LikeSaveRequestDto requestDto){

        User user = userRepository.findById(requestDto.getUser_id())
                .orElseThrow(()-> new IllegalArgumentException("해당 회원 정보가 존재하지 않습니다. user_id = "+ requestDto.getUser_id()));

        Memoir memoir = memoirRepository.findById(requestDto.getMemoir_id())
                .orElseThrow(()-> new IllegalArgumentException("해당 게시글 정보가 존재하지 않습니다. user_id = "+ requestDto.getMemoir_id()));

        MemoirLike newLike = memoirLikeRepository.save(requestDto.toEntity(user,memoir));

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
}
