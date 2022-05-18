-- Для @GeneratedValue(strategy = GenerationType.IDENTITY)
/*
create table client
(
    id   bigserial not null primary key,
    name varchar(50)
);

 */

-- Для @GeneratedValue(strategy = GenerationType.SEQUENCE)
create sequence clients_sequence start with 1 increment by 1;
create sequence addresses_sequence start with 1 increment by 1;
create sequence phones_sequence start with 1 increment by 1;

create table addresses
(
    id   bigint not null primary key,
    street varchar(255),
    client_id bigint
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
    name varchar(50)
);
