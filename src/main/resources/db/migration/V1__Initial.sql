
create table shopping_cart.user
(
    id       int          not null
        primary key,
    name     varchar(255) not null,
    surname  varchar(255) not null,
    birthday date         not null,
    email    varchar(255) not null,
    password varchar(255) not null,
    role     varchar(255) not null,
    constraint UK_kiqfjabx9puw3p1eg7kily8kg
        unique (password),
    constraint UK_ob8kqyqqgmefl0aco34akdtpe
        unique (email)
);

create table shopping_cart.product
(
    id           int          not null
        primary key,
    created_date date         null,
    description  varchar(255) null,
    name         varchar(255) null,
    price        int          null,
    stock_count  int          null,
    type         varchar(255) null,
    updated_date date         null
);


create table if not exists shopping_cart.`order`
(
    id            bigint       not null
        primary key,
    product_count int          null,
    status        varchar(255) not null,
    customer_id   int          null,
    product_id    int          null,
    constraint fk_shopping_cart_customer_id
        foreign key (customer_id) references shopping_cart.user (id),
    constraint fk_shopping_cart_product_id
        foreign key (product_id) references shopping_cart.product (id)
);




insert into shopping_cart.`user`  (id, name, surname, birthday, email, password, role)
values (1, 'admin', 'test Admin', '2022-10-31', 'testadmin@gmail.com', 'password', 'ADMIN');