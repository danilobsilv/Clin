create table pacientes(

    id bigint not null auto_increment,
    nome varchar(100) not null,
    email varchar(150) not null,
    telefone varchar(20) not null,
    cpf varchar(20) not null,
    logradouro varchar(100) not null,
    bairro varchar(200) not null,
    cep varchar(9) not null,
    complemento varchar(200),
    numero varchar(10),
    uf char(2) not null,
    cidade varchar(100) not null,

    primary key(id)

);