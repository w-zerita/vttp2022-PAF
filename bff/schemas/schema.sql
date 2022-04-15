-- create a new database
drop schema if exists address_book;
create schema address_book;

-- select database
use address_book;

-- create table
create table bff (
    email varchar(128) not null,
    name varchar(128) not null,
    phone varchar(16),
    dob date,
    status enum('friend', 'foe') default 'friend',
    passphrase varchar(128),
    primary key (email)
);