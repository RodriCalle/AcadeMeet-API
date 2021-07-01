package com.academic.academeet.service;

import com.academic.academeet.domain.model.Plan;
import com.academic.academeet.domain.repository.IPlanRepository;
import com.academic.academeet.domain.service.IPlanService;
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
public class PlanServiceImplTest {
    @MockBean
    private IPlanRepository planRepository;

    @Autowired
    private IPlanService planService;

    @TestConfiguration
    static class PlanServiceTestConfiguration{
        @Bean
        public IPlanService planService(){
            return new PlanServiceImpl();
        }
    }

    @Test
    void whenPlanByIdWithValidIdThenReturnsPlan(){

        //Arrange
        Long id = 1L;

        Plan plan = new Plan();

        plan.setId(id);
        plan.setName("Juan");
        plan.setPrice(10);


        when(planRepository.findById(id)).thenReturn(Optional.of(plan));
        //Act
        Plan plan1 = planService.getPlanById(id);

        //Assert
        assertThat(plan1.getId()).isEqualTo(id);
    }

    @Test
    public void whenGetPlanByIdWithInvalidIdThenReturnsResourceNotFoundException() {
        // Arrange
        Long id = 1L;

        String template = "Resource %s not found for %s with value %s";
        when(planRepository.findById(id))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "Plan", "Id", id);

        // ACt
        Throwable exception = catchThrowable(() -> { // capturing exception
            Plan plan1 = planService.getPlanById(id);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    public void whenDeletePlanByIdWithInvalidIdThenReturnsResourceNotFoundException() {
        // Arrange
        Long id = 1L;
        String template = "Resource %s not found for %s with value %s";
        when(planRepository.findById(id))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "Plan", "Id", id);

        // ACt
        Throwable exception = catchThrowable(() -> { // capturing exception
            ResponseEntity<?> result = planService.deletePlan(id);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    public void whenDeletePlanByIdWithValidIdThenReturnsResponseEntityOk() {
        // Arrange
        Long id = 1L;

        Plan plan = new Plan();

        plan.setId(id);

        when(planRepository.findById(id)).thenReturn(Optional.of(plan));

        //Act
        ResponseEntity<?> result = planService.deletePlan(id);

        //Assert
        assertThat(result).isEqualTo(ResponseEntity.ok().build());
    }

    @Test
    public void whenSavePlanWithValidOrganizerReturnsSchedule() {
        // Arrange
        Long id = 1L;

        Plan plan = new Plan();

        plan.setId(id);
        plan.setName("Juan");
        plan.setPrice(10);

        when(planRepository.save(plan)).thenReturn(plan);

        // ACt

        Plan result = planService.createPlan(plan);
        // Assert
        assertThat(result).isEqualTo(plan);
    }

    @Test
    public void whenUpdatePlanWithInvalidIdThenReturnsResourceNotFoundException() {
        // Arrange
        Long id = 1L;

        Plan updatePlan = new Plan();

        updatePlan.setId(id);
        updatePlan.setName("Juan");
        updatePlan.setPrice(10);

        when(planRepository.findById(id)).thenReturn(Optional.empty());
        when(planRepository.save(updatePlan)).thenReturn(updatePlan);

        String expectedMessage = String.format("Resource %s not found for %s with value %s", "Plan", "Id", id);

        // Act
        Throwable exception = catchThrowable(() -> { // capturing exception
            Plan plan = planService.updatePlan(id, updatePlan);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }
}
