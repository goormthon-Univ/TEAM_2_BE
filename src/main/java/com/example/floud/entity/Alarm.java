package com.example.floud.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Alarm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long alarm_id;

    @Column
    private LocalDateTime alarmDate;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "memoir_id")
    private Memoir memoir;


    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "memoir_like_id")
    private MemoirLike memoirLike;


}
