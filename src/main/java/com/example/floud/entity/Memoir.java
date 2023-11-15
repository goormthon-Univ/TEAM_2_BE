package com.example.floud.entity;

<<<<<<< HEAD
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Memoir {
    @Id
    @GeneratedValue
    private Long memoir_id;

    @Column(length = 50)
    private String title;

    @Column
    private String memoir_keep;

    @Column
    private String memoir_prob;

    @Column
    private String memoir_try;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User users;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate created_at;


=======
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "memoirs")
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
>>>>>>> origin/main
}
