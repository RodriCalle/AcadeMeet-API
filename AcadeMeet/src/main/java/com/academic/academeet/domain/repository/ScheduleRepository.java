package com.academic.academeet.domain.repository;

import com.academic.academeet.domain.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
