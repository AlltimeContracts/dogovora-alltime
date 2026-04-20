-- liquibase formatted sql

-- changeset sedub01:1

CREATE TYPE user_role_enum AS ENUM(
    'ADMIN',
    'MANAGER',
    'OWNER'
);

COMMENT ON TYPE user_role_enum IS '';

-- changeset sedub01:2

CREATE TABLE users
(
    id UUID PRIMARY KEY,
    is_active BOOLEAN NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    login VARCHAR(255) NOT NULL UNIQUE,
    "position" VARCHAR(255),
    role user_role_enum NOT NULL,
    second_name VARCHAR(255) NOT NULL,
    third_name VARCHAR(255)
);

COMMENT ON TABLE users IS '';
COMMENT ON COLUMN users.is_active IS '';
COMMENT ON COLUMN users.first_name IS '';
COMMENT ON COLUMN users.login IS '';
COMMENT ON COLUMN users.position IS '';
COMMENT ON COLUMN users.role IS '';
COMMENT ON COLUMN users.second_name IS '';
COMMENT ON COLUMN users.third_name IS '';
