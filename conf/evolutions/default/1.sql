# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table municipios (
  id                        bigint auto_increment not null,
  municipio                 varchar(255),
  constraint pk_municipios primary key (id))
;

create table parroquias (
  id                        bigint auto_increment not null,
  municipios_id             bigint,
  parroquia                 varchar(255),
  constraint pk_parroquias primary key (id))
;

create table registro (
  id                        bigint auto_increment not null,
  parroquias_id             bigint,
  cedula                    integer,
  nombre                    varchar(255),
  apellido                  varchar(255),
  telefono                  varchar(255),
  fecha_nac                 datetime,
  fecha_reg                 datetime,
  nacionalidad              varchar(255),
  estado_civil              varchar(255),
  direccion                 varchar(255),
  sexo                      varchar(255),
  ingreso_mensual           double,
  hijos                     integer,
  constraint pk_registro primary key (id))
;

create table registro_conyugue (
  id                        bigint auto_increment not null,
  registro_id               bigint,
  cedula                    integer,
  nombres                   varchar(255),
  apellidos                 varchar(255),
  fecha_nac                 datetime,
  nacionalidad              varchar(255),
  sexo                      varchar(255),
  constraint pk_registro_conyugue primary key (id))
;

create table solicitud (
  id                        bigint auto_increment not null,
  registro_id               bigint,
  solicitud_id              bigint,
  fecha_reg_sol             datetime,
  lph                       varchar(255),
  tenencia                  varchar(255),
  estado_sol                varchar(255),
  doc_completa              varchar(255),
  observacion               varchar(255),
  constraint pk_solicitud primary key (id))
;

create table tipo_solicitudes (
  id                        bigint auto_increment not null,
  solicitud                 varchar(255),
  codigo                    varchar(255),
  constraint pk_tipo_solicitudes primary key (id))
;

create table usuarios (
  id                        bigint auto_increment not null,
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

SET FOREIGN_KEY_CHECKS=0;

drop table municipios;

drop table parroquias;

drop table registro;

drop table registro_conyugue;

drop table solicitud;

drop table tipo_solicitudes;

drop table usuarios;

SET FOREIGN_KEY_CHECKS=1;

