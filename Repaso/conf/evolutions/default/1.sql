# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table compu (
  id                            bigserial not null,
  nombre                        varchar(255),
  descripcion                   varchar(255),
  constraint pk_compu primary key (id)
);


# --- !Downs

drop table if exists compu cascade;

