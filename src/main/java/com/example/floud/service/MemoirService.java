package com.example.floud.service;

import com.example.floud.dto.request.memoir.MemoirCreateRequestDto;
import com.example.floud.dto.request.memoir.MemoirUpdateRequestDto;
import com.example.floud.dto.response.memoir.MemoirAnonymousResponseDto;
import com.example.floud.dto.response.memoir.MemoirCreateResponseDto;

import com.example.floud.dto.response.memoir.MemoirGetOneResponseDto;
import com.example.floud.dto.response.memoir.MemoirUpdateResponseDto;
import com.example.floud.entity.Hashtag;
import com.example.floud.entity.Memoir;
import com.example.floud.entity.Users;
import com.example.floud.repository.HashtagRepository;
import com.example.floud.repository.MemoirLikeRepository;
import com.example.floud.repository.MemoirRepository;
import com.example.floud.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class MemoirService {

    private final MemoirRepository memoirRepository;
    private final MemoirLikeRepository memoirLikeRepository;
    private final UserRepository userRepository;
    private final HashtagRepository hashtagRepository;

    @Transactional
    public MemoirCreateResponseDto createMemoir(MemoirCreateRequestDto requestDto) {
        Long user_id = requestDto.getUser_id();

        Users users = userRepository.findById(user_id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + user_id));

        Memoir newMemoir = memoirRepository.save(requestDto.toMemoir(users));

        MemoirCreateResponseDto responseDto = MemoirCreateResponseDto.builder()
                .memoir_id(newMemoir.getId())
                .build();

        int backColor = users.getBackColor();
        if (backColor==4) backColor=1;
        else backColor++;

        users.updateColor(backColor); users.updateContinueDate(users.getContinueDate()+1);

        return responseDto;
    }

    @Transactional(readOnly = true)
    public MemoirGetOneResponseDto getOneMemoir(Long memoir_id) {
        Memoir oneMemoir = memoirRepository.findById(memoir_id)
                .orElseThrow(() -> new RuntimeException("해당 회고 정보가 존재하지 않습니다. memoir_id = " + memoir_id));

        Hibernate.initialize(oneMemoir.getCommentList());
        int commentCount = oneMemoir.getCommentList() != null ? oneMemoir.getCommentList().size() : 0;
        int likeCount = memoirLikeRepository.countByMemoirId(memoir_id); // 좋아요 개수 계산

        List<Hashtag> Hashtags = hashtagRepository.findByMemoirId(memoir_id);
        String hashtag1 = Hashtags.size() > 0 ? Hashtags.get(0).getTagContent() : "";
        String hashtag2 = Hashtags.size() > 1 ? Hashtags.get(1).getTagContent() : "";
        String hashtag3 = Hashtags.size() > 2 ? Hashtags.get(2).getTagContent() : "";


        return MemoirGetOneResponseDto.builder()
                .user_id(oneMemoir.getUsers().getId())
                .title(oneMemoir.getTitle())
                .place(oneMemoir.getPlace())
                .memoirKeep(oneMemoir.getMemoirKeep())
                .memoirProblem(oneMemoir.getMemoirProblem())
                .memoirTry(oneMemoir.getMemoirTry())
                .likeCount(likeCount)
                .commentCount(commentCount)
                .commentList(oneMemoir.getCommentList())
                .createdAt(LocalDateTime.parse(oneMemoir.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))))
                .hashtag1(hashtag1)
                .hashtag2(hashtag2)
                .hashtag3(hashtag3)
                .build();
    }

    @Transactional(readOnly = true)
    public List<MemoirAnonymousResponseDto> getAnonymousMemoir(Long user_id) {
        List<Long> memoirIds = memoirRepository.findAllIdsByUsersIdNot(user_id);
        if (memoirIds.isEmpty()) {
            throw new RuntimeException("No memoirs available for anonymous viewing.");
        }

        Random random = new Random();
        List<MemoirAnonymousResponseDto> memoirDtos = new ArrayList<>();
        while (memoirDtos.size() < 5) {
            Long randomMemoirId = memoirIds.get(random.nextInt(memoirIds.size()));
            memoirIds.remove(randomMemoirId); // avoid duplicate selection

            Memoir memoir = memoirRepository.findById(randomMemoirId)
                    .orElseThrow(() -> new RuntimeException("Memoir not found with id: " + randomMemoirId));

            memoir.getCommentList().forEach(comment -> Hibernate.initialize(comment.getAlarmList()));
            int commentCount = memoir.getCommentList() != null ? memoir.getCommentList().size() : 0;
            int likeCount = memoirLikeRepository.countByMemoirId(randomMemoirId); // 좋아요 개수 계산

            memoirDtos.add(
                    MemoirAnonymousResponseDto.builder()
                            .user_id(memoir.getUsers().getId())
                            .title(memoir.getTitle())
                            .place(memoir.getPlace())
                            .memoirKeep(memoir.getMemoirKeep())
                            .memoirProblem(memoir.getMemoirProblem())
                            .memoirTry(memoir.getMemoirTry())
                            .likeCount(likeCount)
                            .commentCount(commentCount)
                            .commentList(memoir.getCommentList())
                            .createdAt(LocalDateTime.parse(memoir.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))))
                            .build()
            );
        }
        return memoirDtos;
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
