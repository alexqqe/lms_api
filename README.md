# 📚 LMS REST API (Learning Management System)

REST API для системы управления обучением (LMS), реализующей работу с пользователями, курсами, темами и задачами.

## Описание проекта

Система поддерживает роли:

- гость
- студент
- преподаватель

Пользователи могут взаимодействовать с курсами, темами и задачами через REST API.

Особенности:

- API спроектировано по REST-принципам
- данные возвращаются в стандартной обёртке (status, error, data)
- данные хранятся в рамках жизненного цикла приложения (in-memory)
- без использования Thymeleaf

---

## API

### Пользователи (Students)

- `POST /students` — регистрация
- `PATCH /students` — обновление (например, решение задачи)
- `GET /students/{id}` — получить студента
- `DELETE /students/{id}` — удалить студента

---

### Курсы (Courses)

- `POST /courses` — создать курс
- `GET /courses/{id}` — получить курс
- `DELETE /courses/{id}` — удалить курс
- `POST /courses/{courseId}/enroll/{studentId}` — записаться на курс
- `POST /courses/{courseId}/unenroll/{studentId}` — выйти из курса

---

### Темы (Topics)

- `POST /courses/{id}/topics` — добавить тему
- `GET /topics/{id}` — получить тему
- `DELETE /topics/{id}` — удалить тему

---

### Задачи (Problems)

- `POST /topics/{id}/problems` — добавить задачу
- `GET /problems/{id}` — получить задачу
- `DELETE /problems/{id}` — удалить задачу

---

## Модель данных

```
Student
- id
- login
- firstName
- lastName
- phoneNumber
- solvedProblems[]

Course
- id
- title
- description
- topics[]
- students[]

Topic
- id
- title
- text
- problems[]

Problem
- id
- title
- description
```

---

## Пользовательские сценарии

1. Пользователь регистрируется, просматривает данные, может удалить аккаунт.
2. Преподаватель создаёт/удаляет курсы, студенты записываются и выходят.
3. Преподаватель добавляет темы в курс, пользователи их просматривают.
4. В теме создаются и удаляются задачи.
5. Студенты решают задачи, система фиксирует выполнение.

---

## Swagger

После запуска приложения документация доступна:

Swagger UI:
```
http://localhost:8080/swagger-ui.html
```

OpenAPI JSON:
```
http://localhost:8080/v3/api-docs
```

Используется зависимость:
```
springdoc-openapi-starter-webmvc-ui
```

---

## Тестирование

Используются:

- JUnit 5
- Mockito

Покрываются:

- бизнес-логика
- edge-кейсы
- корректность обработки ошибок

---

## Запуск проекта

Сборка:
```
mvn clean install
```

Запуск:
```
mvn spring-boot:run
```

---

### Процесс разработки

1. Запуск тестов:
```
mvn test
```

2. Создание Pull Request  
3. Code Review  
4. Merge в `dev`

---

## Финальное тестирование

- запуск всех тестов:
```
mvn test
```
- ручная проверка API (через Swagger/Postman)

---



- Java 17+
- Spring Boot
- Maven
- JUnit 5
- Mockito
- Swagger (springdoc-openapi)

