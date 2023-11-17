package com.example.floud.controller;

import com.example.floud.dto.SuccessResponse;
import com.example.floud.dto.request.comment.CommentSaveRequestDto;
import com.example.floud.dto.request.user.LoginRequestDto;
import com.example.floud.dto.request.user.SignupRequestDto;
import com.example.floud.dto.response.comment.CommentSaveResponseDto;
import com.example.floud.dto.response.user.LoginResponseDto;
import com.example.floud.dto.response.user.SignupResponseDto;
import com.example.floud.entity.User;
import com.example.floud.exception.Success;
import com.example.floud.repository.UserRepository;
import com.example.floud.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    private SuccessResponse<SignupResponseDto> saveUser(@RequestBody SignupRequestDto requestDto){
        return SuccessResponse.success(Success.SAVE_USER_SUCCESS, userService.saveUser(requestDto));
    }

    @PostMapping("login")
    private SuccessResponse<LoginResponseDto> loginUser(@RequestBody LoginRequestDto requestDto){
        return SuccessResponse.success(Success.GET_USER_SUCCESS, userService.loginUser(requestDto));
    }


//    private JwtProvider jwtProvider;

    // 유저 ID로 사용자를 조회
//    @GetMapping("/{id}")
//    public ResponseEntity<User> getUserById(
//            @PathVariable Long id) {
//        return userService.getUserById(id)
//                .map(user -> ResponseEntity.ok(user))
//                .orElse(ResponseEntity.notFound().build());
//    }

//    @PostMapping("/signup")
//    public ResponseEntity<?> signup(
//            @RequestBody SignupRequestDto user) {
//        try {
//            ResponseEntity<?> registeredUser = userService.signup(user);
//            return ResponseEntity.ok(registeredUser);
//
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(e.getMessage());  // 오류 응답
//        }
//    }

//    @PostMapping("/login")
//    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
//        LoginResponseDto.Data data = userService.login(loginRequestDto.getLoginId(), loginRequestDto.getPassword());
//        Long userId = data.getUserId();
//        LoginResponseDto.Data responseData = new LoginResponseDto.Data(userId, data.getAccessToken(), data.getRefreshToken());
//        LoginResponseDto response = new LoginResponseDto("로그인 성공", responseData);
//
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }


}