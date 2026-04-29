package com.example.Project_week6.service;

import com.example.Project_week6.ecxeption.HttpStatusException;
import com.example.Project_week6.model.*;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Getter
public class TopicService {
    private final Map<Long, Topic> data;
    private long nextId;

    @Autowired
    public TopicService(CourseService courseService) {
        this.data = new HashMap<>();
        this.nextId = 0;
    }

    public Topic getTopic(long id) {
        if (!this.data.containsKey(id)) {
            throw new HttpStatusException(HttpStatus.NOT_FOUND, "Topic with id = %s not found".formatted(id));
        }
        return this.data.get(id);
    }

    public Topic deleteTopic(long id) throws HttpStatusException {
        if (!this.data.containsKey(id)) {
            throw new HttpStatusException(HttpStatus.NOT_FOUND, "Topic with id = %s not found".formatted(id));
        }
        Topic topicBack = this.data.get(id);
        this.data.remove(id);
        return topicBack;
    }

    public Topic createTopic(Topic topic) {
        long newId = this.nextId;
        this.nextId++;
        topic.setId(newId);

        this.data.put(newId, topic);

        return topic;
    }

    public Problem createProblemInTopic(long topicId, Problem problem) {
        if (!this.data.containsKey(topicId)) {
            throw new HttpStatusException(HttpStatus.NOT_FOUND, "Topic with id = %s not found".formatted(topicId));
        }
        Topic currentTopic = this.data.get(topicId);

        problem.getTopic().add(topicId);

        // получаем текущий список задачи и добавляем в него только что созданную задачу
        currentTopic.getProblems().add(problem.getId());
        this.data.put(topicId, currentTopic);

        return problem;
    }
}
