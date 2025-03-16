package com.example.Project_week6.controller;

import com.example.Project_week6.model.ProblemDto;
import com.example.Project_week6.service.ProblemService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/problems")
public class ProblemController {
    private final ProblemService problemService;

    public ProblemController(ProblemService problemService) {
        this.problemService = problemService;  // внедрили через конструктор
    }

    @GetMapping("/{id}")
    public ProblemDto getProblem(@PathVariable long id) {
        return this.problemService.getData().get(id);
    }

    @DeleteMapping("/{id}")
    public void deleteProblem(@PathVariable long id) {
        this.problemService.removeProblem(id);
    }
}
