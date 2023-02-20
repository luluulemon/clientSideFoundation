
drop database if exists second_hand;
create database second_hand;


use second_hand;

create table postings (
    posting_id char(8) not null,
    posting_date date not null,
    name varchar(64) not null,
    email varchar(128) not null,
    phone int,
    title varchar(256) not null,
    description text not null,
    image varchar(256) not null,

    primary key(posting_id)
);