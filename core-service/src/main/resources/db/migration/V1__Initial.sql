CREATE TABLE categories
(
    category_id SERIAL PRIMARY KEY,
    category_name VARCHAR(100) NOT NULL
);

CREATE TABLE products
(
    product_id SERIAL PRIMARY KEY,
    product_name VARCHAR(255)  NOT NULL,
    price INT NOT NULL,
    category_id BIGINT UNSIGNED,
    FOREIGN KEY (category_id) REFERENCES categories (category_id)
);


