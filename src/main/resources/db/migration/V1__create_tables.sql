CREATE TABLE roles (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       contact_phone VARCHAR(20),
                       role_id INT NOT NULL,
                       CONSTRAINT fk_users_role FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE RESTRICT
);

CREATE TABLE users_details_information (
                                           id SERIAL PRIMARY KEY,
                                           firstname VARCHAR(100),
                                           lastname VARCHAR(100),
                                           surname VARCHAR(100),
                                           birth_day DATE,
                                           picture_url VARCHAR(255),
                                           user_id INT NOT NULL UNIQUE,
                                           CONSTRAINT fk_user_details_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);