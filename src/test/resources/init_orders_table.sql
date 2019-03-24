delete from ordered_products;
delete from  `orders`;
delete from users;
delete from products;




INSERT INTO `orders`(`purchase_date`, `total`, `sum_quantity`) VALUES
('2019-03-20 20:20:20', 50, 50),
('2019-03-20 20:20:21', 40, 30),
('2019-03-20 20:20:22', 20, 10);
