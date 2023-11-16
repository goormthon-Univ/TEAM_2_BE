package com.example.floud.repository;

import com.example.floud.entity.Memoir;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemoirRepository extends JpaRepository<Memoir, Long> {

    List<Memoir> findByIdIn(List<Long> memori_idxs);

    Optional<Memoir> findById(Long memoirId);

    List<Memoir> findAll();

    void deleteById(Long memoirId);

}