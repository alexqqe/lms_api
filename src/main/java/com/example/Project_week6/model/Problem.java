package com.example.Project_week6.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;

@AllArgsConstructor
@Getter
@Setter
public class Problem {
    private long id;
    private String title;
    private String description;
    private final HashSet<Long> topic = new HashSet<>();

    public Problem(String title, String description){
        this.title = title;
        this.description = description;
    }
}
