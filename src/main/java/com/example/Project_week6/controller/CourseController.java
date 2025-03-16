package com.example.Project_week6.controller;

import com.example.Project_week6.model.CourseDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.Project_week6.model.CourseCreationDto;
import com.example.Project_week6.service.CourseService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public CourseDto creatCourse(@Valid @RequestBody CourseCreationDto courseCreationDto) {
        return this.courseService.createCourse(courseCreationDto);
    }

    @GetMapping("/{id}")
    public CourseDto readCourse(@PathVariable long id) {
        return this.courseService.readCourse(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable long id) {
        this.courseService.deleteCourse(id);
    }

    @PostMapping("/{courseId}/enroll/{studentId}")
    public CourseDto addStudentToCourse(@PathVariable long courseId, @PathVariable long studentId) {
        return this.courseService.addStudentToCourse(courseId, studentId);
    }

    @PostMapping("/{courseId}/unenroll/{studentId}")
    public CourseDto deleteStudentFromCourse(@PathVariable long courseId, @PathVariable long studentId) {
        return this.courseService.deleteStudentFromCourse(courseId, studentId);
    }

    @PostMapping("/{id}/topics")
    public CourseDto addTopicToCourse(@PathVariable long courseId, @Valid @RequestBody long topicId) {
        return this.courseService.addTopicToCourse(courseId, topicId);
    }
}
