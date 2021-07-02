package com.academic.academeet.service;

import com.academic.academeet.domain.model.Schedule;
import com.academic.academeet.domain.repository.IScheduleRepository;
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
    private IScheduleRepository scheduleRepository;

    @Override
    public Page<Schedule> getAll(Pageable pageable) {
        return scheduleRepository.findAll(pageable);
    }

    @Override
    public Schedule getScheduleById(Long scheduleId) {
        return scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new ResourceNotFoundException("Schedule", "Id", scheduleId));
    }

    @Override
    public Schedule createSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    @Override
    public Schedule updateSchedule(Long scheduleId, Schedule scheduleDetails) {
        return scheduleRepository.findById(scheduleId).map(
                Schedule1 ->{
                    Schedule1.setStart_date(scheduleDetails.getStart_date());
                    Schedule1.setEnd_date(scheduleDetails.getEnd_date());
                    return scheduleRepository.save(Schedule1);
                }
        ).orElseThrow(() -> new ResourceNotFoundException("Schedule", "Id", scheduleId));
    }

    @Override
    public ResponseEntity<?> deleteSchedule(Long scheduleId) {
        return scheduleRepository.findById(scheduleId).
                map(student -> {
                    scheduleRepository.delete(student);
                    return ResponseEntity.ok().build(); })
                .orElseThrow(() -> new ResourceNotFoundException("Schedule", "Id", scheduleId));
    }
}
