package com.example.floud.repository;

import com.example.floud.entity.Hashtag;
import com.example.floud.entity.Memoir;
import com.example.floud.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
public interface HashtagRepository extends JpaRepository<Hashtag, Long> {
    boolean existsByMemoir_UserAndTagContentAndMemoir_CreatedAtIsAfter(User user, String tagContent, LocalDateTime date);
    Hashtag findByMemoir_UserAndTagContent(User user, String tagContent);
//    @Query("SELECT h FROM Hashtag h WHERE h.memoir.user.id = :userId AND h.memoir.createdAt BETWEEN :startDateTime AND :endDateTime ORDER BY h.tagNum DESC")
//    List<Hashtag> findTop3ByUserIdAndCreatedAtBetween(@Param("userId") Long userId,
//                                                      @Param("startDateTime") LocalDateTime startDateTime,
//                                                      @Param("endDateTime") LocalDateTime endDateTime,
//                                                      Pageable pageable);
    List<Hashtag> findByMemoir(Memoir memoir);

    @Query("SELECT h FROM Hashtag h JOIN h.memoir m WHERE m.user.id = :userId AND m.createdAt BETWEEN :startDateTime AND :endDateTime ORDER BY h.tagNum DESC")
    Page<Hashtag> findTop3ByUserIdAndCreatedAtBetween(@Param("userId") Long userId, @Param("startDateTime") LocalDateTime startDateTime, @Param("endDateTime") LocalDateTime endDateTime, Pageable pageable);

}
