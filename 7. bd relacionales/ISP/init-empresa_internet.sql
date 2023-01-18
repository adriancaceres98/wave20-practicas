-- MySQL Script generated by MySQL Workbench
-- Wed Jan 18 17:44:04 2023
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
-- Table `mydb`.`plan`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`plan` (
  `id_plan` INT NOT NULL AUTO_INCREMENT,
  `velocidad_mb` INT NULL,
  `precio` INT NOT NULL,
  `descuento` DECIMAL(0) NULL,
  PRIMARY KEY (`id_plan`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`cliente` (
  `dni` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  `apellido` VARCHAR(45) NULL,
  `fecha_nacimiento` DATETIME NULL,
  `provincia` VARCHAR(45) NULL,
  `ciudad` VARCHAR(45) NULL,
  `plan_id_plan` INT NOT NULL,
  PRIMARY KEY (`dni`),
  INDEX `fk_cliente_plan_idx` (`plan_id_plan` ASC) VISIBLE,
  CONSTRAINT `fk_cliente_plan`
    FOREIGN KEY (`plan_id_plan`)
    REFERENCES `mydb`.`plan` (`id_plan`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
