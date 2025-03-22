package com.example.Project_week6.service;

import com.example.Project_week6.ecxeption.HttpStatusException;
import com.example.Project_week6.model.Student;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Getter
@Service
public class StudentService {
    private final Map<Long, Student> data;
    private final ProblemService problemService;
    private long nextId;


    @Autowired
    public StudentService(ProblemService problemService){
        this.data = new HashMap<>();
        this.problemService = problemService;
    }

    public Student createStudent(Student student){
        long newId = this.nextId;
        this.nextId++;
        student.setId(newId);
        data.put(newId, student);

        return student;
    }

    public Student getStudent(long id) {
        if (!this.data.containsKey(id)){
            throw new HttpStatusException(HttpStatus.NOT_FOUND, "Student with id = %s not found".formatted(id));
        }
            return data.get(id);
    }

    public Student solvingTask(long studentId, long problemId){
        if (!this.data.containsKey(studentId)){
            throw new HttpStatusException(HttpStatus.NOT_FOUND, "Student with id = %s not found".formatted(studentId));
        }
        if (problemService.getData().get(problemId) == null){
            throw new HttpStatusException(HttpStatus.NOT_FOUND, "Problem with id = %s not found".formatted(studentId));
        }
        Student student = data.get(studentId);
        student.getSolvedProblems().add(problemId);
        data.put(studentId, student);
        return student;
    }

    public Student deleteStudent(long id){
        if (!this.data.containsKey(id)){
            throw new HttpStatusException(HttpStatus.NOT_FOUND, "Student with id = %s not found".formatted(id));
        }
        Student student = data.get(id);
        data.remove(id);
        return student;
    }
}
