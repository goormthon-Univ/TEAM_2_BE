package com.example.floud.service;

import com.example.floud.dto.request.MemoirCreateRequestDto;
import com.example.floud.dto.request.MemoirUpdateRequestDto;
import com.example.floud.dto.response.MemoirCreateResponseDto;
import com.example.floud.dto.response.MemoirUpdateResponseDto;
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
