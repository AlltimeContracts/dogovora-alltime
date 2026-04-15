-- liquibase formatted sql

-- changeset dubenkov.semen47@gmail.com:1

CREATE TABLE client_details
(
    id UUID PRIMARY KEY,
    actual_address VARCHAR(255),
    correspondent_account VARCHAR(255),
    current_account VARCHAR(255),
    inn VARCHAR(255) NOT NULL,
    kpp VARCHAR(255),
    legal_address VARCHAR(255),
    ogrn_ogrnip VARCHAR(255) NOT NULL
);

-- TODO вставить комментарии
-- TODO разделить на файлы поменьше!
-- TODO переименовать таблицы и поля в Java
-- TODO подумать, что делать с таблицами databasechangelog и databasechangeloglock!!

CREATE TABLE clients
(
    id UUID PRIMARY KEY,
    is_active BOOLEAN NOT NULL,
    client_details_id UUID UNIQUE REFERENCES client_details (id) ON DELETE CASCADE,
    business_form VARCHAR(255),
    contract_list VARCHAR(255),
    full_name VARCHAR(255) NOT NULL
);

CREATE TABLE contracts -- TODO за этой таблицей нужно в бровь да в глаз
(
    id UUID PRIMARY KEY,
    contract_num VARCHAR(255) NOT NULL,
    is_active BOOLEAN NOT NULL,
    contract_date_from TIMESTAMP,
    contract_date_to TIMESTAMP,
    client_id UUID NOT NULL, -- TODO должен ли здесь быть FOREIGN KEY??
    manager_ids uuid[] NOT NULL, -- TODO переименовать в Java
    description_text TEXT,
    current_status SMALLINT
);

CREATE TABLE users
(
    id UUID PRIMARY KEY,
    active BOOLEAN NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    login VARCHAR(255) NOT NULL UNIQUE,
    "position" VARCHAR(255),
    roles VARCHAR(255) NOT NULL,
    second_name VARCHAR(255) NOT NULL,
    third_name VARCHAR(255)
);

CREATE TABLE history_record
(
    id UUID PRIMARY KEY,
    contract_after_id UUID NOT NULL REFERENCES contracts (id) ON DELETE CASCADE,
    contract_before_id UUID NOT NULL REFERENCES contracts (id) ON DELETE CASCADE,
    contract_id UUID REFERENCES contracts (id) ON DELETE SET NULL,
    user_id UUID NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    status_after VARCHAR(255) NOT NULL, -- TODO должно быть перечисление, а не текст!
    status_before VARCHAR(255) NOT NULL
);