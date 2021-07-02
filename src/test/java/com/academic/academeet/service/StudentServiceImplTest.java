package com.academic.academeet.service;

import com.academic.academeet.domain.model.Level;
import com.academic.academeet.domain.model.School;
import com.academic.academeet.domain.model.Student;
import com.academic.academeet.domain.repository.ILevelRepository;
import com.academic.academeet.domain.repository.ISchoolRepository;
import com.academic.academeet.domain.repository.IStudentRepository;
import com.academic.academeet.domain.service.IStudentService;
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
public class StudentServiceImplTest {
    @Autowired
    private IStudentService studentService;

    @MockBean
    private IStudentRepository studentRepository;
    @MockBean
    private ILevelRepository levelRepository;
    @MockBean
    private ISchoolRepository schoolRepository;

    @TestConfiguration
    static class StudentServiceTestConfiguration{
        @Bean
        public IStudentService studentService() {
            return new StudentServiceImpl();
        }
    }

    @Test
    public void whenCreateStudentReturnsStudent(){
        Long levelId = 1L; Long schoolId = 1L; Long studentId = 1L;
        Calendar born = new GregorianCalendar(2001,8,27);
        Level level = new Level();level.setId(levelId);level.setName("First");
        School school = new School();school.setId(schoolId);school.setName("Pedro Ruiz Gallo");
        school.setLocation("Chorrillos");

        Student student = new Student();
        student.setId(studentId);student.setFirst_name("Rodrigo");student.setLast_name("Calle");
        student.setPassword("rokis123@");student.setMail("rodrigo@hotmail.com");
        student.setBornDate(born.getTime());

        when(levelRepository.findById(levelId)).thenReturn(Optional.of(level));
        when(schoolRepository.findById(schoolId)).thenReturn(Optional.of(school));
        when(studentRepository.save(student)).thenReturn(student);

        Student result = studentService.createStudent(levelId,schoolId,student);

        assertThat(result).isEqualTo(student);
    }

    @Test
    public void whenCreateStudentReturnsLevelException(){
        Long levelId = 1L; Long schoolId = 1L; Long studentId = 1L;
        Calendar born = new GregorianCalendar(2001,8,27);
        School school = new School();school.setId(schoolId);school.setName("Pedro Ruiz Gallo");
        school.setLocation("Chorrillos");

        Student student = new Student();
        student.setId(studentId);student.setFirst_name("Rodrigo");student.setLast_name("Calle");
        student.setPassword("rokis123@");student.setMail("rodrigo@hotmail.com");
        student.setBornDate(born.getTime());

        when(levelRepository.findById(levelId)).thenReturn(Optional.empty());
        when(schoolRepository.findById(schoolId)).thenReturn(Optional.of(school));
        when(studentRepository.save(student)).thenReturn(student);

        Throwable exception = catchThrowable(() -> {
            Student result = studentService.createStudent(levelId,schoolId,student);
        });

        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Resource Level not found for Id with value 1");
    }

    @Test
    public void whenUpdateStudentReturnsStudent(){
        Long levelId = 1L; Long schoolId = 1L; Long studentId = 1L;
        Long levelIdUpdate = 5L; Long schoolIdUpdate = 5L;
        Calendar born = new GregorianCalendar(2001,8,27);
        Calendar bornUpdate = new GregorianCalendar(1999,7,12);

        Level level = new Level();level.setId(levelId);level.setName("First");
        School school = new School();school.setId(schoolId);school.setName("Pedro Ruiz Gallo");
        school.setLocation("Chorrillos");

        Level levelUpdate = new Level();level.setId(levelIdUpdate);level.setName("Third");
        School schoolUpdate = new School();school.setId(schoolIdUpdate);school.setName("San Pedro");
        school.setLocation("Matellini");

        Student student = new Student();
        student.setId(studentId);student.setFirst_name("Rodrigo");student.setLast_name("Calle");
        student.setPassword("rokis123@");student.setMail("rodrigo@hotmail.com");
        student.setBornDate(born.getTime()); student.setLevel(level); student.setSchool(school);

        Student studentUpdate = new Student();
        studentUpdate.setId(studentId);studentUpdate.setFirst_name("Diego");studentUpdate.setLast_name("Galdos");
        studentUpdate.setPassword("diego00@");studentUpdate.setMail("diego@hotmail.com");
        studentUpdate.setBornDate(bornUpdate.getTime()); studentUpdate.setLevel(levelUpdate);
        studentUpdate.setSchool(schoolUpdate);

        when(levelRepository.findById(levelIdUpdate)).thenReturn(Optional.of(levelUpdate));
        when(schoolRepository.findById(schoolIdUpdate)).thenReturn(Optional.of(schoolUpdate));
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
        when(studentRepository.save(studentUpdate)).thenReturn(studentUpdate);

        Student result = studentService.updateStudent(levelIdUpdate,schoolIdUpdate,studentId, studentUpdate);

        assertThat(result).isEqualTo(studentUpdate);
    }

