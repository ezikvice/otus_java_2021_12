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
    id        bigint not null auto_increment primary key,
    client_id bigint,
    street    varchar(255)
);

create table phones
(
    id        bigint not null auto_increment primary key,
    number    varchar(255),
    client_id bigint
);

create table clients
(
    id   bigint not null auto_increment primary key,
    name varchar(50)
);


-- INSERT INTO clients (id, name) VALUES (1, 'myName');
-- INSERT INTO addresses (id, client_id, street) VALUES (1, 1, 'myStreet');
-- INSERT INTO phones(id, client_id, number) VALUES (1, 1, '1234'), (2, 1, '2314');