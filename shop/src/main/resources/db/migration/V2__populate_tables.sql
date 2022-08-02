INSERT INTO product_category (name, description) VALUES
     ('electronics', 'technology'),
     ('clothes', 'best quality'),
     ('shoes', 'best quality'),
     ('toys', 'for kids'),
     ('makeup', 'original');

INSERT INTO supplier (name) VALUES
     ('Electronic Shop'),
     ('Reserved'),
     ('Noriel'),
     ('Sephora');

INSERT INTO product (name, description, price, weight, category_id, supplier_id, image_url) VALUES
     ('samsung', 'black', 256, 2.6, 1, 1,'phoneUrl'),
     ('T-shirt', 'white', 15, 0.2, 2, 2, 'T-shirtUrl'),
     ('laptop DELL', 'grey', 250, 1.5, 1, 1, 'laptopUrl'),
     ('mascara', 'black', 32, 0.1, 5, 4, 'mascaraUrl'),
     ('car', 'green', 5, 1, 4, 3, 'carUrl');

INSERT INTO location (name, address_country, address_city, address_county, address_street_address ) VALUES
    ('l1','Romania','Cluj-Napoca','Cluj','Oasului'),
    ('l2','Romania','Bucuresti','Bucuresti','Ialomitei'),
    ('l3','Romania','Iasi','Iasi','Buna ziua'),
    ('l4','Romania','Sibiu','Sibiu','Marinei');

INSERT INTO stock (product_id, location_id, quantity) VALUES
    (1, 2, 20),
    (1, 1, 22),
    (2, 3, 12),
    (2, 2, 120),
    (2, 4, 15),
    (3, 1, 4),
    (4, 3, 4);