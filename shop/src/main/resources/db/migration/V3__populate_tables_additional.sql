INSERT INTO customer (first_name, last_name, username, password, email) VALUES
    ('Evie', 'Kalfr', 'adantiou', '$2a$10$/8sJI32uGKmzcrz4NT9uH.XYtuNrr3bqAgnUDW9Bf8RIxBSS2ZRgC', 'eimear@gmail.com'),
    ('Josephus', 'Marie', 'sardybda', '$2a$10$Y2afFFH.jyCERISyTEjLz.0gDhNTQ62SZfYalQOUXnRfPbN7WedVG', 'grdschl@yahoo.ca'),
    ('Christie', 'Torhild', 'procycen', '$2a$10$J92r.2J1/BOVLI3vY3a6GeCVaLN9ytPeQld.P0IMMOPyuF7qv7u8K', 'jlbaumga@hotmail.com'),
    ('Juliya', 'Larisa', 'minackso', '$2a$10$jCZGy6Ks5N/RBWsGbvfFZuGh9cwX3ONVXQwz7YjT8rkQDGL..NtbK', 'kosact@yahoo.com');

INSERT INTO orderp (shipped_from_id, customer_id, created_at, address_country, address_city, address_county, address_street_address) VALUES
    (1, 1, '2018-08-19', 'Romania','Cluj-Napoca','Cluj','Oasului'),
    (3, 3, CURRENT_DATE, 'Romania','Iasi','Iasi','Buna ziua');

INSERT INTO order_detail(orderp_id, product_id, quantity) VALUES
    (1, 1, 2),
    (2, 4, 5);