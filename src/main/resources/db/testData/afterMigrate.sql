set foreign_key_checks = 0;

delete from cidade;
delete from cozinha;
delete from estado;
delete from forma_pagamento;
delete from grupo;
delete from grupo_permissao;
delete from permissao;
delete from produto;
delete from restaurante;
delete from restaurante_forma_pagamento;
delete from usuario;
delete from usuario_grupo;

alter table cidade auto_increment=1;
alter table cozinha auto_increment=1;
alter table estado auto_increment=1;
alter table forma_pagamento auto_increment=1;
alter table grupo auto_increment=1;
alter table permissao auto_increment=1;
alter table produto auto_increment=1;
alter table restaurante auto_increment=1;
alter table usuario auto_increment=1;

insert into estado (id, nome) values (1, 'Sao Paulo');
insert into estado (id, nome) values (2, 'Parana');
insert into estado (id, nome) values (3, 'Rio de Janeiro');
insert into estado (id, nome) values (4, 'Minas Gerais');

insert into cidade (nome, estado_id) values ('Sao Paulo', 1);
insert into cidade (nome, estado_id) values ('Sao Caetano do Sul', 1);
insert into cidade (nome, estado_id) values ('Belo Horizonte', 4);

insert into permissao (nome, descricao) values ('Consultar Forma de Pagamento','consultar a forma de pagamento');

insert into cozinha (id, nome) values (1, 'Tailandesa');
insert into cozinha (id, nome) values (2, 'Indiana');
insert into cozinha (id, nome) values (3, 'Argentina');
insert into cozinha (id, nome) values (4, 'Brasileira');

insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro) values (1, 'Thai Gourmet', 10, 1, utc_timestamp, utc_timestamp, true, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo) values (2, 'Vivano',0, 1, utc_timestamp, utc_timestamp, true);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo) values (3, 'Food4Body',5.99, 2, utc_timestamp, utc_timestamp, true);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo) values (4, 'Java Steakhouse', 12, 3, utc_timestamp, utc_timestamp, true);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo) values (5, 'Lanchonete do Tio Sam', 11, 4, utc_timestamp, utc_timestamp, true);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo) values (6, 'Bar da Maria', 6, 4, utc_timestamp, utc_timestamp, true);

insert into forma_pagamento (id, descricao) values (1, 'dinheiro');
insert into forma_pagamento (id, descricao) values (2, 'cartao de credito');
insert into forma_pagamento (id, descricao) values (3, 'pix');

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3), (4, 1), (4, 2), (5, 1), (5, 2), (6, 3);

alter table produto change descricao descricao varchar(200) not null; 

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Pizza', 'pizza de calabresa', 25.99, 1, 2);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, 1, 1);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Camarão tailandês', '16 camarões grandes ao molho picante', 110, 1, 1);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20, 1, 2);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21, 1, 3);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43, 1, 3);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79, 1, 4);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89, 1, 4);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19, 1, 5);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8, 1, 6);

insert into grupo (nome) values ('Gerente'), ('Vendedor'), ('Secretária'), ('Cadastrador'), ('Funcionário');

insert into usuario (id, nome, email, senha, data_cadastro) values (1, 'João da Silva', 'joao@gmail.com', '123', utc_timestamp);
insert into usuario (id, nome, email, senha, data_cadastro) values (2, 'Maria das Couves', 'maria@gmail.com', '123', utc_timestamp);
insert into usuario (id, nome, email, senha, data_cadastro) values (3, 'José de Souza', 'jose@gmail.com', '123', utc_timestamp);
insert into usuario (id, nome, email, senha, data_cadastro) values (4, 'Jorge Araújo', 'jorgao@gmail.com', '123', utc_timestamp);

set foreign_key_checks = 1;