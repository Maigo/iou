# specify database
USE db_iou;

# drop all tables
DROP TABLE IF EXISTS t_transaction;
DROP TABLE IF EXISTS t_user_event;
DROP TABLE IF EXISTS t_event;
DROP TABLE IF EXISTS t_user_group;
DROP TABLE IF EXISTS t_group;
DROP TABLE IF EXISTS t_user;

DROP TABLE IF EXISTS t_account_role;
DROP TABLE IF EXISTS t_role;
DROP TABLE IF EXISTS t_account;

# recreate all tables
CREATE TABLE t_account (
  account_id INT NOT NULL AUTO_INCREMENT,
  auth_id VARCHAR(45) NOT NULL,
  auth_pwd VARCHAR(45) NOT NULL,
  enabled BIT NOT NULL DEFAULT TRUE,
  modify_date TIMESTAMP,
  create_date TIMESTAMP,
  PRIMARY KEY (account_id)
);

CREATE TABLE t_role (
  role_id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(45) NOT NULL,
  description VARCHAR(255) NULL,
  modify_date TIMESTAMP,
  create_date TIMESTAMP,
  PRIMARY KEY (role_id)  
);

CREATE TABLE t_account_role (
  account_id INT NOT NULL,
  role_id INT NOT NULL,
  modify_date TIMESTAMP,
  create_date TIMESTAMP,
  FOREIGN KEY (account_id)  REFERENCES t_account (account_id) ON DELETE CASCADE,
  FOREIGN KEY (role_id) REFERENCES t_role (role_id) ON DELETE CASCADE,
  PRIMARY KEY (account_id, role_id)
);

CREATE TABLE t_user (
  user_id INT NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(45) NOT NULL,
  last_name VARCHAR(45) NOT NULL,
  email VARCHAR(45) NOT NULL,
  modify_date TIMESTAMP,
  create_date TIMESTAMP,
  PRIMARY KEY (user_id)
);

CREATE TABLE t_group (
  group_id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(45) NOT NULL,
  description VARCHAR(255) NULL,
  modify_date TIMESTAMP,
  create_date TIMESTAMP,
  PRIMARY KEY (group_id)
);

CREATE TABLE t_user_group (
  user_id INT NOT NULL,
  group_id INT NOT NULL,
  privilages INT NOT NULL DEFAULT 0,
  modify_date TIMESTAMP,
  create_date TIMESTAMP,
  FOREIGN KEY (user_id)  REFERENCES t_user (user_id) ON DELETE CASCADE,
  FOREIGN KEY (group_id) REFERENCES t_group (group_id) ON DELETE CASCADE,
  PRIMARY KEY (user_id, group_id)
);

CREATE TABLE t_event (
  event_id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(45) NOT NULL,
  description VARCHAR(255) NULL,
  date DATETIME NOT NULL,
  modify_date TIMESTAMP,
  create_date TIMESTAMP,
  PRIMARY KEY (event_id)
);

CREATE TABLE t_user_event (
  user_id INT NOT NULL,
  event_id INT NOT NULL,
  modify_date TIMESTAMP,
  create_date TIMESTAMP,
  FOREIGN KEY (user_id)  REFERENCES t_user (user_id) ON DELETE CASCADE,
  FOREIGN KEY (event_id) REFERENCES t_event (event_id) ON DELETE CASCADE,
  PRIMARY KEY (user_id, event_id)
);

CREATE TABLE t_transaction (
  transaction_id INT NOT NULL AUTO_INCREMENT,
  amount DECIMAL(10,2) NOT NULL,
  currency CHAR(3) NOT NULL,
  date DATETIME NOT NULL,
# ...
  modify_date TIMESTAMP,
  create_date TIMESTAMP,
  PRIMARY KEY (transaction_id)
);
