package com.example.Project_week6.service;

import com.example.Project_week6.ecxeption.HttpStatusException;
import com.example.Project_week6.model.ProblemCreationDto;
import com.example.Project_week6.model.ProblemDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Getter
public class ProblemService {
    private final Map<Long, ProblemDto> data;
    private long nextId;

    public ProblemService() {
        this.data = new HashMap<>();
        this.nextId = 0;
    }

    public ProblemDto createProblem(ProblemCreationDto problemCreationDto) {
        long newId = this.nextId;
        this.nextId++;

        ProblemDto newProblem = new ProblemDto(
                newId,
                problemCreationDto.getTitle(),
                problemCreationDto.getDescription()
        );

        this.data.put(newId, newProblem);

        return newProblem;
    }

    public void removeProblem(long id) {
        if (!this.data.containsKey(id)) {
            throw new HttpStatusException(HttpStatus.NOT_FOUND, "Problem with id = %s not found".formatted(id));
        }
        this.data.remove(id);
    }
}
