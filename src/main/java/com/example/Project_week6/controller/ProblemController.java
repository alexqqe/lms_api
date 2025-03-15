package com.example.Project_week6.controller;

@RestController
@RequestMapping("/problems")
public class ProblemController {
    private final ProblemService problemService;
    @GetMapping("/{id}")
    public ProblemDto getProblem(@PathVariable long id) {

    }

    @DeleteMapping("/{id}")
    public void deleteProblem(@PathVariable long id) {

    }
}
