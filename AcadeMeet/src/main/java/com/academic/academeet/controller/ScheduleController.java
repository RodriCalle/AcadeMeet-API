package com.academic.academeet.controller;

import com.academic.academeet.domain.model.Schedule;
import com.academic.academeet.domain.service.IScheduleService;
import com.academic.academeet.resource.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ScheduleController {
    @Autowired
    private IScheduleService scheduleService;

    @Autowired
    private ModelMapper mapper;

    private Schedule convertToEntity(SaveScheduleResource resource) {
        return mapper.map(resource, Schedule.class);
    }
    private ScheduleResource convertToResource(Schedule entity) {
        return mapper.map(entity, ScheduleResource.class);
    }

    @PostMapping("/schedules")
    public ScheduleResource createSchedule(@RequestBody SaveScheduleResource resource) {
        return convertToResource(scheduleService.createSchedule(convertToEntity(resource)));
    }

    @PutMapping("/schedules/{scheduleId}")
    public ScheduleResource updateSchedule(@PathVariable Long scheduleId, @Valid @RequestBody SaveScheduleResource resource) {
        return convertToResource(scheduleService.updateSchedule(scheduleId,convertToEntity(resource)));
    }

    @GetMapping("/schedules/{scheduleId}")
    public ScheduleResource getScheduleById(@PathVariable Long scheduleId) {
        return convertToResource(scheduleService.getScheduleById(scheduleId));
    }

    @DeleteMapping("/schedules/{scheduleId}")
    public ResponseEntity<?> deleteSchedule(@PathVariable Long scheduleId) {
        return scheduleService.deleteSchedule(scheduleId);
    }

    @GetMapping("/schedules")
    public Page<ScheduleResource> getSchedules(Pageable pageable) {
        List<ScheduleResource> schools = scheduleService.getAll(pageable)
                .getContent().stream().map(this::convertToResource).
                        collect(Collectors.toList());
        int schoolCount = schools.size();
        return new PageImpl<>(schools, pageable, schoolCount);
    }
}
