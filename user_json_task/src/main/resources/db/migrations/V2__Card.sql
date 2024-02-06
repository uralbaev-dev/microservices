CREATE SEQUENCE IF NOT EXISTS card_id_sequence START WITH 1 INCREMENT BY 50;

CREATE TABLE cards
(
    id           INTEGER NOT NULL,
    user_id      INTEGER,
    card_name    VARCHAR(255),
    card_type    VARCHAR(255),
    card_number  VARCHAR(255),
    card_expire  VARCHAR(255),
    card_balance DOUBLE PRECISION,
    card_status  INTEGER,
    created_at   TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_card PRIMARY KEY (id)
);

ALTER TABLE cards
    ADD CONSTRAINT FK_CARD_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);