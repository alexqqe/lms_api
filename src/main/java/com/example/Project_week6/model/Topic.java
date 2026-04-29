package com.example.Project_week6.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;

@Setter
@Getter
@AllArgsConstructor
public class Topic {
    private long id;
    private String title;
    private String text;

    private final HashSet<Long> problems = new HashSet<>();
    private final HashSet<Long> courses = new HashSet<>();

    public Topic(String title, String text){
        this.title = title;
        this.text = text;
    }
}
