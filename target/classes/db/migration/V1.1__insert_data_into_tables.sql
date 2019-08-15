insert into area(area_name, state_name, zipcode) values
('Crystal City', 'VA', 22202),
('Pentagon City', 'VA', 22202),
('Rosslyn', 'VA', 22209),
('East Fallchurch', 'VA', 22040)
;
commit;

insert into restaurant(name, category, address, tel, starrated, area_id) values
('Young Chow', 'Chinese', '420 23rd St S','2028737254', 4, 1),
('Bob&Edith Diner', 'American', '539 23rd St S', '2028737254', 4, 1)
;
commit;

insert into merchant(name, first_name, last_name, email, tel, address, restaurant_id) values
('Bowen Shen', 'Bowen', 'Shen', 'bshen36@gwu.edu', '2028737254', '1900 S Eads St', 1),
('Yutong Chen', 'Yutong', 'Chen', 'bshen36@gwu.edu', '2028737254', '1900 S Eads St', 2)
;
commit;

insert into customer(name, first_name, last_name, email, tel, address, area_id) values
('Bowen Shen', 'Bowen', 'Shen', 'bshen36@gwu.edu', '2028737254', '1900 S Eads St', 1),
('Yutong Chen', 'Bowen', 'Shen', 'bshen36@gwu.edu', '2028737254', '1900 S Eads St', 1)
;
commit;

