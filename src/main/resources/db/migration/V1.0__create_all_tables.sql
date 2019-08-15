DROP SEQUENCE IF EXISTS area_id_seq;
DROP SEQUENCE IF EXISTS restaurant_id_seq;
DROP SEQUENCE IF EXISTS merchant_id_seq;
DROP SEQUENCE IF EXISTS customer_id_seq;
CREATE SEQUENCE area_id_seq START WITH 1;
CREATE SEQUENCE restaurant_id_seq START WITH 1;
CREATE SEQUENCE merchant_id_seq START WITH 1;
CREATE SEQUENCE customer_id_seq START WITH 1;
DROP TABLE IF EXISTS area CASCADE;
DROP TABLE IF EXISTS restaurant CASCADE;
DROP TABLE IF EXISTS merchant CASCADE;
DROP TABLE IF EXISTS customer CASCADE;

CREATE TABLE area(
--	id          INTEGER NOT NULL default nextval('area_id_seq'),
    id          SERIAL NOT NULL,
	area_name   VARCHAR(30) not null unique,
	state_name  VARCHAR(30),
	zipcode     INTEGER
);

ALTER TABLE area ADD CONSTRAINT area_pk PRIMARY KEY ( id );

CREATE TABLE restaurant (
--    id        INTEGER NOT NULL default nextval('restaurant_id_seq'),
    id        SERIAL NOT NULL,
	name      VARCHAR(30) not null unique,
	category  VARCHAR(50),
	address   VARCHAR(150),
	tel       VARCHAR(50),
	starrated INTEGER,
	area_id   INTEGER NOT NULL
);

ALTER TABLE restaurant ADD CONSTRAINT resutrant_pk PRIMARY KEY ( id );

CREATE TABLE merchant(
--    id           INTEGER NOT NULL default nextval('merchant_id_seq'),
	id        SERIAL NOT NULL,
	name         VARCHAR(30) not null unique,
    first_name   VARCHAR(30),
    last_name    VARCHAR(30),
    email        VARCHAR(50),
	tel          VARCHAR(30),
    address      VARCHAR(150),
	restaurant_id INTEGER not null
);

ALTER TABLE merchant ADD CONSTRAINT merchant_pk PRIMARY KEY ( id );

CREATE TABLE customer (
--    id           INTEGER NOT NULL default nextval('customer_id_seq'),
    id        SERIAL NOT NULL,
	name         VARCHAR(30) not null unique,
    first_name   VARCHAR(30),
    last_name    VARCHAR(30),
    email        VARCHAR(50),
	tel          VARCHAR(30),
    address      VARCHAR(150),
	area_id      INTEGER not null
);

ALTER TABLE customer ADD CONSTRAINT customer_pk PRIMARY KEY ( id );

ALTER TABLE merchant
   ADD CONSTRAINT merchant_restaurant_fk FOREIGN KEY ( restaurant_id )
       REFERENCES restaurant ( id );

ALTER TABLE restaurant
   ADD CONSTRAINT restaurant_area_fk FOREIGN KEY (area_id)
   	   REFERENCES area(id);

ALTER TABLE customer
   ADD CONSTRAINT customer_area_fk FOREIGN KEY (area_id)
       REFERENCES area(id);
