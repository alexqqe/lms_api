package com.example.Project_week6.model;

import java.util.HashSet;

public class CourseDto {
    private long id;
    private String title;
    private String description;

    private HashSet<Long> topics_id;
    private HashSet<Long> students_id;

    public CourseDto(long id, String title, String description){
        this.id = id;
        this.title = title;
        this.description = description;

        this.topics_id = new HashSet<>();
        this.students_id = new HashSet<>();
    }
}
