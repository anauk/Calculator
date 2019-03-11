CREATE TABLE history (
    id serial,
    a INTEGER,
    op VARCHAR,
    b INTEGER,
    r INTEGER,
    at TIMESTAMP DEFAULT now(),
    user_id INTEGER
);
CREATE TABLE users (
    id serial,
    name VARCHAR(32)
);
