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
    
    insert into Pessoa values(1, "Asa sul", "(61)95241-9854", "74.512-632", "Planalto", "254.397.854-73", "thomashelby@outlook.com", "Thomas Shelby", 12, "5.698.745-10", "Rua 123", "(61)5241-9224", 1);
    insert into Pessoa values(2, "Setor bueno", "(62)92222-9854", "11.512-632", "Carrefour", "114.397.114-73", "dicaprio@outlook.com", "Leonardo Dicaprio", 13, "5.118.711-10", "Rua 22", "(61)3322-9224", 1);
    insert into Pessoa values(3, "Corredor Vitoria", "(61)93341-9334", "74.332-112", "Praia", "266.397.854-73", "demi@outlook.com", "Demi Lovato", 19, "5.998.799-10", "Rua 443", "(61)8841-9224", 1);
    
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
/*Hibernate:*/ 
    
    alter table Caixa 
       drop index UK_q0kuq95kkhljs73mfc3cqgiqa
/*Hibernate:*/ 
    
    alter table Caixa 
       add constraint UK_q0kuq95kkhljs73mfc3cqgiqa unique (dataAbertura)
/*Hibernate:*/ 
    
    alter table Caixa 
       add constraint FKb6atrbkd25otag59fj8itr9ge 
       foreign key (funcionario_codigo) 
       references Funcionario (codigo)
/*Hibernate:*/ 
    
    alter table Cidade 
       add constraint FKqed4xf4didnaoj5gas05bjwd9 
       foreign key (estado_codigo) 
       references Estado (codigo)
/*Hibernate:*/ 
    
    alter table Cliente 
       add constraint FKc4lj33kirbp7g6ml69pifsad3 
       foreign key (pessoa_codigo) 
       references Pessoa (codigo)
/*Hibernate:*/ 
    
    alter table Funcionario 
       add constraint FKmx5ooktj8cc6iuy6puti3eq7h 
       foreign key (pessoa_codigo) 
       references Pessoa (codigo)
/*Hibernate:*/ 
    
    alter table Historico 
       add constraint FKla3c52i52cmi7p6tru6xtxtb 
       foreign key (produto_codigo) 
       references Produto (codigo)
/*Hibernate:*/ 
    
    alter table ItemVenda 
       add constraint FK9xfrg6a3eskvqhp6t3ufad51v 
       foreign key (produto_codigo) 
       references Produto (codigo)
/*Hibernate:*/ 
    
    alter table ItemVenda 
       add constraint FK1lnqqd94h939mcq0n8s3frhcl 
       foreign key (venda_codigo) 
       references Venda (codigo)
/*Hibernate:*/ 
    
    alter table Menu 
       add constraint FKayt5rhtqki4xkger6ywue1ao5 
       foreign key (menus_codigo) 
       references Menu (codigo)
/*Hibernate:*/ 
    
    alter table Movimentacao 
       add constraint FKj69po2c2hyyiaer3xu2wyl06c 
       foreign key (caixa_codigo) 
       references Caixa (codigo)
/*Hibernate:*/ 
    
    alter table Pessoa 
       add constraint FK8x7ghll3apu2q6e02nfr47xlj 
       foreign key (cidade_codigo) 
       references Cidade (codigo)
/*Hibernate:*/ 
    
    alter table Produto 
       add constraint FK168ivj47s4nkb2931ar1qsd7l 
       foreign key (fabricante_codigo) 
       references Fabricante (codigo)
/*Hibernate:*/ 
    
    alter table Usuario 
       add constraint FKj63mm706fd9l85r5j2tqd0rbm 
       foreign key (pessoa_codigo) 
       references Pessoa (codigo)
/*Hibernate:*/ 
    
    alter table Venda 
       add constraint FK2ubp6rlo627cxy79sj17c7e7y 
       foreign key (cliente_codigo) 
       references Cliente (codigo)
/*Hibernate:*/ 
    
    alter table Venda 
       add constraint FKlkfy1qiemd9tpeejlk73ebftj 
       foreign key (funcionario_codigo) 
       references Funcionario (codigo)