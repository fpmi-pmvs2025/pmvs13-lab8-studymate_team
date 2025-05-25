---
layout: default
title: Схема базы данных
parent: StudyMate Documentation
nav_order: 5
---

# Схема базы данных

## ER-диаграмма

```
[Users]
- id (PK)
- email
- password
- full_name
- group
- created_at
- updated_at

[Schedule]
- id (PK)
- subject_id (FK)
- teacher_id (FK)
- group_id (FK)
- day_of_week
- start_time
- end_time
- room
- created_at
- updated_at

[Subjects]
- id (PK)
- name
- description
- created_at
- updated_at

[Materials]
- id (PK)
- subject_id (FK)
- user_id (FK)
- title
- description
- file_path
- file_type
- created_at
- updated_at

[Groups]
- id (PK)
- name
- faculty
- created_at
- updated_at

[Teachers]
- id (PK)
- full_name
- email
- department
- created_at
- updated_at
```

## Связи между таблицами

- Users -> Groups (Many-to-One)
- Schedule -> Subjects (Many-to-One)
- Schedule -> Teachers (Many-to-One)
- Schedule -> Groups (Many-to-One)
- Materials -> Subjects (Many-to-One)
- Materials -> Users (Many-to-One)

## SQL-файл

Полная схема базы данных доступна в файле: [database/schema.sql](https://github.com/your-username/studymate/blob/main/database/schema.sql)

## Основные таблицы и их назначение

### Users
Хранит информацию о пользователях системы (студентах)

### Schedule
Содержит расписание занятий

### Subjects
Список учебных предметов

### Materials
Учебные материалы, загруженные пользователями

### Groups
Учебные группы

### Teachers
Информация о преподавателях 