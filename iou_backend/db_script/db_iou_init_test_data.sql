# specify database
USE db_iou;

# delete test values
DELETE FROM t_user_event;
DELETE FROM t_event;
DELETE FROM t_user_group;
DELETE FROM t_group;
DELETE FROM t_user;

DELETE FROM t_account_role;
DELETE FROM t_role;
DELETE FROM t_account;

# insert test values
INSERT INTO t_account (auth_id, auth_pwd, enabled, modify_date, create_date)
VALUES ('admin', 'admin123', true, NULL, NULL),
       ('test',  'test123',  true, NULL, NULL),
       ('guest', 'guest123', true, NULL, NULL);

INSERT INTO t_role (name, description, modify_date, create_date)
VALUES ('admin', '', NULL, NULL),
       ('user',  '', NULL, NULL),
       ('guest', '', NULL, NULL);

INSERT INTO t_account_role (account_id, role_id, modify_date, create_date)
 SELECT a.account_id, r.role_id, NULL, NULL
 FROM t_account a
 LEFT JOIN t_role r ON (r.name = 'admin')
 WHERE a.auth_id IN ('admin')
UNION ALL
 SELECT a.account_id, r.role_id, NULL, NULL
 FROM t_account a
 LEFT JOIN t_role r ON (r.name = 'user')
 WHERE a.auth_id IN ('test')
UNION ALL
 SELECT a.account_id, r.role_id, NULL, NULL
 FROM t_account a
 LEFT JOIN t_role r ON (r.name = 'guest')
 WHERE a.auth_id IN ('guest');

INSERT INTO t_user (first_name, last_name, email, modify_date, create_date)
VALUES ('teodor', 'jakobsson', 'teja@email.com', NULL, NULL),
       ('erik',   'jakobsson', 'erja@email.com', NULL, NULL),
       ('niklas', 'myrberg',   'nimy@email.com', NULL, NULL),
       ('joel',   'nises',     'joni@email.com', NULL, NULL),
       ('justine','trenh',     'jutr@email.com', NULL, NULL);

INSERT INTO t_group (name, description, modify_date, create_date)
VALUES ('busy_waiting', '', NULL, NULL),
       ('jakobsson', '', NULL, NULL),
       ('home', '', NULL, NULL);

INSERT INTO t_user_group (user_id, group_id, modify_date, create_date)
 SELECT u.user_id, g.group_id, NULL, NULL
 FROM t_user u
 LEFT JOIN t_group g ON (g.name = 'busy_waiting')
 WHERE u.first_name IN ('teodor','niklas','joel','justine')
UNION ALL
 SELECT u.user_id, g.group_id, NULL, NULL
 FROM t_user u
 LEFT JOIN t_group g ON (g.name = 'jakobsson')
 WHERE u.first_name IN ('teodor','erik')
UNION ALL
 SELECT u.user_id, g.group_id, NULL, NULL
 FROM t_user u
 LEFT JOIN t_group g ON (g.name = 'home')
 WHERE u.first_name IN ('teodor','justine');

INSERT INTO t_event (name, description, date, modify_date, create_date)
VALUES ('gravity', '', '2013-11-02', NULL, NULL),
       ('thor 2',  '', '2013-11-08', NULL, NULL);

INSERT INTO t_user_event (user_id, event_id, modify_date, create_date)
 SELECT ug.user_id, e.event_id, NULL, NULL
 FROM t_user_group ug
 INNER JOIN t_group g ON (ug.group_id = g.group_id AND g.name = 'busy_waiting')
 INNER JOIN t_event e ON (e.name = 'gravity')
UNION ALL
 SELECT ug.user_id, e.event_id, NULL, NULL
 FROM t_user_group ug
 INNER JOIN t_group g ON (ug.group_id = g.group_id AND g.name = 'home')
 INNER JOIN t_event e ON (e.name = 'thor 2');
