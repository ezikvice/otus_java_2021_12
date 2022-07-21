-- Для @GeneratedValue(strategy = GenerationType.IDENTITY)
/*
create table client
(
    id   bigserial not null primary key,
    name varchar(50)
);

 */


create table addresses
(
    id   bigint not null primary key,
    street varchar(255)
);

create table phones
(
    id   bigint not null primary key,
    number varchar(255),
    client_id bigint
);

create table clients
(
    id   bigint not null primary key,
    name varchar(50),
    address_id bigint
);


INSERT INTO addresses (id, street) VALUES (1, "street");
INSERT INTO clients (id, name, address_id) VALUES (1, "name", 1);