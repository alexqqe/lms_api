package com.example.Project_week6.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class StudentCreationDto {
    @NotBlank
    private String login;
    @NotBlank
        private String firstName;
    @NotBlank
    private String lastName;
    private String phoneNumber;

}
