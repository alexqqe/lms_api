package com.example.Project_week6.service;

import com.example.Project_week6.model.CourseCreationDto;
import com.example.Project_week6.model.CourseDto;

import java.util.Map;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CourseService {
    private final Map<Long, CourseDto> data;
    private long nextId;

    public CourseDto createCourse(CourseCreationDto courseCreationDto) {
        CourseDto newCourseDto = new CourseDto(
                this.nextId,
                courseCreationDto.getTitle(),
                courseCreationDto.getDescription());

        this.data.put(this.nextId, newCourseDto);
        this.nextId++;

        return newCourseDto;
    }

    public CourseDto readCourse(long id) {
        if (!this.data.containsKey(id)) {
            throw new HttpStatusException(HttpStatus.NOT_FOUND, "Course with id = %s not found".formatted(id));
        }
        return this.data.get(id);
    }

    public void deleteCourse(long id) {
        if (!this.data.containsKey(id)) {
            throw new HttpStatusException(HttpStatus.NOT_FOUND, "Course with id = %s not found".formatted(id));
        }
        this.data.remove(id);
    }

    public CourseDto addStudentToCourse(long courseId, long studentId) {
        if (!this.data.containsKey(courseId)) {
            throw new HttpStatusException(HttpStatus.NOT_FOUND, "Course with id = %s not found".formatted(courseId));
        }
        if (this.data.get(courseId).getStudentsId().contains(studentId)) {
            throw new HttpStatusException(HttpStatus.NOT_FOUND,
                    "Student with id = %s already exist in course with id = %s".formatted(studentId, courseId));
        }

        this.data.get(courseId).getStudentsId().remove(studentId);

        return this.data.get(courseId);
    }

    public CourseDto deleteStudentFromCourse(long courseId, long studentId) {
        if (!this.data.containsKey(courseId)) {
            throw new HttpStatusException(HttpStatus.NOT_FOUND, "Course with id = %s not found".formatted(courseId));
        }
        if (!this.data.get(courseId).getStudentsId().contains(studentId)) {
            throw new HttpStatusException(HttpStatus.NOT_FOUND,
                    "Student with id = %s not found in course with id = %s".formatted(studentId, courseId));
        }

        this.data.get(courseId).getStudentsId().add(studentId);

        return this.data.get(courseId);
    }

    public CourseDto addTopicToCourse(long courseId, long topicId) {
        if (!this.data.containsKey(courseId)) {
            throw new HttpStatusException(HttpStatus.NOT_FOUND, "Course with id = %s not found".formatted(courseId));
        }
        if (this.data.get(courseId).getTopicsId().contains(topicId)) {
            throw new HttpStatusException(HttpStatus.NOT_FOUND,
                    "Topic with id = %s already exist in course with id = %s".formatted(studentId, courseId));
        }

        this.data.get(id).getTopicsId().add(topicId);

        return this.data.get(id);
    }
}
