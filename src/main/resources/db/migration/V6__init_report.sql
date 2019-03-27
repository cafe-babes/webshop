INSERT INTO orders(id, purchase_date, user_id,`total`, sum_quantity, order_status) VALUES
(1, '2019-03-20 20:20:20', 4, 1102000, 2, 'ACTIVE'),
(2, '2019-02-21 20:20:20', 4, 45000, 1, 'SHIPPED'),
(3, '2019-01-20 20:20:20', 4, 25800, 1, 'DELETED'),
(4, '2019-02-20 20:20:20', 4, 128000, 1, 'SHIPPED');



INSERT INTO ordered_products(product_id, order_id, ordering_price, ordering_name) VALUES
(10,1,112000, 'Blow Fish'),
(5,1,990000, 'Waver'),
(2,2,45000, 'Coder'),
(8,3,25800, 'Funy'),
(7,4,128000, 'Longest');