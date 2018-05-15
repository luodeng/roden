drop table user if exists;
create table user (
  id int primary key auto_increment,
  user_id varchar(32),
  user_name varchar(32),
  birth_day DATE,
  now TIME,
  create_time TIMESTAMP
);