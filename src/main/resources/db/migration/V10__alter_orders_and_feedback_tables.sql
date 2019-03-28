ALTER TABLE orders
ADD delivery_id BIGINT,
ADD constraint fk_orders_delivery foreign key (delivery_id) REFERENCES delivery(id);

ALTER TABLE feedback
add constraint fk_feedback_users foreign key (user_id) REFERENCES users(id),
add constraint fk_feedback_product foreign key (product_id) REFERENCES products(id);