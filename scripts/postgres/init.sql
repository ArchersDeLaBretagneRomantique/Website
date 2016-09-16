create table albums
(
  id bigserial,
  creation_date date not null,
  enabled boolean not null,
  title varchar(255) not null,
  primary key (id)
);

create table articles (
  id bigserial,
  content text not null,
  creation_date date not null,
  enabled boolean not null,
  title varchar(255) not null,
  primary key (id)
);

create table photos (
  id bigserial,
  file varchar(255) not null,
  album_id bigint,
  primary key (id),
  foreign key (album_id) references albums (id)
);

create table users (
  id bigserial,
  enabled boolean not null,
  locked boolean not null,
  password varchar(255) not null,
  role varchar(255) not null,
  username varchar(255) not null,
  primary key (username),
  unique (username)
);
