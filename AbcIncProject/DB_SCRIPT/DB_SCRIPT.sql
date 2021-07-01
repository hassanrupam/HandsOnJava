-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.4.19-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for abc_inc_db_01_07_2021
CREATE DATABASE IF NOT EXISTS `abc_inc_db_01_07_2021` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `abc_inc_db_01_07_2021`;

-- Dumping structure for table abc_inc_db_01_07_2021.project
CREATE TABLE IF NOT EXISTS `project` (
  `prj_id` char(32) NOT NULL,
  `prj_description` varchar(512) NOT NULL,
  `prj_name` varchar(100) NOT NULL,
  `prj_status` varchar(20) NOT NULL,
  PRIMARY KEY (`prj_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Data exporting was unselected.

-- Dumping structure for table abc_inc_db_01_07_2021.task_information
CREATE TABLE IF NOT EXISTS `task_information` (
  `tsk_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tsk_name` varchar(250) NOT NULL,
  `tsk_status` varchar(20) NOT NULL,
  `tsk_project` char(32) NOT NULL,
  PRIMARY KEY (`tsk_id`),
  KEY `FKdqh46q2m9s7ffjupnb2fmh4vc` (`tsk_project`),
  CONSTRAINT `FKdqh46q2m9s7ffjupnb2fmh4vc` FOREIGN KEY (`tsk_project`) REFERENCES `project` (`prj_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Data exporting was unselected.

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
