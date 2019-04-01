delete from feedback;
delete from ordered_products;
delete from orders;
delete from basket;
delete from users;
delete from products;
delete from category;

INSERT INTO users(id, name, user_name, password, enabled, role, user_status) VALUES
(1, 'admin', 'admin', 'admin', 1, 'ROLE_ADMIN', 'ACTIVE'),
(2, 'user', 'user', 'user', 1, 'ROLE_USER', 'ACTIVE'),
(3, 'admin2', 'admin2', '$2a$04$LjyBhChXHNOl.Q/N6QQijeDWaf5Qp8S7w5f3mlxYRwWX/gQtvNAs.', 1, 'ROLE_ADMIN', 'ACTIVE');

INSERT INTO orders(id, purchase_date, user_id, order_status) VALUES
(1, '2019-03-20 20:20:20', 2, 'ACTIVE'),
(2, '2019-04-20 20:20:20', 2, 'SHIPPED'),
(3, '2019-01-20 20:20:20', 2, 'DELETED'),
(4, '2019-02-20 20:20:20', 2, 'SHIPPED'),
(5, '2019-01-20 20:20:20', 2, 'SHIPPED');

INSERT INTO category(id, name, ordinal) values
(1, "pretty", 1),
(2, "fast", 2),
(3, "smart", 3);

INSERT INTO products (id, code, address, name, manufacture, price, product_status, category_id) VALUES
(1, '351MBA', 'surf_killer', 'Killer', 'cafebabes', 1500000, 'ACTIVE', 2),
(2, 'SURF45', 'surf_coder', 'Coder', 'cafebabes', 45000, 'ACTIVE', 1),
(3, 'az01', 'surf_slayer2', 'SLAYER2', 'cafebabes', 98000, 'ACTIVE', 1),
(4, 'az02', 'surf_theblade', 'THE BLADE', 'cafebabes', 87500, 'DELETED', 1),
(5, '848HRE', 'surf_waver', 'Waver', 'cafebabes', 990000, 'DELETED', 1),
(6, 'OCEAN1', 'surf_ocean', 'Ocean', 'cafebabes', 290000, 'ACTIVE', 1),
(7, '15KJLM3', 'longest_board', 'Longest', 'cafebabes', 128000, 'ACTIVE', 1),
(8, 'LO34KJ8', 'funny_bunny', 'Funny', 'cafebabes', 25800, 'ACTIVE', 1),
(9, 'GH672V4', 'shark', 'Shark', 'cafebabes', 97000, 'ACTIVE', 2),
(10, 'DB89AS1', 'blowfish', 'Blow Fish', 'cafebabes', 112000, 'ACTIVE', 2),
(11, 'SK001', 'surfer', 'Surfer', 'Pacific Islands Surfboards', 990000, 'ACTIVE', 2),
(12, 'R2001', 'R2', 'R2', 'Chemistry Surfboards', 880000, 'ACTIVE', 2),
(13, 'SA001', 'sampler', 'Sampler', 'Channel Islands', 45000, 'ACTIVE', 2),
(14, 'BA001', 'bastard', 'Bastard', 'Pyzel', 45000, 'DELETED', 3),
(15, 'GB001', 'greedy_beaver', 'Greedy Beaver', 'Firewire', 45000, 'ACTIVE', 3),
(16, 'JB101', 'jb-1', 'JB-1', 'T. Patterson', 45000, 'ACTIVE', 3),
(17, 'UT001', 'utility', 'Utility', 'Rusty Surfboards', 45000, 'DELETED', 3),
(18, 'DI001', 'deserted_island', 'Deserted Island', 'Blue Sea Watersports', 45000, 'ACTIVE', 2);

INSERT INTO ordered_products(product_id, order_id, ordering_price, ordering_name, pieces) VALUES
(10,1,112000, 'Blow Fish', 1),
(5,1,990000, 'Waver', 2),
(2,1,45000, 'Coder', 3),
(8,1,25800, 'Funny', 1),
(8,2,25800, 'Funny', 2),
(7,1,128000, 'Longest', 1),
(16,2,45000, 'JB-1', 1),
(17,4,45000, 'Utility', 1),
(18,5,45000, 'Deserted', 1);

INSERT INTO basket(id, user_id, product_id) VALUES
(1, 2, 5),
(2, 2, 3),
(3, 1, 2),
(4, 3, 5);

INSERT INTO feedback (id, feedback_date, feedback, rating, user_id, product_id)
VALUES (1, '2019-03-03 10:10:10', 'Never a better shop!', 5,3,1);