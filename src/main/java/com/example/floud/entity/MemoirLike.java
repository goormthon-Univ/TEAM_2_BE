package com.example.floud.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MemoirLike {
    @Id
    @GeneratedValue
    private Long memoir_like_id;


    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(nullable = false)
    private LocalDateTime likeDate;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "memoir_id")
    private Memoir memoir;

    @JsonManagedReference
    @OneToMany(mappedBy = "memoirLike", cascade = CascadeType.ALL)
    private List<Alarm> alarmList = new ArrayList<>();
}
