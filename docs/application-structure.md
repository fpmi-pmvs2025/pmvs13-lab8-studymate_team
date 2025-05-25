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
│   │   │   │       └── bsu/
│   │   │   │           └── studymate/
│   │   │   │               ├── ui/
│   │   │   │               ├── db/
│   │   │   │               ├── data/
│   │   │   │               └── MainActivity.kt
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

### Kotlin пакеты (java/com/bsu/studymate/)
- **ui/** - компоненты пользовательского интерфейса
  - Экранные компоненты
  - Навигация
  - UI утилиты

- **db/** - работа с базой данных
  - Room база данных
  - DAO интерфейсы
  - Сущности

- **data/** - модели данных и репозитории
  - Модели данных
  - Репозитории
  - API клиенты

- **MainActivity.kt** - главная активность приложения

### Ресурсы (res/)
- **layout/** - XML файлы разметки
  - activity_main.xml
  - Другие экраны приложения

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