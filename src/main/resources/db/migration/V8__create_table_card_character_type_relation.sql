create table cards_character_type(
    cards_id INTEGER REFERENCES cards(id),
    character_type_id INTEGER REFERENCES character_type(id),
    PRIMARY KEY (cards_id, character_type_id)
);