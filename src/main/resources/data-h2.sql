INSERT INTO module (name, base_path) VALUES ('AUTH', '/auth');
INSERT INTO module (name, base_path) VALUES ('PRODUCTS','/products');
INSERT INTO module (name, base_path) VALUES ('CATEGORIES','/categories');
INSERT INTO module (name, base_path) VALUES ('USER','/user');
INSERT INTO module (name, base_path) VALUES ('PAYMENT METHODS','/payment-methods');
INSERT INTO module (name, base_path) VALUES ('ADDRESS','/addresses');
INSERT INTO module (name, base_path) VALUES ('COUNTRY','/countries');
INSERT INTO module (name, base_path) VALUES ('ORDER STATUSES','/order-statuses');
INSERT INTO module (name, base_path) VALUES ('PAYMENT TYPES','/payment-types');
INSERT INTO module (name, base_path) VALUES ('SHIPPING METHODS','/shipping-methods');
INSERT INTO module (name, base_path) VALUES ('ORDERS','/orders');
INSERT INTO module (name, base_path) VALUES ('SHOPPING CART','/cart');

-- PUBLIC ACCESS ----

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('REGISTER_ONE','/register', 'POST', true, 1);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('AUTHENTICATE','/login', 'POST', true, 1);

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_PRODUCTS','', 'GET', true, 2);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('READ_ONE_PRODUCT','/[0-9]*', 'GET', true, 2);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_NAME_LIKE','/name', 'GET', true, 2);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_BETWEEN_PRICE','/between_price', 'GET', true, 2);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_LESS_PRICE','/less_price', 'GET', true, 2);

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_CATEGORIES','', 'GET', true, 3);


INSERT INTO role (name) VALUES ('CUSTOMER');
INSERT INTO role (name) VALUES ('ASSISTANT_ADMIN');
INSERT INTO role (name) VALUES ('ADMIN');

insert into product_category (category_name, status) values ('Camisetas', 'ENABLED');
insert into product_category (category_name, status) values ('camisas', 'ENABLED');
insert into product_category (category_name, status) values ('pantalones', 'ENABLED');
insert into product_category (category_name, status) values ('vaqueros', 'ENABLED');
insert into product_category (category_name, status) values ('vestidos', 'ENABLED');
insert into product_category (category_name, status) values ('faldas', 'ENABLED');
insert into product_category (category_name, status) values ('trajes', 'ENABLED');
insert into product_category (category_name, status) values ('chaquetas', 'ENABLED');
insert into product_category (category_name, status) values ('abrigos', 'ENABLED');
insert into product_category (category_name, status) values ('ropa deportiva', 'ENABLED');
insert into product_category (category_name, status) values ('ropa interior', 'ENABLED');
insert into product_category (category_name, status) values ('pijamas', 'ENABLED');
insert into product_category (category_name, status) values ('ropa de baño', 'ENABLED');
insert into product_category (category_name, status) values ('ropa de maternidad', 'ENABLED');
insert into product_category (category_name, status) values ('ropa de tallas grandes', 'ENABLED');
insert into product_category (category_name, status) values ('ropa para niños', 'ENABLED');
insert into product_category (category_name, status) values ('ropa para bebés', 'ENABLED');
insert into product_category (category_name, status) values ('accesorios', 'ENABLED');
insert into product_category (category_name, status) values ('calzado', 'ENABLED');
insert into product_category (category_name, status) values ('ropa de noche/para ocasiones especiales', 'DISABLED');

insert into country (country_name) values ('Colombia');

insert into shipping_method (name,price) values ( 'Standard', 5.0 );
insert into shipping_method (name,price) values ( 'Express', 10.0 );
insert into shipping_method (name,price) values ( 'Priority', 15.0 );

insert into order_status (status) values ( 'Ordered');
insert into order_status (status) values ( 'In_Transit');
insert into order_status (status) values ( 'Delivered');
insert into order_status (status) values ( 'Cancelled');

insert into payment_type ("value") values ( 'Tarjeta de crédito');
insert into payment_type ("value") values ( 'Tarjeta de débito');