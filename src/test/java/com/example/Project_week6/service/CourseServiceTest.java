package com.example.Project_week6.service;

import com.example.Project_week6.ecxeption.HttpStatusException;
import com.example.Project_week6.model.CourseCreationDto;
import com.example.Project_week6.model.CourseDto;
import com.example.Project_week6.service.CourseService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.beans.Transient;

public class CourseServiceTest {

    @InjectMocks
    private CourseService CourseService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateCourse_True() {
        String title = "Title";
        String description = "some desc";

        CourseCreationDto courseCreationDto = new CourseCreationDto(title, description);
        CourseDto createdCourse = CourseService.createCourse(courseCreationDto);

        assertNotNull(createdCourse);
        assertEquals(title, createdCourse.getTitle());
        assertEquals(description, createdCourse.getDescription());
    }

    @Test
    public void testReadCourse_True() {
        String title = "Title";
        String description = "some desc";

        CourseCreationDto courseCreationDto = new CourseCreationDto(title, description);
        CourseDto createdCourse = CourseService.createCourse(courseCreationDto);

        CourseDto fetchedCourse = CourseService.readCourse(createdCourse.getId());

        assertNotNull(createdCourse);
        assertNotNull(fetchedCourse);
        assertEquals(createdCourse, fetchedCourse);
    }

    @Test
    public void testReadCourse_NotFound() {
        assertThrows(HttpStatusException.class, () -> {
            studentService.getStudent(999L);
        });
    }

    @Test
    void testDeleteCourse_deletedAfterCreated_True() {
        CourseCreationDto courseCreationDto = new CourseCreationDto("title", "some desc");
        CourseDto createdCourse = CourseService.createCourse(courseCreationDto);

        courseService.deleteCourse(createdCourse.getId());

        HttpStatusException exception = assertThrows(HttpStatusException.class, () -> {
            courseService.readCourse(createdCourse.getId());
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
        assertEquals("Course with id = 0 not found", exception.getMessage());
    }

    @Test
    void testDeleteCourse_deletedBeforeCreated_False() {
        long nonExistentId = 999L;

        HttpStatusException exception = assertThrows(HttpStatusException.class, () -> {
            courseService.deleteCourse(nonExistentId);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
        assertEquals("Course with id = 999 not found", exception.getMessage());
    }

    @Test
    void addStudentToCourse_True() {
        CourseCreationDto courseCreationDto = new CourseCreationDto("title", "some desc");
        CourseDto createdCourse = courseService.createCourse(courseCreationDto);

        CourseDto updatedCourse = courseService.addStudentToCourse(createdCourse.getId(), 1L);

        assertTrue(updatedCourse.getStudentsId().contains(1L));
    }

    @Test
    void addStudentToCourse_CourseNotFound_False() {
        long nonExistentCourseId = 999L;

        HttpStatusException exception = assertThrows(HttpStatusException.class, () -> {
            courseService.addStudentToCourse(nonExistentCourseId, 1L);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
        assertEquals("Course with id = 999 not found", exception.getMessage());
    }

    @Test
    void addStudentToCourse_StudentAlreadyExists_False() {
        CourseCreationDto courseCreationDto = new CourseCreationDto("title", "some desc");
        CourseDto createdCourse = courseService.createCourse(courseCreationDto);

        courseService.addStudentToCourse(createdCourse.getId(), 1L);

        HttpStatusException exception = assertThrows(HttpStatusException.class, () -> {
            courseService.addStudentToCourse(createdCourse.getId(), 1L);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
        assertEquals("Student with id = 1 already exist in course with id = 0", exception.getMessage());
    }

    @Test
    void deleteStudentFromCourse_True() {
        CourseCreationDto courseCreationDto = new CourseCreationDto("title", "some desc");
        CourseDto createdCourse = courseService.createCourse(courseCreationDto);

        courseService.addStudentToCourse(createdCourse.getId(), 1L);
        CourseDto updatedCourse = courseService.deleteStudentFromCourse(createdCourse.getId(), 1L);

        assertFalse(updatedCourse.getStudentsId().contains(1L));
    }

    @Test
    void deleteStudentFromCourse_CourseNotFound_False() {
        long nonExistentCourseId = 999L;

        HttpStatusException exception = assertThrows(HttpStatusException.class, () -> {
            courseService.deleteStudentFromCourse(nonExistentCourseId, 1L);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
        assertEquals("Course with id = 999 not found", exception.getMessage());
    }

    @Test
    void deleteStudentFromCourse_StudentNotFound_False() {
        CourseCreationDto courseCreationDto = new CourseCreationDto("title", "some desc");
        CourseDto createdCourse = courseService.createCourse(courseCreationDto);

        HttpStatusException exception = assertThrows(HttpStatusException.class, () -> {
            courseService.deleteStudentFromCourse(createdCourse.getId(), 1L);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
        assertEquals("Student with id = 1 not found in course with id = 0", exception.getMessage());
    }

    @Test
    void addTopicToCourse_True() {
        CourseCreationDto courseCreationDto = new CourseCreationDto("title", "some desc");
        CourseDto createdCourse = courseService.createCourse(courseCreationDto);

        CourseDto updatedCourse = courseService.addTopicToCourse(createdCourse.getId(), 1L);

        assertTrue(updatedCourse.getTopicsId().contains(1L));
    }

    @Test
    void addTopicToCourse_CourseNotFound_False() {
        long nonExistentCourseId = 999L;

        HttpStatusException exception = assertThrows(HttpStatusException.class, () -> {
            courseService.addTopicToCourse(nonExistentCourseId, 1L);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
        assertEquals("Course with id = 999 not found", exception.getMessage());
    }

    @Test
    void addTopicToCourse_TopicAlreadyExists_False() {
        CourseCreationDto courseCreationDto = new CourseCreationDto("title", "some desc");
        CourseDto createdCourse = courseService.createCourse(courseCreationDto);

        courseService.addTopicToCourse(createdCourse.getId(), 1L);

        HttpStatusException exception = assertThrows(HttpStatusException.class, () -> {
            courseService.addTopicToCourse(createdCourse.getId(), 1L);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
        assertEquals("Topic with id = 1 already exist in course with id = 0", exception.getMessage());
    }
}
