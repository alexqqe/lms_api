package com.example.Project_week6.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;

@Setter
@Getter
@AllArgsConstructor
public class StudentDto {
    private long id;
    private String login;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    private HashSet<Long> solvedProblems;

    public StudentDto(long id, String login, String firstName, String lastName, String phoneNumber){
        this.id = id;
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;

        solvedProblems = new HashSet<>();
    }
}
