package com.academic.academeet.service;

import com.academic.academeet.domain.model.Schedule;
import com.academic.academeet.domain.repository.ScheduleRepository;
import com.academic.academeet.domain.service.IScheduleService;
import com.academic.academeet.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ScheduleServiceImpl implements IScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public Page<Schedule> getAllSchedules(Pageable pageable) {
        return scheduleRepository.findAll(pageable);
    }

    @Override
    public Schedule getScheduleById(Long id) {
        return scheduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Schedule", "Id", id));
    }

    @Override
    public Schedule saveSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    @Override
    public Schedule updateSchedule(Long scheduleId, Schedule schedule) {
        return scheduleRepository.findById(scheduleId).map(
                Schedule1 ->{
                    Schedule1.setStart_date(schedule.getStart_date());
                    Schedule1.setEnd_date(schedule.getEnd_date());
                    return scheduleRepository.save(Schedule1);
                }
        ).orElseThrow(() -> new ResourceNotFoundException("Schedule", "Id", schedule));
    }

    @Override
    public ResponseEntity<?> deleteSchedule(Long id) {
        return scheduleRepository.findById(id).
                map(student -> {
                    scheduleRepository.delete(student);
                    return ResponseEntity.ok().build(); })
                .orElseThrow(() -> new ResourceNotFoundException("Schedule", "Id", id));
    }
}
