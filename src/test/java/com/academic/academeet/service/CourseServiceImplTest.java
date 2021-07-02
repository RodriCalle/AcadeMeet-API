package com.academic.academeet.service;

import com.academic.academeet.domain.model.Course;
import com.academic.academeet.domain.repository.ICourseRepository;
import com.academic.academeet.domain.service.ICourseService;
import com.academic.academeet.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class CourseServiceImplTest {

    @MockBean
    private ICourseRepository courseRepository;

    @Autowired
    private ICourseService courseService;

    @TestConfiguration
    static class CourseServiceTestConfiguration{
        @Bean
        public ICourseService courseService(){
            return new CourseServiceImpl();
        }
    }

    @Test
    public void WhenGetCourseByIdWithValidIdThenReturnsCourse(){
        //Arrange
        Long id = 1L;
        Course course = new Course();
        course.setId(id);
        course.setName("Aritmetica");
        when(courseRepository.findById(id)).thenReturn(Optional.of(course));
        //Act
        Optional<Course> foundCourse = courseService.getCourseById(id);
        //Assert
        assertThat(foundCourse.get().getId()).isEqualTo(id);
    }

    @Test
    public void WhenGetCourseByIdWithInvalidIdThenReturnsNull(){
        //Arrange
        Long id = 1L;
        when(courseRepository.findById(id)).thenReturn(null);
        //Act
        Optional<Course> foundCourse = courseService.getCourseById(id);
        //Assert
        assertThat(foundCourse).isEqualTo(null);
    }

    @Test
    public void WhenDeleteCourseByIdWithInvalidIdThenReturnsResourceNotFoundException(){
        //Arrange
        Long id = 1L;
        String template = "Resource %s not found for %s with value %s";
        when(courseRepository.findById(id)).thenReturn(Optional.empty());
        String  expectedMessage = String.format(template, "Course", "Id", id);

        //Act
        Throwable exception = catchThrowable(() -> {
            ResponseEntity<?> result = courseService.deleteCourse(id);
        });

        //Assert
        assertThat(exception).isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    public void WhenDeleteCourseByIdWithValidIdThenReturnsResponseEntityOk(){
        //Arrange
        Long id = 1L;
        Course course = new Course();
        course.setId(id);

        when(courseRepository.findById(id)).thenReturn(Optional.of(course));

        //Act
        ResponseEntity<?> result = courseService.deleteCourse(id);

        //Assert
        assertThat(result).isEqualTo(ResponseEntity.ok().build());
    }

    @Test
    public void whenSaveCourseWithValidNameReturnsCourse() {
        // Arrange
        Long id = 1L;

        Course course = new Course();

        course.setId(id);
        course.setName("matematica");

        when(courseRepository.save(course)).thenReturn(course);

        // ACt
        Course result = courseService.createCourse(course);
        // Assert
        assertThat(result).isEqualTo(course);
    }

    /*
    @Test
    public void whenUpdateCourseWithInvalidIdThenReturnsResourceNotFoundException() {
        // Arrange
        Long id = 1L;

        Course updatecourse = new Course();

        updatecourse.setId(id);
        updatecourse.setName("Math");

        when(courseRepository.findById(id)).thenReturn(Optional.empty());
        when(courseRepository.save(updatecourse)).thenReturn(updatecourse);

        String expectedMessage = String.format("Resource %s not found for %s with value %s", "Course", "Id", id);

        // Act
        Throwable exception = catchThrowable(() -> {
            Course course = courseService.updateCourse(id,updatecourse);
        });

        // Assert
        assertThat(exception).isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }
    */
}
