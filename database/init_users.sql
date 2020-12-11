INSERT INTO distacours.role (id, created_at, updated_at, authority, enabled) VALUES (1, '2020-11-29 21:05:13', '2020-11-29 21:05:13', 'guest', false);
INSERT INTO distacours.role (id, created_at, updated_at, authority, enabled) VALUES (2, '2020-11-29 21:05:22', '2020-11-29 21:05:22', 'admin', false);
INSERT INTO distacours.role (id, created_at, updated_at, authority, enabled) VALUES (3, '2020-11-29 21:05:34', '2020-11-29 21:05:34', 'member', false);

INSERT INTO distacours.user (id, created_at, updated_at, account_non_expired, account_non_locked, credentials_non_expired, email, enabled, password, username) VALUES (1, '2020-11-22 20:32:55', '2020-11-22 20:33:23', true, true, true, null, true, '$2a$10$RTI/UxMmjB3.2.8us82ohuYKMlVxV5SX4e1Ca.GNVBG.h1cLD6c56', 'musta');
INSERT INTO distacours.user (id, created_at, updated_at, account_non_expired, account_non_locked, credentials_non_expired, email, enabled, password, username) VALUES (3, '2020-11-22 22:30:34', '2020-11-23 00:23:17', true, true, true, null, true, '$2a$10$q9dD27WQc4pGlTo1VXcS.ezD7EoOKC8fkwI4e4rM1neuKwWsq3ZWm', 'musta_');
INSERT INTO distacours.user (id, created_at, updated_at, account_non_expired, account_non_locked, credentials_non_expired, email, enabled, password, username) VALUES (4, '2020-11-28 15:08:14', '2020-11-28 15:15:26', true, true, true, 'musta@musta.com', true, '$2a$10$yfxbZC6IAok7/NyalXhUC.GXZcVkbVhroIulHGZCjwT/m.h/sKp1O', 'new');

INSERT INTO distacours.user_role (user_id, role_id) VALUES (4, 3);
INSERT INTO distacours.user_role (user_id, role_id) VALUES (4, 2);
INSERT INTO distacours.user_role (user_id, role_id) VALUES (4, 1);