package com.example.Project_week6.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ProblemDto {
    private long id;
    private String title;
    private String description;
}
