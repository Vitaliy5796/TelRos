INSERT INTO roles (name)
VALUES ('ROLE_USER'),
       ('ROLE_ADMIN') ON CONFLICT (id) DO NOTHING;

INSERT INTO users (email, password, contact_phone, role_id)
VALUES ('user@mail.ru', '$2a$10$NfAmrhMry1fxdmxUUYQgtOOGAiJiUWWR6RIjZ4eWptKCpVSWTHXLK', '+79999999999', 1),
       ('admin@mail.ru', '$2a$10$rhTG56PdB9N56YkPHIvEaO94byo8pmgRJ.yJ4TkhQri.8wt2ylEf.', '+78888888888', 2);