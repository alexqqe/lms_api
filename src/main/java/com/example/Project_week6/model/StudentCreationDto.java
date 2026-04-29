package com.example.Project_week6.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
    @Pattern(regexp = "^\\+?[1-9]\\d{0,2}\\s?$?\\d{1,4}?$?[\\s.-]?\\d{1,4}[\\s.-]?\\d{1,4}[\\s.-]?\\d{1,9}$\n")
    private String phoneNumber;

}
