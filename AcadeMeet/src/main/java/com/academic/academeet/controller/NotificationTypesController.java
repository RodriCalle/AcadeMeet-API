package com.academic.academeet.controller;

import com.academic.academeet.domain.model.NotificationType;
import com.academic.academeet.domain.service.NotificationTypeService;
import com.academic.academeet.resource.*;
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

    @PostMapping("/notifications_type")
    public NotificationTypeResource createNotificationType(@Valid @RequestBody SaveNotificationTypeResource resource) {
        NotificationType notificationType = convertToEntity(resource);
        return  convertToResource(notificationTypeService.saveNotificationType(notificationType));
    }

    @GetMapping("/notifications_type/{id}")
    public NotificationTypeResource getNotificationTypeById(@PathVariable Long id) {
        NotificationType notificationType = notificationTypeService.getById(id);
        return convertToResource(notificationType);
    }

    @PutMapping("/notifications_type/{id}")
    public NotificationTypeResource updateNotificationType(@PathVariable Long id, @Valid @RequestBody SaveNotificationTypeResource resource) {
        NotificationType notificationType = convertToEntity(resource);
        return convertToResource(notificationTypeService.updateNotificationType(id, notificationType));
    }

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