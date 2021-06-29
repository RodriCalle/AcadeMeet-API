package com.academic.academeet.controller;

import com.academic.academeet.domain.model.NotificationType;
import com.academic.academeet.domain.service.INotificationTypeService;
import com.academic.academeet.resource.*;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class NotificationTypeController {

    @Autowired
    private INotificationTypeService notificationTypeService;

    @Autowired
    private ModelMapper mapper;

    public NotificationType convertToEntity(SaveNotificationTypeResource resource){
        return mapper.map(resource, NotificationType.class);
    }
    public NotificationTypeResource convertToResource(NotificationType entity){
        return mapper.map(entity, NotificationTypeResource.class);
    }

    @Operation(summary = "Add Notification Type"
            , description = "Add Notification Type"
            , tags = {"notification_types"})
    @PostMapping("/notification_types")
    public NotificationTypeResource createNotificationType(@Valid @RequestBody SaveNotificationTypeResource resource) {
        NotificationType notificationType = convertToEntity(resource);
        return convertToResource(notificationTypeService.createNotificationType(notificationType));
    }

    @Operation(summary = "Update Notification Type"
            , description = "Update Notification Type By Id"
            , tags = {"notification_types"})
    @PutMapping("/notification_types/{notificationTypeId}")
    public NotificationTypeResource updateNotificationType(@PathVariable Long notificationTypeId, @Valid @RequestBody SaveNotificationTypeResource resource) {
        NotificationType notificationType = convertToEntity(resource);
        return convertToResource(notificationTypeService.updateNotificationType(notificationTypeId, notificationType));
    }

    @Operation(summary = "Get Notification Type"
            , description = "Get Notification Type By Id"
            , tags = {"notification_types"})
    @GetMapping("/notification_types/{notificationTypeId}")
    public NotificationTypeResource getNotificationTypeById(@PathVariable Long notificationTypeId) {
        NotificationType notificationType = notificationTypeService.getById(notificationTypeId);
        return convertToResource(notificationType);
    }

    @Operation(summary = "Delete Notification Type"
            , description = "Delete Notification Type By Id"
            , tags = {"notification_types"})
    @DeleteMapping("/notification_types/{notificationTypeId}")
    public ResponseEntity<?> deleteNotificationType(@PathVariable Long notificationTypeId) {
        notificationTypeService.deleteNotificationType(notificationTypeId);
        return ResponseEntity.ok().build();
    }
}
