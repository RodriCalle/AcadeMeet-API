package com.academic.academeet.service;

import com.academic.academeet.domain.model.NotificationType;
import com.academic.academeet.domain.repository.INotificationTypeRepository;
import com.academic.academeet.domain.service.INotificationTypeService;
import com.academic.academeet.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class NotificationTypeServiceImplTest {

    @MockBean
    private INotificationTypeRepository _notificationTypeRepository;

    @Autowired
    private INotificationTypeService _notificationTypeService;

    @TestConfiguration
    static class NotificationTypeServiceConfiguration {
        @Bean
        public INotificationTypeService notificationTypeService() { return new NotificationTypeServiceImpl(); }
    }

    @Test
    public void whenGetNotificationTypeByIdWithValidIdThenReturnsNotificationType() {
        // Arrange
        long id = 1;
        NotificationType notificationType = new NotificationType();
        notificationType.setId(id);
        notificationType.setDescription("A simple description");
        when(_notificationTypeRepository.findById(id)).thenReturn(Optional.of(notificationType));

        // ACt
        NotificationType foundNotificationType = _notificationTypeService.getById(id);

        // Assert
        assertThat(foundNotificationType.getId()).isEqualTo(id);

    }


    @Test
    public void whenGetNotificationTypeByIdWithInvalidIdThenReturnsResourceNotFoundException() {
        // Arrange
        long id = 1;
        String template = "Resource %s not found for %s with value %s";
        when(_notificationTypeRepository.findById(id))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "Notification Type", "Id", id);


        // ACt
        Throwable exception = catchThrowable(() -> { // capturing exception
            NotificationType foundNotificationType = _notificationTypeService.getById(id);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    public void whenDeleteNotificationTypeByIdWithInvalidIdThenReturnsResourceNotFoundException(){
        // Arrange
        long id = 1;
        String template = "Resource %s not found for %s with value %s";
        when(_notificationTypeRepository.findById(id)).thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "Notification Type", "Id", id);

        // ACt
        Throwable exception = catchThrowable(() -> { // capturing exception
            ResponseEntity<?> result = _notificationTypeService.deleteNotificationType(id);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    public void whenDeleteNotificationTypeByIdWithValidIdThenReturnsResponseEntityOk() {
        // Arrange
        long id = 1;
        NotificationType notificationType = new NotificationType();
        notificationType.setId(id);
        notificationType.setDescription("A simple description");
        when(_notificationTypeRepository.findById(id)).thenReturn(Optional.of(notificationType));

        // ACt
        ResponseEntity<?> result = _notificationTypeService.deleteNotificationType(id);

        // Assert
        assertThat(result).isEqualTo(ResponseEntity.ok().build());
    }

    @Test
    public void whenSaveNotificationTypeWithValidReturnsNotificationType () {
        // Arrange
        NotificationType notificationType = new NotificationType();
        notificationType.setId(1L);
        notificationType.setDescription("A simple description");
        when(_notificationTypeRepository.save(notificationType)).thenReturn(notificationType);

        // ACt
        NotificationType result = _notificationTypeService.createNotificationType(notificationType);

        // Assert
        assertThat(result).isEqualTo(notificationType);
    }

    //@Test
    public void whenUpdateNotificationTypeWithValidIdThenReturnsNotificationTypeUpdated() {
        // Arrange
        long id = 1;
        NotificationType updateNotificationType = new NotificationType();
        updateNotificationType.setId(id);
        updateNotificationType.setDescription("A simple description");

        NotificationType oldNotificationType = new NotificationType();
        oldNotificationType.setId(1L);
        oldNotificationType.setDescription("An old simple description");

        when(_notificationTypeRepository.findById(id)).thenReturn(Optional.of(oldNotificationType));
        when(_notificationTypeRepository.save(updateNotificationType)).thenReturn(updateNotificationType);

        // ACt
        NotificationType notificationType = _notificationTypeService.updateNotificationType(id, updateNotificationType);

        // Assert
        assertThat(notificationType).isEqualTo(updateNotificationType);
    }



}
