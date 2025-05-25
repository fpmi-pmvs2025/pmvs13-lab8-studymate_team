---
layout: default
title: Структура приложения
parent: StudyMate Documentation
nav_order: 3
---

# Структура приложения

## Диаграмма файлов

```
studymate/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/
│   │   │   │       └── studymate/
│   │   │   │           ├── activities/
│   │   │   │           ├── adapters/
│   │   │   │           ├── models/
│   │   │   │           ├── services/
│   │   │   │           └── utils/
│   │   │   ├── res/
│   │   │   │   ├── layout/
│   │   │   │   ├── values/
│   │   │   │   ├── drawable/
│   │   │   │   └── mipmap/
│   │   │   └── AndroidManifest.xml
│   │   ├── test/
│   │   └── androidTest/
│   ├── build.gradle.kts
│   └── proguard-rules.pro
├── gradle/
├── build.gradle.kts
├── settings.gradle.kts
├── gradle.properties
└── gradlew
```

## Описание компонентов

### Java пакеты (java/com/studymate/)
- **activities/** - активности Android
  - MainActivity.java - главная активность
  - ScheduleActivity.java - активность расписания
  - MaterialsActivity.java - активность материалов
  - ProfileActivity.java - активность профиля

- **adapters/** - адаптеры для RecyclerView
  - ScheduleAdapter.java - адаптер для списка расписания
  - MaterialsAdapter.java - адаптер для списка материалов

- **models/** - модели данных
  - User.java - модель пользователя
  - Schedule.java - модель расписания
  - Material.java - модель материала
  - Subject.java - модель предмета

- **services/** - сервисы
  - ApiService.java - работа с API
  - AuthService.java - аутентификация
  - NotificationService.java - уведомления

- **utils/** - утилиты
  - Constants.java - константы
  - Helpers.java - вспомогательные функции

### Ресурсы (res/)
- **layout/** - XML файлы разметки
  - activity_main.xml
  - activity_schedule.xml
  - activity_materials.xml
  - activity_profile.xml

- **values/** - ресурсы значений
  - colors.xml - цвета
  - strings.xml - строки
  - styles.xml - стили

- **drawable/** - графические ресурсы
  - Иконки
  - Фоновые изображения
  - Векторные изображения

### Конфигурационные файлы
- **build.gradle.kts** - конфигурация сборки приложения
- **AndroidManifest.xml** - манифест приложения
- **proguard-rules.pro** - правила ProGuard
- **gradle.properties** - настройки Gradle 