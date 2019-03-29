insert into category(id, name, ordinal) values (1, "ruhak", 1);
insert into category(id, name, ordinal) values (2, "cipok", 2);
insert into category(id, name, ordinal) values (3, "taskak", 3);
insert into category(id, name, ordinal) values (4, "ruhak", 1);
insert into category(id, name, ordinal) values (5, "cipok", 2);
insert into category(id, name, ordinal) values (6, "taskak", 3);
insert into category(id, name, ordinal) values (7, "ruhak", 1);

UPDATE products SET category_id = 1;
UPDATE products SET category_id = 2 WHERE id IN (1,3,5,7);
UPDATE products SET category_id = 3 WHERE id IN (4,6,10,15,17);
