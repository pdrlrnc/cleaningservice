CREATE SCHEMA IF NOT EXISTS `cleaningservice`;

USE `cleaningservice`;

-- Disable foreign key checks
SET FOREIGN_KEY_CHECKS = 0;

-- Drop tables
DROP TABLE IF EXISTS `user_detail`;
DROP TABLE IF EXISTS `role`;
DROP TABLE IF EXISTS `user_detail_role`;
DROP TABLE IF EXISTS `user_photo`;

-- Enable foreign key checks
SET FOREIGN_KEY_CHECKS = 1;

--
-- Table structure for `roles`
--
CREATE TABLE IF NOT EXISTS `role` (
	`role_id` TINYINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	`name` VARCHAR(50) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for `user_photo`
--
CREATE TABLE IF NOT EXISTS `user_photo`(
	`user_photo_id` INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	`path` VARCHAR(1000)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for `users-detail`
--
CREATE TABLE IF NOT EXISTS `user_detail` (
	`user_detail_id` INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `username` VARCHAR(50) NOT NULL,
    `email` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `first_name` VARCHAR(100) NOT NULL,
    `full_name` VARCHAR(255),
    `date_of_birth` DATE,
    `phone_number` VARCHAR(20),
    `address` VARCHAR(255),
    `photo_id` INT UNSIGNED
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



--
-- Inserting the roles into the DB
--
INSERT INTO `role` (`name`) VALUES ('ROLE_ADMIN');
INSERT INTO `role` (`name`) VALUES ('ROLE_BOSS');
INSERT INTO `role` (`name`) VALUES ('ROLE_MANAGER');
INSERT INTO `role` (`name`) VALUES ('ROLE_EMPLOYEE');
INSERT INTO `role` (`name`) VALUES ('ROLE_CUSTOMER');




