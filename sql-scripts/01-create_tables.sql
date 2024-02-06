CREATE SCHEMA IF NOT EXISTS `cleaningservice`;

-- Disable foreign key checks
SET FOREIGN_KEY_CHECKS = 0;

-- Drop tables
DROP TABLE IF EXISTS `user_detail`;
DROP TABLE IF EXISTS `role`;
DROP TABLE IF EXISTS `user_detail_role`;

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
    `photo` VARCHAR(255),
    `role` TINYINT UNSIGNED,
    KEY `FK_ROLE_role_id` (`role`),
    CONSTRAINT `FK_ROLE` FOREIGN KEY (`role`)
    REFERENCES `role` (`role_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for 'user_detail_role'
--
CREATE TABLE IF NOT EXISTS `user_detail_role` (
	`user_detail_id` INT UNSIGNED NOT NULL,
    `role_id` TINYINT UNSIGNED NOT NULL,
    PRIMARY KEY (`user_detail_id`, `role_id`),
    CONSTRAINT `CONSTR_USER_DETAIL_ROLE_USER_DETAIL_fk`
		FOREIGN KEY `USER_DETAIL_fk` (`user_detail_id`) REFERENCES `user_detail` (`user_detail_id`)
        ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT `CONSTR_USER_DETAIL_ROLE_ROLE_fk`
		FOREIGN KEY `ROLE_fk` (`role_id`) REFERENCES `role` (`role_id`)
        ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=latin1;


--
-- Inserting the roles into the DB
--
INSERT INTO `role` (`name`) VALUES ('ROLE_ADMIN');
INSERT INTO `role` (`name`) VALUES ('ROLE_BOSS');
INSERT INTO `role` (`name`) VALUES ('ROLE_MANAGER');
INSERT INTO `role` (`name`) VALUES ('ROLE_EMPLOYEE');
INSERT INTO `role` (`name`) VALUES ('ROLE_CUSTOMER');




