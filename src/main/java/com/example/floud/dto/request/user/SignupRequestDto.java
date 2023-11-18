package com.example.floud.dto.request.user;

import com.example.floud.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
public class SignupRequestDto {
//    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String username;

//    @NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
    private String password;

//    @NotEmpty(message = "이메일은 필수 입력 값입니다.")
    private String email;

    private String phone;

    private LocalDate birth;

    public Users toUser(){
        return Users.builder()
                .username(username)
                .email(email)
                .password(password)
                .phone(phone)
                .birth(birth)
                .backColor(1)
                .continueDate(0)
                .build();
    }
}
