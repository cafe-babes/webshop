--delete from users;
--delete from products;
--delete from orders;


--INSERT INTO users(name, email, user_name, password, enabled, role) VALUES
--('John Doe', 'a@a.com', 'johndoe', 'john', 1, 'ROLE_USER'),
--('Calvin Doe', 'cd@b.com', 'cdoe', '$2y$12$.kwH5V5PC1OzOEeI4AFj6.7.LJ9.w33z2KcxTe7KIgbU4GiKi', 1, 'ROLE_USER'),
--('Clement Doe', 'cld@b.com', 'clDoe', '$2y$12$.kwH5V5PC1OzOEeI4AFj6.7.LJ9.wz2L7HVKcxTe7KIgbU4GiKi', 1, 'ROLE_USER'),
--('Alexander Doe', 'ad@b.com', 'aDoe', '$2y$12$.kwH5V5PC1OzOEeI4AFj6.7.LJL7HVKcxTe7KIgbU4GiKi', 1, 'ROLE_ADMIN');

--DELETE FROM users where name = 'Alexander Doe';

--INSERT INTO products (id, code, address, name, manufacture, price, product_status) VALUES
--(1, 'DKL35A', 'surfer1', 'Surfer1', 'Cafebabes', 125800, 'ACTIVE'),
--(2, 'DKL35B', 'surfer12', 'Surfer1', 'Cafebabes', 582460, 'ACTIVE'),
--(3, 'DKL35C', 'surfer3', 'Surfer2', 'Cafebabes', 12000, 'DELETED'),
--(4, 'DKL35D', 'surfer4', 'Surfer3', 'Cafebabes', 128900, 'ACTIVE'),
--(5, 'DKL35E', 'surfer5', 'Surfer4', 'Cafebabes', 126390, 'DELETED'),
--(6, 'DKL35F', 'surfer6', 'Surfer5', 'Cafebabes', 201500, 'ACTIVE'),
--(7, 'DKL35G', 'surfer7', 'Surfer6', 'Cafebabes', 205840, 'DELETED');

--delete from products where id = 7;

--INSERT INTO `orders`(`purchase_date`, `total`, `sum_quantity`, order_status) VALUES
--('2019-03-20 20:20:20', 50, 50, 'ACTIVE'),
--('2019-03-20 20:20:21', 40, 30, 'DELETED'),
--('2019-03-20 20:20:22', 20, 10, 'ACTIVE'),
--('2019-03-20 20:20:22', 50, 20, 'SHIPPED'),
--('2019-03-20 20:20:22', 60, 30, 'ACTIVE'),
--('2019-03-20 20:20:22', 7, 10, 'ACTIVE'),
--('2019-03-20 20:20:22', 60, 10, 'ACTIVE'),
--('2019-03-20 20:20:22', 10, 70, 'ACTIVE');

--DELETE FROM orders where total = 7;

