package com.example.Project_week6.service;

import com.example.Project_week6.ecxeption.HttpStatusException;
import com.example.Project_week6.model.Course;

import java.util.HashMap;
import java.util.Map;

import com.example.Project_week6.model.Topic;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Getter
public class CourseService {
    private final Map<Long, Course> data;
    private long nextId;
    private final StudentService studentService;

    @Autowired
    public CourseService(StudentService studentService){
        this.data = new HashMap<>();
        this.nextId = 0;
        this.studentService = studentService;
    }

    public Course createCourse(Course course) {
        long newId = this.nextId;
        course.setId(newId);
        this.data.put(newId, course);

        this.nextId++;
        return course;
    }

    public Course readCourse(long id) {
        if (!this.data.containsKey(id)) {
            throw new HttpStatusException(HttpStatus.NOT_FOUND, "Course with id = %s not found".formatted(id));
        }
        return this.data.get(id);
    }

    public Course deleteCourse(long id) {
        if (!this.data.containsKey(id)) {
            throw new HttpStatusException(HttpStatus.NOT_FOUND, "Course with id = %s not found".formatted(id));
        }
        Course courseBack = this.data.get(id);
        this.data.remove(id);

        return courseBack;
    }

    public Course addStudentToCourse(long courseId, long studentId) {
        if (!this.data.containsKey(courseId)) {
            throw new HttpStatusException(HttpStatus.NOT_FOUND, "Course with id = %s not found".formatted(courseId));
        }
        if (this.data.get(courseId).getStudentsId().contains(studentId)) {
            throw new HttpStatusException(HttpStatus.NOT_FOUND,
                    "Student with id = %s already exist in course with id = %s".formatted(studentId, courseId));
        }
        if (!studentService.getData().containsKey(studentId)){
            throw new HttpStatusException(HttpStatus.BAD_REQUEST,
                    "Student with id = %s doesnt't exist".formatted(studentId));
        }

        this.data.get(courseId).getStudentsId().add(studentId);

        return this.data.get(courseId);
    }

    public Course deleteStudentFromCourse(long courseId, long studentId) {
        if (!this.data.containsKey(courseId)) {
            throw new HttpStatusException(HttpStatus.NOT_FOUND, "Course with id = %s not found".formatted(courseId));
        }
        if (!this.data.get(courseId).getStudentsId().contains(studentId)) {
            throw new HttpStatusException(HttpStatus.NOT_FOUND,
                    "Student with id = %s not found in course with id = %s".formatted(studentId, courseId));
        }

        this.data.get(courseId).getStudentsId().remove(studentId);

        return this.data.get(courseId);
    }

    public Course addTopicToCourse(long courseId, Topic topic) {
        if (!this.data.containsKey(courseId)) {
            throw new HttpStatusException(HttpStatus.NOT_FOUND, "Course with id = %s not found".formatted(courseId));
        }
        topic.getCourses().add(courseId);

        this.data.get(courseId).getTopicsId().add(topic.getId());

        return this.data.get(courseId);
    }
}
