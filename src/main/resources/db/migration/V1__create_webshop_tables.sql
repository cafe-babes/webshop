create table products (
id bigint auto_increment,
code varchar(255),
address varchar(255),
name varchar(255),
manufacture varchar(255),
price bigint,
product_status varchar(50),
constraint pk_products primary key (id)
)
engine = innodb character set = utf8 collate utf8_general_ci;