create table item_pedido (id bigint not null auto_increment, observacao varchar(255), preco_total decimal(19,2) not null, preco_unitario decimal(19,2) not null, quantidade integer not null, pedido_id bigint, produto_id bigint, primary key (id)) engine=InnoDB;
create table pedido (id bigint not null auto_increment, data_cancelamento datetime, data_confirmacao datetime, data_criacao datetime not null, data_entrega datetime, endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), status_pedido integer, subtotal decimal(19,2) not null, taxa_frete decimal(19,2) not null, valor_total decimal(19,2) not null, usuario_cliente_id bigint not null, endereco_cidade_id bigint, forma_pagamento_id bigint not null, restaurante_id bigint not null, primary key (id)) engine=InnoDB;
alter table item_pedido add constraint FK60ym08cfoysa17wrn1swyiuda foreign key (pedido_id) references pedido (id);
alter table item_pedido add constraint FKtk55mn6d6bvl5h0no5uagi3sf foreign key (produto_id) references produto (id);
alter table pedido add constraint FKcccmjvm9ytuxbe00h3wmtm77y foreign key (usuario_cliente_id) references usuario (id);
alter table pedido add constraint FKk987vfg9cpgx7qxj3166fdqig foreign key (endereco_cidade_id) references cidade (id);
alter table pedido add constraint FKqaa411xsl0xu4tkvt1wpccd3b foreign key (forma_pagamento_id) references forma_pagamento (id);
alter table pedido add constraint FK3eud5cqmgsnltyk704hu3qj71 foreign key (restaurante_id) references restaurante (id);

