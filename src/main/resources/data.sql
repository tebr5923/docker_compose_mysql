INSERT INTO task_1_1_4.role (name) VALUES ("ROLE_USER");
INSERT INTO task_1_1_4.role (name) VALUES ("ROLE_ADMIN");

INSERT INTO task_1_1_4.users(email, first_name, last_name, password)
VALUES ("user", "user", "user", "$2a$12$mWAyAiroFR7TrXb9BQrnNeYtkh3njpL.LmxN7HJbjvxj4m4QtIEny");
INSERT INTO task_1_1_4.users(email, first_name, last_name, password)
VALUES ("admin", "admin", "admin", "$2a$12$SH09Yvew2rtUN6IhfLqaL.aIhNDrwQ/KYtMRB6.4V1UUu3SeOlxGW");

INSERT INTO task_1_1_4.users_role(user_id, role_id) VALUES (1,1);
INSERT INTO task_1_1_4.users_role(user_id, role_id) VALUES (2,2);
