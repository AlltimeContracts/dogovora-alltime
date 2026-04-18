-- liquibase formatted sql

-- changeset sedub01:1

-- TODO про стиль в енамах расписать в вики (будем использовать маленькие
-- буквы, типа НЕ SQL сущности!)
-- Также называем в единственном числе!
CREATE TYPE business_form_enum AS ENUM(
    'SOLE_PROPRIETOR',
    'LLC',
    'JSC',
    'NON_PROFIT',
    'LIMITED_PARTNERSHIP',
    'FARM_ENTERPRISE'
);

-- changeset sedub01:2
-- TODO про это тоже расписать в ВИКИ

CREATE TABLE clients
(
    id UUID PRIMARY KEY,
    is_active BOOLEAN NOT NULL,
    client_details_id UUID UNIQUE REFERENCES client_details (id) ON DELETE CASCADE,
    business_form business_form_enum,
    contract_list VARCHAR(255),
    full_name VARCHAR(255) NOT NULL
);
