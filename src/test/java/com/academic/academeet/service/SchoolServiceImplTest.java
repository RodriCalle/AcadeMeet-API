package com.academic.academeet.service;

import com.academic.academeet.domain.model.School;
import com.academic.academeet.domain.repository.ISchoolRepository;
import com.academic.academeet.domain.service.ISchoolService;
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
public class SchoolServiceImplTest {
    @MockBean
    private ISchoolRepository schoolRepository;

    @Autowired
    private ISchoolService schoolService;


    @TestConfiguration
    static class SchoolServiceTestConfiguration{
        @Bean
        public ISchoolService schoolService(){
            return new SchoolServiceImpl();
        }
    }

    @Test
    void whenSchoolByIdWithValidIdThenReturnsSchedule(){

        //Arrange
        Long id = 1L;
        School school = new School();

        school.setId(id);
        school.setLocation("Lima");
        school.setName("Jorge");


        when(schoolRepository.findById(id)).thenReturn(Optional.of(school));
        //Act
        School school1 = schoolService.getSchoolById(id);

        //Assert
        assertThat(school1.getId()).isEqualTo(id);
    }

    @Test
    public void whenGetSchoolByIdWithInvalidIdThenReturnsResourceNotFoundException() {
        // Arrange
        Long id = 1L;

        String template = "Resource %s not found for %s with value %s";
        when(schoolRepository.findById(id))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "School", "Id", id);

        // ACt
        Throwable exception = catchThrowable(() -> { // capturing exception
            School school1 = schoolService.getSchoolById(id);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    public void whenDeleteSchoolByIdWithInvalidIdThenReturnsResourceNotFoundException() {
        // Arrange
        Long id = 1L;
        String template = "Resource %s not found for %s with value %s";
        when(schoolRepository.findById(id))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "School", "Id", id);

        // ACt
        Throwable exception = catchThrowable(() -> { // capturing exception
            ResponseEntity<?> result = schoolService.deleteSchool(id);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    public void whenDeleteSchoolByIdWithValidIdThenReturnsResponseEntityOk() {
        // Arrange
        Long id = 1L;
        School school = new School();

        school.setId(id);
        school.setLocation("Lima");
        school.setName("Jorge");

        when(schoolRepository.findById(id)).thenReturn(Optional.of(school));

        //Act
        ResponseEntity<?> result =  schoolService.deleteSchool(id);

        //Assert
        assertThat(result).isEqualTo(ResponseEntity.ok().build());
    }

    @Test
    public void whenSaveSchoolWithValidOrganizerReturnsSchool() {
        // Arrange
        Long id = 1L;
        School school = new School();

        school.setId(id);
        school.setLocation("Lima");
        school.setName("Jorge");

        when(schoolRepository.save(school)).thenReturn(school);

        // ACt

        School result = schoolService.createSchool(school);
        // Assert
        assertThat(result).isEqualTo(school);
    }

    @Test
    public void whenUpdateSchoolWithInvalidIdThenReturnsResourceNotFoundException() {
        // Arrange
        Long id = 1L;
        School updateSchool = new School();

        updateSchool.setId(id);
        updateSchool.setLocation("Lima");
        updateSchool.setName("Jorge");

        when(schoolRepository.findById(id)).thenReturn(Optional.empty());
        when(schoolRepository.save(updateSchool)).thenReturn(updateSchool);

        String expectedMessage = String.format("Resource %s not found for %s with value %s", "School", "Id", id);

        // Act
        Throwable exception = catchThrowable(() -> { // capturing exception
            School school = schoolService.updateSchool(id, updateSchool);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }
}
