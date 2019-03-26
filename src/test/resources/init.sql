delete from ordered_products;
delete from orders;
delete from basket;
delete from users;
delete from products;

INSERT INTO users(id, name, user_name, password, enabled, role, user_status) VALUES
(1, 'admin', 'admin', 'admin', 1, 'ROLE_ADMIN', 'ACTIVE'),
(2, 'user', 'user', 'user', 1, 'ROLE_USER', 'ACTIVE'),
(3, 'admin2', 'admin2', '$2a$04$LjyBhChXHNOl.Q/N6QQijeDWaf5Qp8S7w5f3mlxYRwWX/gQtvNAs.', 1, 'ROLE_ADMIN', 'ACTIVE');

INSERT INTO orders(id, purchase_date, user_id,`total`, sum_quantity, order_status) VALUES
(1, '2019-03-20 20:20:20', 2, 1300800, 5, 'ACTIVE'),
(2, '2019-04-20 20:20:20', 2, 1300800, 5, 'SHIPPED'),
(3, '2019-01-20 20:20:20', 2, 1300800, 5, 'DELETED'),
(4, '2019-02-20 20:20:20', 2, 1300800, 5, 'SHIPPED'),
(5, '2019-01-20 20:20:20', 2, 1300800, 5, 'SHIPPED');

INSERT INTO products (id, code, address, name, manufacture, price, product_status) VALUES
(1, '351MBA', 'surf_killer', 'Killer', 'cafebabes', 1500000, 'ACTIVE'),
(2, 'SURF45', 'surf_coder', 'Coder', 'cafebabes', 45000, 'ACTIVE'),
(3, 'az01', 'surf_slayer2', 'SLAYER2', 'cafebabes', 98000, 'ACTIVE'),
(4, 'az02', 'surf_theblade', 'THE BLADE', 'cafebabes', 87500, 'DELETED'),
(5, '848HRE', 'surf_waver', 'Waver', 'cafebabes', 990000, 'DELETED'),
(6, 'OCEAN1', 'surf_ocean', 'Ocean', 'cafebabes', 290000, 'ACTIVE'),
(7, '15KJLM3', 'longest_board', 'Longest', 'cafebabes', 128000, 'ACTIVE'),
(8, 'LO34KJ8', 'funny_bunny', 'Funny', 'cafebabes', 25800, 'ACTIVE'),
(9, 'GH672V4', 'shark', 'Shark', 'cafebabes', 97000, 'ACTIVE'),
(10, 'DB89AS1', 'blowfish', 'Blow Fish', 'cafebabes', 112000, 'ACTIVE');

INSERT INTO ordered_products(product_id, order_id, ordering_price, ordering_name, ordering_address) VALUES
(10,1,112000, 'Blow Fish', 'blowfish'),
(5,1,990000, 'Waver', 'surf_waver'),
(2,1,45000, 'Coder', 'surf_coder'),
(8,1,25800, 'Funy', 'funny_bunny'),
(7,1,128000, 'Longest', 'longest_board');