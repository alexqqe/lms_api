package com.example.Project_week6.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;

@Getter
@AllArgsConstructor
@Setter
public class Course {
    private long id;
    private String title;
    private String description;

    private final HashSet<Long> topicsId = new HashSet<>();
    private final HashSet<Long> studentsId = new HashSet<>();

    public Course(String title, String description){
        this.title = title;
        this.description = description;
    }

}
