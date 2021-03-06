SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;

USE `mydb`;

ALTER TABLE `db_example`.`autores` DROP FOREIGN KEY `fk_autores_editoras` ;

ALTER TABLE `db_example`.`autores` DROP COLUMN `ID_EDITORA` , ADD COLUMN `ID_EDITORA` INT(11) NOT NULL  AFTER `EMAIL` , 
  ADD CONSTRAINT `fk_autores_editoras`
  FOREIGN KEY (`ID_EDITORA` )
  REFERENCES `db_example`.`editoras` (`ID_EDITORA` )
  ON DELETE CASCADE
  ON UPDATE NO ACTION
, ADD INDEX `fk_autores_editoras_idx` (`ID_EDITORA` ASC) 
, DROP INDEX `fk_autores_editoras_idx` ;

CREATE  TABLE IF NOT EXISTS `db_example`.`livros` (
  `ID_LIVRO` INT(11) NOT NULL ,
  `TITULO` VARCHAR(45) NOT NULL ,
  `EDICAO` INT(11) NOT NULL ,
  `PAGINAS` INT(11) NOT NULL ,
  `livroscol` VARCHAR(45) NULL DEFAULT NULL ,
  `livroscol1` VARCHAR(45) NULL DEFAULT NULL ,
  PRIMARY KEY (`ID_LIVRO`) ,
  UNIQUE INDEX `TITULO_EDICAO` (`TITULO` ASC, `EDICAO` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE  TABLE IF NOT EXISTS `db_example`.`livros_autores` (
  `ID_LIVRO_AUTOR` INT(11) NOT NULL ,
  `ID_AUTOR` INT(11) NOT NULL ,
  `ID_LIVRO` INT(11) NOT NULL ,
  INDEX `fk_autores_has_livros_livros1_idx` (`ID_LIVRO` ASC) ,
  INDEX `fk_autores_has_livros_autores1_idx` (`ID_AUTOR` ASC) ,
  PRIMARY KEY (`ID_LIVRO_AUTOR`) ,
  UNIQUE INDEX `ID_LIVRO_ID_AUTOR` (`ID_AUTOR` ASC, `ID_LIVRO` ASC) ,
  CONSTRAINT `fk_autores_has_livros_autores1`
    FOREIGN KEY (`ID_AUTOR` )
    REFERENCES `db_example`.`autores` (`ID_AUTOR` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_autores_has_livros_livros1`
    FOREIGN KEY (`ID_LIVRO` )
    REFERENCES `db_example`.`livros` (`ID_LIVRO` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
