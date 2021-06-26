package com.academic.academeet.service;

import com.academic.academeet.domain.model.Lesson;
import com.academic.academeet.domain.repository.*;
import com.academic.academeet.domain.service.ILessonService;
import com.academic.academeet.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LessonServiceImpl implements ILessonService {
    @Autowired
    private ILessonRepository lessonRepository;
    @Autowired
    private ICourseRepository courseRepository;
    @Autowired
    private ITutorRepository tutorRepository;
    @Autowired
    private LessonTypeRepository lessonTypeRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;


    @Override
    public Lesson createLesson(Long courseId, Long tutorId, Long lessonTypeId, Long scheduleId, Lesson lesson) {
        return courseRepository.findById(courseId).map(course -> {
            lesson.setCourse(course);
            return tutorRepository.findById(tutorId).map(tutor -> {
                lesson.setTutor(tutor);
                return lessonTypeRepository.findById(lessonTypeId).map(lessontype -> {
                    lesson.setLesson_type(lessontype);
                    return scheduleRepository.findById(scheduleId).map(schedule -> {
                        lesson.setSchedule(schedule);
                        return lessonRepository.save(lesson);
                    }).orElseThrow(() -> new ResourceNotFoundException("Schedule", "Id", scheduleId));
                }).orElseThrow(() -> new ResourceNotFoundException("LessonType", "Id", lessonTypeId));
            }).orElseThrow(() -> new ResourceNotFoundException("Lesson", "Id", tutorId));
        }).orElseThrow(() -> new ResourceNotFoundException("Course", "Id", courseId));
    }

    @Override
    public Lesson updateLesson(Long lessonId, Lesson lessonDetails) {
        return lessonRepository.findById(lessonId).map(lesson ->{
                    lesson.setTopic(lessonDetails.getTopic());
                    return lessonRepository.save(lesson);
                }
        ).orElseThrow(() -> new ResourceNotFoundException("Lesson", "Id", lessonId));
    }

    @Override
    public Lesson getLessonById(Long lessonId) {
        return lessonRepository.findById(lessonId)
                .orElseThrow(()-> new ResourceNotFoundException("Lesson", "Id", lessonId));
    }

    @Override
    public ResponseEntity<?> deleteLesson(Long lessonId) {
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(()-> new ResourceNotFoundException("Lesson", "Id", lessonId));
        lessonRepository.delete(lesson);
        return ResponseEntity.ok().build();
    }

    @Override
    public Page<Lesson> getAll(Pageable pageable) {
        return lessonRepository.findAll(pageable);
    }
}
