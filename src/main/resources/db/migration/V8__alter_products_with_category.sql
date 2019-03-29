create table category (
id bigint auto_increment,
name varchar(255),
ordinal bigint,
constraint pk_category primary key (id)
) engine = innodb character set = utf8 collate utf8_general_ci;

ALTER TABLE basket ADD pieces INT;
ALTER TABLE ordered_products ADD pieces INT;

ALTER TABLE products
ADD category_id BIGINT,
ADD constraint fk_category_products foreign key (category_id) REFERENCES category(id);