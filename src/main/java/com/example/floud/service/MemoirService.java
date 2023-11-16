package com.example.floud.service;

import com.example.floud.dto.request.MemoirRequestDto;
import com.example.floud.dto.response.MemoirResponseDto;
import com.example.floud.entity.Memoir;
import com.example.floud.entity.User;
import com.example.floud.repository.MemoirRepository;
import com.example.floud.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemoirService {

    private final MemoirRepository memoirRepository;
    private final UserRepository userRepository;

    @Transactional
    public MemoirResponseDto createMemoir(MemoirRequestDto requestDto) {
        Long user_id = requestDto.getUser_id();

        User user = userRepository.findById(user_id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + user_id));

        Memoir newMemoir = memoirRepository.save(requestDto.toMemoir(user));

        MemoirResponseDto responseDto = MemoirResponseDto.builder()
                .memoir_id(newMemoir.getId())
                .build();
        return responseDto;
    }



}
