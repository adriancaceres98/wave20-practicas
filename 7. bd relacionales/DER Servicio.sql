-- MySQL Script generated by MySQL Workbench
-- Wed Jan 18 14:36:09 2023
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Cliente` (
  `idCliente` INT NOT NULL AUTO_INCREMENT,
  `dni` VARCHAR(45) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `apellidos` VARCHAR(45) NOT NULL,
  UNIQUE INDEX `dni_UNIQUE` (`dni` ASC) VISIBLE,
  PRIMARY KEY (`idCliente`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Coche`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Coche` (
  `idCoche` INT NOT NULL AUTO_INCREMENT,
  `marca` VARCHAR(45) NOT NULL,
  `modelo` VARCHAR(45) NOT NULL,
  `numero_puertas` INT NULL,
  `cant_kilometros` DECIMAL(2) NULL,
  `Cliente_idCliente` INT NOT NULL,
  PRIMARY KEY (`idCoche`),
  INDEX `fk_Coche_Cliente_idx` (`Cliente_idCliente` ASC) VISIBLE,
  CONSTRAINT `fk_Coche_Cliente`
    FOREIGN KEY (`Cliente_idCliente`)
    REFERENCES `mydb`.`Cliente` (`idCliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Servicio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Servicio` (
  `idServicio` INT NOT NULL,
  `fecha` DATETIME NOT NULL,
  `precio` DECIMAL(2) NULL,
  PRIMARY KEY (`idServicio`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Coche_has_Servicio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Coche_has_Servicio` (
  `Coche_idCoche` INT NOT NULL,
  `Servicio_idServicio` INT NOT NULL,
  PRIMARY KEY (`Coche_idCoche`, `Servicio_idServicio`),
  INDEX `fk_Coche_has_Servicio_Servicio1_idx` (`Servicio_idServicio` ASC) VISIBLE,
  INDEX `fk_Coche_has_Servicio_Coche1_idx` (`Coche_idCoche` ASC) VISIBLE,
  CONSTRAINT `fk_Coche_has_Servicio_Coche1`
    FOREIGN KEY (`Coche_idCoche`)
    REFERENCES `mydb`.`Coche` (`idCoche`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Coche_has_Servicio_Servicio1`
    FOREIGN KEY (`Servicio_idServicio`)
    REFERENCES `mydb`.`Servicio` (`idServicio`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
