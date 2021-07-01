package com.academic.academeet.service;

import com.academic.academeet.domain.model.TypeOfGrade;
import com.academic.academeet.domain.repository.ITypeOfGradeRepository;
import com.academic.academeet.domain.service.ITypeOfGradeService;
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
public class TypeOfGradeServiceImplTest {
    @MockBean
    private ITypeOfGradeRepository typeOfGradeRepository;

    @Autowired
    private ITypeOfGradeService typeOfGradeService;

    @TestConfiguration
    static class TypeOfGradeServiceTestConfiguration{
        @Bean
        public ITypeOfGradeService typeOfGradeService(){
            return new TypeOfGradeServiceImpl();
        }
    }

    @Test
    void whenTypeOfGradeByIdWithValidIdThenReturnsTypeOfGrade(){

        //Arrange
        Long id = 1L;

        TypeOfGrade typeOfGrade = new TypeOfGrade();

        typeOfGrade.setId(id);
        typeOfGrade.setName("Paola");

        when(typeOfGradeRepository.findById(id)).thenReturn(Optional.of(typeOfGrade));
        //Act
        TypeOfGrade typeOfGrade1 = typeOfGradeService.getTypeOfGradeById(id);

        //Assert
        assertThat(typeOfGrade1.getId()).isEqualTo(id);
    }

    @Test
    public void whenGetTypeOfGradeByIdWithInvalidIdThenReturnsResourceNotFoundException() {
        // Arrange
        Long id = 1L;

        String template = "Resource %s not found for %s with value %s";
        when(typeOfGradeRepository.findById(id))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "TypeOfGrade", "Id", id);

        // ACt
        Throwable exception = catchThrowable(() -> { // capturing exception
            TypeOfGrade typeOfGrade = typeOfGradeService.getTypeOfGradeById(id);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    public void whenDeleteTypeOfGradeByIdWithInvalidIdThenReturnsResourceNotFoundException() {
        // Arrange
        Long id = 1L;
        String template = "Resource %s not found for %s with value %s";
        when(typeOfGradeRepository.findById(id))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "TypeOfGrade", "Id", id);

        // ACt
        Throwable exception = catchThrowable(() -> { // capturing exception
            ResponseEntity<?> result = typeOfGradeService.deleteTypeOfGrade(id);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    public void whenDeleteTypeOfGradeByIdWithValidIdThenReturnsResponseEntityOk() {
        // Arrange
        Long id = 1L;

        TypeOfGrade typeOfGrade = new TypeOfGrade();

        typeOfGrade.setId(id);
        typeOfGrade.setName("Paola");

        when(typeOfGradeRepository.findById(id)).thenReturn(Optional.of(typeOfGrade));

        //Act
        ResponseEntity<?> result = typeOfGradeService.deleteTypeOfGrade(id);

        //Assert
        assertThat(result).isEqualTo(ResponseEntity.ok().build());
    }

    @Test
    public void whenSaveTypeOfGradeWithValidOrganizerReturnsTypeOfGrade() {
        // Arrange
        Long id = 1L;

        TypeOfGrade typeOfGrade = new TypeOfGrade();

        typeOfGrade.setId(id);
        typeOfGrade.setName("Paola");

        when(typeOfGradeRepository.save(typeOfGrade)).thenReturn(typeOfGrade);

        // ACt

        TypeOfGrade result = typeOfGradeService.createTypeOfGrade(typeOfGrade);
        // Assert
        assertThat(result).isEqualTo(typeOfGrade);
    }

    @Test
    public void whenUpdateTypeOfGradeWithInvalidIdThenReturnsResourceNotFoundException() {
        // Arrange
        Long id = 1L;

        TypeOfGrade updateTypeOfGrade = new TypeOfGrade();

        updateTypeOfGrade.setId(id);
        updateTypeOfGrade.setName("Paola");

        when(typeOfGradeRepository.findById(id)).thenReturn(Optional.empty());
        when(typeOfGradeRepository.save(updateTypeOfGrade)).thenReturn(updateTypeOfGrade);

        String expectedMessage = String.format("Resource %s not found for %s with value %s", "TypeOfGrade", "Id", id);

        // Act
        Throwable exception = catchThrowable(() -> { // capturing exception
            TypeOfGrade schedule = typeOfGradeService.updateTypeOfGrade(id, updateTypeOfGrade);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }
}
