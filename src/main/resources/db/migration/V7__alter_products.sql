ALTER TABLE products MODIFY code varchar(255) NOT NULL UNIQUE KEY;
ALTER TABLE products MODIFY address varchar(255) NOT NULL UNIQUE KEY;
ALTER TABLE products MODIFY name varchar(255) NOT NULL;
ALTER TABLE products MODIFY manufacture varchar(255) NOT NULL;
ALTER TABLE products MODIFY price bigint NOT NULL;
ALTER TABLE products MODIFY product_status varchar(50) NOT NULL;