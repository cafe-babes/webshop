create table users (
id bigint auto_increment,
name varchar(255),
email varchar(255) UNIQUE NOT NULL,
user_name varchar(255) UNIQUE,
password varchar(255),
role ENUM('CUSTOMER','ADMIN') DEFAULT 'CUSTOMER',
user_status ENUM('ACTIVE','DELETED') DEFAULT 'ACTIVE',
constraint pk_users primary key (id)
) engine = innodb character set = utf8 collate utf8_general_ci;

create table basket (
id bigint auto_increment,
user_id bigint,
product_id bigint,
constraint pk_basket primary key (id),
constraint fk_user_basket foreign key (user_id) REFERENCES users(id),
constraint fk_product_basket foreign key (product_id) REFERENCES products(id)
) engine = innodb character set = utf8 collate utf8_general_ci;