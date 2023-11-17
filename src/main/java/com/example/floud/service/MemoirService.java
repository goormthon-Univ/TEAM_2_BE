package com.example.floud.service;

import com.example.floud.dto.request.memoir.MemoirCreateRequestDto;
import com.example.floud.dto.request.memoir.MemoirUpdateRequestDto;
import com.example.floud.dto.response.memoir.MemoirAnonymousResponseDto;
import com.example.floud.dto.response.memoir.MemoirCreateResponseDto;
import com.example.floud.dto.response.memoir.MemoirGetOneResponseDto;
import com.example.floud.dto.response.memoir.MemoirUpdateResponseDto;
import com.example.floud.entity.Memoir;
import com.example.floud.entity.User;
import com.example.floud.repository.MemoirLikeRepository;
import com.example.floud.repository.MemoirRepository;
import com.example.floud.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

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


    @Transactional(readOnly = true)
    public MemoirGetOneResponseDto getOneMemoir(Long memoir_id) {
        Memoir oneMemoir = memoirRepository.findById(memoir_id)
                .orElseThrow(() -> new RuntimeException("해당 회고 정보가 존재하지 않습니다. memoir_id = " + memoir_id));

        return MemoirGetOneResponseDto.builder()
                .user_id(oneMemoir.getUser().getId())
                .title(oneMemoir.getTitle())
                .place(oneMemoir.getPlace())
                .memoirKeep(oneMemoir.getMemoirKeep())
                .memoirProblem(oneMemoir.getMemoirProblem())
                .memoirTry(oneMemoir.getMemoirTry())
                .createdAt(LocalDateTime.parse(oneMemoir.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))))
                .build();
    }

    @Transactional(readOnly = true)
    public MemoirAnonymousResponseDto getAnonymousMemoir(Long user_id) {
        List<Long> memoirIds = memoirRepository.findAllIdsByUserIdNot(user_id);
        if (memoirIds.isEmpty()) {
            throw new RuntimeException("No memoirs available for anonymous viewing.");
        }

        Random random = new Random();
        Long randomMemoirId = memoirIds.get(random.nextInt(memoirIds.size()));
        Memoir memoir = memoirRepository.findById(randomMemoirId)
                .orElseThrow(() -> new RuntimeException("Memoir not found with id: " + randomMemoirId));

        return MemoirAnonymousResponseDto.builder()
                .user_id(memoir.getUser().getId())
                .title(memoir.getTitle())
                .place(memoir.getPlace())
                .memoirKeep(memoir.getMemoirKeep())
                .memoirProblem(memoir.getMemoirProblem())
                .memoirTry(memoir.getMemoirTry())
                .createdAt(LocalDateTime.parse(memoir.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))))
                .build();
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
