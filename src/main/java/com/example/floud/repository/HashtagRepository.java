package com.example.floud.repository;

import com.example.floud.entity.Hashtag;
import com.example.floud.entity.Memoir;
import com.example.floud.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface HashtagRepository extends JpaRepository<Hashtag, Long> {
    boolean existsByMemoir_UsersAndTagContentAndMemoir_CreatedAtIsAfter(Users users, String tagContent, LocalDateTime date);
    Hashtag findByMemoir_UsersAndTagContent(Users users, String tagContent);
    List<Hashtag> findByMemoir(Memoir memoir);
}
