# GET /rest/user
SELECT u.user_id, u.first_name, u.last_name, u.email FROM t_user u;
# GET /rest/user/{id}
SELECT u.user_id, u.first_name, u.last_name, u.email FROM t_user u WHERE u.user_id = ?;
# POST /rest/user
INSERT INTO t_user u (first_name, last_name, email, modify_date, create_date) VALUES (?, ?, ?, NULL, NULL);
# PUT /rest/user/{id}
UPDATE t_user u SET u.first_name = ?, u.last_name = ?, u.email = ? WHERE u.user_id = ?;
# DELETE /rest/user/{id}
DELETE u FROM t_user u WHERE u.user_id = ?;

# GET /rest/group
SELECT g.group_id, g.name FROM t_group g;
# GET /rest/group/{id}
SELECT g.group_id, g.name FROM t_group g WHERE g.group_id = ?;
# POST /rest/group
INSERT INTO t_group (name, modify_date, create_date) VALUES (?, NULL, NULL);
# PUT /rest/group/{id}
UPDATE t_group g SET g.name = ? WHERE g.group_id = ?;
# DELETE /rest/group/{id}
DELETE g FROM t_group g WHERE g.group_id = ?;

# GET /rest/event
SELECT e.event_id, e.name, e.description, e.date FROM t_event e;
# /rest/event/{id}
SELECT e.event_id, e.name, e.description, e.date FROM t_event e WHERE e.event_id = ?;
# POST /rest/event
INSERT INTO t_event e (name, description, date, modify_date, create_date) VALUES (?, ?, ?, NULL, NULL);
# PUT /rest/event/{id}
UPDATE t_event e SET e.name = ?, e.description = ?, e.date = ? WHERE e.event_id = ?;
# DELETE /rest/event/{id}
DELETE e FROM t_event e WHERE e.event_id = ?;

# GET /rest/transaction
SELECT t.transaction_id, t.amount, t.currency, t.date FROM t_transaction t;
# GET /rest/transaction/{id}
SELECT t.transaction_id, t.amount, t.currency, t.date FROM t_transaction t WHERE t.transaction_id = ?;

# GET /rest/user/{id}/group
SELECT g.group_id, g.name FROM t_user_group ug LEFT JOIN t_group g ON (ug.group_id = g.group_id) WHERE ug.user_id = ?;
# GET /rest/user/{id}/group/{id}
SELECT g.group_id, g.name FROM t_user_group ug LEFT JOIN t_group g ON (ug.group_id = g.group_id) WHERE ug.user_id = ? AND ug.group_id = ?;

# GET /rest/user/{id}/event
SELECT e.event_id, e.name, e.description, e.date FROM t_user_event ue LEFT JOIN t_event e ON (ue.event_id = e.event_id) WHERE ue.user_id = ?;
# GET /rest/user/{id}/event/{id}
SELECT e.event_id, e.name, e.description, e.date FROM t_user_event ue LEFT JOIN t_event e ON (ue.event_id = e.event_id) WHERE ue.user_id = ? AND ue.event_id = ?;

# GET /rest/user/{id}/transaction
# TODO
# GET /rest/user/{id}/transaction/{id}
# TODO

# GET /rest/group/{id}/user
SELECT u.user_id, u.first_name, u.last_name, u.email FROM t_user_group ug LEFT JOIN t_user u ON (ug.user_id = u.user_id) WHERE ug.group_id = ?;
# GET /rest/group/{id}/user/{id}
SELECT u.user_id, u.first_name, u.last_name, u.email FROM t_user_group ug LEFT JOIN t_user u ON (ug.user_id = u.user_id) WHERE ug.group_id = ? AND ug.user_id = ?;

# GET /rest/event/{id}/user
SELECT u.user_id, u.first_name, u.last_name, u.email FROM t_user_event ue LEFT JOIN t_user u ON (ue.user_id = u.user_id) WHERE ue.event_id = ?;
# GET /rest/event/{id}/user/{id}
SELECT u.user_id, u.first_name, u.last_name, u.email FROM t_user_event ue LEFT JOIN t_user u ON (ue.user_id = u.user_id) WHERE ue.event_id = ? AND ue.user_id = ?;
