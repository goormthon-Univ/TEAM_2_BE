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

    List<Memoir> findByUsersIdAndCreatedAtBetween(Long userId, LocalDateTime startDate, LocalDateTime endDate);
    Optional<Memoir> findByUsers_IdAndCreatedAtBetween(Long userId, LocalDateTime startDateTime, LocalDateTime endDateTime);

    @Query("SELECT m.id FROM Memoir m WHERE m.users.id <> :userId")
    List<Long> findAllIdsByUsersIdNot(@Param("userId") Long user_id);
}