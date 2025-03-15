package com.example.Project_week6.service;

import com.example.Project_week6.model.ProblemDto;
import org.springframework.stereotype.Service;

@Service
public class ProblemService {

    public ProblemDto getProblem(long id){
        return new ProblemDto(123, "bla", "bla");
    }
}
