package com.example.Project_week6.service;

import com.example.Project_week6.ecxeption.HttpStatusException;
import com.example.Project_week6.model.ProblemCreationDto;
import com.example.Project_week6.model.ProblemDto;
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
        ProblemCreationDto problemCreationDto = new ProblemCreationDto("Problem title", "Problem desc");

        ProblemDto createdProblem = problemService.createProblem(problemCreationDto);

        assertNotNull(createdProblem);
        assertEquals(0L, createdProblem.getId());
        assertEquals("Problem title", createdProblem.getTitle());
        assertEquals("Problem desc", createdProblem.getDescription());

        // Проверяем, что проблема сохранена в мапу
        assertTrue(problemService.getData().containsKey(0L));
    }

    @Test
    void testCreateMultipleProblems_IdsIncrement() {
        ProblemCreationDto firstProblem = new ProblemCreationDto("First", "First desc");
        ProblemCreationDto secondProblem = new ProblemCreationDto("Second", "Second desc");

        ProblemDto problem1 = problemService.createProblem(firstProblem);
        ProblemDto problem2 = problemService.createProblem(secondProblem);

        assertEquals(0L, problem1.getId());
        assertEquals(1L, problem2.getId());
        assertEquals(2L, problemService.getNextId()); // nextId должен быть 2 после двух созданий
    }

    @Test
    void testRemoveProblem_True() {
        ProblemCreationDto problemCreationDto = new ProblemCreationDto("Problem title", "Problem desc");
        ProblemDto createdProblem = problemService.createProblem(problemCreationDto);

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
