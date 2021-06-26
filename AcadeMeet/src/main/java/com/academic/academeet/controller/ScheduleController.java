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
    private IScheduleService IScheduleService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/schedules")
    public Page<ScheduleResource> getSchedules(Pageable pageable) {
        List<ScheduleResource> schools = IScheduleService.getAllSchedules(pageable)
                .getContent().stream().map(this::convertToResource).
                        collect(Collectors.toList());
        int schoolCount = schools.size();
        return new PageImpl<>(schools, pageable, schoolCount);
    }

    @GetMapping("/schedules/{id}")
    public ScheduleResource getScheduleById(@PathVariable Long id) {
        return convertToResource(IScheduleService.getScheduleById(id));
    }

    @PostMapping("/schedules")
    public ScheduleResource createSchedule(@RequestBody SaveScheduleResource resource) {
        return convertToResource(IScheduleService.saveSchedule(convertToEntity(resource)));
    }


    @PutMapping("/schedule/{id}")
    public ScheduleResource updateSchedule(@PathVariable Long id, @Valid @RequestBody SaveScheduleResource resource) {
        return convertToResource(IScheduleService.updateSchedule(id,convertToEntity(resource)));
    }

    @DeleteMapping("/schedule/{id}")
    public ResponseEntity<?> deleteSchedule(@PathVariable Long id) {
        return IScheduleService.deleteSchedule(id);
    }

    private Schedule convertToEntity(SaveScheduleResource resource) {
        return mapper.map(resource, Schedule.class);
    }
    private ScheduleResource convertToResource(Schedule entity) {
        return mapper.map(entity, ScheduleResource.class);
    }
}
