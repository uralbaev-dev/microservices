CREATE TABLE card_transactions
(
    id          INTEGER NOT NULL,
    user_id     INTEGER,
    card_id     INTEGER,
    type        VARCHAR(255),
    amount      DOUBLE PRECISION,
    old_balance DOUBLE PRECISION,
    new_balance DOUBLE PRECISION,
    created_at  TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_cardtransaction PRIMARY KEY (id)
);

ALTER TABLE card_transactions
    ADD CONSTRAINT FK_CARDTRANSACTION_ON_CARD FOREIGN KEY (card_id) REFERENCES cards (id);

ALTER TABLE card_transactions
    ADD CONSTRAINT FK_CARDTRANSACTION_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);