-- :name create-dogs-table
-- :command :execute
-- :result :raw
-- :doc create users table
create table dogs(
  id integer primary key autoincrement,
  name varchar(20) not null,
  color varchar(20) not null,
  creatime datetime not null default current_timestamp
);
