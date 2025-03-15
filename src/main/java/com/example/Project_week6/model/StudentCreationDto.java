package com.example.Project_week6.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;

@Getter
@Setter
@AllArgsConstructor
public class StudentCreationDto {
    private String login;
    private String firstName;
    private String lastName;
    private String phoneNumber;

}
