package com.example.floud.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
public class UserFormDto {
    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    private String loginId;

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String username;

    @NotEmpty(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String email;

    @NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
    private String password;

    @NotEmpty(message = "번호는 필수 입력 값입니다.")
    private String phone;

    @NotEmpty(message = "번호는 필수 입력 값입니다.")
    private LocalDate birth;

    @Builder
    public UserFormDto(String username, String email, String password, String phone, LocalDate birth) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.birth = birth;
    }
}
