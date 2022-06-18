create table pauta (
  id bigint not null auto_increment,
  descricao_pauta varchar(120) not null,
  data_validade datetime not null,
  primary key (id)
) engine=InnoDB default charset=utf8;

create table votacao_pauta (
  id bigint not null auto_increment,
  cpf varchar(11) not null,	
  voto varchar(3) not null,
  pauta_id bigint not null,
  primary key (id),
  constraint fk_votacao_pauta foreign key (pauta_id) references pauta (id)
) engine=InnoDB default charset=utf8;