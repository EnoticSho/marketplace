CREATE TABLE roles
(
    role_id SERIAL PRIMARY KEY,
    role_name VARCHAR(255)  NOT NULL
);

CREATE TABLE users
(
    user_id SERIAL PRIMARY KEY,
    user_username VARCHAR(255)  NOT NULL,
    user_password VARCHAR(255) NOT NULL
);

CREATE TABLE users_roles
(
    user_id BIGINT UNSIGNED NOT NULL,
    role_id BIGINT UNSIGNED NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (user_id),
    FOREIGN KEY (role_id) REFERENCES roles (role_id)
);

Insert Into users(user_username, user_password)
VALUES ('Alisa', '$2a$12$PQkh5S4HTgo.PL.ev0Qu8eBHQZc0LQzExc3XgP5hsvgtgW3KP4yw.'),
       ('Sanya', '$2a$12$VjnRB/gZybumbtOpqWjYfOMU4yrFFbQYZPeQO6ZPyHVN4gfH1twta');

Insert Into roles(role_name)
VALUES ('ROLE_USER'),
       ('ROLE_ADMIN');

Insert Into users_roles(user_id, role_id)
VALUES (1,1),
       (2,2);