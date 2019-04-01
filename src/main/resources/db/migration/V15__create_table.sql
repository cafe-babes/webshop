CREATE TABLE image (
id bigint auto_increment,
filename VARCHAR(255),
file blob,
constraint pk_products primary key (id)
) engine = innodb character set = utf8 collate utf8_general_ci;