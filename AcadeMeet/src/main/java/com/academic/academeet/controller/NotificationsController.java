package com.academic.academeet.controller;

import com.academic.academeet.domain.model.Notification;
import com.academic.academeet.domain.service.NotificationService;
import com.academic.academeet.domain.service.NotificationTypeService;
import com.academic.academeet.resource.NotificationResource;
import com.academic.academeet.resource.SaveNotificationResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class NotificationsController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private ModelMapper mapper;

    @Operation(summary = "Add Notification"
            , description = "Add Notification By Notification Type Id"
            , tags = {"notifications"})
    @PostMapping("/notifications_type/{notificationsTypeId}/notifications")
    public NotificationResource createNotification(@PathVariable Long notificationTypeId, @Valid @RequestBody SaveNotificationResource resource) {
        Notification notification = convertToEntity(resource);
        return convertToResource(notificationService.saveNotification(notificationTypeId, notification));
    }

    @Operation(summary = "Get Notification"
            , description = "Get Notification By Id"
            , tags = {"notifications"})
    @GetMapping("/notifications/{id}")
    public NotificationResource getNotificationById(@PathVariable Long id) {
        Notification notification = notificationService.getById(id);
        return convertToResource(notification);
    }

    @Operation(summary = "Update Notification"
            , description = "Update Notification By Id"
            , tags = {"notifications"})
    @PutMapping("/notifications/{id}")
    public NotificationResource updateNotification(@PathVariable Long id, @Valid @RequestBody SaveNotificationResource resource) {
        Notification notification = convertToEntity(resource);
        return convertToResource(notificationService.updateNotification(id, notification));
    }

    @Operation(summary = "Delete Notification"
            , description = "Delete Notification By Id"
            , tags = {"notifications"})
    @DeleteMapping("/notifications/{id}")
    public ResponseEntity<?> deleteNotification(@PathVariable Long id) {
        notificationService.deleteNotification(id);
        return ResponseEntity.ok().build();
    }


    public Notification convertToEntity(SaveNotificationResource resource){
        return mapper.map(resource, Notification.class);
    }
    public NotificationResource convertToResource(Notification entity){
        return mapper.map(entity, NotificationResource.class);
    }
}
