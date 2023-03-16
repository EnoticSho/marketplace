CREATE TABLE orders
(
    order_id SERIAL PRIMARY KEY,
    user_id BIGINT UNSIGNED NOT NULL,
    order_date DATETIME NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (user_id)
);


CREATE TABLE order_items
(
    order_items_id SERIAL PRIMARY KEY,
    order_id BIGINT UNSIGNED NOT NULL,
    product_id BIGINT UNSIGNED NOT NULL,
    product_count BIGINT UNSIGNED NOT NULL,
    product_price INT NOT NULL,
    FOREIGN KEY (product_id) REFERENCES products (product_id),
    FOREIGN KEY (order_id) REFERENCES orders (order_id)
);