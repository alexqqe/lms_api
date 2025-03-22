package com.example.Project_week6.controller;

import com.example.Project_week6.model.StudentCreationDto;
import com.example.Project_week6.model.Student;
import com.example.Project_week6.model.StudentPatchData;
import com.example.Project_week6.service.StudentService;
import com.example.Project_week6.сonverters.Converter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @PostMapping
    public Student postStudent(@Valid @RequestBody StudentCreationDto studentDto){
        Student student = Converter.StudentDTO2Model(studentDto);
        return studentService.createStudent(student);
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable long id){
        return studentService.getStudent(id);
    }

    @PatchMapping()
    public Student pathStudent(@RequestBody StudentPatchData studentPatchData){
        return studentService.solvingTask(studentPatchData.getStudentId(), studentPatchData.getSolvedProblemId());
    }

    @DeleteMapping("/{id}")
    public Student deleteStudentById(@PathVariable long id){
        return studentService.deleteStudent(id);
    }
}
