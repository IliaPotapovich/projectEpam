-- MySQL Script generated by MySQL Workbench
-- 09/06/18 23:03:17
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema taxi_park_schema
-- -----------------------------------------------------
-- Taxi service.
-- Description of the subject area.
-- Taxi service provides transportation services to customers. Each particular carrier is a driver, and has one or more cars.
-- Each car has its own personal number, brand (model), year of manufacture and owner identification number.
-- Each driver has his identification number, name, a certain amount of experience (experience) and information about the driver's activity (is there a ban, is it active at the moment, or is busy or not working)
-- Our clients are various individuals and each has their own personal identification number, which can be easily traced to his trips.
-- We collect some information about each client (name and phone number) with which we can find the client in the database and find out if there is a bank on the client, the discount amount and the total number of trips.
-- We also collect information about the trips themselves, where each trip has its own identification number knowing that we can find out who was or is (if the trip is being performed now) by the driver and the client (by identification numbers), as well as the price of the trip and status result (whether it is executed, completed and paid, or completed and not paid)

CREATE SCHEMA IF NOT EXISTS `taxi_park_schema` DEFAULT CHARACTER SET utf8 ;
USE `taxi_park_schema` ;

-- -----------------------------------------------------
-- Table `taxi_park_schema`.`customer`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `taxi_park_schema`.`customer` (
  `customer_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'Customer ID',
  `name` VARCHAR(20) NOT NULL COMMENT 'Customer name In order to then check for a ban or discount on one of the parameters (name or phone number).',
  `phone_number` VARCHAR(20) NOT NULL COMMENT 'Customer phone number. In order to then check for a ban or discount on one of the parameters (name or phone number).',
  `status` VARCHAR(45) NOT NULL COMMENT 'Status: Is there a ban on the client',
  `discount` INT(11) NOT NULL COMMENT 'Discount percentage',
  `trip_number` INT(11) NOT NULL COMMENT 'Number of trips',
  `login` VARCHAR(45) NOT NULL 'User login',
  `password` VARCHAR(45) NOT NULL 'User password',
  PRIMARY KEY (`customer_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Taxi client description';


-- -----------------------------------------------------
-- Table `taxi_park_schema`.`taxi_drivers`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `taxi_park_schema`.`taxi_drivers` (
  `driver_id` INT(50) NOT NULL AUTO_INCREMENT COMMENT 'Driver Identification Number',
  `driver_name` VARCHAR(45) NOT NULL COMMENT 'Driver name',
  `experience` INT(25) NOT NULL COMMENT 'Driving experience',
  `status` VARCHAR(45) NOT NULL COMMENT 'Information about the drivers activity (is there a ban, is it active at the moment, is it busy or does not work)',
  `driver_password` VARCHAR(45) NOT NULL 'Driver password',
  `delete_status` VARCHAR(45) NOT NULL 'Status deleted whether user',
  PRIMARY KEY (`driver_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Taxi driver table';


-- -----------------------------------------------------
-- Table `taxi_park_schema`.`taxi_cars`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `taxi_park_schema`.`taxi_cars` (
  `car_id` INT(100) NOT NULL AUTO_INCREMENT COMMENT 'Car identification number',
  `owner_id` INT(11) NOT NULL COMMENT 'Identification number of the car owner',
  `model` VARCHAR(45) NOT NULL COMMENT 'Car model',
  `year` INT(11) NOT NULL COMMENT 'Year of issue',
  `delete_status` VARCHAR(45) NOT NULL 'Status deleted whether user',
  `image_car_id` INT NULL 'Personal photo number auto',
  PRIMARY KEY (`car_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Table with the description of the car-taxi';


-- -----------------------------------------------------
-- Table `taxi_park_schema`.`taxi_trips`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `taxi_park_schema`.`taxi_trips` (
  `trip_id` INT NOT NULL AUTO_INCREMENT COMMENT 'Personal trip number',
  `taxi_id` INT NOT NULL COMMENT 'Identification number of the driver during the trip',
  `customer_id` INT ZEROFILL NULL COMMENT 'Customer ID number during the trip',
  `price` DOUBLE NOT NULL COMMENT 'Trip price',
  `in_way` VARCHAR(20) NOT NULL COMMENT 'Trip status at the given moment (in progress, completed) If false then the trip has already been completed',
  `mark_of_trip` INT NULL COMMENT 'The assessment is made by the client',
  `customer_name` VARCHAR(45) NULL COMMENT 'Client name',
  `customer_phone` VARCHAR(45) NULL COMMENT 'Customer phone number',
  `start_trip` VARCHAR(45) NULL 'Time to start the trip',
  `finish_trip` VARCHAR(45) NULL 'Trip end time',
  PRIMARY KEY (`trip_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Trips. Executed and current';


-- -----------------------------------------------------
-- Table `taxi_park_schema`.`admin`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `taxi_park_schema`.`admin` (
  `admin_name` VARCHAR(20) NOT NULL 'Admin name',
  `admin_password` VARCHAR(45) NOT NULL 'Admin passsword',
  PRIMARY KEY (`admin_name`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `taxi_park_schema`.`taxi`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `taxi_park_schema`.`taxi` (
  `taxi_id` INT NOT NULL AUTO_INCREMENT 'Personal taxi number',
  `driver_id` INT NOT NULL 'Taxi driver personal number',
  `car_id` INT NOT NULL 'Taxi car personal number',
  `work_status` VARCHAR(45) NOT NULL 'Status, is taxi working now',
  `coordX` INT NULL 'The location of the taxi driver. X coordinate',
  `coordY` INT NULL 'The location of the taxi driver. Y coordinate',
  `free` VARCHAR(45) NULL 'Is the taxi driver currently free',
  `start_work` VARCHAR(45) NULL 'Taxi driver start time',
  `finish_work` VARCHAR(45) NULL'Taxi driver finish time',
  PRIMARY KEY (`taxi_id`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
