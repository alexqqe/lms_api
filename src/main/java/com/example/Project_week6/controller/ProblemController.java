package com.example.Project_week6.controller;

import com.example.Project_week6.model.Problem;
import com.example.Project_week6.service.ProblemService;
import com.example.Project_week6.service.TopicService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/problems")
public class ProblemController {
    private final ProblemService problemService;
    private final TopicService topicService;

    public ProblemController(ProblemService problemService, TopicService topicService) {
        this.problemService = problemService;  // внедрили через конструктор
        this.topicService = topicService;
    }

    @GetMapping("/{id}")
    public Problem getProblem(@PathVariable long id) {
        return this.problemService.getData().get(id);
    }

    @DeleteMapping("/{id}")
    public Problem deleteProblem(@PathVariable long id) {

        Problem problemBack = this.problemService.deleteProblem(id);
        for (long topicId: problemBack.getTopic()){
            topicService.getData().get(topicId).getProblems().remove(id);
        }

        return problemBack;
    }
}
