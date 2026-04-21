-- liquibase formatted sql

-- changeset sedub01:1

CREATE TYPE user_role_enum AS ENUM(
    'ADMIN',
    'MANAGER',
    'OWNER'
);

COMMENT ON TYPE user_role_enum IS 'Пользовательские роли';

-- changeset sedub01:2

CREATE TABLE users
(
    id UUID PRIMARY KEY,
    is_active BOOLEAN NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    second_name VARCHAR(255) NOT NULL,
    third_name VARCHAR(255),
    login VARCHAR(255) NOT NULL UNIQUE,
    "position" VARCHAR(255),
    role user_role_enum NOT NULL
);

COMMENT ON TABLE users IS 'Список пользователей';
COMMENT ON COLUMN users.is_active IS 'Статус активности пользователя';
COMMENT ON COLUMN users.first_name IS 'Имя';
COMMENT ON COLUMN users.second_name IS 'Фамилия';
COMMENT ON COLUMN users.third_name IS 'Отчество';
COMMENT ON COLUMN users.login IS 'Логин';
COMMENT ON COLUMN users.position IS 'Должность пользователя';
COMMENT ON COLUMN users.role IS 'Пользовательская роль';
