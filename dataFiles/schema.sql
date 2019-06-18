-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema gardenPlanner
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `gardenPlanner`;

-- -----------------------------------------------------
-- Schema gardenPlanner
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `gardenPlanner` DEFAULT CHARACTER SET utf8 ;
USE `gardenPlanner` ;

-- -----------------------------------------------------
-- Table `gardenPlanner`.`plantHeight`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gardenPlanner`.`plantHeight` ;

CREATE TABLE IF NOT EXISTS `gardenPlanner`.`plantHeight` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `description` VARCHAR(200) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gardenPlanner`.`plantSunRequirement`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gardenPlanner`.`plantSunRequirement` ;

CREATE TABLE IF NOT EXISTS `gardenPlanner`.`plantSunRequirement` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `description` VARCHAR(200) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gardenPlanner`.`lifecycle`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gardenPlanner`.`lifecycle` ;

CREATE TABLE IF NOT EXISTS `gardenPlanner`.`lifecycle` (
  `id` INT NOT NULL,
  `name` VARCHAR(50) NOT NULL,
  `description` VARCHAR(200) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gardenPlanner`.`waterPreference`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gardenPlanner`.`waterPreference` ;

CREATE TABLE IF NOT EXISTS `gardenPlanner`.`waterPreference` (
  `id` INT NOT NULL,
  `name` VARCHAR(50) NOT NULL,
  `description` VARCHAR(200) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gardenPlanner`.`plant`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gardenPlanner`.`plant` ;

CREATE TABLE IF NOT EXISTS `gardenPlanner`.`plant` (
  `commonName` VARCHAR(100) NOT NULL,
  `species` VARCHAR(100) NULL,
  `genus` VARCHAR(100) NULL,
  `plantingDepth` DECIMAL(8,2) NULL COMMENT 'in inches',
  `distanceBetweenRows` DECIMAL(8,2) NULL COMMENT 'in inches',
  `distanceBetweenRowsAfterThinning` DECIMAL(8,2) NULL,
  `id` INT NOT NULL AUTO_INCREMENT,
  `RR` TINYINT NULL DEFAULT 0,
  `H` TINYINT NULL DEFAULT 0,
  `plantsPerRow` VARCHAR(45) NULL COMMENT 'seeds/plants per 10’ row in #/onces',
  `plantsPerRowOnces` TINYINT NULL,
  `startIndoorsWeekCountS` DECIMAL(8,2) NULL,
  `startOutdoorsWeekCountS` DECIMAL(8,2) NULL,
  `startIndoorsWeekCountF` DECIMAL(8,2) NULL,
  `startOutdoorsWeekCountF` DECIMAL(8,2) NULL,
  `firstHarvestDateAfterStartOutdoors` INT NULL,
  `lastHarvestDateBeforeFreeze` INT NULL,
  `protectFromHeat` TINYINT NULL,
  `averageYield` DECIMAL(8,2) NULL COMMENT 'lbs per 10’ row, 12” wide',
  `plantHeight_id` INT NULL,
  `plantSunRequirement_id` INT NULL,
  `poleTrellis` TINYINT NULL,
  `ladderTrellis` TINYINT NULL,
  `icon` VARCHAR(200) NULL COMMENT 'url or icon relative path\n',
  `lifecycle_id` INT NULL,
  `waterPreference_id` INT NULL,
  `imageUrl` VARCHAR(500) NULL,
  `plantsPerSquareFoot` DECIMAL(10,2) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_plant_plantHeight_idx` (`plantHeight_id` ASC),
  INDEX `fk_plant_plantSunRequirements1_idx` (`plantSunRequirement_id` ASC),
  INDEX `fk_plant_lifecycle1_idx` (`lifecycle_id` ASC),
  INDEX `fk_plant_waterPreferences1_idx` (`waterPreference_id` ASC),
  CONSTRAINT `fk_plant_plantHeight`
    FOREIGN KEY (`plantHeight_id`)
    REFERENCES `gardenPlanner`.`plantHeight` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_plant_plantSunRequirements1`
    FOREIGN KEY (`plantSunRequirement_id`)
    REFERENCES `gardenPlanner`.`plantSunRequirement` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_plant_lifecycle1`
    FOREIGN KEY (`lifecycle_id`)
    REFERENCES `gardenPlanner`.`lifecycle` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_plant_waterPreferences1`
    FOREIGN KEY (`waterPreference_id`)
    REFERENCES `gardenPlanner`.`waterPreference` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gardenPlanner`.`companionBenefit`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gardenPlanner`.`companionBenefit` ;

CREATE TABLE IF NOT EXISTS `gardenPlanner`.`companionBenefit` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NULL,
  `description` VARCHAR(200) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gardenPlanner`.`plantCompanion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gardenPlanner`.`plantCompanion` ;

