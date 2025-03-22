package com.example.Project_week6.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StudentPatchData {
    @NotNull
    private long studentId;
    @NotNull
    private long solvedProblemId;
}
