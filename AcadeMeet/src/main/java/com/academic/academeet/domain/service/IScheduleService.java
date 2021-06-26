package com.academic.academeet.domain.service;

import com.academic.academeet.domain.model.LessonType;
import com.academic.academeet.domain.model.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface IScheduleService {
    Page<Schedule> getAllSchedules(Pageable pageable);
    Schedule getScheduleById(Long id);
    Schedule saveSchedule(Schedule schedule);
    Schedule updateSchedule(Long scheduleId, Schedule schedule);
    ResponseEntity<?> deleteSchedule(Long id);
}