CREATE TABLE IF NOT EXISTS `gardenPlanner`.`plantCompanion` (
  `firstPlantId` INT NOT NULL,
  `secondPlantId` INT NOT NULL,
  `companionBenefitId` INT NOT NULL,
  `id` INT NOT NULL AUTO_INCREMENT,
  INDEX `fk_plant_has_plant_plant2_idx` (`secondPlantId` ASC),
  INDEX `fk_plantCompanion_companionBenefit1_idx` (`companionBenefitId` ASC),
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_plant_has_plant_plant1`
    FOREIGN KEY (`firstPlantId`)
    REFERENCES `gardenPlanner`.`plant` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_plant_has_plant_plant2`
    FOREIGN KEY (`secondPlantId`)
    REFERENCES `gardenPlanner`.`plant` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_plantCompanion_companionBenefit1`
    FOREIGN KEY (`companionBenefitId`)
    REFERENCES `gardenPlanner`.`companionBenefit` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gardenPlanner`.`incompatabilityType`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gardenPlanner`.`incompatabilityType` ;

CREATE TABLE IF NOT EXISTS `gardenPlanner`.`incompatabilityType` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `description` VARCHAR(200) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gardenPlanner`.`plantIncompatable`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gardenPlanner`.`plantIncompatable` ;

CREATE TABLE IF NOT EXISTS `gardenPlanner`.`plantIncompatable` (
  `firstPlantId` INT NOT NULL,
  `secondPlantId` INT NOT NULL,
  `incompatabilityTypeId` INT NOT NULL,
  `id` INT NOT NULL AUTO_INCREMENT,
  INDEX `fk_plant_has_plant_plant4_idx` (`secondPlantId` ASC),
  INDEX `fk_plant_has_plant_plant3_idx` (`firstPlantId` ASC),
  INDEX `fk_plantIncompatable_incompatabilityType1_idx` (`incompatabilityTypeId` ASC),
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_plant_has_plant_plant3`
    FOREIGN KEY (`firstPlantId`)
    REFERENCES `gardenPlanner`.`plant` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_plant_has_plant_plant4`
    FOREIGN KEY (`secondPlantId`)
    REFERENCES `gardenPlanner`.`plant` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_plantIncompatable_incompatabilityType1`
    FOREIGN KEY (`incompatabilityTypeId`)
    REFERENCES `gardenPlanner`.`incompatabilityType` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gardenPlanner`.`hardinessZone`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gardenPlanner`.`hardinessZone` ;

CREATE TABLE IF NOT EXISTS `gardenPlanner`.`hardinessZone` (
  `zipcode` INT NOT NULL,
  `zone` VARCHAR(45) NOT NULL,
  `trange` VARCHAR(45) NULL,
  `zonetitle` VARCHAR(45) NULL,
  `lastSpringFrost` DATE NOT NULL,
  `firstFallFrost` DATE NOT NULL,
  PRIMARY KEY (`zipcode`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gardenPlanner`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gardenPlanner`.`user` ;

CREATE TABLE IF NOT EXISTS `gardenPlanner`.`user` (
  `id` VARCHAR(100) NOT NULL,
  `phone` VARCHAR(45) NULL,
  `notificationEmail` VARCHAR(45) NULL COMMENT 'different than sign in email?',
  `hardinessZone_zipcode` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_hardinessZones1_idx` (`hardinessZone_zipcode` ASC),
  CONSTRAINT `fk_user_hardinessZones1`
    FOREIGN KEY (`hardinessZone_zipcode`)
    REFERENCES `gardenPlanner`.`hardinessZone` (`zipcode`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gardenPlanner`.`gardenDiagram`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gardenPlanner`.`gardenDiagram` ;

CREATE TABLE IF NOT EXISTS `gardenPlanner`.`gardenDiagram` (
  `userId` VARCHAR(100) NOT NULL,
  `gridCount` INT NOT NULL,
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT 'Untitled',
  `year` DATE NULL,
  INDEX `fk_gardenLayout_user1_idx` (`userId` ASC),
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_gardenLayout_user1`
    FOREIGN KEY (`userId`)
    REFERENCES `gardenPlanner`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gardenPlanner`.`plantGardenObject`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gardenPlanner`.`plantGardenObject` ;

CREATE TABLE IF NOT EXISTS `gardenPlanner`.`plantGardenObject` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `x` INT NOT NULL DEFAULT 0,
  `y` INT NOT NULL DEFAULT 0,
  `w` INT NOT NULL DEFAULT 0,
  `h` INT NOT NULL DEFAULT 0,
  `plantId` INT NOT NULL,
  `gardenDiagramId` INT NOT NULL,
  `icon` VARCHAR(200) NULL,
  `minW` DECIMAL(8,2) NULL,
  `minH` DECIMAL(8,2) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_plantGardenObject_plant1_idx` (`plantId` ASC),
  INDEX `fk_plantGardenObject_gardenDiagram1_idx` (`gardenDiagramId` ASC),
  CONSTRAINT `fk_plantGardenObject_plant1`
    FOREIGN KEY (`plantId`)
    REFERENCES `gardenPlanner`.`plant` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_plantGardenObject_gardenDiagram1`
    FOREIGN KEY (`gardenDiagramId`)
    REFERENCES `gardenPlanner`.`gardenDiagram` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gardenPlanner`.`generalCategory`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gardenPlanner`.`generalCategory` ;

CREATE TABLE IF NOT EXISTS `gardenPlanner`.`generalCategory` (
  `id` INT NOT NULL,
  `name` VARCHAR(50) NOT NULL,
  `description` VARCHAR(200) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gardenPlanner`.`plant_generalCategory`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gardenPlanner`.`plant_generalCategory` ;

CREATE TABLE IF NOT EXISTS `gardenPlanner`.`plant_generalCategory` (
  `generalCategory_id` INT NOT NULL,
  `plant_id` INT NOT NULL,
  `id` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  INDEX `fk_generalCategory_has_plant_plant1_idx` (`plant_id` ASC),
  INDEX `fk_generalCategory_has_plant_generalCategory1_idx` (`generalCategory_id` ASC),
  CONSTRAINT `fk_generalCategory_has_plant_generalCategory1`
    FOREIGN KEY (`generalCategory_id`)
    REFERENCES `gardenPlanner`.`generalCategory` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_generalCategory_has_plant_plant1`
    FOREIGN KEY (`plant_id`)
    REFERENCES `gardenPlanner`.`plant` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gardenPlanner`.`userPlant`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gardenPlanner`.`userPlant` ;

CREATE TABLE IF NOT EXISTS `gardenPlanner`.`userPlant` (
  `userId` VARCHAR(100) NOT NULL,
  `plantId` INT NOT NULL,
  `id` INT NOT NULL AUTO_INCREMENT,
  INDEX `fk_user_has_plant_plant1_idx` (`plantId` ASC),
  INDEX `fk_user_has_plant_user1_idx` (`userId` ASC),
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_user_has_plant_user1`
    FOREIGN KEY (`userId`)
    REFERENCES `gardenPlanner`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_plant_plant1`
    FOREIGN KEY (`plantId`)
    REFERENCES `gardenPlanner`.`plant` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
