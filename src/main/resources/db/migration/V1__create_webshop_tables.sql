create table products (
id bigint auto_increment,
code varchar(255),
address varchar(255),
name varchar(255),
manufacture varchar(255),
price bigint,
product_status varchar(50),
constraint pk_products primary key (id)
)
engine = innodb character set = utf8 collate utf8_general_ci;

INSERT INTO products (`code`, `address`, `name`, `manufacture`, `price`, `product_status`)
VALUES ('351MBA', 'surf_killer', 'Killer', 'cafebabes', 1500000, 'ACTIVE');
INSERT INTO products (`code`, `address`, `name`, `manufacture`, `price`, `product_status`)
VALUES ('SURF45', 'surf_coder', 'Coder', 'cafebabes', 45000, 'ACTIVE');

INSERT INTO products(`code`, `address`, `name`, `manufacture`, `price`, `product_status`)
VALUES ('az01', 'surf_slayer2', 'SLAYER2', 'cafebabes', 98000, 'ACTIVE'),
('az02', 'surf_theblade', 'THE BLADE', 'cafebabes', 87500, 'DELETED');

INSERT INTO products (`code`, `address`, `name`, `manufacture`, `price`, `product_status`)
VALUES ('848HRE', 'surf_waver', 'Waver', 'cafebabes', 990000, 'DELETED');
INSERT INTO products (`code`, `address`, `name`, `manufacture`, `price`, `product_status`)
VALUES ('OCEAN1', 'surf_ocean', 'Ocean', 'cafebabes', 290000, 'ACTIVE');

INSERT INTO products (`code`, `address`, `name`, `manufacture`, `price`, `product_status`)
VALUES ('15KJLM3', 'longest_board', 'Longest', 'cafebabes', 128000, 'ACTIVE');
INSERT INTO products (`code`, `address`, `name`, `manufacture`, `price`, `product_status`)
VALUES ('LO34KJ8', 'funny_bunny', 'Funny', 'cafebabes', 25800, 'ACTIVE');

INSERT INTO products (`code`, `address`, `name`, `manufacture`, `price`, `product_status`)
VALUES ('GH672V4', 'shark', 'Shark', 'cafebabes', 97000, 'ACTIVE');
INSERT INTO products (`code`, `address`, `name`, `manufacture`, `price`, `product_status`)
VALUES ('DB89AS1', 'blowfish', 'Blow Fish', 'cafebabes', 112000, 'ACTIVE');