package com.academic.academeet.controller;

import com.academic.academeet.domain.model.NotificationType;
import com.academic.academeet.domain.service.NotificationTypeService;
import com.academic.academeet.resource.*;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class NotificationTypesController {

    @Autowired
    private NotificationTypeService notificationTypeService;

    @Autowired
    private ModelMapper mapper;

    @Operation(summary = "Add Notification Type"
            , description = "Add Notification Type"
            , tags = {"notification_types"})
    @PostMapping("/notifications_type")
    public NotificationTypeResource createNotificationType(@Valid @RequestBody SaveNotificationTypeResource resource) {
        NotificationType notificationType = convertToEntity(resource);
        return  convertToResource(notificationTypeService.saveNotificationType(notificationType));
    }

    @Operation(summary = "Get Notification Type"
            , description = "Get Notification Type By Id"
            , tags = {"notification_types"})
    @GetMapping("/notifications_type/{id}")
    public NotificationTypeResource getNotificationTypeById(@PathVariable Long id) {
        NotificationType notificationType = notificationTypeService.getById(id);
        return convertToResource(notificationType);
    }

    @Operation(summary = "Update Notification Type"
            , description = "Update Notification Type By Id"
            , tags = {"notification_types"})
    @PutMapping("/notifications_type/{id}")
    public NotificationTypeResource updateNotificationType(@PathVariable Long id, @Valid @RequestBody SaveNotificationTypeResource resource) {
        NotificationType notificationType = convertToEntity(resource);
        return convertToResource(notificationTypeService.updateNotificationType(id, notificationType));
    }

    @Operation(summary = "Delete Notification Type"
            , description = "Delete Notification Type By Id"
            , tags = {"notification_types"})
    @DeleteMapping("/notifications_type/{id}")
    public ResponseEntity<?> deleteNotificationType(@PathVariable Long id) {
        notificationTypeService.deleteNotificationType(id);
        return ResponseEntity.ok().build();
    }


    public NotificationType convertToEntity(SaveNotificationTypeResource resource){
        return mapper.map(resource, NotificationType.class);
    }
    public NotificationTypeResource convertToResource(NotificationType entity){
        return mapper.map(entity, NotificationTypeResource.class);
    }

}
