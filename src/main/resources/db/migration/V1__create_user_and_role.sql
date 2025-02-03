CREATE TABLE roles
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE users
(
    id            SERIAL PRIMARY KEY,
    password      VARCHAR(255)        NOT NULL,
    firstname     VARCHAR(255),
    lastname      VARCHAR(255),
    surname       VARCHAR(255),
    birth_day     DATE,
    email         VARCHAR(255) UNIQUE NOT NULL,
    contact_phone VARCHAR(12),
    picture_url   VARCHAR(255),
    role_id       INT                 NOT NULL,
    FOREIGN KEY (role_id) REFERENCES roles (id)
);

INSERT INTO roles (name)
VALUES ('ROLE_USER'),
       ('ROLE_ADMIN');

INSERT INTO users (email, password, role_id, firstname, lastname, surname, birth_day, contact_phone)
VALUES ('user@mail.ru', '$2a$10$NfAmrhMry1fxdmxUUYQgtOOGAiJiUWWR6RIjZ4eWptKCpVSWTHXLK', 1, 'User', 'Userov', 'Userovich', '1996-08-22', '+79999999999'),
       ('admin@mail.ru', '$2a$10$rhTG56PdB9N56YkPHIvEaO94byo8pmgRJ.yJ4TkhQri.8wt2ylEf.', 2, 'Admin', 'Adminov', 'Adminovich', '1995-02-07', '+78888888888');