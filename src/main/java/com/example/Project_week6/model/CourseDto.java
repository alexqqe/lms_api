package com.example.Project_week6.model;

import lombok.Getter;

import java.util.HashSet;

@Getter
public class CourseDto {
    private long id;
    private String title;
    private String description;

    private HashSet<Long> topicsId;
    private HashSet<Long> studentsId;

    public CourseDto(long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;

        this.topicsId = new HashSet<>();
        this.studentsId = new HashSet<>();
    }
}
