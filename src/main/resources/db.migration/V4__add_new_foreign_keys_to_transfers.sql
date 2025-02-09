BEGIN;
ALTER TABLE transfers
ADD COLUMN receive_wallet_id INTEGER,
ADD CONSTRAINT fk_receive_wallet_id
FOREIGN KEY (receive_wallet_id) REFERENCES wallets(number),
ADD COLUMN receiver_phone BIGINT,
ADD CONSTRAINT fk_receiver_phone
FOREIGN KEY (receiver_phone) REFERENCES users(phone);