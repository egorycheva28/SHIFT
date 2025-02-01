CREATE TABLE IF NOT EXISTS wallets (
       number SERIAL PRIMARY KEY,
       balance BIGINT NOT NULL
);

CREATE TABLE IF NOT EXISTS users (
       id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
       wallet_id INTEGER UNIQUE,
       last_name VARCHAR(50) NOT NULL,
       first_name VARCHAR(50) NOT NULL,
       middle_name VARCHAR(50),
       phone BIGINT NOT NULL UNIQUE,
       email VARCHAR NOT NULL UNIQUE,
       birthday DATE NOT NULL,
       password VARCHAR NOT NULL CHECK(LENGTH(password) >= 8 AND LENGTH(password) <= 64),
       FOREIGN KEY (wallet_id) REFERENCES wallets(number)
);

CREATE TABLE IF NOT EXISTS sessions (
       id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
       user_id UUID NOT NULL,
       expiration_time TIMESTAMP NOT NULL,
       active BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS transfers (
       id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
       user_id UUID NOT NULL,
       creation_time TIMESTAMP NOT NULL,
       amount BIGINT NOT NULL,
       transfer_type VARCHAR(20) NOT NULL,
       status VARCHAR(20) NOT NULL
);


