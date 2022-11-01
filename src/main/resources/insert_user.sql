create table "user"
(
    id       bigint primary key auto_increment,
    name     varchar(250) not null,
    surname  varchar(250) not null,
    birthday date         not null,
    email    varchar(250) not null,
    password varchar(15)  not null,
    role     varchar(250) not null

);
insert into "user" (id, name, surname, birthday, email, password, role)
values (1, 'admin', 'test Admin', '2022-10-31', 'testadmin@gmail.com', 'password', 'ADMIN');