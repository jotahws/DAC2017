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
    foreign key(idAtividade) references TipoAtividade(id)
);

create table TipoAtividade(
	id int not null auto_increment,
    idDepartamento int,
    nome varchar(50),
    primary key(id)
);

DELIMITER $$
DROP TRIGGER IF EXISTS sistema_atoa.str_empty$$
USE `sistema_atoa`$$
CREATE TRIGGER `sistema_atoa`.`str_empty` BEFORE INSERT ON `FuncionarioAtividade` 
FOR EACH ROW
BEGIN
	if (new.descricao = "") OR (new.statusAtividade = "") then
     signal sqlstate '45000';
    end if;
END$$
DELIMITER ;

DELIMITER $$
DROP TRIGGER IF EXISTS sistema_atoa.str_empty$$
USE `sistema_atoa`$$
CREATE TRIGGER `sistema_atoa`.`str_empty_atv_updat` BEFORE update ON `FuncionarioAtividade` 
FOR EACH ROW
BEGIN
	if (new.descricao = "") OR (new.statusAtividade = "") then
     signal sqlstate '45000';
    end if;
END$$
DELIMITER ;

DELIMITER $$
DROP TRIGGER IF EXISTS sistema_atoa.str_empty_tipo$$
USE `sistema_atoa`$$
CREATE TRIGGER `sistema_atoa`.`str_empty_tipo` BEFORE INSERT ON `TipoAtividade` 
FOR EACH ROW
BEGIN
	if (new.nome = "") then
     signal sqlstate '45000';
    end if;
END$$
DELIMITER ;

DELIMITER $$
DROP TRIGGER IF EXISTS sistema_atoa.str_empty_tipo$$
USE `sistema_atoa`$$
CREATE TRIGGER `sistema_atoa`.`str_empty_tipo_up` BEFORE UPDATE ON `TipoAtividade` 
FOR EACH ROW
BEGIN
	if (new.nome = "") then
     signal sqlstate '45000';
    end if;
END$$
DELIMITER ;

create table Departamento(
	id int not null auto_increment,
    primary key(id)
);

DELIMITER $$
DROP TRIGGER IF EXISTS sistema_atoa.str_empty_edicao$$
USE `sistema_atoa`$$
CREATE TRIGGER `sistema_atoa`.`str_empty_edicao` BEFORE INSERT ON `EdicaoAtividade` 
FOR EACH ROW
BEGIN
	if (new.descricao = "") OR (new.statusAprovacao = "") then
     signal sqlstate '45000';
    end if;
END$$
DELIMITER ;

DELIMITER $$
DROP TRIGGER IF EXISTS sistema_atoa.str_empty_edicao_up$$
USE `sistema_atoa`$$
CREATE TRIGGER `sistema_atoa`.`str_empty_up` BEFORE UPDATE ON `EdicaoAtividade` 
FOR EACH ROW
BEGIN
	if (new.descricao = "") OR (new.statusAprovacao = "") then
     signal sqlstate '45000';
    end if;
END$$
DELIMITER ;

create table statusAtividade(
	id int not null auto_increment,
    nome varchar(50),
    primary key(id)
);

INSERT INTO statusAtividade (nome) VALUES ("INICIADO");
INSERT INTO statusAtividade (nome) VALUES ("ENCERRADO");
INSERT INTO statusAtividade (nome) VALUES ("PAUSADO");

ALTER TABLE `sistema_atoa`.`FuncionarioAtividade` 
CHANGE COLUMN `statusAtividade` `statusAtividade` INT NULL DEFAULT NULL ,
ADD INDEX `funcionarioatividade_ibfk_2_idx` (`statusAtividade` ASC);
ALTER TABLE `sistema_atoa`.`FuncionarioAtividade` 
ADD CONSTRAINT `funcionarioatividade_ibfk_2`
  FOREIGN KEY (`statusAtividade`)
  REFERENCES `sistema_atoa`.`statusAtividade` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;



alter table funcionarioatividade CHANGE inicio inicio TIMESTAMP ;
alter table funcionarioatividade CHANGE fim fim TIMESTAMP ;
