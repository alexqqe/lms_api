package com.example.Project_week6.service;

import com.example.Project_week6.model.StudentCreationDto;
import com.example.Project_week6.model.StudentDto;

import java.util.HashMap;
import java.util.Map;

public class StudentService {
    private final Map<Long, StudentDto> data;

    public StudentService(){
        this.data = new HashMap<>();
    }

    public StudentDto createStudent(StudentCreationDto studentCreationDto){
        long newId = data.size();

        StudentDto newStudent = new StudentDto(newId, studentCreationDto.getLogin(),
                studentCreationDto.getFirstName(), studentCreationDto.getLastName(),
                studentCreationDto.getPhoneNumber());

        data.put(newId, newStudent);

        return newStudent;
    }

    public StudentDto getStudent(long id){
            return data.get(id);
    }

    public StudentDto solvingTask(long studentId, long problemId){
        StudentDto student = data.get(studentId);
        student.getSolvedProblems().add(problemId);
        data.put(studentId, student);
        return student;
    }

    public StudentDto deleteStudent(long id){
        StudentDto student = data.get(id);
        data.remove(id);
        return student;
    }
}
