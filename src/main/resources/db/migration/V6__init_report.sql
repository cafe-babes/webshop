INSERT INTO orders(id, purchase_date, user_id,`total`, sum_quantity, order_status) VALUES
(1, '2019-03-20 20:20:20', 4, 1300800, 5, 'ACTIVE'),
(2, '2019-04-20 20:20:20', 4, 1300800, 5, 'SHIPPED'),
(3, '2019-01-20 20:20:20', 4, 1300800, 5, 'DELETED'),
(4, '2019-02-20 20:20:20', 4, 1300800, 5, 'SHIPPED'),
(5, '2019-01-20 20:20:20', 4, 1300800, 5, 'SHIPPED');



INSERT INTO ordered_products(product_id, order_id, ordering_price, ordering_name, ordering_address) VALUES
(10,1,112000, 'Blow Fish', 'blowfish'),
(5,1,990000, 'Waver', 'surf_waver'),
(2,2,45000, 'Coder', 'surf_coder'),
(8,3,25800, 'Funy', 'funny_bunny'),
(7,4,128000, 'Longest', 'longest_board');