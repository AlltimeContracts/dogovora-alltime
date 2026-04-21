-- liquibase formatted sql

-- changeset sedub01:1

CREATE TYPE business_form_enum AS ENUM(
    'SOLE_PROPRIETOR',
    'LLC',
    'JSC',
    'NON_PROFIT',
    'LIMITED_PARTNERSHIP',
    'FARM_ENTERPRISE'
);

COMMENT ON TYPE business_form_enum IS 'Организационно-правовая форма';

-- changeset sedub01:2

CREATE TABLE clients
(
    id UUID PRIMARY KEY,
    is_active BOOLEAN NOT NULL,
    client_details_id UUID UNIQUE REFERENCES client_details (id) ON DELETE CASCADE,
    business_form business_form_enum,
    contract_list VARCHAR(255),
    full_name VARCHAR(255) NOT NULL
);

CREATE INDEX ON clients(client_details_id);

COMMENT ON TABLE clients IS 'Список клиентов';
COMMENT ON COLUMN clients.is_active IS 'Флаг активности клиента';
COMMENT ON COLUMN clients.client_details_id IS 'Ссылка на клиентские данные';
COMMENT ON COLUMN clients.business_form IS 'Вид организационно-правовой формы';
COMMENT ON COLUMN clients.contract_list IS 'Список контактов';
COMMENT ON COLUMN clients.full_name IS 'ФИО';
