create table users
(email varchar(30) primary key,
 first_name varchar(30),
 last_name varchar(30),
 admin boolean,
 last_login timestamp,
 is_active boolean,
 password varchar(300));
