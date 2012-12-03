# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table municipios (
  id                        bigint not null,
  municipio                 varchar(255),
  constraint pk_municipios primary key (id))
;

create table parroquias (
  id                        bigint not null,
  municipios_id             bigint,
  parroquia                 varchar(255),
  constraint pk_parroquias primary key (id))
;

create table registro (
  id                        bigint not null,
  parroquias_id             bigint,
  cedula                    integer,
  nombre                    varchar(255),
  apellido                  varchar(255),
  telefono                  varchar(255),
  fecha_nac                 timestamp,
  fecha_reg                 timestamp,
  nacionalidad              varchar(255),
  estado_civil              varchar(255),
  direccion                 varchar(255),
  sexo                      varchar(255),
  ingreso_mensual           double,
  hijos                     integer,
  constraint pk_registro primary key (id))
;

create table registro_conyugue (
  id                        bigint not null,
  registro_id               bigint,
  cedula                    integer,
  nombres                   varchar(255),
  apellidos                 varchar(255),
  fecha_nac                 timestamp,
  nacionalidad              varchar(255),
  sexo                      varchar(255),
  constraint pk_registro_conyugue primary key (id))
;

create table solicitud (
  id                        integer not null,
  registro_id               bigint,
  solicitud_id              integer,
  codigo                    varchar(255),
  lph                       boolean,
  tenencia                  varchar(255),
  estado_sol                varchar(255),
  doc_completa              boolean,
  observacion               varchar(255),
  constraint pk_solicitud primary key (id))
;

create table tipo_solicitudes (
  id                        integer not null,
  solicitud                 varchar(255),
  codigo                    varchar(255),
  constraint pk_tipo_solicitudes primary key (id))
;

create table usuarios (
  id                        bigint not null,
  usuario                   varchar(255),
  contrasena                varchar(255),
  cedula                    integer,
  nombre                    varchar(255),
  apellido                  varchar(255),
  sexo                      varchar(255),
  tipo                      varchar(255),
  theme                     varchar(255),
  constraint pk_usuarios primary key (id))
;

create sequence municipios_seq;

create sequence parroquias_seq;

create sequence registro_seq;

create sequence registro_conyugue_seq;

create sequence solicitud_seq;

create sequence tipo_solicitudes_seq;

create sequence usuarios_seq;

alter table parroquias add constraint fk_parroquias_municipios_1 foreign key (municipios_id) references municipios (id) on delete restrict on update restrict;
create index ix_parroquias_municipios_1 on parroquias (municipios_id);
alter table registro add constraint fk_registro_parroquias_2 foreign key (parroquias_id) references parroquias (id) on delete restrict on update restrict;
create index ix_registro_parroquias_2 on registro (parroquias_id);
alter table registro_conyugue add constraint fk_registro_conyugue_registro_3 foreign key (registro_id) references registro (id) on delete restrict on update restrict;
create index ix_registro_conyugue_registro_3 on registro_conyugue (registro_id);
alter table solicitud add constraint fk_solicitud_registro_4 foreign key (registro_id) references registro (id) on delete restrict on update restrict;
create index ix_solicitud_registro_4 on solicitud (registro_id);
alter table solicitud add constraint fk_solicitud_solicitud_5 foreign key (solicitud_id) references tipo_solicitudes (id) on delete restrict on update restrict;
create index ix_solicitud_solicitud_5 on solicitud (solicitud_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists municipios;

drop table if exists parroquias;

drop table if exists registro;

drop table if exists registro_conyugue;

drop table if exists solicitud;

drop table if exists tipo_solicitudes;

drop table if exists usuarios;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists municipios_seq;

drop sequence if exists parroquias_seq;

drop sequence if exists registro_seq;

drop sequence if exists registro_conyugue_seq;

drop sequence if exists solicitud_seq;

drop sequence if exists tipo_solicitudes_seq;

drop sequence if exists usuarios_seq;

