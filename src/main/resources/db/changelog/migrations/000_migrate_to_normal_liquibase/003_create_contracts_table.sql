-- liquibase formatted sql

-- changeset sedub01:1

CREATE TYPE contract_status_enum AS ENUM(
    'ACTIVE',
    'EXPIRED',
    'DRAFT',
    'IN_APPROVAL',
    'SIGNATURE_PENDING',
    'SIGNED',
    'HOLD',
    'TERMINATED',
    'CANCELLED'
);

-- changeset sedub01:2

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
    current_status contract_status_enum
);
