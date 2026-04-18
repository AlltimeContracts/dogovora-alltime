-- liquibase formatted sql

-- changeset sedub01:1
-- TODO упомянуть в вики нейминг миграций!!

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
