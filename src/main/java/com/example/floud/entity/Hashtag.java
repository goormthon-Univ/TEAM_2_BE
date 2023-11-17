package com.example.floud.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Hashtag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hashtag_id;

    @Column
    private String tagContent;

    @Column
    private Long tagNum;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "memoir_id")
    private Memoir memoir;
    public void increaseTagNum() {
        this.tagNum += 1;
    }
}
