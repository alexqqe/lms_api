package com.example.Project_week6.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;

@Setter
@Getter
@AllArgsConstructor
public class CourseCreationDto {
    @NotBlank
    private String title;
    private String description;
}
