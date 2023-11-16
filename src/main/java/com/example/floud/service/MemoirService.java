package com.example.floud.service;

import com.example.floud.dto.request.LikeMemoirListRequestDto;
import com.example.floud.dto.response.LikeMemoirListResponseDto;
import com.example.floud.dto.request.MemoirCreateRequestDto;
import com.example.floud.dto.request.MemoirUpdateRequestDto;
import com.example.floud.dto.response.MemoirCreateResponseDto;
import com.example.floud.dto.response.MemoirUpdateResponseDto;
import com.example.floud.entity.Memoir;
import com.example.floud.entity.MemoirLike;
import com.example.floud.entity.User;
import com.example.floud.repository.MemoirLikeRepository;
import com.example.floud.repository.MemoirRepository;
import com.example.floud.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MemoirService {

    private final MemoirRepository memoirRepository;
    private final MemoirLikeRepository memoirLikeRepository;
    private final UserRepository userRepository;

    @Transactional
    public MemoirCreateResponseDto createMemoir(MemoirCreateRequestDto requestDto) {
        Long user_id = requestDto.getUser_id();

        User user = userRepository.findById(user_id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + user_id));

        Memoir newMemoir = memoirRepository.save(requestDto.toMemoir(user));

        MemoirCreateResponseDto responseDto = MemoirCreateResponseDto.builder()
                .memoir_id(newMemoir.getId())
                .build();
        return responseDto;
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
                            .created_at(memoir.getCreatedAt())
                            .memoir_like_id(memoirLike.getMemoir_like_id())
                            .build();
                })
                .collect(Collectors.toList());

        return responseDtos;
    }

    @Transactional
    public MemoirUpdateResponseDto updateMemoir(Long memoirId, MemoirUpdateRequestDto requestDto) {
        Memoir memoir = memoirRepository.findById(memoirId)
                .orElseThrow(() -> new RuntimeException("Memoir not found with id: " + memoirId));

        memoir.updateMemoir(requestDto);
        Memoir updateMemoir = memoirRepository.save(memoir);

        return MemoirUpdateResponseDto.builder()
                .memoir_id(updateMemoir.getId())
                .build();
    }

    @Transactional
    public void deleteMemoir(Long memoirId) {
        if (!memoirRepository.existsById(memoirId)) {
            throw new RuntimeException("Memoir not found with id: " + memoirId);
        }
        memoirRepository.deleteById(memoirId);
    }
}
