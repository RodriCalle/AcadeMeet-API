package com.academic.academeet.service;

import com.academic.academeet.domain.model.Schedule;
import com.academic.academeet.domain.repository.IScheduleRepository;
import com.academic.academeet.domain.service.IScheduleService;
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
public class ScheduleServiceImplTest {
    @MockBean
    private IScheduleRepository scheduleRepository;

    @Autowired
    private IScheduleService scheduleService;

    @TestConfiguration
    static class ScheduleServiceTestConfiguration{
        @Bean
        public IScheduleService scheduleService(){
            return new ScheduleServiceImpl();
        }
    }

    @Test
    void whenScheduleByIdWithValidIdThenReturnsSchedule(){

        //Arrange
        Long id = 1L;
        Schedule schedule = new Schedule();

        schedule.setId(id);

        when(scheduleRepository.findById(id)).thenReturn(Optional.of(schedule));
        //Act
        Schedule schedule1 = scheduleService.getScheduleById(id);

        //Assert
        assertThat(schedule1.getId()).isEqualTo(id);
    }

    @Test
    public void whenDeleteScheduleByIdWithInvalidIdThenReturnsResourceNotFoundException() {
        // Arrange
        Long id = 1L;
        String template = "Resource %s not found for %s with value %s";
        when(scheduleRepository.findById(id))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "Schedule", "Id", id);

        // ACt
        Throwable exception = catchThrowable(() -> { // capturing exception
            ResponseEntity<?> result = scheduleService.deleteSchedule(id);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    public void whenDeleteScheduleByIdWithValidIdThenReturnsResponseEntityOk() {
        // Arrange
        Long id = 1L;

        Schedule schedule = new Schedule();

        schedule.setId(id);

        when(scheduleRepository.findById(id)).thenReturn(Optional.of(schedule));

        //Act
        ResponseEntity<?> result =  scheduleService.deleteSchedule(id);

        //Assert
        assertThat(result).isEqualTo(ResponseEntity.ok().build());
    }

    @Test
    public void whenSaveScheduleWithValidOrganizerReturnsSchedule() {
        // Arrange
        Long id = 1L;

        Schedule schedule = new Schedule();

        schedule.setId(id);

        when(scheduleRepository.save(schedule)).thenReturn(schedule);

        // ACt

        Schedule result = scheduleService.createSchedule(schedule);
        // Assert
        assertThat(result).isEqualTo(schedule);
    }

    @Test
    public void whenUpdateScheduleWithInvalidIdThenReturnsResourceNotFoundException() {
        // Arrange
        Long id = 1L;

        Schedule updateSchedule = new Schedule();

        updateSchedule.setId(id);

        when(scheduleRepository.findById(id)).thenReturn(Optional.empty());
        when(scheduleRepository.save(updateSchedule)).thenReturn(updateSchedule);

        String expectedMessage = String.format("Resource %s not found for %s with value %s", "Schedule", "Id", id);

        // Act
        Throwable exception = catchThrowable(() -> { // capturing exception
            Schedule schedule = scheduleService.updateSchedule(id, updateSchedule);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }
}
