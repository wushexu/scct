/*
SQLyog Ultimate v8.32 
MySQL - 5.1.58-community : Database - scctbi_analysis_tst
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`scctbi_analysis_tst` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `scctbi_analysis_tst`;

/*Table structure for table `category` */

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_date` varchar(27) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `createDate` varchar(27) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `PK__category__3213E83FBE6241A6` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `report_definition` */

DROP TABLE IF EXISTS `report_definition`;

CREATE TABLE `report_definition` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` varchar(27) DEFAULT NULL,
  `last_modified_date` varchar(27) DEFAULT NULL,
  `code` varchar(255) NOT NULL,
  `content` longblob,
  `content_size` int(10) NOT NULL,
  `datasource_type` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `memo` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `created_by` bigint(19) DEFAULT NULL,
  `last_modified_by` bigint(19) DEFAULT NULL,
  `category_id` bigint(19) DEFAULT NULL,
  `createdDate` varchar(27) DEFAULT NULL,
  `lastModifiedDate` varchar(27) DEFAULT NULL,
  `datasourceType` varchar(255) DEFAULT NULL,
  `createdBy_id` bigint(19) DEFAULT NULL,
  `lastModifiedBy_id` bigint(19) DEFAULT NULL,
  `categoryId_id` bigint(19) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `PK__report_d__3213E83F773D4FCB` (`id`),
  UNIQUE KEY `UK_j7gh9qltrbxjtfj5b601m4it4` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `reportdefinition` */

DROP TABLE IF EXISTS `reportdefinition`;

CREATE TABLE `reportdefinition` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createdDate` varchar(27) DEFAULT NULL,
  `lastModifiedDate` varchar(27) DEFAULT NULL,
  `code` varchar(255) NOT NULL,
  `content` longblob,
  `contentSize` int(10) NOT NULL,
  `datasourceType` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `memo` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `createdBy_id` bigint(19) DEFAULT NULL,
  `lastModifiedBy_id` bigint(19) DEFAULT NULL,
  `categoryId_id` bigint(19) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `PK__ReportDe__3213E83FA71428CD` (`id`),
  UNIQUE KEY `UK_1h5tv75gswccgireybpn207f2` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `PK__users__3213E83F0CDC9D04` (`id`),
  UNIQUE KEY `UK_3g1j96g94xpk3lpxl2qbl985x` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
