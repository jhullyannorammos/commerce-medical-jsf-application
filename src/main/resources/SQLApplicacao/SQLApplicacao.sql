drop schema drugstore;
create schema drugstore;

SELECT * FROM ESTADO;

SELECT c.nome, uf.sigla
FROM drugstore.cidade as c
JOIN drugstore.estado as uf
ON c.estado_codigo = uf.codigo;

use drugstore;    
select * from usuario;
    
    create table Caixa (codigo bigint not null,
        dataAbertura date not null,
        dataFechamento date,
        valorAbertura decimal(7,2) not null,
        funcionario_codigo bigint not null,
        primary key (codigo)) engine=InnoDB;
   
   insert into cidade values(1, "Salvador", 4);
    create table Cidade (
       codigo bigint not null,
        nome varchar(50) not null,
        estado_codigo bigint not null,
        primary key (codigo)
    ) engine=InnoDB;
/*Hibernate:*/ 
    
    create table Cliente (
       codigo bigint not null,
        dataCadastro date not null,
        liberado bit not null,
        pessoa_codigo bigint not null,
        primary key (codigo)
    ) engine=InnoDB;
/*Hibernate:*/ 
    
    insert into estado values(1, "ACRE", "AC");
    insert into estado values(2, "AMAZONAS", "AM");
    insert into estado values(3, "ALAGOAS", "AL");
    insert into estado values(4, "Bahia", "BA");
    
    create table Estado (
       codigo bigint not null,
        nome varchar(50) not null,
        sigla varchar(2) not null,
        primary key (codigo)
    ) engine=InnoDB;
/*Hibernate:*/ 
    
    create table Fabricante (
       codigo bigint not null,
        descricao varchar(50) not null,
        primary key (codigo)
    ) engine=InnoDB;
/*Hibernate:*/ 
    
    create table Funcionario (
       codigo bigint not null,
        carteiraTrabalho varchar(15) not null,
        dataAdmissao date not null,
        pessoa_codigo bigint not null,
        primary key (codigo)
    ) engine=InnoDB;
/*Hibernate:*/ 
    
    create table hibernate_sequence (
       next_val bigint
    ) engine=InnoDB;
    
    create table Historico (
       codigo bigint not null,
        horario datetime(6) not null,
        observacoes varchar(500) not null,
        produto_codigo bigint not null,
        primary key (codigo)
    ) engine=InnoDB;
/*Hibernate:*/ 
    
    create table ItemVenda (
       codigo bigint not null,
        precoParcial decimal(7,2) not null,
        quantidade smallint not null,
        produto_codigo bigint not null,
        venda_codigo bigint not null,
        primary key (codigo)
    ) engine=InnoDB;
/*Hibernate:*/ 
    
    create table Menu (
       codigo bigint not null,
        caminho varchar(50),
        rotulo varchar(50) not null,
        menus_codigo bigint,
        primary key (codigo)
    ) engine=InnoDB;
/*Hibernate:*/ 
    
    create table Movimentacao (
       codigo bigint not null,
        horario datetime(6) not null,
        valor decimal(7,2) not null,
        caixa_codigo bigint not null,
        primary key (codigo)
    ) engine=InnoDB;
/*Hibernate:*/ 
    
    insert into pessoa values(1, "Asa sul", "(61)95241-9854", "74.512-632", "Planalto", "254.397.854-73", "thomashelby@outlook.com", "Thomas Shelby", 12, "5.698.745-10", "Rua 123", "(61)5241-9224", 1);
    insert into pessoa values(2, "Setor bueno", "(62)92222-9854", "11.512-632", "Carrefour", "114.397.114-73", "dicaprio@outlook.com", "Leonardo Dicaprio", 13, "5.118.711-10", "Rua 22", "(61)3322-9224", 1);
    insert into pessoa values(3, "Corredor Vitoria", "(61)93341-9334", "74.332-112", "Praia", "266.397.854-73", "demi@outlook.com", "Demi Lovato", 19, "5.998.799-10", "Rua 443", "(61)8841-9224", 1);
    
    select * from produto;
    select * from pessoa;
	select * from cidade;
    select * from estado;
    
    create table Pessoa (codigo bigint not null,
        bairro varchar(30) not null,
        celular varchar(14) not null,
        cep varchar(10) not null,
        complemento varchar(10),
        cpf varchar(14) not null,
        email varchar(100) not null,
        nome varchar(50) not null,
        numero smallint,
        rg varchar(12) not null,
        rua varchar(100) not null,
        telefone varchar(13) not null,
        cidade_codigo bigint,
        primary key (codigo)
    ) engine=InnoDB;
/*Hibernate:*/ 
    
    create table Produto (
       codigo bigint not null,
        descricao varchar(80) not null,
        preco decimal(6,2) not null,
        quantidade smallint not null,
        fabricante_codigo bigint not null,
        primary key (codigo)
    ) engine=InnoDB;

	use drugstore;
    select cpf, senha from usuario as u join pessoa as p on u.pessoa_codigo = p.codigo where p.cpf = "254.397.854-73" and u.senha = "1q2w3e4r";
    
    insert into usuario values(1, true, "1q2w3e4r", "ADMINISTRADOR", 1);
    insert into usuario values(2, true, "12345678", "GERENTE", 3);
    insert into usuario values(3, true, "09876543", "BALCONISTA", 2);
    
    create table Usuario (
        codigo bigint not null,
        ativo bit not null,
        senha varchar(32) not null,
        tipoUsuario varchar(255) not null,
        pessoa_codigo bigint not null,
        primary key (codigo)
    ) engine=InnoDB;
/*Hibernate:*/ 
    
    create table Venda (
       codigo bigint not null,
        horario datetime(6) not null,
        precoTotal decimal(7,2) not null,
        cliente_codigo bigint,
        funcionario_codigo bigint not null,
        primary key (codigo)
    ) engine=InnoDB;
