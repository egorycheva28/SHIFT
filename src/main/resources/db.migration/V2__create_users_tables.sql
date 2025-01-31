CREATE TABLE IF NOT EXISTS wallets (
       number INTEGER PRIMARY KEY,
       balance INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS users (
       id UUID PRIMARY KEY,
       wallet_id INTEGER UNIQUE,
       last_name VARCHAR(50) NOT NULL,
       first_name VARCHAR(50) NOT NULL,
       middle_name VARCHAR(50),
       phone INTEGER NOT NULL UNIQUE,
       email VARCHAR NOT NULL UNIQUE,
       birthday DATE NOT NULL,
       password VARCHAR NOT NULL CHECK(LENGTH(password) >= 8 AND LENGTH(password) <= 64),
       FOREIGN KEY (wallet_id) REFERENCES wallet(number)
);

CREATE TABLE IF NOT EXISTS sessions (
       id UUID PRIMARY KEY,
       userId UUID NOT NULL,
       expirationTime TIMESTAMP NOT NULL,
       active BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS transfers (
       id UUID PRIMARY KEY,
       userId UUID NOT NULL,
       creationTime TIMESTAMP NOT NULL,
       amount BIGINT NOT NULL,
       transferType VARCHAR(20) NOT NULL,
       status VARCHAR(20) NOT NULL
);