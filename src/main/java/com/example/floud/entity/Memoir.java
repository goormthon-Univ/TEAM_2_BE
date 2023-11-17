package com.example.floud.entity;

import com.example.floud.dto.request.memoir.MemoirUpdateRequestDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "memoir")
public class Memoir {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memoir_id", unique = true, nullable = false)
    private Long id;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(length = 50, nullable = false)
    private String place;

    @Column(columnDefinition = "TEXT")
    private String memoirKeep;

    @Column(columnDefinition = "TEXT")
    private String memoirProblem;

    @Column(columnDefinition = "TEXT")
    private String memoirTry;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @JsonManagedReference
    @OneToMany(mappedBy = "memoir", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> commentList = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "memoir", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MemoirLike> memoirLikeList = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "memoir", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Alarm> alarmList = new ArrayList<>();


    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @Builder
    public Memoir(User user, String title, String place, String memoirKeep, String memoirProblem, String memoirTry) {
        this.user = user;
        this.title = title;
        this.place = place;
        this.memoirKeep = memoirKeep;
        this.memoirProblem = memoirProblem;
        this.memoirTry = memoirTry;
    }

    public void updateMemoir(MemoirUpdateRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.place = requestDto.getPlace();
        this.memoirKeep = requestDto.getMemoirKeep();
        this.memoirProblem = requestDto.getMemoirProblem();
        this.memoirTry = requestDto.getMemoirTry();
    }
}
