-- liquibase formatted sql

-- changeset sedub01:1

CREATE TABLE history_records
(
    id UUID PRIMARY KEY,
    contract_before_id UUID NOT NULL REFERENCES contracts (id) ON DELETE CASCADE,
    contract_after_id UUID NOT NULL REFERENCES contracts (id) ON DELETE CASCADE,
    contract_id UUID REFERENCES contracts (id) ON DELETE SET NULL,
    user_id UUID NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    status_before contract_status_enum NOT NULL,
    status_after contract_status_enum NOT NULL
);

CREATE INDEX ON history_records(contract_after_id);
CREATE INDEX ON history_records(contract_before_id);
CREATE INDEX ON history_records(contract_id);
CREATE INDEX ON history_records(user_id);

COMMENT ON TABLE history_records IS 'История изменений договоров';
COMMENT ON COLUMN history_records.contract_before_id IS 'Идентификатор версии договора до внесения изменений';
COMMENT ON COLUMN history_records.contract_after_id IS 'Идентификатор версии договора после внесения изменений';
COMMENT ON COLUMN history_records.contract_id IS 'Идентификатор договора, к которому относится запись истории';
COMMENT ON COLUMN history_records.user_id IS 'Идентификатор пользователя, выполнившего изменение';
COMMENT ON COLUMN history_records.status_before IS 'Статус договора до изменения';
COMMENT ON COLUMN history_records.status_after IS 'Статус договора после изменения';
