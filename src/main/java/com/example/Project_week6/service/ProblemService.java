package com.example.Project_week6.service;

import com.example.Project_week6.ecxeption.HttpStatusException;
import com.example.Project_week6.model.Problem;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Getter
public class ProblemService {
    private final Map<Long, Problem> data;
    private long nextId;

    public ProblemService() {
        this.data = new HashMap<>();
        this.nextId = 0;}

    public Problem createProblem(Problem problem) {
        long newId = this.nextId;
        this.nextId++;

        problem.setId(newId);

        this.data.put(newId, problem);

        return problem;
    }

    public Problem deleteProblem(long id) {
        if (!this.data.containsKey(id)) {
            throw new HttpStatusException(HttpStatus.NOT_FOUND, "Problem with id = %s not found".formatted(id));
        }
        Problem problemBack = this.data.get(id);

        this.data.remove(id);
        return problemBack;
    }
}
