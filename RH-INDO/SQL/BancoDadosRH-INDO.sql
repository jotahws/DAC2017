

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


ALTER TABLE `sistema_rhindo`.`Cargo` 
CHANGE COLUMN `horasMinimas` `horasMinimas` INT NULL DEFAULT NULL ,
CHANGE COLUMN `descontoImposto` `descontoImposto` DOUBLE NULL DEFAULT NULL ,
CHANGE COLUMN `requisitos` `requisitos` VARCHAR(1000) NULL DEFAULT NULL;
ALTER TABLE `sistema_rhindo`.`Funcionario`
ADD COLUMN `perfil` VARCHAR(45) NULL AFTER `celular`;

ALTER TABLE `sistema_rhindo`.`Endereco` 
CHANGE COLUMN `numero` `numero` INT NULL DEFAULT NULL ;

ALTER TABLE `sistema_rhindo`.`Funcionario`
ADD COLUMN `senha` VARCHAR(45) NULL AFTER `perfil`;

-- TRIGGERS PARA ENVIAR UM ERRO QUANDO ELE TENTA INSERIR STRING SEM NADA 
-- tb DEPARTAMENTO
DELIMITER $$
DROP TRIGGER IF EXISTS sistema_rhindo.str_empty$$
USE `sistema_rhindo`$$
CREATE TRIGGER `sistema_rhindo`.`str_empty` BEFORE INSERT ON `Departamento` 
FOR EACH ROW
BEGIN
	if (new.localizacao = "") OR (new.nome = "") then
     signal sqlstate '45000';
    end if;
END$$
DELIMITER ;
-- tb CARGO
DELIMITER $$
DROP TRIGGER IF EXISTS sistema_rhindo.str_empty_cargo$$
USE `sistema_rhindo`$$
CREATE TRIGGER `sistema_rhindo`.`str_empty_cargo` BEFORE INSERT ON `Cargo` 
FOR EACH ROW
BEGIN
	if (new.nome = '') OR (new.requisitos = '') then
     signal sqlstate '45000';
    end if;
END$$
DELIMITER ;
-- tb Cidade
DELIMITER $$
DROP TRIGGER IF EXISTS sistema_rhindo.str_empty_cidade$$
USE `sistema_rhindo`$$
CREATE TRIGGER `sistema_rhindo`.`str_empty_cidade` BEFORE INSERT ON `Cidade` 
FOR EACH ROW
BEGIN
	if (new.nome = '') then
     signal sqlstate '45000';
    end if;
END$$
DELIMITER ;
-- tb Endereco
DELIMITER $$
DROP TRIGGER IF EXISTS sistema_rhindo.str_empty_endereco$$
USE `sistema_rhindo`$$
CREATE TRIGGER `sistema_rhindo`.`str_empty_endereco` BEFORE INSERT ON `Endereco` 
FOR EACH ROW
BEGIN
	if (new.rua = '') OR (new.cep = '') OR (new.bairro = '') then
     signal sqlstate '45000';
    end if;
END$$
DELIMITER ;
-- tb Estado
DELIMITER $$
DROP TRIGGER IF EXISTS sistema_rhindo.str_empty_estado$$
USE `sistema_rhindo`$$
CREATE TRIGGER `sistema_rhindo`.`str_empty_estado` BEFORE INSERT ON `Estado` 
FOR EACH ROW
BEGIN
	if (new.uf = '') OR (new.nome = '') then
     signal sqlstate '45000';
    end if;
END$$
DELIMITER ;
-- tb Funcionario
DELIMITER $$
DROP TRIGGER IF EXISTS sistema_rhindo.str_empty_funcionario$$
USE `sistema_rhindo`$$
CREATE TRIGGER `sistema_rhindo`.`str_empty_funcionario` BEFORE INSERT ON `Funcionario` 
FOR EACH ROW
BEGIN
	if (new.nome = '') OR (new.cpf = '') OR (new.rg = '') OR (new.celular = '') OR (new.email = '') OR (new.perfil = '') OR (new.senha = '') then
     signal sqlstate '45000';
    end if;
