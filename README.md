# Dogovora Alltime

Система управления договорами.

## Требования

- Java 25
- Docker и Docker Compose
- Gradle (wrapper включен)

## Начало работы

### 1. Настройка базы данных с Docker

PostgreSQL работает в Docker. Выполните следующие шаги для настройки базы данных:

#### Запуск контейнера PostgreSQL

```bash
# Скопируйте пример файла окружения
cp .env.example .env

# Запустите контейнер PostgreSQL
docker-compose up -d

# Проверьте, что контейнер запущен
docker-compose ps
```

#### Остановка контейнера PostgreSQL

```bash
docker-compose down
```

#### Остановка и удаление данных

```bash
# Это удалит том базы данных (все данные будут потеряны)
docker-compose down -v
```

### 2. Конфигурация

Конфигурация PostgreSQL в файле `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/dogovora_db
spring.datasource.username=alltime
spring.datasource.password=alltime
```

Вы можете настроить конфигурацию базы данных, отредактировав файл `.env`:

- `POSTGRES_DB` - Имя базы данных (по умолчанию: dogovora_db)
- `POSTGRES_USER` - Пользователь базы данных (по умолчанию: alltime)
- `POSTGRES_PASSWORD` - Пароль базы данных (по умолчанию: alltime)
- `POSTGRES_PORT` - Порт базы данных (по умолчанию: 5432)

### 3. Запуск приложения

```bash
# Сборка проекта
./gradlew build

# Запуск приложения
./gradlew bootRun
```

Приложение запустится на `http://localhost:8080`

### 4. Документация API

Swagger UI доступен по адресу: `http://localhost:8080/swagger-ui.html`

## Справка по командам Docker

```bash
# Просмотр логов
docker-compose logs -f alltimeDb

# Доступ к CLI PostgreSQL
docker-compose exec alltimeDb psql -U alltime -d dogovora_db

# Перезапуск базы данных
docker-compose restart alltimeDb

# Проверка работоспособности базы данных
docker-compose exec alltimeDb pg_isready -U alltime -d dogovora_db
```

## Разработка

### Схема базы данных

## Технологический стек

- Spring Boot 3.5.6
- Java 25
- PostgreSQL 18
- Spring Data JPA
- Lombok
- SpringDoc OpenAPI (Swagger)
