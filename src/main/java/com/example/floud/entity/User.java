package com.example.floud.entity;

import com.example.floud.dto.request.SignupRequestDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", unique = true, nullable = false)
    private Long id;

    @Column(length = 50, name = "login_id", nullable = false, unique = true)
    private String loginId;

    @Column(length = 10, name = "username", nullable = false)
    private String username;

    @Column(length = 500, nullable = false)
    private String password;

    @Column(length = 50, nullable = false)
    private String email;

    @Column(length = 13, nullable = false)
    private String phone;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate birth;

    @JsonManagedReference
    @OneToMany(mappedBy = "user",  cascade = CascadeType.ALL)
    private List<Memoir> memoirList = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Comment> commentList = new ArrayList<>();

    @Builder
    public User(String loginId, String username, String password, String email, String phone, LocalDate birth) {
        this.loginId = loginId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.birth = birth;
    }

//    public static User createUser(SignupRequestDto signupRequestDto, PasswordEncoder passwordEncoder) {
//        User user = User.builder()
//                .loginId(signupRequestDto.getLoginId())
//                .password(signupRequestDto.getPassword())
//                .username(signupRequestDto.getUsername())
//                .email(signupRequestDto.getEmail())
//                .phone(signupRequestDto.getPhone())
//                .birth(signupRequestDto.getBirth())
//                .build();
//        return user;
//    }
}
