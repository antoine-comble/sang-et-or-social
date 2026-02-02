TRUNCATE TABLE roles;
TRUNCATE TABLE users;
TRUNCATE TABLE user_roles;

INSERT INTO roles VALUES (1, 'USER');
INSERT INTO roles VALUES (2, 'ADMIN');
INSERT INTO roles VALUES (3, 'MODERATEUR');

INSERT INTO users VALUES (1, now(), 'user1@test.com', true, 'password', 'user1');
INSERT INTO users VALUES (2, now(), 'user2@test.com', true, 'password', 'user2');
INSERT INTO users VALUES (3, now(), 'user3@test.com', true, 'password', 'user3');

INSERT INTO user_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO user_roles (user_id, role_id) VALUES (2,2);
INSERT INTO user_roles (user_id, role_id) VALUES (3,3);
