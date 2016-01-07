create table if not exists role (
	role_id serial primary key,
	role_name varchar(30) not null
);

create table if not exists users (
	user_id serial primary key,
	login varchar(30) not null,
	password varchar(20) not null,
	name varchar(30) not null,
	surname varchar(30) not null,
	email varchar(40) not null,
	role_id integer references role(role_id)
);

create table if not exists calendar (
	cal_id serial primary key,
	user_id integer references users(user_id)
);

create table if not exists event (
	event_id serial primary key,
	date_time timestamp not null,
	event_name varchar(100) not null,
	event_desc text,
	cal_id integer references calendar(cal_id)
);

create table if not exists file (
	file_id serial primary key,
	file_name varchar(30) not null,
	file_ext varchar(10),
	file_binary BYTEA not null,
	user_id integer references users(user_id)
);

create table if not exists shared_file (
	shared_id serial primary key,
	file_id integer references file(file_id),
	reveiver_id integer references users(user_id)
);
