package com.example.floud.repository;

import com.example.floud.entity.Memoir;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoirRepository extends JpaRepository<Memoir, Long> {
}
