package com.example.floud.entity;

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


}
