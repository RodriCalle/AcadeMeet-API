package com.academic.academeet.service;

import com.academic.academeet.domain.model.University;
import com.academic.academeet.domain.repository.IUniversityRepository;
import com.academic.academeet.domain.service.IUniversityService;
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
public class UniversityServiceImplTest {
    @MockBean
    private IUniversityRepository universityRepository;

    @Autowired
    private IUniversityService universityService;

    @TestConfiguration
    static class UniversityServiceTestConfiguration{
        @Bean
        public IUniversityService scheduleService(){
            return new UniversityServiceImpl();
        }
    }

    @Test
    void whenUniversityByIdWithValidIdThenReturnsUniversity(){

        //Arrange
        Long id = 1L;

        University university = new University();

        university.setId(id);
        university.setName("John");

        when(universityRepository.findById(id)).thenReturn(Optional.of(university));
        //Act
        University university1 = universityService.getUniversityById(id);

        //Assert
        assertThat(university1.getId()).isEqualTo(id);
    }

    @Test
    public void whenGetUniversityByIdWithInvalidIdThenReturnsResourceNotFoundException() {
        // Arrange
        Long id = 1L;

        String template = "Resource %s not found for %s with value %s";
        when(universityRepository.findById(id))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "University", "Id", id);

        // ACt
        Throwable exception = catchThrowable(() -> { // capturing exception
            University university1 = universityService.getUniversityById(id);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    public void whenDeleteUniversityByIdWithInvalidIdThenReturnsResourceNotFoundException() {
        // Arrange
        Long id = 1L;
        String template = "Resource %s not found for %s with value %s";
        when(universityRepository.findById(id))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "University", "Id", id);

        // ACt
        Throwable exception = catchThrowable(() -> { // capturing exception
            ResponseEntity<?> result = universityService.deleteUniversity(id);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    public void whenDeleteUniversityByIdWithValidIdThenReturnsResponseEntityOk() {
        // Arrange
        Long id = 1L;

        University university = new University();

        university.setId(id);
        university.setName("John");

        when(universityRepository.findById(id)).thenReturn(Optional.of(university));

        //Act
        ResponseEntity<?> result =  universityService.deleteUniversity(id);

        //Assert
        assertThat(result).isEqualTo(ResponseEntity.ok().build());
    }

    @Test
    public void whenSaveUniversityWithValidOrganizerReturnsSchedule() {
        // Arrange
        Long id = 1L;

        University university = new University();

        university.setId(id);
        university.setName("John");

        when(universityRepository.save(university)).thenReturn(university);

        // ACt

        University result = universityService.createUniversity(university);
        // Assert
        assertThat(result).isEqualTo(university);
    }

    @Test
    public void whenUpdateUniversityWithInvalidIdThenReturnsResourceNotFoundException() {
        // Arrange
        Long id = 1L;

        University university = new University();

        university.setId(id);
        university.setName("John");

        when(universityRepository.findById(id)).thenReturn(Optional.empty());
        when(universityRepository.save(university)).thenReturn(university);

        String expectedMessage = String.format("Resource %s not found for %s with value %s", "University", "Id", id);

        // Act
        Throwable exception = catchThrowable(() -> { // capturing exception
            University university1 = universityService.updateUniversity(id, university);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }
}
