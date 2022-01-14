# Дипломная работа “Облачное хранилище”

## Описание проекта

Приложение представляет собой REST-сервис для загрузки файлов в хранилище и вывода списка уже загруженных файлов пользователем. 

## Компоненты реализации

- Приложение разработано с использованием Spring Boot, Spring Security
- Для хранения данных пользователей использована база данных Postgresql 12
- Для хранения файлов использован Amason s3
- Использован сборщик пакетов gradle
- Для запуска используется docker, docker-compose

<img src="https://storage-files.hb.bizmrg.com/%D0%A1%D1%85%D0%B5%D0%BC%D0%B0%20%D0%BF%D1%80%D0%B8%D0%BB%D0%BE%D0%B6%D0%B5%D0%BD%D0%B8%D0%B9.png" width="600">


## Тестовый пользователь для тестирования приложения
- username: stdd01@gmail.com
- password: stzv78@yandex.ru

[Документация REST API](/doc/filestorage.md))

## Локальный клиент s3 minio
- http://localhost:9900/minio/login
- access key: filestorage_test
- secret key: filestorage

## Подключение к БД
- db: filestorage
- port: 5435  
- username: postgres
- password: postgres