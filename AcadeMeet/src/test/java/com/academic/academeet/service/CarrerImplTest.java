package com.academic.academeet.service;

import com.academic.academeet.domain.model.Carrer;
import com.academic.academeet.domain.model.University;
import com.academic.academeet.domain.repository.ICarrerRepository;
import com.academic.academeet.domain.repository.IUniversityRepository;
import com.academic.academeet.domain.service.ICarrerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class CarrerImplTest {
    @MockBean
    private ICarrerRepository carrerRepository;
    @MockBean
    private IUniversityRepository universityRepository;

    @Autowired
    private ICarrerService carrerService;

    @TestConfiguration
    static class CarrerServiceTestsConfiguration {
        @Bean
        public ICarrerService carrerService() { return new CarrerServiceImpl(); }
    }

    @Test
    void whenCreateCarrerWithIdsValidThenReturnsCarrer() {
        // Arrange
        long universityId = 1;
        Carrer carrer = new Carrer();
        University university = new University();
        carrer.setUniversity(university);

        when(universityRepository.findById(universityId)).thenReturn(Optional.of(university));
        when(carrerRepository.save(carrer)).thenReturn(carrer);

        // Action
        Carrer result = carrerService.createCarrer(universityId, carrer);

        // Assert
        assertThat(result).isEqualTo(carrer);
    }


    @Test
    void whenUpdateCarrerWithIdsValidThenReturnsCarrer() {
        // Arrange
        long universityId = 1;
        University university = new University();
        long carrerId = 1;
        Carrer carrer  = new Carrer();
        carrer.setUniversity(university);

        when(universityRepository.findById(universityId)).thenReturn(Optional.of(university));
        when(carrerRepository.findById(carrerId)).thenReturn(Optional.of(carrer));
        when(carrerRepository.save(carrer)).thenReturn(carrer);

        // Act
        Carrer result = carrerService.updateCarrer(universityId, carrerId, carrer);

        // Assert
        assertThat(result).isEqualTo(carrer);
    }


}
