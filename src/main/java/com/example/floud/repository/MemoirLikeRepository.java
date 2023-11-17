package com.example.floud.repository;

import com.example.floud.entity.MemoirLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MemoirLikeRepository extends JpaRepository<MemoirLike, Long> {
    List<MemoirLike> findByUserIdAndLikeDateBetween(Long userId, LocalDate startDate, LocalDate endDate);

    int countByMemoirId(Long memoir_id);


}
