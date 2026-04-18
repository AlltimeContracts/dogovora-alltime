-- liquibase formatted sql

-- changeset sedub01:1

CREATE TYPE user_role_enum AS ENUM(
    'ADMIN',
    'MANAGER',
    'OWNER'
);

-- changeset sedub01:2

CREATE TABLE users
(
    id UUID PRIMARY KEY,
    active BOOLEAN NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    login VARCHAR(255) NOT NULL UNIQUE,
    "position" VARCHAR(255),
    role user_role_enum NOT NULL,
    second_name VARCHAR(255) NOT NULL,
    third_name VARCHAR(255)
);
