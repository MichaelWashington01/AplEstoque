create table movimentoestoque(
	idMovimento serial primary key,
	entradaSaida varchar (2),
	documento varchar(100),
	data varchar (20),
	quantidade numeric,
	valorMovimento numeric,
	produto integer not null,
	funcionario integer not null,
	tipoMovimento integer not null,
	CONSTRAINT fk_produto FOREIGN KEY (produto) REFERENCES produto (idProduto),
	CONSTRAINT fk_funcionario FOREIGN KEY (funcionario) REFERENCES funcionario (idFuncionario),
	CONSTRAINT fk_tipo_movimento FOREIGN KEY (tipoMovimento) REFERENCES tipomovimento (idTipoMovimento)
);


create table funcionario (
 idFuncionario serial primary key,
	nomeFuncionario varchar (100)
);

create table tipomovimento (
 idTipoMovimento serial primary key,
	descricao varchar(100)
);


create table produto (
 idProduto serial primary key,
	nomeProduto varchar (100),
	ultimoPreçoPago numeric,
	 saldoAtual numeric,
	  unidadeMedida integer not null,
	   tipoProduto integer not null,
	CONSTRAINT fk_unidade FOREIGN KEY (unidadeMedida) REFERENCES unidademedida (idUnidadeMedida),
	CONSTRAINT fk_tipo FOREIGN KEY (tipoProduto) REFERENCES tipoproduto (idTipoProduto)
);

create table unidademedida(
	idUnidadeMedida serial primary key,
	descricao varchar(100),
	sigla varchar (20)
);
INSERT INTO unidademedida (descricao, sigla) VALUES ('Metro', 'MT');

create table tipoproduto(
  idTipoProduto serial primary key,
	descricao varchar(100)
);
INSERT INTO tipoproduto(descricao) VALUES('Alimento');