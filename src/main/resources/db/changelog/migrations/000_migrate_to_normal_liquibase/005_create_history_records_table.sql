-- liquibase formatted sql

-- changeset sedub01:1

CREATE TABLE history_records
(
    id UUID PRIMARY KEY,
    contract_after_id UUID NOT NULL REFERENCES contracts (id) ON DELETE CASCADE,
    contract_before_id UUID NOT NULL REFERENCES contracts (id) ON DELETE CASCADE,
    contract_id UUID REFERENCES contracts (id) ON DELETE SET NULL,
    user_id UUID NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    status_before contract_status_enum NOT NULL,
    status_after contract_status_enum NOT NULL
);

CREATE INDEX ON history_records(contract_after_id);
CREATE INDEX ON history_records(contract_before_id);
CREATE INDEX ON history_records(contract_id);
CREATE INDEX ON history_records(user_id);

COMMENT ON TABLE history_records IS '';
COMMENT ON COLUMN history_records.contract_after_id IS '';
COMMENT ON COLUMN history_records.contract_before_id IS '';
COMMENT ON COLUMN history_records.contract_id IS '';
COMMENT ON COLUMN history_records.user_id IS '';
COMMENT ON COLUMN history_records.status_before IS '';
COMMENT ON COLUMN history_records.status_after IS '';