    @Test
    public void whenUpdateStudentReturnsSchoolException(){
        Long levelId = 1L; Long schoolId = 1L; Long studentId = 1L;
        Long levelIdUpdate = 5L; Long schoolIdUpdate = 5L;
        Calendar born = new GregorianCalendar(2001,8,27);
        Calendar bornUpdate = new GregorianCalendar(1999,7,12);

        Level level = new Level();level.setId(levelId);level.setName("First");
        School school = new School();school.setId(schoolId);school.setName("Pedro Ruiz Gallo");
        school.setLocation("Chorrillos");

        Level levelUpdate = new Level();level.setId(levelIdUpdate);level.setName("Third");
        School schoolUpdate = new School();school.setId(schoolIdUpdate);school.setName("San Pedro");
        school.setLocation("Matellini");

        Student student = new Student();
        student.setId(studentId);student.setFirst_name("Rodrigo");student.setLast_name("Calle");
        student.setPassword("rokis123@");student.setMail("rodrigo@hotmail.com");
        student.setBornDate(born.getTime()); student.setLevel(level); student.setSchool(school);

        Student studentUpdate = new Student();
        studentUpdate.setId(studentId);studentUpdate.setFirst_name("Diego");studentUpdate.setLast_name("Galdos");
        studentUpdate.setPassword("diego00@");studentUpdate.setMail("diego@hotmail.com");
        studentUpdate.setBornDate(bornUpdate.getTime()); studentUpdate.setLevel(levelUpdate);
        studentUpdate.setSchool(schoolUpdate);

        when(levelRepository.findById(levelIdUpdate)).thenReturn(Optional.of(levelUpdate));
        when(schoolRepository.findById(schoolIdUpdate)).thenReturn(Optional.empty());
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
        when(studentRepository.save(studentUpdate)).thenReturn(studentUpdate);

        Throwable exception = catchThrowable(() -> {
            Student result = studentService.updateStudent(levelIdUpdate,schoolIdUpdate,studentId, studentUpdate);
        });

        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Resource School not found for Id with value 5");
    }

    @Test
    public void whenGetStudentReturnsStudent(){
        Long levelId = 1L; Long schoolId = 1L; Long studentId = 1L;
        Calendar born = new GregorianCalendar(2001,8,27);

        Level level = new Level();level.setId(levelId);level.setName("First");
        School school = new School();school.setId(schoolId);school.setName("Pedro Ruiz Gallo");
        school.setLocation("Chorrillos");

        Student student = new Student();
        student.setId(studentId);student.setFirst_name("Rodrigo");student.setLast_name("Calle");
        student.setPassword("rokis123@");student.setMail("rodrigo@hotmail.com");
        student.setBornDate(born.getTime()); student.setLevel(level); student.setSchool(school);

        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));

        Student result = studentService.getStudentById(studentId);

        assertThat(result).isEqualTo(student);
    }

    @Test
    public void whenGetStudentReturnsStudentException(){
        Long levelId = 1L; Long schoolId = 1L; Long studentId = 1L;

        Throwable exception = catchThrowable(() -> {
            Student result = studentService.getStudentById(studentId);
        });

        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Resource Student not found for Id with value 1");
    }

    @Test
    public void whenDeleteStudentReturnsResponseEntity(){
        Long levelId = 1L; Long schoolId = 1L; Long studentId = 1L;
        Calendar born = new GregorianCalendar(2001,8,27);

        Level level = new Level();level.setId(levelId);level.setName("First");
        School school = new School();school.setId(schoolId);school.setName("Pedro Ruiz Gallo");
        school.setLocation("Chorrillos");

        Student student = new Student();
        student.setId(studentId);student.setFirst_name("Rodrigo");student.setLast_name("Calle");
        student.setPassword("rokis123@");student.setMail("rodrigo@hotmail.com");
        student.setBornDate(born.getTime()); student.setLevel(level); student.setSchool(school);

        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));

        ResponseEntity<?> result = studentService.deleteStudent(studentId);

        assertThat(result).isEqualTo(ResponseEntity.ok().build());
    }

    @Test
    public void whenDeleteStudentReturnsStudentException(){
        Long studentId = 1L;

        Throwable exception = catchThrowable(() -> {
            ResponseEntity<?> result = studentService.deleteStudent(studentId);
        });

        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Resource Student not found for Id with value 1");
    }

}
