SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
CREATE SCHEMA IF NOT EXISTS `db_example` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;
USE `db_example` ;

-- -----------------------------------------------------
-- Table `db_example`.`editoras`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `db_example`.`editoras` (
  `ID_EDITORA` INT(11) NOT NULL AUTO_INCREMENT ,
  `RAZAO_SOCIAL` VARCHAR(60) NOT NULL ,
  `CIDADE` VARCHAR(60) NOT NULL ,
  `EMAIL` VARCHAR(60) NOT NULL ,
  PRIMARY KEY (`ID_EDITORA`) ,
  UNIQUE INDEX `RAZAO_SOCIAL_UNIQUE` (`RAZAO_SOCIAL` ASC) ,
  UNIQUE INDEX `EMAIL_UNIQUE` (`EMAIL` ASC) )
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `db_example`.`hibernate_sequence`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `db_example`.`hibernate_sequence` (
  `next_val` BIGINT(20) NULL DEFAULT NULL )
ENGINE = MyISAM
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `db_example`.`user`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `db_example`.`user` (
  `id` INT(11) NOT NULL ,
  `email` VARCHAR(255) NULL DEFAULT NULL ,
  `name` VARCHAR(255) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = MyISAM
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `db_example`.`autores`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `db_example`.`autores` (
  `ID_AUTOR` INT NOT NULL ,
  `NOME` VARCHAR(45) NOT NULL ,
  `EMAIL` VARCHAR(45) NOT NULL ,
  `ID_EDITORA` INT(11) NOT NULL ,
  PRIMARY KEY (`ID_AUTOR`) ,
  UNIQUE INDEX `NOME_UNIQUE` (`NOME` ASC) ,
  UNIQUE INDEX `EMAIL_UNIQUE` (`EMAIL` ASC) ,
  INDEX `fk_autores_editoras_idx` (`ID_EDITORA` ASC) ,
  CONSTRAINT `fk_autores_editoras`
    FOREIGN KEY (`ID_EDITORA` )
    REFERENCES `db_example`.`editoras` (`ID_EDITORA` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
