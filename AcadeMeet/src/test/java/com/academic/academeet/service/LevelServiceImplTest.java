package com.academic.academeet.service;

import com.academic.academeet.domain.model.Level;
import com.academic.academeet.domain.repository.ILevelRepository;
import com.academic.academeet.domain.repository.IStudentRepository;
import com.academic.academeet.domain.service.ILevelService;
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
public class LevelServiceImplTest {
    @MockBean
    private ILevelRepository levelRepository;

    @Autowired
    private ILevelService levelService;

    @MockBean
    private IStudentRepository studentRepository;

    @TestConfiguration
    static class LevelServiceTestConfiguration{
        @Bean
        public ILevelService levelService(){
            return new LevelServiceImpl();
        }
    }

    @Test
    void whenLevelByIdWithValidIdThenReturnsLevel(){

        //Arrange
        Long id = 1L;
        Level level = new Level();

        level.setId(id);
        level.setName("Diego");

        when(levelRepository.findById(id)).thenReturn(Optional.of(level));
        //Act
        Level level1 = levelService.getLevelById(id);

        //Assert
        assertThat(level1.getId()).isEqualTo(id);
    }

    @Test
    public void whenGetLevelByIdWithInvalidIdThenReturnsResourceNotFoundException() {
        // Arrange
        Long id = 1L;

        String template = "Resource %s not found for %s with value %s";
        when(levelRepository.findById(id))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "Level", "Id", id);

        // ACt
        Throwable exception = catchThrowable(() -> { // capturing exception
            Level level1 = levelService.getLevelById(id);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    public void whenDeleteLevelByIdWithInvalidIdThenReturnsResourceNotFoundException() {
        // Arrange
        Long id = 1L;
        String template = "Resource %s not found for %s with value %s";
        when(levelRepository.findById(id))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "Level", "Id", id);

        // ACt
        Throwable exception = catchThrowable(() -> { // capturing exception
            ResponseEntity<?> result = levelService.deleteLevel(id);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    public void whenDeleteLevelByIdWithValidIdThenReturnsResponseEntityOk() {
        // Arrange
        Long id = 1L;
        Level level = new Level();

        level.setId(id);
        level.setName("Diego");

        when(levelRepository.findById(id)).thenReturn(Optional.of(level));

        //Act
        ResponseEntity<?> result = levelService.deleteLevel(id);

        //Assert
        assertThat(result).isEqualTo(ResponseEntity.ok().build());
    }

    @Test
    public void whenSaveLevelWithValidOrganizerReturnsLevel() {
        // Arrange
        Long id = 1L;
        Level level = new Level();

        level.setId(id);
        level.setName("Diego");

        when(levelRepository.save(level)).thenReturn(level);

        // ACt

        Level result = levelService.createLevel(level);
        // Assert
        assertThat(result).isEqualTo(level);
    }

    @Test
    public void whenUpdateLevelWithInvalidIdThenReturnsResourceNotFoundException() {
        // Arrange
        Long id = 1L;
        Level updateLevel = new Level();

        updateLevel.setId(id);
        updateLevel.setName("Diego");

        when(levelRepository.findById(id)).thenReturn(Optional.empty());
        when(levelRepository.save(updateLevel)).thenReturn(updateLevel);

        String expectedMessage = String.format("Resource %s not found for %s with value %s", "Level", "Id", id);

        // Act
        Throwable exception = catchThrowable(() -> { // capturing exception
            Level level = levelService.updateLevel(id, updateLevel);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }
}
