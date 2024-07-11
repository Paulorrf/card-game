create table cards(
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) UNIQUE,
    image VARCHAR(255) NOT NULL UNIQUE,
    description TEXT NOT NULL UNIQUE,
    type card_type NOT NULL,
    sub_type card_sub_type NOT NULL
);