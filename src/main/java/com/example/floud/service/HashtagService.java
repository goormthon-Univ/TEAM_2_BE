package com.example.floud.service;

import com.example.floud.dto.request.hashtag.HashtagSaveRequestDto;
import com.example.floud.dto.request.memoir.MemoirCreateRequestDto;
import com.example.floud.dto.response.memoir.MemoirCreateResponseDto;
import com.example.floud.entity.Hashtag;
import com.example.floud.entity.Memoir;
import com.example.floud.entity.User;
import com.example.floud.repository.HashtagRepository;
import com.example.floud.repository.MemoirRepository;
import com.example.floud.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class HashtagService {
    private final UserRepository userRepository;
    private final HashtagRepository hashtagRepository;
    private final MemoirRepository memoirRepository;


    ///Ater
    @Transactional
    public void createHashtag(MemoirCreateRequestDto requestDto, Long memoir_id){
        Long user_id = requestDto.getUser_id();
        List<String> hashtagList = requestDto.getHashtag();

        for(String tag : hashtagList) {
            if(tag != null && !tag.isEmpty()) {
                createOrUpdateHashtag(tag, memoir_id, requestDto);
            }
        }
    }

    private void createOrUpdateHashtag(String tag, Long memoir_id, MemoirCreateRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getUser_id())
                .orElseThrow(()-> new IllegalArgumentException("해당 회원 정보가 존재하지 않습니다. user_id = "+ requestDto.getUser_id()));
        Memoir memoir = memoirRepository.findById(memoir_id)
                .orElseThrow(()-> new IllegalArgumentException("해당 회원 정보가 존재하지 않습니다. user_id = "+ memoir_id));

        //해당 유저가 한달간 다른 회고록(Memoir) 그 해시태그를 적은 적이 있는 지 확인
        LocalDateTime oneMonthAgo = LocalDateTime.now().minusMonths(1);
        boolean hasUsedTag = hashtagRepository.existsByMemoir_UserAndTagContentAndMemoir_CreatedAtIsAfter(
                user, tag, oneMonthAgo);

        if(hasUsedTag) {
            //이미 있다면 tagNum + 1
            Hashtag existingHashtag = hashtagRepository.findByMemoir_UserAndTagContent(user, tag);
            existingHashtag.increaseTagNum();
        } else {
            //없다면 새로 생성
            HashtagSaveRequestDto saveRequestDto = HashtagSaveRequestDto.builder()
                    .hashtag(tag)
                    .build();
            hashtagRepository.save(saveRequestDto.toHashtag(memoir));
        }
    }

}