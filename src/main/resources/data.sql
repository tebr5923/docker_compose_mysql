INSERT INTO task_1_1_4.role (name) VALUES ("ROLE_USER");
INSERT INTO task_1_1_4.role (name) VALUES ("ROLE_ADMIN");

INSERT INTO task_1_1_4.users(email, first_name, last_name, password)
VALUES ("user", "user", "user", "user");
INSERT INTO task_1_1_4.users(email, first_name, last_name, password)
VALUES ("admin", "admin", "admin", "admin");

INSERT INTO task_1_1_4.users_role(user_id, role_id) VALUES (1,1);
INSERT INTO task_1_1_4.users_role(user_id, role_id) VALUES (2,2);
