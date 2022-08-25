create table card if not exists
(
	id uuid not null
		constraint card_pkey
			primary key,
	definition varchar(255),
	term varchar(255),
	set_id uuid
		constraint fkn2f6am7956yqlr0hukry1nb6o
			references card_set
);

alter table card owner to postgres;

create table card_set if not exists
(
	id uuid not null
		constraint card_set_pkey
			primary key,
	description varchar(255),
	is_public boolean,
	title varchar(255),
	owner_id uuid
		constraint fkkfslu38cp7l261swal8t20mup
			references usr
);

alter table card_set owner to postgres;


create table card_set_editors if not exists
(
	card_set_id uuid not null
		constraint fkfbo5rd3lotk0arl8g2sexuno9
			references card_set,
	editors_id uuid not null
		constraint fkdtsobmntfsdgy2wrdc3gotrud
			references usr
);

alter table card_set_editors owner to postgres;

create table role if not exists
(
	id uuid not null
		constraint role_pkey
			primary key,
	name varchar(255)
);

alter table role owner to postgres;

create table usr if not exists
(
	id uuid not null
		constraint usr_pkey
			primary key,
	password varchar(255),
	username varchar(30)
);

alter table usr owner to postgres;

create table usr_roles if not exists
(
	user_id uuid not null
		constraint fk8fls6uiuo5dxpqdjk3y2p8v8a
			references usr,
	roles_id uuid not null
		constraint fkatlsh4dmfdkl5m7rdvhrls5if
			references role,
	constraint usr_roles_pkey
		primary key (user_id, roles_id)
);

alter table usr_roles owner to postgres;


