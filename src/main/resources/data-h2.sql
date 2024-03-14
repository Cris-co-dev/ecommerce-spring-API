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

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('REGISTER_ONE','/register', 'POST', true, 1);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('AUTHENTICATE','/login', 'POST', true, 1);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_PRODUCTS','', 'GET', true, 2);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('READ_ONE_PRODUCT','/[0-9]*', 'GET', true, 2);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_NAME_LIKE','/name', 'GET', true, 2);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_BETWEEN_PRICE','/between_price', 'GET', true, 2);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_LESS_PRICE','/less_price', 'GET', true, 2);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('SAVE_ONE_PRODUCT','', 'POST', false, 2);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_ONE_PRODUCT','/[0-9]*', 'PUT', false, 2);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('DISABLE_PRODUCT','/[0-9]*/disabled', 'PATCH', false, 2);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('ENABLE_PRODUCT','/[0-9]*/enable', 'PATCH', false, 2);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('DELETE_PRODUCT','/[0-9]*', 'DELETE', false, 2);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_CATEGORIES','', 'GET', true, 3);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('SAVE_ONE_CATEGORY','', 'POST', false, 3);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_ONE_CATEGORY','/[0-9]*', 'PUT', false , 3);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('DISABLE_CATEGORY','/[0-9]*/disabled', 'PATCH', false, 3);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('ENABLE_CATEGORY','/[0-9]*/enable', 'PACTH', false , 3);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_PAYMENT_METHODS','/payment-methods', 'GET', false, 4);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_ADDRESSES','/addresses', 'GET', false, 4);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('READ_ONE_PAYMENT_METHOD','/[0-9]*', 'GET', false, 5);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('SAVE_ONE_PAYMENT_METHOD','', 'POST', false, 5);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_ONE_PAYMENT_METHOD','/[0-9]*',  'PUT', false, 5);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_DEFAULT_PAYMENT_METHOD','/[0-9]*', 'PATCH', false, 5);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('DELETE_ONE_PAYMENT_METHOD','/[0-9]*', 'DELETE', false, 5);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('READ_ONE_ADDRESS','/[0-9]*', 'GET', false, 6);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('SAVE_ONE_ADDRESS','', 'POST', false, 6);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_ONE_ADDRESS','/[0-9]*',  'PUT', false, 6);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_DEFAULT_ADDRESS','/[0-9]*', 'PATCH', false, 6);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('DELETE_ONE_ADDRESS','/[0-9]*', 'DELETE', false, 6);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_COUNTRIES','', 'GET', false, 7);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('READ_ONE_COUNTRY','/[0-9]*', 'GET', false, 7);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('SAVE_ONE_COUNTRY','', 'POST', false, 7);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_ONE_COUNTRY','/[0-9]*', 'PUT', false, 7);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('DELETE_ONE_COUNTRY','/[0-9]*', 'DELETE', false, 7);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('READ_AVAILABLE_ORDER_STATUSES','', 'GET', false, 8);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_PAYMENT_TYPE','', 'GET', false, 9);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('SAVE_ONE_PAYMENT_TYPE','', 'POST', false, 9);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_ONE_PAYMENT_TYPE','/[0-9]*', 'PUT', false, 9);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('DELETE_ONE_PAYMENT_TYPE','/[0-9]*', 'DELETE', false, 9);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_SHIPPING_METHODS','', 'GET', false, 10);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('READ_ONE_SHIPPING_METHOD','/[0-9]*', 'GET', false, 10);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('ADD_SHIPPING_METHOD','', 'POST', false, 10);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_ORDERS','', 'GET', false, 11);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('MADE_ORDER','', 'POST', false, 11);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('READ_ONE_ORDER','/[0-9]*', 'GET', false, 11);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_ORDER_STATUS','/[0-9]*', 'PATCH', false, 11);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('READ_CART','', 'GET', false, 12);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('ADD_PRODUCT','/item', 'POST', false, 12);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_PRODUCT_QUANTITY','/item/[0-9]*', 'PUT', false, 12);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('DELETE_PRODUCT_ITEM','/item', 'DELETE', false, 12);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('CLEAR_CART','', 'DELETE', false, 12);

INSERT INTO role (name) VALUES ('ADMIN');
INSERT INTO role (name) VALUES ('ASSISTANT_ADMIN');
INSERT INTO role (name) VALUES ('CUSTOMER');

INSERT INTO granted_permission (role_id, operation_id) VALUES (1, 8);
INSERT INTO granted_permission (role_id, operation_id) VALUES (1, 9);
INSERT INTO granted_permission (role_id, operation_id) VALUES (1, 10);
INSERT INTO granted_permission (role_id, operation_id) VALUES (1, 11);
INSERT INTO granted_permission (role_id, operation_id) VALUES (1, 12);

INSERT INTO granted_permission (role_id, operation_id) VALUES (1, 14);
INSERT INTO granted_permission (role_id, operation_id) VALUES (1, 15);
INSERT INTO granted_permission (role_id, operation_id) VALUES (1, 16);
INSERT INTO granted_permission (role_id, operation_id) VALUES (1, 17);

