CREATE SCHEMA IF NOT EXISTS "commerce";

CREATE TABLE IF NOT EXISTS commerce."account" (
    id BIGSERIAL PRIMARY KEY,
    company_id VARCHAR(128),
    category_id VARCHAR(128),
    keyword VARCHAR(128), -- 키워드
    transaction_dateTime TIMESTAMP NOT NULL, -- 거래 일시
    description VARCHAR(128) NOT NULL, -- 적요
    deposit_amount NUMERIC(15,4) NOT NULL, -- 입금액
    withdraw_amount NUMERIC(15,4) NOT NULL, -- 출금액
    balance_after_transaction NUMERIC(15,4) NOT NULL, -- 거래 후 잔액
    transaction_branch VARCHAR(64) NOT NULL, -- 거래 지점
    is_matched BOOLEAN NOT NULL -- 매칭 여부
);

CREATE TABLE IF NOT EXISTS commerce."company" (
    id VARCHAR(128) PRIMARY KEY,
    name VARCHAR(64) NOT NULL
);

CREATE TABLE IF NOT EXISTS commerce."category" (
    id VARCHAR(128) PRIMARY KEY,
    company_id VARCHAR(128) NOT NULL,
    name VARCHAR(64) NOT NULL
);

CREATE TABLE IF NOT EXISTS commerce."keyword" (
    id BIGSERIAL PRIMARY KEY,
    category_id VARCHAR(128) NOT NULL,
    name VARCHAR(128) NOT NULL
);

