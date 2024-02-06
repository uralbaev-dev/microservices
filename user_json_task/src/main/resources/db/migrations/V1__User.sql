CREATE SEQUENCE IF NOT EXISTS user_id_sequence START WITH 1 INCREMENT BY 50;

CREATE TABLE users
(
    id           INTEGER NOT NULL,
    fio          VARCHAR(255),
    email        VARCHAR(255),
    phone_number INTEGER,
    status       INTEGER,
    birth_date   date,
    created_at   TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_user PRIMARY KEY (id)
);