INSERT INTO granted_permission (role_id, operation_id) VALUES (1, 18);
INSERT INTO granted_permission (role_id, operation_id) VALUES (1, 19);
INSERT INTO granted_permission (role_id, operation_id) VALUES (1, 20);
INSERT INTO granted_permission (role_id, operation_id) VALUES (1, 21);
INSERT INTO granted_permission (role_id, operation_id) VALUES (1, 22);
INSERT INTO granted_permission (role_id, operation_id) VALUES (1, 23);
INSERT INTO granted_permission (role_id, operation_id) VALUES (1, 24);

INSERT INTO granted_permission (role_id, operation_id) VALUES (1, 25);
INSERT INTO granted_permission (role_id, operation_id) VALUES (1, 26);
INSERT INTO granted_permission (role_id, operation_id) VALUES (1, 27);
INSERT INTO granted_permission (role_id, operation_id) VALUES (1, 28);
INSERT INTO granted_permission (role_id, operation_id) VALUES (1, 29);

INSERT INTO granted_permission (role_id, operation_id) VALUES (1, 30);
INSERT INTO granted_permission (role_id, operation_id) VALUES (1, 31);
INSERT INTO granted_permission (role_id, operation_id) VALUES (1, 32);
INSERT INTO granted_permission (role_id, operation_id) VALUES (1, 33);
INSERT INTO granted_permission (role_id, operation_id) VALUES (1, 34);

INSERT INTO granted_permission (role_id, operation_id) VALUES (1, 35);

INSERT INTO granted_permission (role_id, operation_id) VALUES (1, 36);
INSERT INTO granted_permission (role_id, operation_id) VALUES (1, 37);
INSERT INTO granted_permission (role_id, operation_id) VALUES (1, 38);
INSERT INTO granted_permission (role_id, operation_id) VALUES (1, 39);

INSERT INTO granted_permission (role_id, operation_id) VALUES (1, 40);
INSERT INTO granted_permission (role_id, operation_id) VALUES (1, 41);
INSERT INTO granted_permission (role_id, operation_id) VALUES (1, 42);

INSERT INTO granted_permission (role_id, operation_id) VALUES (1, 43);
INSERT INTO granted_permission (role_id, operation_id) VALUES (1, 44);
INSERT INTO granted_permission (role_id, operation_id) VALUES (1, 45);
INSERT INTO granted_permission (role_id, operation_id) VALUES (1, 46);

INSERT INTO granted_permission (role_id, operation_id) VALUES (1, 47);
INSERT INTO granted_permission (role_id, operation_id) VALUES (1, 48);
INSERT INTO granted_permission (role_id, operation_id) VALUES (1, 49);
INSERT INTO granted_permission (role_id, operation_id) VALUES (1, 50);
INSERT INTO granted_permission (role_id, operation_id) VALUES (1, 51);

INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 8);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 9);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 10);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 11);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 12);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 14);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 15);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 16);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 17);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 18);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 19);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 20);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 21);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 22);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 23);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 24);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 25);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 26);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 27);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 28);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 29);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 35);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 36);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 40);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 41);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 42);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 43);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 44);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 45);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 46);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 47);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 48);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 49);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 50);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 51);

INSERT INTO granted_permission (role_id, operation_id) VALUES (3, 18);
INSERT INTO granted_permission (role_id, operation_id) VALUES (3, 19);
INSERT INTO granted_permission (role_id, operation_id) VALUES (3, 20);
INSERT INTO granted_permission (role_id, operation_id) VALUES (3, 21);
INSERT INTO granted_permission (role_id, operation_id) VALUES (3, 22);
INSERT INTO granted_permission (role_id, operation_id) VALUES (3, 23);
INSERT INTO granted_permission (role_id, operation_id) VALUES (3, 24);

INSERT INTO granted_permission (role_id, operation_id) VALUES (3, 35);
INSERT INTO granted_permission (role_id, operation_id) VALUES (3, 36);
INSERT INTO granted_permission (role_id, operation_id) VALUES (3, 40);
INSERT INTO granted_permission (role_id, operation_id) VALUES (3, 43);
INSERT INTO granted_permission (role_id, operation_id) VALUES (3, 44);
INSERT INTO granted_permission (role_id, operation_id) VALUES (3, 47);
INSERT INTO granted_permission (role_id, operation_id) VALUES (3, 48);
INSERT INTO granted_permission (role_id, operation_id) VALUES (3, 49);
INSERT INTO granted_permission (role_id, operation_id) VALUES (3, 50);
INSERT INTO granted_permission (role_id, operation_id) VALUES (3, 51);

INSERT INTO site_user (username, email, password, role_id) VALUES ('CrisDevAdmin', 'crisdev@admin.com', '$2a$10$8Pc5rjKfjg66.kHepRwZ9OzFfElWoR/8Xx/TRDyOTO/uTfWMns/nS', 1);
INSERT INTO shopping_cart(user_id) VALUES (1);

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