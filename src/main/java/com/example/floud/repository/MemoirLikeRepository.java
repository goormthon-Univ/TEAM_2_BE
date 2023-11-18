package com.example.floud.repository;

import com.example.floud.entity.MemoirLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface MemoirLikeRepository extends JpaRepository<MemoirLike, Long> {
    int countByMemoirId(Long memoir_id);
    @Query("SELECT ml FROM MemoirLike ml WHERE ml.users.id = :userId AND ml.memoir.createdAt BETWEEN :startDate AND :endDate")
    List<MemoirLike> findByUserIdAndMemoirCreatedAtBetween(@Param("userId") Long userId,
                                                           @Param("startDate") LocalDateTime startDate,
                                                           @Param("endDate") LocalDateTime endDate);

}
