create table Funcionario(
	id int not null auto_increment,
    idCargo int,
    idDepartamento int,
    idEndereco int,
    nome varchar(100) not null,
    cpf varchar(11) unique,
    rg varchar(11) unique,
    email varchar(40),
    celular varchar(14),
    primary key(id),
    foreign key(idCargo) references Cargo(id),
    foreign key(idDepartamento) references Departamento(id),
    foreign key(idEndereco) references Endereco(id)
);

create table Cargo(
	id int not null auto_increment,
    nome varchar(50) not null,
    salario double,
    requisitos varchar(100),
    horasMinimas time,
    descontoImposto int,
    primary key(id)
);


create table Departamento(
	id int not null auto_increment,
    nome varchar(50) not null,
    localizacao varchar(50) not null,
    primary key(id)
);


create table Endereco(
	id int not null auto_increment,
    idCidade int,
    rua varchar(100),
    numero varchar(5),
    cep varchar(9),
    bairro varchar(45),
    primary key(id),
    foreign key(idCidade) references Cidade(id)
);

create table Cidade(
	id int not null auto_increment,
    idEstado int,
    nome varchar(100) not null,
    primary key(id),
    foreign key(idEstado) references Estado(id)
);

create table Estado(
	id int not null auto_increment,
    uf varchar(2),
    nome varchar(50),
    primary key(id)
);