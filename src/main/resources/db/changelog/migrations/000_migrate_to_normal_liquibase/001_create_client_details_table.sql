-- liquibase formatted sql

-- changeset sedub01:1

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

COMMENT ON TABLE client_details IS 'Клиентские данные';
COMMENT ON COLUMN client_details.actual_address IS 'Фактический адрес';
COMMENT ON COLUMN client_details.correspondent_account IS 'Корреспондентский счет';
COMMENT ON COLUMN client_details.current_account IS 'Расчетные реквизиты';
COMMENT ON COLUMN client_details.inn IS 'ИНН';
COMMENT ON COLUMN client_details.kpp IS 'КПП';
COMMENT ON COLUMN client_details.legal_address IS 'Юридический адрес';
COMMENT ON COLUMN client_details.ogrn_ogrnip IS 'ОГРН/ОГРНИП';
