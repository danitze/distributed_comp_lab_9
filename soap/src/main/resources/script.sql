create schema lab_9;
use lab_9;

create table `country`
(
    id           int          not null primary key,
    country_name varchar(255) not null
);

create table `city`
(
    id         int          not null primary key,
    country_id int          not null,
    city_name  varchar(255) not null,
    population int          not null,
    is_capital tinyint      not null,
    foreign key (country_id) references `country` (id) on delete cascade on update cascade
);
