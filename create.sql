create table conta (id number(19,0) generate as identity, icone varchar(255), nome varchar(255), saldo_inicial numeric(38,2) check (saldo_inicial>=0), primary key (id));
create table despesa_artistas (id number(19,0) GENERATED ALWAYS AS IDENTITY, artista varchar(50), data_cadastro_art date not null, detalhes_art varchar(300), gravadora varchar(50), valor_art numeric(38,2) not null check (valor_art>=0), conta_id bigint, primary key (id));
create table despesa_funcionario (id number(19,0) GENERATED ALWAYS AS IDENTITY, data_cadastro_func date not null, detalhes_func varchar(300), nome_equipe varchar(50), nome_lider varchar(50), valor_func numeric(38,2) not null check (valor_func>=0), conta_id bigint, primary key (id));
create table despesa_infraestrutura (id number(19,0) GENERATED ALWAYS AS IDENTITY, data_cadastro_estrutura date not null, detalhes_estrutura varchar(300), empresa varchar(50), tipo_estrutura varchar(200), valor_estrutura numeric(38,2) not null check (valor_estrutura>=0), conta_id bigint, primary key (id));
create table usuario (id number(19,0) GENERATED ALWAYS AS IDENTITY, email varchar(255), nome varchar(255), senha varchar(255), primary key (id));
alter table if exists despesa_artistas add constraint FKqebok81nvksfwg3i77y0esgpe foreign key (conta_id) references conta;
alter table if exists despesa_funcionario add constraint FKr68j6f2b3eeptnml3v1th1ru5 foreign key (conta_id) references conta;
alter table if exists despesa_infraestrutura add constraint FKddrcyveq3prv0jy7qlikx9569 foreign key (conta_id) references conta;
