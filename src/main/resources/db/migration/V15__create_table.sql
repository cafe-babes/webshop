CREATE TABLE image (
id bigint auto_increment,
file blob,
constraint pk_products primary key (id)
) engine = innodb character set = utf8 collate utf8_general_ci;