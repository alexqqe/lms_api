package com.example.Project_week6.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;

@Setter
@Getter
@AllArgsConstructor
public class Student {
    private long id;
    private String login;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    private final HashSet<Long> solvedProblems = new HashSet<>();

    public Student(String login, String firstName, String lastName, String phoneNumber){
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }
}
