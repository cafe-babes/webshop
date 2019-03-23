CREATE TABLE orders (
    id BIGINT AUTO_INCREMENT,
    purchase_date DATE,
    user_id BIGINT,
    total BIGINT,
    sum_quantity BIGINT,
    order_status ENUM('ACTIVE','SHIPPED','DELETED') DEFAULT 'ACTIVE',
    CONSTRAINT pk_orders PRIMARY KEY (id),
    CONSTRAINT fk_orders_user_id FOREIGN KEY (user_id) REFERENCES users(id)
    )
    engine = innodb character set = utf8 collate utf8_general_ci;

    INSERT INTO `orders`(`purchase_date`, `user_id`, `total`, `sum_quantity`, `order_status`) VALUES ('2019-03-15', 5, 2, 900000, 'SHIPPED');
    INSERT INTO `orders`(`purchase_date`, `user_id`, `total`, `sum_quantity`, `order_status`) VALUES ('2019-03-23', 5, 3, 1500000, 'ACTIVE');

    CREATE TABLE ordered_products (
    id BIGINT AUTO_INCREMENT,
    product_id BIGINT,
    order_id BIGINT,
    ordering_price BIGINT,
    ordering_name VARCHAR(255),
    CONSTRAINT pk_ordered_products PRIMARY KEY (id),
    CONSTRAINT fk_op_product_id FOREIGN KEY (product_id) REFERENCES products(id),
    CONSTRAINT fk_op_order_id FOREIGN KEY (order_id) REFERENCES orders(id)
    )
    engine = innodb character set = utf8 collate utf8_general_ci;