package com.academic.academeet.service;

import com.academic.academeet.domain.model.Tutor;
import com.academic.academeet.domain.repository.ICarrerRepository;
import com.academic.academeet.domain.repository.ITutorRepository;
import com.academic.academeet.domain.service.ITutorService;
import com.academic.academeet.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class TutorServiceImplTest {
    @Autowired
    private ITutorService tutorService;

    @MockBean
    private ICarrerRepository carrerRepository;
    @MockBean
    private ITutorRepository tutorRepository;

    @TestConfiguration
    static class TutorServiceTestConfiguration{
        @Bean
        public ITutorService tutorService() {
            return new TutorServiceImpl();
        }
    }

    @Test
    public void whenCreateTutorReturnsTutor(){
        Long tutorId = 1L;
        Calendar hire = new GregorianCalendar(2021,6,30);

        Tutor tutor = new Tutor();
        tutor.setId(tutorId); tutor.setMail("rokis@gmail.com"); tutor.setPassword("12rokis34");
        tutor.setFirst_name("Rodrigo"); tutor.setLast_name("Calle"); tutor.setHireDate(hire.getTime());

        when(tutorRepository.save(tutor)).thenReturn(tutor);

        Tutor result = tutorService.createTutor(tutor);

        assertThat(result).isEqualTo(tutor);
    }

    @Test
    public void whenUpdateTutorReturnsTutor(){
        Long tutorId = 1L;
        Calendar hire = new GregorianCalendar(2021,6,30);
        Calendar hireUpdate = new GregorianCalendar(2020,5,19);


        Tutor tutor = new Tutor();
        tutor.setId(tutorId); tutor.setMail("rokis@gmail.com"); tutor.setPassword("12rokis34");
        tutor.setFirst_name("Rodrigo"); tutor.setLast_name("Calle"); tutor.setHireDate(hire.getTime());

        Tutor tutorUpdate = new Tutor();
        tutorUpdate.setId(tutorId); tutorUpdate.setMail("paolo@gmail.com");
        tutorUpdate.setPassword("1234");tutorUpdate.setFirst_name("Paolo");
        tutorUpdate.setLast_name("Guerrero");tutorUpdate.setHireDate(hireUpdate.getTime());

        when(tutorRepository.findById(tutorId)).thenReturn(Optional.of(tutor));
        when(tutorRepository.save(tutorUpdate)).thenReturn(tutorUpdate);

        Tutor result = tutorService.updateTutor(tutorId, tutorUpdate);

        assertThat(result).isEqualTo(tutorUpdate);
    }

    @Test
    public void whenUpdateTutorReturnsTutorException(){
        Long tutorId = 1L;
        Calendar hireUpdate = new GregorianCalendar(2020,5,19);

        Tutor tutorUpdate = new Tutor();
        tutorUpdate.setId(tutorId); tutorUpdate.setMail("paolo@gmail.com");
        tutorUpdate.setPassword("1234");tutorUpdate.setFirst_name("Paolo");
        tutorUpdate.setLast_name("Guerrero");tutorUpdate.setHireDate(hireUpdate.getTime());

        when(tutorRepository.save(tutorUpdate)).thenReturn(tutorUpdate);

        Throwable exception = catchThrowable(() -> {
            Tutor result = tutorService.updateTutor(tutorId, tutorUpdate);
        });

        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Resource Tutor not found for Id with value 1");
    }

    @Test
    public void whenGetTutorReturnsTutor(){
        Long tutorId = 1L;
        Calendar hire = new GregorianCalendar(2021,6,30);

        Tutor tutor = new Tutor();
        tutor.setId(tutorId); tutor.setMail("rokis@gmail.com"); tutor.setPassword("12rokis34");
        tutor.setFirst_name("Rodrigo"); tutor.setLast_name("Calle"); tutor.setHireDate(hire.getTime());

        when(tutorRepository.findById(tutorId)).thenReturn(Optional.of(tutor));

        Tutor result = tutorService.getTutorById(tutorId);

        assertThat(result).isEqualTo(tutor);
    }

    @Test
    public void whenGetTutorReturnsTutorException(){
        Long tutorId = 1L;

        Throwable exception = catchThrowable(() -> {
            Tutor result = tutorService.getTutorById(tutorId);
        });

        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Resource Tutor not found for Id with value 1");
    }

    @Test
    public void whenDeleteTutorReturnsResponseEntity(){
        Long tutorId = 1L;
        Calendar hire = new GregorianCalendar(2021,6,30);

        Tutor tutor = new Tutor();
        tutor.setId(tutorId); tutor.setMail("rokis@gmail.com"); tutor.setPassword("12rokis34");
        tutor.setFirst_name("Rodrigo"); tutor.setLast_name("Calle"); tutor.setHireDate(hire.getTime());

        when(tutorRepository.findById(tutorId)).thenReturn(Optional.of(tutor));

        ResponseEntity<?> result = tutorService.deleteTutor(tutorId);

        assertThat(result).isEqualTo(ResponseEntity.ok().build());
    }

    @Test
    public void whenDeleteTutorReturnsTutorException(){
        Long tutorId = 1L;

        Throwable exception = catchThrowable(() -> {
            ResponseEntity<?> result = tutorService.deleteTutor(tutorId);
        });

        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Resource Tutor not found for Id with value 1");
    }

}
