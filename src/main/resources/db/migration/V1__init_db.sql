create table categories
(
    id             bigint not null,
    category_title varchar(255),
    pod_category   varchar(255),
    primary key (id)
) engine=InnoDB;

create table processors
(
    id         bigint not null,
    count_core integer,
    frequency  integer,
    name       varchar(255),
    primary key (id)
) engine=InnoDB;

create table discounts
(
    id       bigint not null,
    discount integer,
    primary key (id)
) engine=InnoDB;

create table orders
(
    id           bigint not null,
    address      varchar(255),
    created      datetime,
    order_status varchar(255),
    sum          decimal(19, 2),
    updated      datetime,
    user_id      bigint,
    primary key (id)
) engine=InnoDB;

create table orders_details
(
    id         bigint not null,
    amount     bigint,
    price      decimal(19, 2),
    order_id   bigint,
    product_id bigint,
    details_id bigint not null,
    primary key (id)
) engine=InnoDB;

create table buckets
(
    id      bigint not null,
    user_id bigint,
    primary key (id)
) engine=InnoDB;

create table buckets_products
(
    bucket_id  bigint not null,
    product_id bigint not null
) engine=InnoDB;

create table hibernate_sequence
(
    next_val bigint
) engine=InnoDB;
 insert into hibernate_sequence values ( 1 );

create table images_app
(
    id           bigint not null,
    icon_company longtext,
    primary key (id)
) engine=InnoDB;


create table products
(
    id                 bigint not null,
    amount             bigint,
    currency_type      varchar(255),
    image_product      longtext,
    image_product2     longtext,
    image_product3     longtext,
    price              decimal(19, 2),
    title              varchar(255),
    category_id        bigint,
    discount_id        bigint,
    processor_id       bigint,
    product_details_id bigint,
    primary key (id)
) engine=InnoDB;

create table products_details
(
    id                   bigint not null,
    accumulator_capacity integer,
    color                varchar(255),
    count_sim            integer,
    display_size         double precision,
    front_camera         double precision,
    in_memory            integer,
    operation_system     varchar(255),
    ram_memory           integer,
    rear_camera          double precision,
    versionos            double precision,
    year_production      date,
    primary key (id)
) engine=InnoDB;

create table user_wallet
(
    id     bigint not null,
    amount decimal(19, 2),
    primary key (id)
) engine=InnoDB;

create table users
(
    id        bigint not null,
    address   varchar(255),
    email     varchar(255),
    enable    bit,
    password  varchar(255),
    phone     varchar(255),
    role      varchar(255),
    user_name varchar(255),
    bucket_id bigint,
    wallet_id bigint,
    primary key (id)
) engine=InnoDB;

create table verification_token
(
    id          bigint not null,
    expiry_date datetime,
    token       varchar(255),
    user_id     bigint,
    primary key (id)
) engine=InnoDB;

