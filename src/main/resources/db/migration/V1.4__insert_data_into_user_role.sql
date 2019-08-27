insert into role (name, allowed_resource, allowed_read, allowed_create, allowed_update, allowed_delete) values
('Admin', '/areas,/restaurants/,/merchants,/customers', 'Y', 'Y', 'Y', 'Y'),
('Manager', '/areas,/restaurants,/merchants,/customers', 'Y', 'Y', 'Y', 'N'),
('user', '/merchants,/customers', 'Y', 'N', 'N', 'N')
;
commit;
insert into users (name, password, first_name, last_name, email) values
('dwang', '123456789', 'David', 'Wang', 'dwang@ascending.com'),
('rhang', '123456789', 'Ryo', 'Hang', 'rhang@ascending.com'),
('xyhuang', '123456789', 'Xinyue', 'Huang', 'xyhuang@ascending.com')
;
commit;
insert into users_role values
(1, 1),
(2, 2),
(3, 3),
(1, 2),
(1, 3)
;
commit;