package com.example.floud.repository;

import com.example.floud.entity.Alarm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlarmRepository extends JpaRepository<Alarm, Long> {
    List<Alarm> findTop30ByUsersIdOrderByAlarmDateDesc(Long userId);
}
