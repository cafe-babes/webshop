DELETE FROM `users` WHERE id in (1,2);
INSERT INTO users(name, user_name, password, enabled, role, user_status) VALUES
('admin', 'admin', '$2a$04$LjyBhChXHNOl.Q/N6QQijeDWaf5Qp8S7w5f3mlxYRwWX/gQtvNAs.', 1, 'ROLE_ADMIN', 'ACTIVE');