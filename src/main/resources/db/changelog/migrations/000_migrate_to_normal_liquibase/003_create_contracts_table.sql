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

COMMENT ON TYPE contract_status_enum IS 'Статус договора';

-- changeset sedub01:2

CREATE TABLE contracts
(
    id UUID PRIMARY KEY,
    contract_num VARCHAR(255) NOT NULL,
    is_active BOOLEAN NOT NULL,
    contract_date_from TIMESTAMP,
    contract_date_to TIMESTAMP,
    client_id UUID NOT NULL REFERENCES clients (id) ON DELETE CASCADE,
    manager_ids uuid[] NOT NULL,
    description_text TEXT,
    current_status contract_status_enum
);

CREATE INDEX ON contracts(client_id);

COMMENT ON TABLE contracts IS 'Список договоров';
COMMENT ON COLUMN contracts.contract_num IS 'Номер договора';
COMMENT ON COLUMN contracts.is_active IS 'Статус активности договора';
COMMENT ON COLUMN contracts.contract_date_from IS 'Дата начала срока договора';
COMMENT ON COLUMN contracts.contract_date_to IS 'Дата окончания срока договора';
COMMENT ON COLUMN contracts.client_id IS 'Ссылка на клиента';
COMMENT ON COLUMN contracts.manager_ids IS 'Менеджеры, которые внесли изменения';
COMMENT ON COLUMN contracts.description_text IS 'Описание договора';
COMMENT ON COLUMN contracts.current_status IS 'Текущий статус договора';
