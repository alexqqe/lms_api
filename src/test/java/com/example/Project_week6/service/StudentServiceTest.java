package com.example.Project_week6.service;

import com.example.Project_week6.ecxeption.HttpStatusException;
import com.example.Project_week6.model.ProblemDto;
import com.example.Project_week6.model.StudentCreationDto;
import com.example.Project_week6.model.StudentDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private ProblemService problemService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateStudent() {
        StudentCreationDto studentCreationDto = new StudentCreationDto("login", "First", "Last", "1234567890");
        StudentDto createdStudent = studentService.createStudent(studentCreationDto);

        assertNotNull(createdStudent);
        assertEquals("login", createdStudent.getLogin());
        assertEquals("First", createdStudent.getFirstName());
        assertEquals("Last", createdStudent.getLastName());
        assertEquals("1234567890", createdStudent.getPhoneNumber());
    }

    @Test
    public void testGetStudent() {
        StudentCreationDto studentCreationDto = new StudentCreationDto("login", "First", "Last", "1234567890");
        StudentDto createdStudent = studentService.createStudent(studentCreationDto);

        StudentDto fetchedStudent = studentService.getStudent(createdStudent.getId());

        assertEquals(createdStudent, fetchedStudent);
    }

    @Test
    public void testGetStudentNotFound() {
        assertThrows(HttpStatusException.class, () -> {
            studentService.getStudent(999L);
        });
    }

    @Test
    public void testSolvingTask() {
        StudentCreationDto studentCreationDto = new StudentCreationDto("login", "First", "Last", "1234567890");
        StudentDto createdStudent = studentService.createStudent(studentCreationDto);

        long problemId = 1L;
        when(problemService.getProblem(problemId)).thenReturn(new ProblemDto(problemId, "Problem title", "12345"));

        StudentDto updatedStudent = studentService.solvingTask(createdStudent.getId(), problemId);

        assertTrue(updatedStudent.getSolvedProblems().contains(problemId));
    }

    @Test
    public void testSolvingTaskStudentNotFound() {
        assertThrows(HttpStatusException.class, () -> {
            studentService.solvingTask(999L, 1L);
        });
    }

    @Test
    public void testSolvingTaskProblemNotFound() {
        StudentCreationDto studentCreationDto = new StudentCreationDto("login", "First", "Last", "1234567890");
        StudentDto createdStudent = studentService.createStudent(studentCreationDto);

        long problemId = 1L;
        when(problemService.getProblem(problemId)).thenReturn(null);

        assertThrows(HttpStatusException.class, () -> {
            studentService.solvingTask(createdStudent.getId(), problemId);
        });
    }

    @Test
    public void testDeleteStudent() {
        StudentCreationDto studentCreationDto = new StudentCreationDto("login", "First", "Last", "1234567890");
        StudentDto createdStudent = studentService.createStudent(studentCreationDto);

        StudentDto deletedStudent = studentService.deleteStudent(createdStudent.getId());

        assertEquals(createdStudent, deletedStudent);
        assertThrows(HttpStatusException.class, () -> {
            studentService.getStudent(createdStudent.getId());
        });
    }

    @Test
    public void testDeleteStudentNotFound() {
        assertThrows(HttpStatusException.class, () -> {
            studentService.deleteStudent(999L);
        });
    }
}
