create schema banner_sh;

CREATE SEQUENCE banner_sh.banner_sec;

create table banner_sh.banner (id NUMBER(36) not null, content BLOB, name varchar(255), ref varchar(255), primary key (id));

CREATE TABLE banner_sh.clicks (bannerid NUMBER(36) not null, counting NUMBER(36), primary key (bannerid));

CREATE TABLE banner_sh.shows (bannerid NUMBER(36) not null, counting NUMBER(36), primary key (bannerid));

ALTER TABLE banner_sh.clicks
ADD FOREIGN KEY (bannerid)
REFERENCES banner_sh.banner(id);

ALTER TABLE banner_sh.shows
ADD FOREIGN KEY (bannerid)
REFERENCES banner_sh.banner(id);


