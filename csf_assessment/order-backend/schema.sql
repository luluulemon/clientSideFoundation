
drop database if exists pizzafactory;
create database pizzafactory;

use pizzafactory;

create table orders(
    order_id char(8) not null,
    name varchar(64) not null,
    email varchar(64) not null,
    pizza_size int not null,
    thick_crust boolean not null,
    sauce varchar(16) not null,
    toppings varchar(128) not null,
    comments text,

    primary key (order_id)
)