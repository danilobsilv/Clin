CREATE TABLE usuarios(

    id bigint not null auto_increment,
    login varchar(200) not null,
    senha varchar(200) not null,

    primary key(id)

);