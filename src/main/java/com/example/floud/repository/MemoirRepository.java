package com.example.floud.repository;

import com.example.floud.entity.Memoir;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MemoirRepository extends JpaRepository<Memoir, Long> {

    List<Memoir> findByUserIdAndCreatedAtBetween(Long userId, LocalDateTime startDate, LocalDateTime endDate);
    Optional<Memoir> findByUser_IdAndCreatedAtBetween(Long userId, LocalDateTime startDateTime, LocalDateTime endDateTime);

//    Optional<Memoir> findById(Long memoirId);
//    List<Memoir> findAll();
//    void deleteById(Long memoirId);
    @Query("SELECT m.id FROM Memoir m WHERE m.user.id <> :userId")
    List<Long> findAllIdsByUserIdNot(@Param("userId") Long user_id);
}