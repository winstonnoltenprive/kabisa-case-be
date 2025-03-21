-- Drop old tables if re-running manually
DROP TABLE IF EXISTS user_likes;
DROP TABLE IF EXISTS quote;
DROP TABLE IF EXISTS "user";

-- User table
CREATE TABLE quote_user (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE
);

-- Quote table
CREATE TABLE quote (
    id SERIAL PRIMARY KEY,
    text TEXT NOT NULL,
    author VARCHAR(255) NOT NULL
);

-- Like table (join table)
CREATE TABLE user_likes (
    id SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL REFERENCES "user"(id) ON DELETE CASCADE,
    quote_id INTEGER NOT NULL REFERENCES quote(id) ON DELETE CASCADE,
    UNIQUE (user_id, quote_id)
);