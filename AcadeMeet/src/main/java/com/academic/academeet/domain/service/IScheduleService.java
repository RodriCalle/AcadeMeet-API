package com.academic.academeet.domain.service;

import com.academic.academeet.domain.model.LessonType;
import com.academic.academeet.domain.model.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface IScheduleService {
    Schedule createSchedule(Schedule schedule);
    Schedule updateSchedule(Long scheduleId, Schedule scheduleDetails);
    Schedule getScheduleById(Long scheduleId);
    ResponseEntity<?> deleteSchedule(Long scheduleId);
    Page<Schedule> getAll(Pageable pageable);

}
