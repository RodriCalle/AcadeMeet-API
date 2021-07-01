package com.academic.academeet.service;

import com.academic.academeet.domain.model.LessonType;
import com.academic.academeet.domain.repository.ILessonTypeRepository;
import com.academic.academeet.domain.service.ILessonTypeService;
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
public class LessonTypeServiceImplTest {
    @MockBean
    private ILessonTypeRepository lessonTypeRepository;

    @Autowired
    private ILessonTypeService lessonTypeService;

    @TestConfiguration
    static class LessonTypeServiceTestConfiguration{
        @Bean
        public ILessonTypeService lessonTypeService(){
            return new LessonTypeServiceImpl();
        }
    }

    @Test
    void whenLessonTypeByIdWithValidIdThenReturnsSchedule(){

        //Arrange
        Long id = 1L;

        LessonType lessonType = new LessonType();

        lessonType.setId(id);
        lessonType.setName("Paolo");
        lessonType.setDescription("Maths");
        lessonType.setStudents_quantity(5);

        when(lessonTypeRepository.findById(id)).thenReturn(Optional.of(lessonType));
        //Act
        LessonType lessonType1 = lessonTypeService.getLessonTypeById(id);

        //Assert
        assertThat(lessonType1.getId()).isEqualTo(id);
    }

    @Test
    public void whenGetLessonTypeByIdWithInvalidIdThenReturnsResourceNotFoundException() {
        // Arrange
        Long id = 1L;

        String template = "Resource %s not found for %s with value %s";
        when(lessonTypeRepository.findById(id))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "LessonType", "Id", id);

        // ACt
        Throwable exception = catchThrowable(() -> { // capturing exception
            LessonType lessonType1 = lessonTypeService.getLessonTypeById(id);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    public void whenDeleteLessonTypeByIdWithInvalidIdThenReturnsResourceNotFoundException() {
        // Arrange
        Long id = 1L;
        String template = "Resource %s not found for %s with value %s";
        when(lessonTypeRepository.findById(id))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "LessonType", "Id", id);

        // ACt
        Throwable exception = catchThrowable(() -> { // capturing exception
            ResponseEntity<?> result =lessonTypeService.deleteLessonType(id);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    public void whenDeleteLessonTypeByIdWithValidIdThenReturnsResponseEntityOk() {
        // Arrange
        Long id = 1L;

        LessonType lessonType = new LessonType();

        lessonType.setId(id);

        when(lessonTypeRepository.findById(id)).thenReturn(Optional.of(lessonType));

        //Act
        ResponseEntity<?> result =lessonTypeService.deleteLessonType(id);

        //Assert
        assertThat(result).isEqualTo(ResponseEntity.ok().build());
    }

    @Test
    public void whenSaveLessonTypeWithValidOrganizerReturnsLessonType() {
        // Arrange
        Long id = 1L;

        LessonType lessonType = new LessonType();

        lessonType.setId(id);
        lessonType.setName("Paolo");
        lessonType.setDescription("Maths");
        lessonType.setStudents_quantity(5);

        when(lessonTypeRepository.save(lessonType)).thenReturn(lessonType);

        // ACt

        LessonType result = lessonTypeService.createLessonType(lessonType);
        // Assert
        assertThat(result).isEqualTo(lessonType);
    }

    @Test
    public void whenUpdateLessonTypeWithInvalidIdThenReturnsResourceNotFoundException() {
        // Arrange
        //Arrange
        Long id = 1L;

        LessonType updateLessonType = new LessonType();

        updateLessonType.setId(id);
        updateLessonType.setName("Paolo");
        updateLessonType.setDescription("Maths");
        updateLessonType.setStudents_quantity(5);

        when(lessonTypeRepository.findById(id)).thenReturn(Optional.empty());
        when(lessonTypeRepository.save(updateLessonType)).thenReturn(updateLessonType);

        String expectedMessage = String.format("Resource %s not found for %s with value %s", "LessonType", "Id", id);

        // Act
        Throwable exception = catchThrowable(() -> { // capturing exception
            LessonType lessonType = lessonTypeService.updateLessonType(id, updateLessonType);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }
}
