package com.example.Project_week6.controller;

import com.example.Project_week6.model.Course;
import com.example.Project_week6.model.Topic;
import com.example.Project_week6.model.TopicCreationDto;
import com.example.Project_week6.service.TopicService;
import com.example.Project_week6.сonverters.Converter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.Project_week6.model.CourseCreationDto;
import com.example.Project_week6.service.CourseService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;
    private final TopicService topicService;

    @Autowired
    public CourseController(CourseService courseService, TopicService topicService) {
        this.courseService = courseService;
        this.topicService = topicService;
    }

    @PostMapping
    public Course creatCourse(@Valid @RequestBody CourseCreationDto courseCreationDto) {
        Course course = Converter.CourseDTO2Model(courseCreationDto);
        return this.courseService.createCourse(course);
    }

    @GetMapping("/{id}")
    public Course readCourse(@PathVariable long id) {
        return this.courseService.readCourse(id);
    }

    @DeleteMapping("/{id}")
    public Course deleteCourse(@PathVariable long id) {
        return this.courseService.deleteCourse(id);
    }

    @PostMapping("/{courseId}/enroll/{studentId}")
    public Course addStudentToCourse(@PathVariable long courseId, @PathVariable long studentId) {
        return this.courseService.addStudentToCourse(courseId, studentId);
    }

    @PostMapping("/{courseId}/unenroll/{studentId}")
    public Course deleteStudentFromCourse(@PathVariable long courseId, @PathVariable long studentId) {
        return this.courseService.deleteStudentFromCourse(courseId, studentId);
    }

    @PostMapping("/{courseId}/topics")
    public Course addTopicToCourse(@PathVariable long courseId, @Valid @RequestBody TopicCreationDto topicDTO) {
        Topic topic = Converter.TopicDTO2Model(topicDTO);
        Topic createdtopic = topicService.createTopic(topic);
        //Будет обращение к топик сервису, в котором будем создавать топик
        //Id-шник топика будет передавать курссервису
        return this.courseService.addTopicToCourse(courseId, createdtopic);
    }
}
