package com.example.floud.service;

import com.example.floud.dto.request.hashtag.HashtagDto;
import com.example.floud.dto.request.user.LoginRequestDto;
import com.example.floud.dto.request.user.MainRequestDto;
import com.example.floud.dto.request.user.SignupRequestDto;
import com.example.floud.dto.response.user.LoginResponseDto;
import com.example.floud.dto.response.user.MainResponseDto;
import com.example.floud.dto.response.user.SignupResponseDto;

import com.example.floud.entity.Hashtag;
import com.example.floud.entity.Memoir;
import com.example.floud.entity.User;
import com.example.floud.exception.CustomException;
import com.example.floud.repository.HashtagRepository;
import com.example.floud.repository.MemoirRepository;
import com.example.floud.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.ArrayList;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final MemoirRepository memoirRepository;
    private final HashtagRepository hashtagRepository;

    @Transactional
    public SignupResponseDto saveUser(SignupRequestDto requestDto){
        if (userRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new CustomException("이미 등록된 이메일입니다.", HttpStatus.BAD_REQUEST);
        }
        User newUser = userRepository.save(requestDto.toUser());

        System.out.println(newUser.getId());
        return SignupResponseDto.builder()
                .user_id(newUser.getId())
                .build();
    }

    @Transactional
    public LoginResponseDto loginUser(LoginRequestDto requestDto){
        User user = userRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        Long user_id = 0L;
        if(requestDto.getPassword().equals(user.getPassword()))
            user_id = user.getId();

        return LoginResponseDto.builder()
                .user_id(user_id)
                .build();
    }


    @Transactional
    public MainResponseDto getMain(Long user_id, MainRequestDto requestDto){
        User user = userRepository.findById(user_id)
                .orElseThrow(()-> new IllegalArgumentException("사용자를 찾을 수 없습니다. user_id = " + user_id));

        LocalDateTime nowTime = requestDto.getNowTime();
        LocalDateTime startOfDay = nowTime.withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime endOfDay = nowTime.withHour(23).withMinute(59).withSecond(59).withNano(999999999);

        //오늘 회고 여부
        Memoir memoir = memoirRepository.findByUser_IdAndCreatedAtBetween(user_id, startOfDay, endOfDay)
                .orElse(null);
        Long memoir_id; String title;
        if (memoir == null) { memoir_id = 0L; title = ""; }
        else { memoir_id = memoir.getId(); title = memoir.getTitle();}

        //어제 회고 여부
        LocalDateTime startOfYesterday = nowTime.minusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime endOfYesterday = nowTime.minusDays(1).withHour(23).withMinute(59).withSecond(59).withNano(999999999);
        Memoir yesterdayMemoir = memoirRepository.findByUser_IdAndCreatedAtBetween(user_id, startOfYesterday, endOfYesterday)
                .orElse(null);
        if(yesterdayMemoir==null) user.updateContinueDate(0);

        //상위 3개 해시태그
        List<HashtagDto> hashtags = findTopThreeHashtags(user_id,nowTime);

        return  MainResponseDto.builder()
                .user_id(user_id)
                .memoir_id(memoir_id)
                .title(title)
                .backColor(user.getBackColor())
                .continueDate(user.getContinueDate())
                .hashtagList(hashtags)
                .build();
    }

    public List<HashtagDto> findTopThreeHashtags(Long userId, LocalDateTime accessTime) {
        LocalDateTime firstDayOfMonth = accessTime.withDayOfMonth(1);
        List<Memoir> memoirs = memoirRepository.findByUserIdAndCreatedAtBetween(userId, firstDayOfMonth, accessTime);

        // 찾은 회고록에 연결된 해시태그 찾기
        List<Hashtag> hashtags = new ArrayList<>();
        for (Memoir memoir : memoirs) {
            hashtags.addAll(hashtagRepository.findByMemoir(memoir));
        }
        // tagNum 기준 내림차순
        hashtags.sort((o1, o2) -> o2.getTagNum().compareTo(o1.getTagNum()));

        // top3
        List<HashtagDto> topThreeHashtags = hashtags.stream()
                .limit(3)
                .map(hashtag -> HashtagDto.builder()
                        .tagContent(hashtag.getTagContent())
                        .tagNum(hashtag.getTagNum())
                        .build())
                .collect(Collectors.toList());
        return topThreeHashtags;
    }

}
