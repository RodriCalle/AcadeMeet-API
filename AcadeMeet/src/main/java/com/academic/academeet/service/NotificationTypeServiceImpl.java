package com.academic.academeet.service;

import com.academic.academeet.domain.model.NotificationType;
import com.academic.academeet.domain.repository.INotificationTypeRepository;
import com.academic.academeet.domain.service.INotificationTypeService;
import com.academic.academeet.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class NotificationTypeServiceImpl implements INotificationTypeService {

    @Autowired
    private INotificationTypeRepository notificationTypeRepository;

    @Override
    public Page<NotificationType> getAll(Pageable pageable) {
        return notificationTypeRepository.findAll(pageable);
    }

    @Override
    public NotificationType getById(Long notificationTypeId) {
        return notificationTypeRepository.findById(notificationTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("Notification Type", "Id", notificationTypeId));
    }

    @Override
    public NotificationType createNotificationType(NotificationType notificationType) {
        return notificationTypeRepository.save(notificationType);
    }

    @Override
    public NotificationType updateNotificationType(Long notificationTypeId, NotificationType notificationTypeDetails) {
       NotificationType resource = notificationTypeRepository.findById(notificationTypeId)
               .orElseThrow(() -> new ResourceNotFoundException("Notification Type", "Id", notificationTypeId));

       resource.setDescription(notificationTypeDetails.getDescription());
       return notificationTypeRepository.save(resource);
    }

    @Override
    public ResponseEntity<?> deleteNotificationType(Long notificationTypeId) {
        NotificationType resource = notificationTypeRepository.findById(notificationTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("Notification Type", "Id", notificationTypeId));

        notificationTypeRepository.delete(resource);
        return ResponseEntity.ok().build();
    }
}
