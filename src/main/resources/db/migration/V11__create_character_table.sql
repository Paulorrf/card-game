create table characters(
    id SERIAL PRIMARY KEY,
    lifes SMALLINT NOT NULL CHECK (lifes > 0),
    character_type INTEGER REFERENCES character_type(id),
    decks_id INTEGER REFERENCES decks(id)
);