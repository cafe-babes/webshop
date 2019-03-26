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
INSERT INTO products (id, `code`, `address`, `name`, `manufacture`, `price`, `product_status`) VALUES
(11, 'SK001', 'surfer', 'Surfer', 'Pacific Islands Surfboards', 990000, 'ACTIVE'),
(12, 'R2001', 'R2', 'R2', 'Chemistry Surfboards', 880000, 'ACTIVE'),
(13, 'SA001', 'sampler', 'Sampler', 'Channel Islands', 45000, 'ACTIVE'),
(14, 'BA001', 'bastard', 'Bastard', 'Pyzel', 45000, 'DELETED'),
(15, 'GB001', 'greedy_beaver', 'Greedy Beaver', 'Firewire', 45000, 'ACTIVE'),
(16, 'JB101', 'jb-1', 'JB-1', 'T. Patterson', 45000, 'ACTIVE'),
(17, 'UT001', 'utility', 'Utility', 'Rusty Surfboards', 45000, 'DELETED'),
(18, 'DI001', 'deserted_island', 'Deserted Island', 'Blue Sea Watersports', 45000, 'ACTIVE');