INSERT INTO User (id, providerurl, userpod) VALUES ('1', 'prov', 'pod');
INSERT INTO product (id, descr, price, imageurl, "name") VALUES ('1', 'Descripcion', 12.34, 'https://image.png', 'Producto');
INSERT INTO inventory (id, stock, "location", productid) VALUES ('1', 12, 'Warehouse', '1');
INSERT INTO review (id, score, productid, user_id) VALUES ('1', 3, '1', '1');
