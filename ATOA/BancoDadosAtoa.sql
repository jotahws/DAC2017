create table Funcionario(
	id int not null,
    idDepartamento int,
    primary key(id),
    foreign key(idDepartamento) references Departamento(id)
);

create table EdicaoAtividade(
	id int not null auto_increment,
    idAtividade int,
    inicio date,
    fim date,
    descricao varchar(100),
    statusAprovacao varchar(20),
    primary key(id),
    foreign key(idAtividade)references FuncionarioAtividade(id)
);

create table FuncionarioAtividade(
	id int not null auto_increment,
    idFuncionario int,
    idAtividade int,
    descricao varchar(100),
    statusAtividade varchar(20),
    inicio date,
    fim date,
    primary key(id),
    foreign key(idFuncionario) references Funcionario(id),
    foreign key(idAtividade) references TipoAtividade(id)
);

create table TipoAtividade(
	id int not null auto_increment,
    idDepartamento int,
    nome varchar(50),
    primary key(id),
    foreign key(idDepartamento) references Departamento(id)
);

create table Departamento(
	id int not null auto_increment,
    primary key(id)
);