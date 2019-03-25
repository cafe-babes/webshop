delete from users;

INSERT INTO users(name, email, user_name, password, enabled, role) VALUES
('John Doe', 'a@a.com', 'johndoe', 'john', 1, 'ROLE_USER'),
('Calvin Doe', 'cd@b.com', 'cdoe', '$2y$12$.kwH5V5PC1OzOEeI4AFj6.7.LJ9.w33z2KcxTe7KIgbU4GiKi', 1, 'ROLE_USER'),
('Clement Doe', 'cld@b.com', 'clDoe', '$2y$12$.kwH5V5PC1OzOEeI4AFj6.7.LJ9.wz2L7HVKcxTe7KIgbU4GiKi', 1, 'ROLE_USER'),
('Alexander Doe', 'ad@b.com', 'aDoe', '$2y$12$.kwH5V5PC1OzOEeI4AFj6.7.LJL7HVKcxTe7KIgbU4GiKi', 1, 'ROLE_ADMIN');