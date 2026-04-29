package com.example.Project_week6.service;

import com.example.Project_week6.ecxeption.HttpStatusException;
import com.example.Project_week6.model.Problem;
import com.example.Project_week6.model.Topic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TopicServiceTest {

    @InjectMocks
    private TopicService topicService;

    @Mock
    private ProblemService problemService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTopic_True() {
        Topic topicCreationDto = new Topic("Topic title", "Topic text");

        Topic createdTopic = topicService.createTopic(topicCreationDto);

        assertNotNull(createdTopic);
        assertEquals("Topic title", createdTopic.getTitle());
        assertEquals("Topic text", createdTopic.getText());
    }

    @Test
    void testGetTopic_True() {
        Topic topicCreationDto = new Topic("Topic title", "Topic text");
        Topic createdTopic = topicService.createTopic(topicCreationDto);

        Topic fetchedTopic = topicService.getTopic(createdTopic.getId());

        assertEquals(createdTopic.getId(), fetchedTopic.getId());
        assertEquals(createdTopic.getTitle(), fetchedTopic.getTitle());
        assertEquals(createdTopic.getText(), fetchedTopic.getText());
    }

    @Test
    void testGetTopic_NotFound() {
        HttpStatusException exception = assertThrows(HttpStatusException.class, () -> {
            topicService.getTopic(999L);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
        assertEquals("Topic with id = 999 not found", exception.getMessage());
    }

    @Test
    void testRemoveTopic_True() {
        Topic topicCreationDto = new Topic("Topic title", "Topic text");
        Topic createdTopic = topicService.createTopic(topicCreationDto);

        topicService.deleteTopic(createdTopic.getId());

        HttpStatusException exception = assertThrows(HttpStatusException.class, () -> {
            topicService.getTopic(createdTopic.getId());
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
    }

    @Test
    void testRemoveTopic_NotFound() {
        HttpStatusException exception = assertThrows(HttpStatusException.class, () -> {
            topicService.deleteTopic(999L);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
        assertEquals("Topic with id = 999 not found", exception.getMessage());
    }

    @Test
    void testCreateProblemInTopic_True() {
        Topic topicCreationDto = new Topic("Topic title", "Topic text");
        Topic createdTopic = topicService.createTopic(topicCreationDto);

        Problem problemCreationDto = new Problem("Problem title", "Problem desc");

        Problem mockProblem = new Problem(1L, "Problem title", "Problem desc");
        when(problemService.createProblem(problemCreationDto)).thenReturn(mockProblem);

        Problem createdProblem = topicService.createProblemInTopic(createdTopic.getId(), problemCreationDto);

        assertNotNull(createdProblem);
        assertEquals(1L, createdProblem.getId());
        assertTrue(topicService.getTopic(createdTopic.getId()).getProblems().contains(1L));
    }

    @Test
    void testCreateProblemInTopic_TopicNotFound() {
        Problem problemCreationDto = new Problem("Problem title", "Problem desc");

        HttpStatusException exception = assertThrows(HttpStatusException.class, () -> {
            topicService.createProblemInTopic(999L, problemCreationDto);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
        assertEquals("Topic with id = 999 not found", exception.getMessage());
    }
}
