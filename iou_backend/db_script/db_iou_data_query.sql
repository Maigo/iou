###############################
# META DATA                   #
###############################

# GET /rest/meta/group
SELECT column_name, is_nullable, data_type, character_maximum_length, character_octet_length, numeric_precision, numeric_scale, character_set_name, collation_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = ?;

###############################
# MAIN DATA                   #
###############################

###########
# ACCOUNT #
###########
# GET /rest/account
SELECT a.account_id, a.auth_id, a.auth_password, a.enabled FROM t_account a;
# GET /rest/account/{id}
SELECT a.account_id, a.auth_id, a.auth_password, a.enabled FROM t_account a WHERE a.account_id = ?;
# POST /rest/account
INSERT INTO t_account (auth_id, auth_password, enabled, modify_date, create_date) VALUES (?, ?, ?, NULL, NULL);
# PUT /rest/account/{id}
UPDATE t_account a SET a.auth_id = ?, a.auth_password = ? WHERE a.account_id = ?;
# DELETE /rest/account/{id}
DELETE a FROM t_account a WHERE a.account_id = ?;

########
# ROLE #
########
# GET /rest/role
SELECT r.role_id, r.name, r.description FROM t_role r;
# GET /rest/role/{id}
SELECT r.role_id, r.name, r.description FROM t_role r WHERE r.role_id = ?;
# POST /rest/role
INSERT INTO t_role (name, description, modify_date, create_date) VALUES (?, ?, NULL, NULL);
# PUT /rest/role/{id}
UPDATE t_role r SET r.name = ?, r.description = ? WHERE r.role_id = ?;
# DELETE /rest/role/{id}
DELETE r FROM t_role r WHERE r.role_id = ?;

################
# ACCOUNT-ROLE #
################
# GET /rest/role/{id}/account
SELECT a.account_id, a.auth_id, a.auth_password FROM t_account_role ar LEFT JOIN t_account a ON (ar.account_id = a.account_id) WHERE ar.role_id = ?;
# GET /rest/role/{id}/account/{id}
SELECT a.account_id, a.auth_id, a.auth_password FROM t_account_role ar LEFT JOIN t_account a ON (ar.account_id = a.account_id) WHERE ar.role_id = ? AND ar.account_id = ?;
# GET /rest/account/{id}/role
SELECT r.role_id, r.name, r.description FROM t_account_role ar LEFT JOIN t_role r ON (ar.role_id = r.role_id) WHERE ar.account_id = ?;
# GET /rest/account/{id}/role/{id}
SELECT r.role_id, r.name, r.description FROM t_account_role ar LEFT JOIN t_role r ON (ar.role_id = r.role_id) WHERE ar.account_id = ? AND ar.role_id = ?;

########
# USER #
########
# GET /rest/user
SELECT u.user_id, u.first_name, u.last_name, u.email FROM t_user u;
# GET /rest/user/{id}
SELECT u.user_id, u.first_name, u.last_name, u.email FROM t_user u WHERE u.user_id = ?;
# POST /rest/user
INSERT INTO t_user (first_name, last_name, email, modify_date, create_date) VALUES (?, ?, ?, NULL, NULL);
# PUT /rest/user/{id}
UPDATE t_user u SET u.first_name = ?, u.last_name = ?, u.email = ? WHERE u.user_id = ?;
# DELETE /rest/user/{id}
DELETE u FROM t_user u WHERE u.user_id = ?;

#########
# GROUP #
#########
# GET /rest/group
SELECT g.group_id, g.name, g.description FROM t_group g;
# GET /rest/group/{id}
SELECT g.group_id, g.name, g.description FROM t_group g WHERE g.group_id = ?;
# POST /rest/group
INSERT INTO t_group (name, description, modify_date, create_date) VALUES (?, ?, NULL, NULL);
# PUT /rest/group/{id}
UPDATE t_group g SET g.name = ?, g.description = ? WHERE g.group_id = ?;
# DELETE /rest/group/{id}
DELETE g FROM t_group g WHERE g.group_id = ?;

#########
# EVENT #
#########
# GET /rest/event
SELECT e.event_id, e.name, e.description, e.date FROM t_event e;
# /rest/event/{id}
SELECT e.event_id, e.name, e.description, e.date FROM t_event e WHERE e.event_id = ?;
# POST /rest/event
INSERT INTO t_event (name, description, date, modify_date, create_date) VALUES (?, ?, ?, NULL, NULL);
# PUT /rest/event/{id}
UPDATE t_event e SET e.name = ?, e.description = ?, e.date = ? WHERE e.event_id = ?;
# DELETE /rest/event/{id}
DELETE e FROM t_event e WHERE e.event_id = ?;

###############
# TRANSACTION #
###############
# GET /rest/transaction
SELECT t.transaction_id, t.amount, t.currency, t.date FROM t_transaction t;
# GET /rest/transaction/{id}
SELECT t.transaction_id, t.amount, t.currency, t.date FROM t_transaction t WHERE t.transaction_id = ?;

##############
# USER-GROUP #
##############
# GET /rest/user/{id}/group
SELECT g.group_id, g.name, g.description FROM t_user_group ug LEFT JOIN t_group g ON (ug.group_id = g.group_id) WHERE ug.user_id = ?;
# GET /rest/user/{id}/group/{id}
SELECT g.group_id, g.name, g.description FROM t_user_group ug LEFT JOIN t_group g ON (ug.group_id = g.group_id) WHERE ug.user_id = ? AND ug.group_id = ?;
# GET /rest/group/{id}/user
SELECT u.user_id, u.first_name, u.last_name, u.email FROM t_user_group ug LEFT JOIN t_user u ON (ug.user_id = u.user_id) WHERE ug.group_id = ?;
# GET /rest/group/{id}/user/{id}
SELECT u.user_id, u.first_name, u.last_name, u.email FROM t_user_group ug LEFT JOIN t_user u ON (ug.user_id = u.user_id) WHERE ug.group_id = ? AND ug.user_id = ?;

##############
# USER-EVENT #
##############
# GET /rest/user/{id}/event
SELECT e.event_id, e.name, e.description, e.date FROM t_user_event ue LEFT JOIN t_event e ON (ue.event_id = e.event_id) WHERE ue.user_id = ?;
# GET /rest/user/{id}/event/{id}
SELECT e.event_id, e.name, e.description, e.date FROM t_user_event ue LEFT JOIN t_event e ON (ue.event_id = e.event_id) WHERE ue.user_id = ? AND ue.event_id = ?;
# GET /rest/event/{id}/user
SELECT u.user_id, u.first_name, u.last_name, u.email FROM t_user_event ue LEFT JOIN t_user u ON (ue.user_id = u.user_id) WHERE ue.event_id = ?;
# GET /rest/event/{id}/user/{id}
SELECT u.user_id, u.first_name, u.last_name, u.email FROM t_user_event ue LEFT JOIN t_user u ON (ue.user_id = u.user_id) WHERE ue.event_id = ? AND ue.user_id = ?;

####################
# USER-TRANSACTION #
####################
# GET /rest/user/{id}/transaction
# TODO
# GET /rest/user/{id}/transaction/{id}
# TODO
