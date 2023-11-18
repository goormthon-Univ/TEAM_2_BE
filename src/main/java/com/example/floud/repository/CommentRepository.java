package com.example.floud.repository;

import com.example.floud.entity.Comment;
import com.example.floud.entity.MemoirLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByUsers_Id(Long userId);

}
