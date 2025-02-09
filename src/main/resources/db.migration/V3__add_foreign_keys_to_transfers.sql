BEGIN;
ALTER TABLE transfers
ADD COLUMN sender_wallet_id INTEGER,
ADD CONSTRAINT fk_sender_wallet_id
FOREIGN KEY (sender_wallet_id) REFERENCES wallets(number);

