package com.academic.academeet.controller;

import com.academic.academeet.domain.model.Notification;
import com.academic.academeet.domain.service.INotificationService;
import com.academic.academeet.domain.service.INotificationTypeService;
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
public class NotificationController {

    @Autowired
    private INotificationService notificationService;

    @Autowired
    private INotificationTypeService notificationTypeService;

    @Autowired
    private ModelMapper mapper;

    public Notification convertToEntity(SaveNotificationResource resource){
        return mapper.map(resource, Notification.class);
    }
    public NotificationResource convertToResource(Notification entity){
        return mapper.map(entity, NotificationResource.class);
    }

    @Operation(summary = "Add Notification"
            , description = "Add Notification By Notification Type Id"
            , tags = {"notifications"})
    @PostMapping("/notification_types/{notificationTypeId}/notifications")
    public NotificationResource createNotification(@PathVariable Long notificationTypeId, @Valid @RequestBody SaveNotificationResource resource) {
        Notification notification = convertToEntity(resource);
        return convertToResource(notificationService.createNotification(notificationTypeId, notification));
    }

    @Operation(summary = "Get Notification"
            , description = "Get Notification By Id"
            , tags = {"notifications"})
    @GetMapping("/notifications/{notificationId}")
    public NotificationResource getNotificationById(@PathVariable Long notificationId) {
        Notification notification = notificationService.getById(notificationId);
        return convertToResource(notification);
    }

    @Operation(summary = "Update Notification"
            , description = "Update Notification By Id"
            , tags = {"notifications"})
    @PutMapping("/notification_types/{notificationTypeId}/notifications/{notificationId}")
    public NotificationResource updateNotification(@PathVariable Long notificationTypeId, @PathVariable Long notificationId, @Valid @RequestBody SaveNotificationResource resource) {
        Notification notification = convertToEntity(resource);
        return convertToResource(notificationService.updateNotification(notificationTypeId, notificationId, notification));
    }

    @Operation(summary = "Delete Notification"
            , description = "Delete Notification By Id"
            , tags = {"notifications"})
    @DeleteMapping("/notifications/{notificationId}")
    public ResponseEntity<?> deleteNotification(@PathVariable Long notificationId) {
        notificationService.deleteNotification(notificationId);
        return ResponseEntity.ok().build();
    }
}
