package com.example.floud.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comment {
    @Id
    @GeneratedValue
    private Long comment_id;

    @Column
    private String content;

    @Column
    private LocalDate created_at;

    @Column
    private Long parent_id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User users;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "memoir_id")
    private Memoir memoir;


}
