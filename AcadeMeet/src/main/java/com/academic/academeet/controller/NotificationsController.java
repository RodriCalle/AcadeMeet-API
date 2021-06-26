package com.academic.academeet.controller;

import com.academic.academeet.domain.model.Notification;
import com.academic.academeet.domain.service.INotificationService;
import com.academic.academeet.resource.NotificationResource;
import com.academic.academeet.resource.SaveNotificationResource;
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
    private INotificationService INotificationService;

    @Autowired
    private ModelMapper mapper;

    @PostMapping("/notifications")
    public NotificationResource createNotification(@Valid @RequestBody SaveNotificationResource resource) {
        Notification notification = convertToEntity(resource);
        return convertToResource(INotificationService.saveNotification(notification));
    }

    @GetMapping("/notifications/{id}")
    public NotificationResource getNotificationById(@PathVariable Long id) {
        Notification notification = INotificationService.getById(id);
        return convertToResource(notification);
    }

    @PutMapping("/notifications/{id}")
    public NotificationResource updateNotification(@PathVariable Long id, @Valid @RequestBody SaveNotificationResource resource) {
        Notification notification = convertToEntity(resource);
        return convertToResource(INotificationService.updateNotification(id, notification));
    }

    @DeleteMapping("/notifications/{id}")
    public ResponseEntity<?> deleteNotification(@PathVariable Long id) {
        INotificationService.deleteNotification(id);
        return ResponseEntity.ok().build();
    }


    public Notification convertToEntity(SaveNotificationResource resource){
        return mapper.map(resource, Notification.class);
    }
    public NotificationResource convertToResource(Notification entity){
        return mapper.map(entity, NotificationResource.class);
    }
}
