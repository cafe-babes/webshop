drop INDEX email on users;
ALTER TABLE users MODIFY email VARCHAR(255) NULL;