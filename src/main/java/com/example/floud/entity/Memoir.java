package com.example.floud.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @Column(columnDefinition = "TEXT")
    private String memoirKeep;

    @Column(columnDefinition = "TEXT")
    private String memoirProblem;

    @Column(columnDefinition = "TEXT")
    private String memoirTry;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @Builder
    public Memoir(User user, String title, String memoirKeep, String memoirProblem, String memoirTry) {
        this.user = user;
        this.title = title;
        this.memoirKeep = memoirKeep;
        this.memoirProblem = memoirProblem;
        this.memoirTry = memoirTry;
    }
}
