drop table if exists user;
create table user (
  id int primary key auto_increment,
  user_id varchar(64),
  user_name varchar(32),
  birth_day DATE,
  now TIME,
  create_time TIMESTAMP
);
drop table if exists date_time;
create table date_time (
  id varchar(64) PRIMARY KEY,
  local_date DATE,
  local_time TIME,
  local_date_time DATETIME,
  create_date TIMESTAMP
);