END$$
DELIMITER ;


-- TRIGGERS PARA ENVIAR UM ERRO QUANDO ELE TENTA DAR UPDATE COM STRING SEM NADA 
-- tb DEPARTAMENTO
DELIMITER $$
DROP TRIGGER IF EXISTS sistema_rhindo.str_empty_update$$
USE `sistema_rhindo`$$
CREATE TRIGGER `sistema_rhindo`.`str_empty_update` BEFORE UPDATE ON `Departamento` 
FOR EACH ROW
BEGIN
	if (new.localizacao = "") OR (new.nome = "") then
     signal sqlstate '45000';
    end if;
END$$
DELIMITER ;
-- tb CARGO
DELIMITER $$
DROP TRIGGER IF EXISTS sistema_rhindo.str_empty_cargo_update$$
USE `sistema_rhindo`$$
CREATE TRIGGER `sistema_rhindo`.`str_empty_cargo_update` BEFORE UPDATE ON `Cargo` 
FOR EACH ROW
BEGIN
	if (new.nome = '') OR (new.requisitos = '') then
     signal sqlstate '45000';
    end if;
END$$
DELIMITER ;
-- tb Cidade
DELIMITER $$
DROP TRIGGER IF EXISTS sistema_rhindo.str_empty_cidade_update$$
USE `sistema_rhindo`$$
CREATE TRIGGER `sistema_rhindo`.`str_empty_cidade_update` BEFORE UPDATE ON `Cidade` 
FOR EACH ROW
BEGIN
	if (new.nome = '') then
     signal sqlstate '45000';
    end if;
END$$
DELIMITER ;
-- tb Endereco
DELIMITER $$
DROP TRIGGER IF EXISTS sistema_rhindo.str_empty_endereco_update$$
USE `sistema_rhindo`$$
CREATE TRIGGER `sistema_rhindo`.`str_empty_endereco_update` BEFORE UPDATE ON `Endereco` 
FOR EACH ROW
BEGIN
	if (new.rua = '') OR (new.cep = '') OR (new.bairro = '') then
     signal sqlstate '45000';
    end if;
END$$
DELIMITER ;
-- tb Estado
DELIMITER $$
DROP TRIGGER IF EXISTS sistema_rhindo.str_empty_estado_update$$
USE `sistema_rhindo`$$
CREATE TRIGGER `sistema_rhindo`.`str_empty_estado_update` BEFORE UPDATE ON `Estado` 
FOR EACH ROW
BEGIN
	if (new.uf = '') OR (new.nome = '') then
     signal sqlstate '45000';
    end if;
END$$
DELIMITER ;
-- tb Funcionario
DELIMITER $$
DROP TRIGGER IF EXISTS sistema_rhindo.str_empty_funcionario_update$$
USE `sistema_rhindo`$$
CREATE TRIGGER `sistema_rhindo`.`str_empty_funcionario_update` BEFORE UPDATE ON `Funcionario` 
FOR EACH ROW
BEGIN
	if (new.nome = '') OR (new.cpf = '') OR (new.rg = '') OR (new.celular = '') OR (new.email = '') OR (new.perfil = '') OR (new.senha = '') then
     signal sqlstate '45000';
    end if;
END$$
DELIMITER ;



 -- ALGUNS INSERTS
insert into funcionario(idCargo,idDepartamento,idEndereco,nome,cpf,rg,email,
celular,perfil,senha) values (1,1,1,'Mauricio de Araujo','07094657935',
'135034657','araujoito@gmail.com','99494401','GERENTE-RH','1234');

insert into cidade(idEstado,nome) values(1,'Curitiba');

insert into Endereco(idCidade,rua,numero,cep,bairro)
values(1,'Rua das dores','860','82620130','Tingui');

insert into cargo(nome,salario,requisitos,horasMinimas,descontoImposto)values
('Analista',3900,'Ser alguma coisa pra analista',40,10);

insert into Departamento(nome,localizacao) values('Desenvolvimento','3 Andar');