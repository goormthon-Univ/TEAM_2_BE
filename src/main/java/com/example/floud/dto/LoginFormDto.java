package com.example.floud.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@Getter
public class LoginFormDto {

    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    private String loginId;

    @NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
    private String password;

}
