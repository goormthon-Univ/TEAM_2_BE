package com.example.floud.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LoginRequestDto {

    @NotBlank
    private Long userId;

    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    private String loginId;

    @NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
    private String password;


}
