CREATE TABLE employees (
      id  int(11) DEFAULT NULL auto_increment PRIMARY KEY,
      first_name VARCHAR(56),
      last_name VARCHAR(56)
  );

create table emp_roles
(
	id int not null auto_increment primary key,
	employee_id int not null,
	role_name varchar(100) not null,
	created_at datetime not null,
	updated_at datetime null,
	created_by varchar(100) not null,
	updated_by varchar(100) null
)ENGINE = InnoDB DEFAULT CHARSET = utf8;