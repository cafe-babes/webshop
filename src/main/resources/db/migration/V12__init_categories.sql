insert into category(id, name, ordinal) values (1, "pretty", 1);
insert into category(id, name, ordinal) values (2, "fast", 2);
insert into category(id, name, ordinal) values (3, "smart", 3);

UPDATE products SET category_id = 1;
UPDATE products SET category_id = 2 WHERE id IN (1,3,5,7);
UPDATE products SET category_id = 3 WHERE id IN (4,6,10,15,17);
