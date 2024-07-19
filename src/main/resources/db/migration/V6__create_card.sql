CREATE TABLE IF NOT EXISTS cards (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) UNIQUE NOT NULL,
    image VARCHAR(255) UNIQUE NOT NULL,
    description TEXT UNIQUE NOT NULL,
    card_type_id BIGINT,
    sub_type_id BIGINT,
    FOREIGN KEY (card_type_id) REFERENCES card_type(id),
    FOREIGN KEY (sub_type_id) REFERENCES card_sub_type(id)
);