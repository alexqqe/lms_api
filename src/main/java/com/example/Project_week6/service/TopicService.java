package com.example.Project_week6.service;

import com.example.Project_week6.ecxeption.HttpStatusException;
import com.example.Project_week6.model.ProblemCreationDto;
import com.example.Project_week6.model.ProblemDto;
import com.example.Project_week6.model.TopicCreationDto;
import com.example.Project_week6.model.TopicDto;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@Service
@Getter
public class TopicService {
    private final Map<Long, TopicDto> data;
    private long nextId;

    @Autowired
    private ProblemService problemService;

    public TopicService() {
        this.data = new HashMap<>();
        this.nextId = 0;
    }

    public void removeTopic(long id) {
        if (!this.data.containsKey(id)) {
            throw new HttpStatusException(HttpStatus.NOT_FOUND, "Topic with id = %s not found".formatted(id));
        }
        this.data.remove(id);
    }

    public TopicDto createTopic(TopicCreationDto topicCreationDto) {
        long newId = this.nextId;
        this.nextId++;

        this.data.put(newId, new TopicDto(
                newId,
                topicCreationDto.getTitle(),
                topicCreationDto.getText()
        ));

        return new TopicDto(
                newId,
                topicCreationDto.getTitle(),
                topicCreationDto.getText()
        );
    }

    public ProblemDto createProblemInTopic(long topicId, ProblemCreationDto problem) {
        if (!this.data.containsKey(topicId)) {
            throw new HttpStatusException(HttpStatus.NOT_FOUND, "Topic with id = %s not found".formatted(topicId));
        }
        TopicDto currentTopic = this.data.get(topicId);

        // создаём новую задачу в "общей" хэш-мапе задач
        ProblemDto newProblem = this.problemService.createProblem(problem);

        // получаем текущий список задачи и добавляем в него только что созданную задачу
        HashSet<Long> newProblemSet = currentTopic.getProblems();
        newProblemSet.add(newProblem.getId());
        currentTopic.setProblems(newProblemSet);
        this.data.put(topicId, currentTopic);

        return newProblem;
    }
}
