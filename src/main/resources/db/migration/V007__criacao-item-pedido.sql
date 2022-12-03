create table item_pedido (
	id bigint not null auto_increment, 
    observacao varchar(200), 
    preco_total decimal(19,2) not null, 
    preco_unitario decimal(19,2) not null, 
    quantidade integer not null, 
    pedido_id bigint, 
    produto_id bigint, 
    
    primary key (id)
) engine=InnoDB default charset=utf8;

create table pedido (
	id bigint not null auto_increment, 
    data_cancelamento datetime, 
    data_confirmacao datetime, 
    data_criacao datetime not null, 
    data_entrega datetime, 
    endereco_bairro varchar(80), 
    endereco_cep varchar(20), 
    endereco_complemento varchar(80), 
    endereco_logradouro varchar(80), 
    endereco_numero varchar(20), 
    status_pedido integer, 
    subtotal decimal(19,2) not null, 
    taxa_frete decimal(19,2) not null, 
    valor_total decimal(19,2) not null, 
    usuario_cliente_id bigint not null, 
    endereco_cidade_id bigint, 
    forma_pagamento_id bigint not null, 
    restaurante_id bigint not null, 
    
    primary key (id)
) engine=InnoDB default charset=utf8;

alter table item_pedido add constraint fk_item_pedido foreign key (pedido_id) references pedido (id);
alter table item_pedido add constraint fk_item_produto foreign key (produto_id) references produto (id);
alter table pedido add constraint fk_pedido_usuario foreign key (usuario_cliente_id) references usuario (id);
alter table pedido add constraint fk_pedido_cidade foreign key (endereco_cidade_id) references cidade (id);
alter table pedido add constraint fk_pedido_forma_pagamento foreign key (forma_pagamento_id) references forma_pagamento (id);
alter table pedido add constraint fk_pedido_restaurante foreign key (restaurante_id) references restaurante (id);
