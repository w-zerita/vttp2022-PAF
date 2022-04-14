drop schema if exists useraccount;

create schema useraccount;

use useraccount;

create table user (
    username varchar(32) not null,
    password varchar(256) not null,

    primary key(username)
);
