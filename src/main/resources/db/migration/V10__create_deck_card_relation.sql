CREATE TABLE IF NOT EXISTS decks_cards (
    decks_id INTEGER,
    cards_id INTEGER,
    PRIMARY KEY (decks_id, cards_id),
    FOREIGN KEY (decks_id) REFERENCES decks(id),
    FOREIGN KEY (cards_id) REFERENCES cards(id)
);