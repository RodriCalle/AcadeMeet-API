package com.academic.academeet.service;

import com.academic.academeet.domain.model.Plan;
import com.academic.academeet.domain.model.Tutor;
import com.academic.academeet.domain.model.User;
import com.academic.academeet.domain.repository.IPlanRepository;
import com.academic.academeet.domain.repository.IUserRepository;
import com.academic.academeet.domain.service.ITutorService;
import com.academic.academeet.domain.service.IUserService;
import com.academic.academeet.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UserPlanServiceImplTest {
    @Autowired
    private IUserService userService;

    @MockBean
    private IUserRepository userRepository;

    @MockBean
    private IPlanRepository planRepository;

    @TestConfiguration
    static class UserPlanServiceTestConfiguration{
        @Bean
        public IUserService userService() {
            return new UserServiceImpl();
        }
    }

    @Test
    public void whenAssignUserToPlanReturnsUser(){
        Long userId = 1L; Long planId =1L;

        User user = new User();
        user.setFirst_name("Rodrigo"); user.setLast_name("Calle"); user.setId(userId);
        user.setMail("rokis@hotmail.com"); user.setPassword("rodri123##");

        Plan plan = new Plan();
        plan.setId(planId); plan.setName("Basic"); plan.setPrice(55);

        when(userRepository.save(user)).thenReturn(user);
        when(planRepository.save(plan)).thenReturn(plan);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(planRepository.findById(planId)).thenReturn(Optional.of(plan));

        User result = userService.assignUserPlan(userId, planId);

        assertThat(result).isEqualTo(user);
    }

    @Test
    public void whenAssignUserToPlanReturnsUserException(){
        Long userId = 1L; Long planId =1L;

        User user = new User();
        user.setFirst_name("Rodrigo"); user.setLast_name("Calle"); user.setId(userId);
        user.setMail("rokis@hotmail.com"); user.setPassword("rodri123##");

        Plan plan = new Plan();
        plan.setId(planId); plan.setName("Basic"); plan.setPrice(55);

        when(planRepository.save(plan)).thenReturn(plan);

        when(planRepository.findById(planId)).thenReturn(Optional.of(plan));

        Throwable exception = catchThrowable(() -> {
            User result = userService.assignUserPlan(userId, planId);
        });

        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Resource User not found for Id with value 1");
    }

    @Test
    public void whenAssignUserToPlanReturnsPlanException(){
        Long userId = 1L; Long planId =1L;

        User user = new User();
        user.setFirst_name("Rodrigo"); user.setLast_name("Calle"); user.setId(userId);
        user.setMail("rokis@hotmail.com"); user.setPassword("rodri123##");

        Plan plan = new Plan();
        plan.setId(planId); plan.setName("Basic"); plan.setPrice(55);

        when(userRepository.save(user)).thenReturn(user);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        Throwable exception = catchThrowable(() -> {
            User result = userService.assignUserPlan(userId, planId);
        });

        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Resource Plan not found for Id with value 1");
    }

    @Test
    public void whenUnassignUserToPlanReturnsUser(){
        Long userId = 1L; Long planId =1L;

        User user = new User();
        user.setFirst_name("Rodrigo"); user.setLast_name("Calle"); user.setId(userId);
        user.setMail("rokis@hotmail.com"); user.setPassword("rodri123##");

        Plan plan = new Plan();
        plan.setId(planId); plan.setName("Basic"); plan.setPrice(55);

        when(userRepository.save(user)).thenReturn(user);
        when(planRepository.save(plan)).thenReturn(plan);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(planRepository.findById(planId)).thenReturn(Optional.of(plan));

        User result = userService.unassignUserPlan(userId, planId);

        assertThat(result).isEqualTo(user);
    }

    @Test
    public void whenUnassignUserToPlanReturnsUserException(){
        Long userId = 1L; Long planId =1L;

        User user = new User();
        user.setFirst_name("Rodrigo"); user.setLast_name("Calle"); user.setId(userId);
        user.setMail("rokis@hotmail.com"); user.setPassword("rodri123##");

        Plan plan = new Plan();
        plan.setId(planId); plan.setName("Basic"); plan.setPrice(55);

        when(planRepository.save(plan)).thenReturn(plan);

        when(planRepository.findById(planId)).thenReturn(Optional.of(plan));

        Throwable exception = catchThrowable(() -> {
            User result = userService.unassignUserPlan(userId, planId);
        });

        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Resource User not found for Id with value 1");
    }

    @Test
    public void whenUnassignUserToPlanReturnsPlanException(){
        Long userId = 1L; Long planId =1L;

        User user = new User();
        user.setFirst_name("Rodrigo"); user.setLast_name("Calle"); user.setId(userId);
        user.setMail("rokis@hotmail.com"); user.setPassword("rodri123##");

        Plan plan = new Plan();
        plan.setId(planId); plan.setName("Basic"); plan.setPrice(55);

        when(userRepository.save(user)).thenReturn(user);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        Throwable exception = catchThrowable(() -> {
            User result = userService.unassignUserPlan(userId, planId);
        });

        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Resource Plan not found for Id with value 1");
    }

}
