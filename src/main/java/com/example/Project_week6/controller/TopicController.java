package com.example.Project_week6.controller;

import com.example.Project_week6.model.ProblemCreationDto;
import com.example.Project_week6.model.ProblemDto;
import com.example.Project_week6.model.TopicDto;
import com.example.Project_week6.service.TopicService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topics")
public class TopicController {
    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping("/{id}")
    public TopicDto getTopic(@PathVariable long id) {
        return this.topicService.getData().get(id);
    }

    @DeleteMapping("/{id}")
    public void deleteTopic(@PathVariable long id) {
        this.topicService.removeTopic(id);
    }

    @PostMapping("/{id}/problems")
    public ProblemDto createProblem(@Valid @PathVariable long topicId, @RequestBody ProblemCreationDto problem) {
        return this.topicService.createProblemInTopic(topicId, problem);
    }
}
