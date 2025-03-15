package com.example.Project_week6.controller;

import com.example.Project_week6.model.StudentCreationDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    @PostMapping
    public void postStudent(@Valid @RequestBody StudentCreationDto student){
        System.out.println(student.toString());
    }

    @GetMapping("/{id}")
    public void getStudentById(@PathVariable long id){
        System.out.println("Student was asked: " + id);
    }

    @PatchMapping()
    public void pathStudent(){
        System.out.println("Pathced");
    }

    @DeleteMapping("/{id}")
    public void deleteStudentById(@PathVariable long id){
        System.out.println("Student with id " + id + " was deleted");
    }
}
