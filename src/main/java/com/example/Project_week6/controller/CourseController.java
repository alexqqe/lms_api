package com.example.Project_week6.controller;

import org.springframework.stereotype.Service;

import com.example.Project_week6.model.CourseCreationDto;
import com.example.Project_week6.service.CourseService;

@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public CourseService creatCourse(@Valid @RequestBody CourseCreationDto courseCreationDto) {
        return this.courseService.createCourse(courseCreationDto);
    }

    @GetMapping("/{id}")
    public CourseService readCourse(@PathVariable long id) {
        return this.courseService.readCourse(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable long id) {
        this.courseService.deleteCourse(id);
    }

    @PostMapping("/{courseId}/enroll/{studentId}")
    public CourseService addStudentToCourse(@PathVariable long courseId, @PathVariable long studentId) {
        return this.courseService.addStudentToCourse(courseId, studentId);
    }

    @PostMapping("/{courseId}/unenroll/{studentId}")
    public CourseService deleteStudentFromCourse(@PathVariable long courseId, @PathVariable long studentId) {
        return this.courseService.deleteStudentFromCourse(courseId, studentId);
    }

    @PostMapping("/{id}/topics")
    public CourseService addTopicToCourse(@PathVariable long courseId, @Valid @RequestBody long topicId) {
        return this.courseService.addTopicToCourse(courseId, topicId);
    }
}
