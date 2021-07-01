package com.academic.academeet.controller;

import com.academic.academeet.domain.model.Schedule;
import com.academic.academeet.domain.service.IScheduleService;
import com.academic.academeet.resource.SaveScheduleResource;
import com.academic.academeet.resource.ScheduleResource;
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
    private IScheduleService scheduleService;

    @Autowired
    private ModelMapper mapper;

    private Schedule convertToEntity(SaveScheduleResource resource) {
        return mapper.map(resource, Schedule.class);
    }
    private ScheduleResource convertToResource(Schedule entity) {
        return mapper.map(entity, ScheduleResource.class);
    }
    @Operation(summary = "Save a Schedule", description = "Save a Schedule", tags = {"shedules"})
    @ApiResponse(
            responseCode = "200",
            description = "Return the Schedule saved",
            content = @Content(mediaType = "application/json")
    )
    @PostMapping("/schedules")
    public ScheduleResource createSchedule(@RequestBody SaveScheduleResource resource) {
        return convertToResource(scheduleService.createSchedule(convertToEntity(resource)));
    }
    @Operation(summary = "Update a Schedule", description = "Update a Schedule given an id", tags = {"shedules"})
    @ApiResponse(
            responseCode = "200",
            description = "Return a Schedule updated",
            content = @Content(mediaType = "application/json")
    )
    @PutMapping("/schedules/{scheduleId}")
    public ScheduleResource updateSchedule(@PathVariable Long scheduleId, @Valid @RequestBody SaveScheduleResource resource) {
        return convertToResource(scheduleService.updateSchedule(scheduleId,convertToEntity(resource)));
    }
    @Operation(summary = "Get Schedule by id", description = "Get a Schedule given an id", tags = {"shedules"})
    @ApiResponse(
            responseCode = "200",
            description = "Return a Schedule",
            content = @Content(mediaType = "application/json")
    )
    @GetMapping("/schedules/{scheduleId}")
    public ScheduleResource getScheduleById(@PathVariable Long scheduleId) {
        return convertToResource(scheduleService.getScheduleById(scheduleId));
    }
    @Operation(summary = "Delete a Schedule", description = "remove a Schedule given an id", tags = {"shedules"})
    @ApiResponse(
            responseCode = "200",
            description = "Return ResponseEntity",
            content = @Content(mediaType = "application/json")
    )
    @DeleteMapping("/schedules/{scheduleId}")
    public ResponseEntity<?> deleteSchedule(@PathVariable Long scheduleId) {
        return scheduleService.deleteSchedule(scheduleId);
    }
    @Operation(summary = "Get Schedules", description = "Get All Schedules by Pages", tags = {"shedules"})
    @ApiResponse(
            responseCode = "200",
            description = "All Schedules returned",
            content = @Content(mediaType = "application/json")
    )
    @GetMapping("/schedules")
    public Page<ScheduleResource> getSchedules(Pageable pageable) {
        List<ScheduleResource> schools = scheduleService.getAll(pageable)
                .getContent().stream().map(this::convertToResource).
                        collect(Collectors.toList());
        int schoolCount = schools.size();
        return new PageImpl<>(schools, pageable, schoolCount);
    }
}
