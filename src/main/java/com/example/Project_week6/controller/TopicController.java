package com.example.Project_week6.controller;

import com.example.Project_week6.model.ProblemCreationDto;
import com.example.Project_week6.model.Problem;
import com.example.Project_week6.model.Topic;
import com.example.Project_week6.service.CourseService;
import com.example.Project_week6.service.ProblemService;
import com.example.Project_week6.service.TopicService;
import com.example.Project_week6.сonverters.Converter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topics")
public class TopicController {
    private final TopicService topicService;
    private final ProblemService problemService;
    private final CourseService courseService;

    @Autowired
    public TopicController(TopicService topicService, ProblemService problemService, CourseService courseService){
        this.topicService = topicService;
        this.problemService = problemService;
        this.courseService = courseService;
    }

    @GetMapping("/{id}")
    public Topic getTopic(@PathVariable long id) {
        return this.topicService.getTopic(id);
    }

    @DeleteMapping("/{id}")
    public Topic deleteTopic(@PathVariable long id) {

        Topic topicBack = this.topicService.deleteTopic(id);
        for (long courseId: topicBack.getCourses()){
            courseService.getData().get(courseId).getTopicsId().remove(id);
        }

        return topicBack;
    }

    @PostMapping("/{topicId}/problems")
    public Problem createProblem(@Valid @PathVariable long topicId, @RequestBody ProblemCreationDto problemCreationDto) {
        Problem problem = Converter.ProblemDTO2Model(problemCreationDto);
        Problem createdProblem = problemService.createProblem(problem);
        return this.topicService.createProblemInTopic(topicId, createdProblem);
    }
}
