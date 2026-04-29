package com.example.Project_week6.сonverters;

import com.example.Project_week6.model.*;


public class Converter {

    public static StudentCreationDto StudentModel2DTO(Student student) {
        return new StudentCreationDto( student.getLogin(), student.getFirstName(),
                student.getLastName(), student.getPhoneNumber()
        );
    }

    public static Student StudentDTO2Model(StudentCreationDto studentCreationDto) {
        return new Student(studentCreationDto.getLogin(), studentCreationDto.getFirstName(),
                studentCreationDto.getLastName(), studentCreationDto.getPhoneNumber());
    }

    public static TopicCreationDto TopicModel2DTO(Topic topic) {
        return new TopicCreationDto(
                topic.getText(),
                topic.getTitle()
        );
    }

    public static Topic TopicDTO2Model(TopicCreationDto topicCreationDto){
        return new Topic(topicCreationDto.getTitle(), topicCreationDto.getText());
    }

    public static ProblemCreationDto ProblemModel2DTO(Problem problem){
        return new ProblemCreationDto(
                problem.getTitle(),
                problem.getDescription()
        );
    }

    public static Problem ProblemDTO2Model(ProblemCreationDto problemCreationDto){
        return new Problem(problemCreationDto.getTitle(), problemCreationDto.getDescription());
    }

    public static CourseCreationDto CourseModel2DTO(Course course){
        return new CourseCreationDto(
                course.getTitle(),
                course.getDescription()
        );
    }

    public static Course CourseDTO2Model(CourseCreationDto courseCreationDto){
        return new Course(courseCreationDto.getTitle(), courseCreationDto.getDescription());
    }


}
