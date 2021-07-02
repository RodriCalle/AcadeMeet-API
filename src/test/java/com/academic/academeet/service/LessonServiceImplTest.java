package com.academic.academeet.service;

import com.academic.academeet.domain.repository.ILessonRepository;
import com.academic.academeet.domain.service.ILessonService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class LessonServiceImplTest {

    @MockBean
    private ILessonRepository lessonRepository;

    @Autowired
    private ILessonService lessonService;

    @TestConfiguration
    static class LessonServiceTestConfiguration{
        @Bean
        public ILessonService lessonService(){
            return new LessonServiceImpl();
        }
    }

}