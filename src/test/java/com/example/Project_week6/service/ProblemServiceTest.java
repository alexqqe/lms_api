package com.example.Project_week6.service;

import com.example.Project_week6.ecxeption.HttpStatusException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

public class ProblemServiceTest {

    private ProblemService problemService;

    @BeforeEach
    public void setUp() {
        problemService = new ProblemService();
    }

    @Test
    void testCreateProblem_True() {


        assertNotNull(createdProblem);
        assertEquals(0L, createdProblem.getId());
        assertEquals("Problem title", createdProblem.getTitle());
        assertEquals("Problem desc", createdProblem.getDescription());

        // Проверяем, что проблема сохранена в мапу
        assertTrue(problemService.getData().containsKey(0L));
    }

    @Test
    void testCreateMultipleProblems_IdsIncrement() {


        assertEquals(0L, problem1.getId());
        assertEquals(1L, problem2.getId());
        assertEquals(2L, problemService.getNextId()); // nextId должен быть 2 после двух созданий
    }

    @Test
    void testRemoveProblem_True() {

        assertTrue(problemService.getData().containsKey(createdProblem.getId()));

        problemService.deleteProblem(createdProblem.getId());

        assertFalse(problemService.getData().containsKey(createdProblem.getId()));
    }

    @Test
    void testRemoveProblem_NotFound() {
        HttpStatusException exception = assertThrows(HttpStatusException.class, () -> {
            problemService.deleteProblem(999L);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
        assertEquals("Problem with id = 999 not found", exception.getMessage());
    }
}
