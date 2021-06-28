package com.academic.academeet.controller;

import com.academic.academeet.domain.model.Schedule;
import com.academic.academeet.domain.model.TypeOfGrade;
import com.academic.academeet.domain.service.ScheduleService;
import com.academic.academeet.domain.service.TypeOfGradeService;
import com.academic.academeet.resource.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    private ScheduleService scheduleService;

    @Autowired
    private ModelMapper mapper;

    @Operation(summary = "Get Schedules", description = "Get All Schedules by Pages", tags = {"shedules"})
    @ApiResponse(
            responseCode = "200",
            description = "All Schedules returned",
            content = @Content(mediaType = "application/json")
    )
    @GetMapping("/schedules")
    public Page<ScheduleResource> getSchedules(Pageable pageable) {
        List<ScheduleResource> schools = scheduleService.getAllSchedules(pageable)
                .getContent().stream().map(this::convertToResource).
                        collect(Collectors.toList());
        int schoolCount = schools.size();
        return new PageImpl<>(schools, pageable, schoolCount);
    }

    @Operation(summary = "Get Schedule by id", description = "Get a Schedule given an id", tags = {"shedules"})
    @ApiResponse(
            responseCode = "200",
            description = "Return a Schedule",
            content = @Content(mediaType = "application/json")
    )
    @GetMapping("/schedules/{id}")
    public ScheduleResource getScheduleById(@PathVariable Long id) {
        return convertToResource(scheduleService.getScheduleById(id));
    }

    @Operation(summary = "Save a Schedule", description = "Save a Schedule", tags = {"shedules"})
    @ApiResponse(
            responseCode = "200",
            description = "Return the Schedule saved",
            content = @Content(mediaType = "application/json")
    )
    @PostMapping("/schedules")
    public ScheduleResource createSchedule(@RequestBody SaveScheduleResource resource) {
        return convertToResource(scheduleService.saveSchedule(convertToEntity(resource)));
    }

    @Operation(summary = "Update a Schedule", description = "Update a Schedule given an id", tags = {"shedules"})
    @ApiResponse(
            responseCode = "200",
            description = "Return a Schedule updated",
            content = @Content(mediaType = "application/json")
    )
    @PutMapping("/schedule/{id}")
    public ScheduleResource updateSchedule(@PathVariable Long id, @Valid @RequestBody SaveScheduleResource resource) {
        return convertToResource(scheduleService.updateSchedule(id,convertToEntity(resource)));
    }

    @Operation(summary = "Delete a Schedule", description = "remove a Schedule given an id", tags = {"shedules"})
    @ApiResponse(
            responseCode = "200",
            description = "Return ResponseEntity",
            content = @Content(mediaType = "application/json")
    )
    @DeleteMapping("/schedule/{id}")
    public ResponseEntity<?> deleteSchedule(@PathVariable Long id) {
        return scheduleService.deleteSchedule(id);
    }

    private Schedule convertToEntity(SaveScheduleResource resource) {
        return mapper.map(resource, Schedule.class);
    }
    private ScheduleResource convertToResource(Schedule entity) {
        return mapper.map(entity, ScheduleResource.class);
    }
}
