package com.example.Project_week6.service;

import com.example.Project_week6.ecxeption.HttpStatusException;
import com.example.Project_week6.model.CourseCreationDto;
import com.example.Project_week6.model.Course;

import com.example.Project_week6.model.Topic;
import com.example.Project_week6.сonverters.Converter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

public class CourseServiceTest {

    @InjectMocks
    private CourseService courseService;

    @Mock
    private StudentService studentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateCourse_True() {
        String title = "Title";
        String description = "some desc";

        CourseCreationDto courseCreationDto = new CourseCreationDto(title, description);
        Course course = Converter.CourseDTO2Model(courseCreationDto);
        Course createdCourse = courseService.createCourse(course);

        assertNotNull(createdCourse);
        assertEquals(title, createdCourse.getTitle());
        assertEquals(description, createdCourse.getDescription());
    }

    @Test
    public void testReadCourse_True() {
        String title = "Title";
        String description = "some desc";

        CourseCreationDto courseCreationDto = new CourseCreationDto(title, description);
        Course course = Converter.CourseDTO2Model(courseCreationDto);
        Course createdCourse = courseService.createCourse(course);

        Course fetchedCourse = courseService.readCourse(createdCourse.getId());

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
        Course course = Converter.CourseDTO2Model(courseCreationDto);
        Course createdCourse = courseService.createCourse(course);

        courseService.deleteCourse(createdCourse.getId());

        HttpStatusException exception = assertThrows(HttpStatusException.class, () -> {
            courseService.readCourse(createdCourse.getId());
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
        assertEquals("Course with id = 0 not found", exception.getMessage());
    }

    @Test
    void testDeleteCourse_deletedBeforeCreated_False() {
        long nonExistentId = 999L;

        HttpStatusException exception = assertThrows(HttpStatusException.class, () -> {
            courseService.deleteCourse(nonExistentId);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
        assertEquals("Course with id = 999 not found", exception.getMessage());
    }

    @Test
    void addStudentToCourse_True() {
        CourseCreationDto courseCreationDto = new CourseCreationDto("title", "some desc");
        Course course = Converter.CourseDTO2Model(courseCreationDto);
        Course createdCourse = courseService.createCourse(course);

        Course updatedCourse = courseService.addStudentToCourse(createdCourse.getId(), 1L);

        assertTrue(updatedCourse.getStudentsId().contains(1L));
    }

    @Test
    void addStudentToCourse_CourseNotFound_False() {
        long nonExistentCourseId = 999L;

        HttpStatusException exception = assertThrows(HttpStatusException.class, () -> {
            courseService.addStudentToCourse(nonExistentCourseId, 1L);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
        assertEquals("Course with id = 999 not found", exception.getMessage());
    }

    @Test
    void addStudentToCourse_StudentAlreadyExists_False() {
        CourseCreationDto courseCreationDto = new CourseCreationDto("title", "some desc");
        Course course = Converter.CourseDTO2Model(courseCreationDto);
        Course createdCourse = courseService.createCourse(course);

        courseService.addStudentToCourse(createdCourse.getId(), 1L);

        HttpStatusException exception = assertThrows(HttpStatusException.class, () -> {
            courseService.addStudentToCourse(createdCourse.getId(), 1L);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
        assertEquals("Student with id = 1 already exist in course with id = 0", exception.getMessage());
    }

    @Test
    void deleteStudentFromCourse_True() {
        CourseCreationDto courseCreationDto = new CourseCreationDto("title", "some desc");
        Course course = Converter.CourseDTO2Model(courseCreationDto);
        Course createdCourse = courseService.createCourse(course);

        courseService.addStudentToCourse(createdCourse.getId(), 1L);
        Course updatedCourse = courseService.deleteStudentFromCourse(createdCourse.getId(), 1L);

        assertFalse(updatedCourse.getStudentsId().contains(1L));
    }

    @Test
    void deleteStudentFromCourse_CourseNotFound_False() {
        long nonExistentCourseId = 999L;

        HttpStatusException exception = assertThrows(HttpStatusException.class, () -> {
            courseService.deleteStudentFromCourse(nonExistentCourseId, 1L);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
        assertEquals("Course with id = 999 not found", exception.getMessage());
    }

    @Test
    void deleteStudentFromCourse_StudentNotFound_False() {
        CourseCreationDto courseCreationDto = new CourseCreationDto("title", "some desc");
        Course course = Converter.CourseDTO2Model(courseCreationDto);
        Course createdCourse = courseService.createCourse(course);

        HttpStatusException exception = assertThrows(HttpStatusException.class, () -> {
            courseService.deleteStudentFromCourse(createdCourse.getId(), 1L);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
        assertEquals("Student with id = 1 not found in course with id = 0", exception.getMessage());
    }

    @Test
    void addTopicToCourse_CourseNotFound_False() {
        long nonExistentCourseId = 999L;

        Topic topic = new Topic(12, "title", "text");

        HttpStatusException exception = assertThrows(HttpStatusException.class, () -> {
            courseService.addTopicToCourse(nonExistentCourseId, topic);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
        assertEquals("Course with id = 999 not found", exception.getMessage());
    }
}
