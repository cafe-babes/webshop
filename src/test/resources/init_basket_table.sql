delete from basket;
delete from users;
delete from products;

INSERT INTO `products`(`id`, `code`, `address`, `name`, `manufacture`, `price`, `product_status`) VALUES
(1, '351MBA', 'surf_killer', 'Killer', 'cafebabes', 990000, 'ACTIVE'),
(2, 'abc123', 'wawe_peak', 'Wawe peak', 'cafebabes', 880000, 'ACTIVE'),
(3, 'SURF45', 'surf_coder', 'Coder', 'cafebabes', 45000, 'ACTIVE');

INSERT INTO `users`(`id`, `name`, `email`, `user_name`, `password`, `enabled`, `role`, `user_status`) VALUES
(2, 'Szép Ilona', 'ilona.szep@gmail.com', 'szepi', 'szi', 1, 'ROLE_USER', 'ACTIVE'),
(3, 'Fekete Ferec', 'ferenc.fekete@gmail.com', 'ff', 'ff', 1, 'ROLE_USER', 'ACTIVE'),
(4, 'Charlie Chaplin', 'ch.ch@gmail.com', 'chch', 'ch01', 1, 'ROLE_USER', 'ACTIVE');

INSERT INTO `basket`(`id`, `user_id`, `product_id`) VALUES
(1, 2, 3),
(2, 3, 1);








