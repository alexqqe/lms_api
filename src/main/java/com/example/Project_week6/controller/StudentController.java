package com.example.Project_week6.controller;

import com.example.Project_week6.model.StudentCreationDto;
import com.example.Project_week6.model.StudentDto;
import com.example.Project_week6.model.StudentPatchData;
import com.example.Project_week6.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @PostMapping
    public StudentDto postStudent(@Valid @RequestBody StudentCreationDto student){
        return studentService.createStudent(student);
    }

    @GetMapping("/{id}")
    public StudentDto getStudentById(@PathVariable long id){
        return studentService.getStudent(id);
    }

    @PatchMapping()
    public StudentDto pathStudent(@RequestBody StudentPatchData studentPatchData){
        return studentService.solvingTask(studentPatchData.getStudentId(), studentPatchData.getSolvedProblemId());
    }

    @DeleteMapping("/{id}")
    public StudentDto deleteStudentById(@PathVariable long id){
        return studentService.deleteStudent(id);
    }
}
