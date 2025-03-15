package com.example.Project_week6.model;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;

@Setter
@Getter
public class TopicDto {
    private long id;
    private String title;
    private String text;

    private HashSet<Long> problems;

    public TopicDto(long id, String title, String text){
        this.id = id;
        this.title = title;
        this.text = text;

        problems = new HashSet<>();
    }
}
