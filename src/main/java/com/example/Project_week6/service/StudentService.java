package com.example.Project_week6.service;

import com.example.Project_week6.ecxeption.HttpStatusException;
import com.example.Project_week6.model.StudentCreationDto;
import com.example.Project_week6.model.StudentDto;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Getter
@Service
public class StudentService {
    private final Map<Long, StudentDto> data;
    private ProblemService problemService;
    private long nextId;


    @Autowired
    public StudentService(ProblemService problemService){
        this.data = new HashMap<>();
        this.problemService = problemService;
    }

    public StudentDto createStudent(StudentCreationDto studentCreationDto){
        long newId = this.nextId;
        this.nextId++;
        StudentDto newStudent = new StudentDto(newId, studentCreationDto.getLogin(),
                studentCreationDto.getFirstName(), studentCreationDto.getLastName(),
                studentCreationDto.getPhoneNumber());

        data.put(newId, newStudent);

        return newStudent;
    }

    public StudentDto getStudent(long id) {
        if (!this.data.containsKey(id)){
            throw new HttpStatusException(HttpStatus.NOT_FOUND, "Student with id = %s not found".formatted(id));
        }
            return data.get(id);
    }

    public StudentDto solvingTask(long studentId, long problemId){
        if (!this.data.containsKey(studentId)){
            throw new HttpStatusException(HttpStatus.NOT_FOUND, "Student with id = %s not found".formatted(studentId));
        }
        if (problemService.getData().get(problemId) == null){
            throw new HttpStatusException(HttpStatus.NOT_FOUND, "Problem with id = %s not found".formatted(studentId));
        }
        StudentDto student = data.get(studentId);
        student.getSolvedProblems().add(problemId);
        data.put(studentId, student);
        return student;
    }

    public StudentDto deleteStudent(long id){
        if (!this.data.containsKey(id)){
            throw new HttpStatusException(HttpStatus.NOT_FOUND, "Student with id = %s not found".formatted(id));
        }
        StudentDto student = data.get(id);
        data.remove(id);
        return student;
    }
}
