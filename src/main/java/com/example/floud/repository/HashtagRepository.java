package com.example.floud.repository;

import com.example.floud.entity.Hashtag;
import com.example.floud.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface HashtagRepository extends JpaRepository<Hashtag, Long> {
    boolean existsByMemoir_UserAndTagContentAndMemoir_CreatedAtIsAfter(User user, String tagContent, LocalDateTime date);
    Hashtag findByMemoir_UserAndTagContent(User user, String tagContent);


}
