package com.example.Project_week6.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class TopicCreationDto {
    @NotBlank
    private String title;
    private String text;
}
