create table client (id bigint not null auto_increment, courriel varchar(255), date_naissance date, nom varchar(255), prenom varchar(255), tel varchar(255), primary key (id)) engine=InnoDB
create table compose (tickets_numero bigint not null, mets_nom varchar(50) not null) engine=InnoDB
create table met (nom varchar(50) not null, prix float not null, type varchar(255), primary key (nom)) engine=InnoDB
create table tables (numero bigint not null, nb_couvert integer not null, supplement float not null, type varchar(50), primary key (numero)) engine=InnoDB
create table ticket (numero bigint not null auto_increment, addition float not null, date date, nb_couvert integer not null, client_id bigint, tables_numero bigint, primary key (numero)) engine=InnoDB
alter table compose add constraint FK3mpvpyxplpyiiqywfr63sh9qg foreign key (mets_nom) references met (nom)
alter table compose add constraint FK9nf8o5dgg4ob7jm5jjdc12l24 foreign key (tickets_numero) references ticket (numero)
alter table ticket add constraint FKj2rjr6m31hp7m00tm1hdxckov foreign key (client_id) references client (id)
alter table ticket add constraint FKg9r48jhxp9w23570fuyx5ckut foreign key (tables_numero) references tables (numero)
create table client (id bigint not null auto_increment, courriel varchar(255), date_naissance date, nom varchar(255), prenom varchar(255), tel varchar(255), primary key (id)) engine=InnoDB
create table compose (tickets_numero varchar(50) not null, mets_nom bigint not null) engine=InnoDB
create table met (nom varchar(50) not null, prix float not null, type varchar(255), primary key (nom)) engine=InnoDB
create table tables (numero bigint not null, nb_couvert integer not null, supplement float not null, type varchar(50), primary key (numero)) engine=InnoDB
create table ticket (numero bigint not null auto_increment, addition float not null, date date, nb_couvert integer not null, client_id bigint, tables_numero bigint, primary key (numero)) engine=InnoDB
alter table compose add constraint FKapyc03alogcxvv9ghxltjcxob foreign key (mets_nom) references ticket (numero)
alter table compose add constraint FKk1lco6imouy9dlr1pka0sj8ih foreign key (tickets_numero) references met (nom)
alter table ticket add constraint FKj2rjr6m31hp7m00tm1hdxckov foreign key (client_id) references client (id)
alter table ticket add constraint FKg9r48jhxp9w23570fuyx5ckut foreign key (tables_numero) references tables (numero)
create table client (id bigint not null auto_increment, courriel varchar(255), date_naissance date, nom varchar(255), prenom varchar(255), tel varchar(255), primary key (id)) engine=InnoDB
create table compose (tickets_numero bigint not null, mets varchar(50) not null) engine=InnoDB
create table met (nom varchar(50) not null, prix float not null, type varchar(255), primary key (nom)) engine=InnoDB
create table tables (numero bigint not null, nb_couvert integer not null, supplement float not null, type varchar(50), primary key (numero)) engine=InnoDB
create table ticket (numero bigint not null auto_increment, addition float not null, date date, nb_couvert integer not null, client_id bigint, tables_numero bigint, primary key (numero)) engine=InnoDB
alter table compose add constraint FKqlonq8tammggq5918531ug8c4 foreign key (mets) references met (nom)
alter table compose add constraint FK9nf8o5dgg4ob7jm5jjdc12l24 foreign key (tickets_numero) references ticket (numero)
alter table ticket add constraint FKj2rjr6m31hp7m00tm1hdxckov foreign key (client_id) references client (id)
alter table ticket add constraint FKg9r48jhxp9w23570fuyx5ckut foreign key (tables_numero) references tables (numero)
