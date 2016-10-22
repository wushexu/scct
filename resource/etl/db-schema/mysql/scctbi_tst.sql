/*
SQLyog Ultimate v8.32 
MySQL - 5.1.58-community : Database - scctbi_tst
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`scctbi_tst` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `scctbi_tst`;

/*Table structure for table `carrier` */

DROP TABLE IF EXISTS `carrier`;

CREATE TABLE `carrier` (
  `carrCode` varchar(50) NOT NULL,
  `carrName` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`carrCode`),
  UNIQUE KEY `PK__carrier__45BB81BF70DB0EC1` (`carrCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `category` */

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `PK__Category__3213E83FD4C93F1E` (`id`),
  UNIQUE KEY `UK_foei036ov74bv692o5lh5oi66` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `country` */

DROP TABLE IF EXISTS `country`;

CREATE TABLE `country` (
  `ctryCode` varchar(3) NOT NULL,
  `ctryName` varchar(200) DEFAULT NULL,
  `ctryRegion` varchar(20) DEFAULT NULL,
  `subGeo` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ctryCode`),
  UNIQUE KEY `PK__country__6CA595CD02BDDCB9` (`ctryCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `datelatitude` */

DROP TABLE IF EXISTS `datelatitude`;

CREATE TABLE `datelatitude` (
  `date_id` varchar(100) NOT NULL,
  `year_id` int(10) DEFAULT NULL,
  `month_id` int(10) DEFAULT NULL,
  `qtr_id` int(10) DEFAULT NULL,
  `week_day_id` int(10) DEFAULT NULL,
  `week_id` int(10) DEFAULT NULL,
  `week_name` int(10) DEFAULT NULL,
  `month_name` varchar(100) DEFAULT NULL,
  `qtr_name` varchar(100) DEFAULT NULL,
  `finance_year_id` int(10) DEFAULT NULL,
  `finance_week_id` int(10) DEFAULT NULL,
  `finance_week_name` varchar(100) DEFAULT NULL,
  `week_day_name` varchar(100) DEFAULT NULL,
  `day_of_month` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`date_id`),
  UNIQUE KEY `PK__dateLati__51FC486528B33F03` (`date_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `datelatitude_test` */

DROP TABLE IF EXISTS `datelatitude_test`;

CREATE TABLE `datelatitude_test` (
  `year_id` int(10) DEFAULT NULL,
  `month_id` int(10) DEFAULT NULL,
  `qtr_id` int(10) DEFAULT NULL,
  `week_day_id` int(10) DEFAULT NULL,
  `week_id` int(10) DEFAULT NULL,
  `week_name` int(10) DEFAULT NULL,
  `month_name` varchar(100) DEFAULT NULL,
  `qtr_name` varchar(100) DEFAULT NULL,
  `finance_year_id` varchar(100) DEFAULT NULL,
  `finance_week_id` varchar(100) DEFAULT NULL,
  `finance_week_name` varchar(100) DEFAULT NULL,
  `date_id` varchar(100) DEFAULT NULL,
  `week_day_name` varchar(100) DEFAULT NULL,
  `day_of_month` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `deliveryorder` */

DROP TABLE IF EXISTS `deliveryorder`;

CREATE TABLE `deliveryorder` (
  `dlvryNum` varchar(20) DEFAULT NULL,
  `X1ReasonCode` varchar(255) DEFAULT NULL,
  `carrCode` varchar(34) DEFAULT NULL,
  `carrName` varchar(80) DEFAULT NULL,
  `carrPickupDate` timestamp NULL DEFAULT NULL,
  `cdd` timestamp NULL DEFAULT NULL,
  `containerNumber` varchar(50) DEFAULT NULL,
  `containerSize` varchar(50) DEFAULT NULL,
  `createdDate` timestamp NULL DEFAULT NULL,
  `departurePort` varchar(50) DEFAULT NULL,
  `deptDate` timestamp NULL DEFAULT NULL,
  `destinationPort` varchar(50) DEFAULT NULL,
  `dlvryItemQty` int(10) DEFAULT NULL,
  `estArrDate` timestamp NULL DEFAULT NULL,
  `exportCustomGate` varchar(50) DEFAULT NULL,
  `hawbHswb` varchar(50) DEFAULT NULL,
  `id` bigint(19) NOT NULL,
  `idocCreatedDate` timestamp NULL DEFAULT NULL,
  `idocNum` int(10) DEFAULT NULL,
  `lastUpdateDate` timestamp NULL DEFAULT NULL,
  `mainCarrier` varchar(50) DEFAULT NULL,
  `modeOfTrspn` varchar(40) DEFAULT NULL,
  `modifiedDate` timestamp NULL DEFAULT NULL,
  `orderMilestone` varchar(50) DEFAULT NULL,
  `orderMilestoneName` varchar(255) DEFAULT NULL,
  `packDate` timestamp NULL DEFAULT NULL,
  `podDate` timestamp NULL DEFAULT NULL,
  `podEntryDate` timestamp NULL DEFAULT NULL,
  `preCarrier` varchar(50) DEFAULT NULL,
  `rte` varchar(12) DEFAULT NULL,
  `serviceLevel` varchar(50) DEFAULT NULL,
  `shipToCtryCd` varchar(6) DEFAULT NULL,
  `shipToCtryNm` varchar(80) DEFAULT NULL,
  `shippedQty` decimal(38,3) DEFAULT NULL,
  `shpngCode` varchar(8) DEFAULT NULL,
  `shpngSrc` varchar(100) DEFAULT NULL,
  `slsOrderNum` varchar(20) DEFAULT NULL,
  `sosDlvryNum` varchar(20) DEFAULT NULL,
  `sosOrderNum` varchar(20) DEFAULT NULL,
  `statusCode` varchar(50) DEFAULT NULL,
  `subMilestone` varchar(50) DEFAULT NULL,
  `subMilestoneName` varchar(255) DEFAULT NULL,
  `truck` varchar(50) DEFAULT NULL,
  `dk01Color` varchar(10) DEFAULT NULL,
  `dk02Color` varchar(10) DEFAULT NULL,
  `dk03Color` varchar(10) DEFAULT NULL,
  `dk04Color` varchar(10) DEFAULT NULL,
  `dk05Color` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `PK__delivery__3213E83F805F5DDE` (`id`),
  KEY `idx_deliveryOrder_lookup` (`dlvryNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `deliveryorderfact` */

DROP TABLE IF EXISTS `deliveryorderfact`;

CREATE TABLE `deliveryorderfact` (
  `dlvryNum` varchar(20) DEFAULT NULL,
  `X1ReasonCode` varchar(255) DEFAULT NULL,
  `carrCode` varchar(34) DEFAULT NULL,
  `cdd_date_id` varchar(12) DEFAULT NULL,
  `cdd_finance_week_id` varchar(100) DEFAULT NULL,
  `cdd_finance_year_id` varchar(100) DEFAULT NULL,
  `cdd_month_id` int(10) DEFAULT NULL,
  `cdd_qtr_id` int(10) DEFAULT NULL,
  `cdd_year_id` int(10) DEFAULT NULL,
  `id` bigint(19) NOT NULL,
  `modeOfTrspn` varchar(40) DEFAULT NULL,
  `pod_date_id` varchar(12) DEFAULT NULL,
  `pod_finance_week_id` varchar(100) DEFAULT NULL,
  `pod_finance_year_id` varchar(100) DEFAULT NULL,
  `pod_month_id` int(10) DEFAULT NULL,
  `pod_qtr_id` int(10) DEFAULT NULL,
  `pod_year_id` int(10) DEFAULT NULL,
  `rte` varchar(12) DEFAULT NULL,
  `shipToCtryCd` varchar(6) DEFAULT NULL,
  `shpngCode` varchar(8) DEFAULT NULL,
  `statusCode` varchar(50) DEFAULT NULL,
  `CDD_detractor` varchar(100) DEFAULT NULL,
  `actlGoodsIssueDate_id` varchar(100) DEFAULT NULL,
  `carrPickupDate_id` varchar(100) DEFAULT NULL,
  `createdDate_id` varchar(100) DEFAULT NULL,
  `modifiedDate_id` varchar(100) DEFAULT NULL,
  `estArrDate_id` varchar(100) DEFAULT NULL,
  `idocCreatedDate_id` varchar(100) DEFAULT NULL,
  `lastUpdateDate_id` varchar(100) DEFAULT NULL,
  `podEntryDate_id` varchar(100) DEFAULT NULL,
  `handOverDate_id` varchar(100) DEFAULT NULL,
  `packDate_id` varchar(100) DEFAULT NULL,
  `pickDate_id` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `PK__delivery__3213E83FFD32A9E6` (`id`),
  KEY `idx_deliveryOrderFact_lookup` (`dlvryNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `deliveryorderitem` */

DROP TABLE IF EXISTS `deliveryorderitem`;

CREATE TABLE `deliveryorderitem` (
  `dlvryNum` varchar(20) DEFAULT NULL,
  `dlvryItemNum` varchar(12) DEFAULT NULL,
  `id` bigint(19) NOT NULL,
  `dlvrdQty` decimal(15,3) DEFAULT NULL,
  `carrCode` varchar(34) DEFAULT NULL,
  `carrName` varchar(80) DEFAULT NULL,
  `carrPickupDate` timestamp NULL DEFAULT NULL,
  `modeOfTrspn` varchar(40) DEFAULT NULL,
  `rte` varchar(12) DEFAULT NULL,
  `shipToCtryCd` varchar(6) DEFAULT NULL,
  `shipToCtryNm` varchar(80) DEFAULT NULL,
  `shpngSrc` varchar(100) DEFAULT NULL,
  `createdDate` timestamp NULL DEFAULT NULL,
  `modifiedDate` timestamp NULL DEFAULT NULL,
  `estArrDate` timestamp NULL DEFAULT NULL,
  `idocNum` int(10) DEFAULT NULL,
  `idocCreatedDate` timestamp NULL DEFAULT NULL,
  `lastUpdateDate` timestamp NULL DEFAULT NULL,
  `deptDate` timestamp NULL DEFAULT NULL,
  `podEntryDate` timestamp NULL DEFAULT NULL,
  `cdd` timestamp NULL DEFAULT NULL,
  `podDate` timestamp NULL DEFAULT NULL,
  `orderMilestone` varchar(50) DEFAULT NULL,
  `orderMilestoneName` varchar(255) DEFAULT NULL,
  `packDate` timestamp NULL DEFAULT NULL,
  `pickDate` timestamp NULL DEFAULT NULL,
  `slsOrdrNum` varchar(20) DEFAULT NULL,
  `slsOrdrLineNum` varchar(12) DEFAULT NULL,
  `productCode` varchar(36) DEFAULT NULL,
  `productDesc` varchar(80) DEFAULT NULL,
  `matlGrp1` varchar(4) DEFAULT NULL,
  `orderType` varchar(8) DEFAULT NULL,
  `dk01Color` varchar(10) DEFAULT NULL,
  `dk02Color` varchar(10) DEFAULT NULL,
  `dk03Color` varchar(10) DEFAULT NULL,
  `dk04Color` varchar(10) DEFAULT NULL,
  `dk05Color` varchar(10) DEFAULT NULL,
  `bol` varchar(35) DEFAULT NULL,
  `partNum` varchar(36) DEFAULT NULL,
  `soldToCtryCd` varchar(6) DEFAULT NULL,
  `poNum` varchar(20) DEFAULT NULL,
  `poItemNum` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `PK__delivery__3213E83F37072748` (`id`),
  KEY `idx_deliveryOrderItem_lookup` (`dlvryNum`,`dlvryItemNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `doeventfact` */

DROP TABLE IF EXISTS `doeventfact`;

CREATE TABLE `doeventfact` (
  `id` bigint(19) NOT NULL,
  `dlvryNum` varchar(20) DEFAULT NULL,
  `createTime` timestamp NULL DEFAULT NULL,
  `DO_ID` bigint(19) DEFAULT NULL,
  `statusCode` varchar(8) DEFAULT NULL,
  `milestone` varchar(50) DEFAULT NULL,
  `seq` int(10) DEFAULT NULL,
  `statusTime` timestamp NULL DEFAULT NULL,
  `diffHours` int(10) DEFAULT NULL,
  `pod_date_id` varchar(12) DEFAULT NULL,
  `pod_month_id` int(10) DEFAULT NULL,
  `pod_qtr_id` int(10) DEFAULT NULL,
  `pod_year_id` int(10) DEFAULT NULL,
  `pod_finance_year_id` varchar(100) DEFAULT NULL,
  `pod_finance_week_id` varchar(100) DEFAULT NULL,
  `carrCode` varchar(34) DEFAULT NULL,
  `modeOfTrspn` varchar(40) DEFAULT NULL,
  `rte` varchar(12) DEFAULT NULL,
  `shipToCtryCd` varchar(6) DEFAULT NULL,
  `IOD_T_make` varchar(100) DEFAULT NULL,
  `createTime_id` varchar(100) DEFAULT NULL,
  `statusTime_id` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `PK__DOEventF__3213E83F5674EC8C` (`id`),
  KEY `idx_DOEventFact_lookup` (`id`,`dlvryNum`,`createTime`,`DO_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `handlingunit` */

DROP TABLE IF EXISTS `handlingunit`;

CREATE TABLE `handlingunit` (
  `id` bigint(19) NOT NULL,
  `sscc` varchar(20) DEFAULT NULL,
  `dlvryNum` varchar(20) DEFAULT NULL,
  `dlvryItemNum` varchar(12) DEFAULT NULL,
  `HU_OID` varchar(20) DEFAULT NULL,
  `netWeight` decimal(15,3) DEFAULT NULL,
  `weightUnitTare` varchar(4) DEFAULT NULL,
  `totalWeight` decimal(15,3) DEFAULT NULL,
  `weightUnit` varchar(4) DEFAULT NULL,
  `length` decimal(15,3) DEFAULT NULL,
  `width` decimal(15,3) DEFAULT NULL,
  `height` decimal(15,3) DEFAULT NULL,
  `unitOfDimension` varchar(4) DEFAULT NULL,
  `totalVolume` decimal(15,3) DEFAULT NULL,
  `volumeUnit` varchar(4) DEFAULT NULL,
  `tareVolume` decimal(15,3) DEFAULT NULL,
  `volumeUnitTare` varchar(4) DEFAULT NULL,
  `packedQty` decimal(15,3) DEFAULT NULL,
  `materialNumber` varchar(18) DEFAULT NULL,
  `idocNum` int(10) DEFAULT NULL,
  `idocCreatedDate` timestamp NULL DEFAULT NULL,
  `DO_ID` bigint(19) DEFAULT NULL,
  `DOITEM_ID` bigint(19) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `PK__handling__3213E83F761A6208` (`id`),
  KEY `idx_handlingUnit_lookup` (`sscc`,`dlvryNum`,`dlvryItemNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `handlingunitfact` */

DROP TABLE IF EXISTS `handlingunitfact`;

CREATE TABLE `handlingunitfact` (
  `ID` bigint(19) NOT NULL,
  `HU_ID` varchar(20) DEFAULT NULL,
  `DO_ID` bigint(19) DEFAULT NULL,
  `DOITEM_ID` bigint(19) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `PK__handling__3214EC2766196BAA` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `kpicolor` */

DROP TABLE IF EXISTS `kpicolor`;

CREATE TABLE `kpicolor` (
  `code` varchar(10) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`code`),
  UNIQUE KEY `PK__kpiColor__357D4CF80325AA4C` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `mot` */

DROP TABLE IF EXISTS `mot`;

CREATE TABLE `mot` (
  `motCode` varchar(50) NOT NULL,
  `motName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`motCode`),
  UNIQUE KEY `PK__mot__32E966DB3DF1FB58` (`motCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `odminfo` */

DROP TABLE IF EXISTS `odminfo`;

CREATE TABLE `odminfo` (
  `id` int(10) DEFAULT NULL,
  `plant` varchar(500) DEFAULT NULL,
  `plantSo` varchar(50) DEFAULT NULL,
  `lenovoPo` varchar(50) DEFAULT NULL,
  `mot` varchar(500) DEFAULT NULL,
  `country` varchar(3) DEFAULT NULL,
  `qty` int(10) DEFAULT NULL,
  `outputTime` timestamp NULL DEFAULT NULL,
  `packTime` timestamp NULL DEFAULT NULL,
  `shipTime` timestamp NULL DEFAULT NULL,
  `sucessTime` timestamp NULL DEFAULT NULL,
  `truck` varchar(50) DEFAULT NULL,
  `rsd` varchar(50) DEFAULT NULL,
  `containerNumber` varchar(50) DEFAULT NULL,
  `reasonCode` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `ordermilestone` */

DROP TABLE IF EXISTS `ordermilestone`;

CREATE TABLE `ordermilestone` (
  `milestoneCode` varchar(50) NOT NULL,
  `milestoneName` varchar(500) DEFAULT NULL,
  `statusCode` varchar(50) DEFAULT NULL,
  `milestoneCategory` varchar(50) DEFAULT NULL,
  `parentMilestoneCode` varchar(50) DEFAULT NULL,
  `parentMilestoneName` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`milestoneCode`),
  UNIQUE KEY `PK__orderMil__603C6B2200FF4A58` (`milestoneCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `reason` */

DROP TABLE IF EXISTS `reason`;

CREATE TABLE `reason` (
  `reasonCode` varchar(50) NOT NULL,
  `reasonName` varchar(200) DEFAULT NULL,
  `category` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`reasonCode`),
  UNIQUE KEY `PK__reason__1C859101F2AC3C2C` (`reasonCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `reasoncategory` */

DROP TABLE IF EXISTS `reasoncategory`;

CREATE TABLE `reasoncategory` (
  `L1` varchar(10) NOT NULL,
  `detractor` varchar(50) DEFAULT NULL,
  `L1_order` int(10) DEFAULT NULL,
  `Detractor_order` int(10) DEFAULT NULL
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
  PRIMARY KEY (`id`),
  UNIQUE KEY `PK__ReportDe__3213E83F56E372C0` (`id`),
  UNIQUE KEY `UK_1h5tv75gswccgireybpn207f2` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `route` */

DROP TABLE IF EXISTS `route`;

CREATE TABLE `route` (
  `route` varchar(6) NOT NULL,
  `departurePort` varchar(35) DEFAULT NULL,
  `arrivalPort` varchar(35) DEFAULT NULL,
  `serviceLevel` varchar(10) DEFAULT NULL,
  `scac` varchar(10) DEFAULT NULL,
  `shipingCondition` varchar(50) DEFAULT NULL,
  `oem` varchar(50) DEFAULT NULL,
  `source` varchar(50) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `mot` varchar(50) DEFAULT NULL,
  `destination` varchar(50) DEFAULT NULL,
  `additional` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`route`),
  UNIQUE KEY `PK__route__4FDB20117289D01E` (`route`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `salesorder` */

DROP TABLE IF EXISTS `salesorder`;

CREATE TABLE `salesorder` (
  `slsOrdrNum` varchar(20) DEFAULT NULL,
  `id` bigint(19) NOT NULL,
  `shipToCtryCd` varchar(6) DEFAULT NULL,
  `subGeo` varchar(20) DEFAULT NULL,
  `poNum` varchar(70) DEFAULT NULL,
  `shpngCond` varchar(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `PK__salesOrd__3213E83F33C02B1B` (`id`),
  KEY `idx_salesOrder_lookup` (`slsOrdrNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `salesorderitem` */

DROP TABLE IF EXISTS `salesorderitem`;

CREATE TABLE `salesorderitem` (
  `slsOrdrNum` varchar(20) DEFAULT NULL,
  `slsOrdrLineNum` varchar(12) DEFAULT NULL,
  `id` bigint(19) NOT NULL,
  `shpngPoint` varchar(8) DEFAULT NULL,
  `geoId` varchar(20) DEFAULT NULL,
  `matlGrp1` varchar(3) DEFAULT NULL,
  `productDesc` varchar(80) DEFAULT NULL,
  `confirmShipDate` timestamp NULL DEFAULT NULL,
  `shipToCtryCd` varchar(6) DEFAULT NULL,
  `planSplrShipDate` timestamp NULL DEFAULT NULL,
  `shipToCity1` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `PK__salesOrd__3213E83F1432D24D` (`id`),
  KEY `idx_salesOrderItem_lookup` (`slsOrdrNum`,`slsOrdrLineNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `shppoint` */

DROP TABLE IF EXISTS `shppoint`;

CREATE TABLE `shppoint` (
  `shpPointCode` varchar(50) NOT NULL,
  `shpPointName` varchar(500) DEFAULT NULL,
  `model` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`shpPointCode`),
  UNIQUE KEY `PK__shpPoint__0486AE3CCB67AFA7` (`shpPointCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `PK__users__3213E83F8348E9C4` (`id`),
  UNIQUE KEY `UK_3g1j96g94xpk3lpxl2qbl985x` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `deliveryfactstatus` */

DROP TABLE IF EXISTS `deliveryfactstatus`;

/*!50001 DROP VIEW IF EXISTS `deliveryfactstatus` */;
/*!50001 DROP TABLE IF EXISTS `deliveryfactstatus` */;

/*!50001 CREATE TABLE  `deliveryfactstatus`(
 `carrCode` varchar(34) ,
 `modeOfTrspn` varchar(40) ,
 `shipToCtryCd` varchar(6) ,
 `dlvryNum` varchar(20) ,
 `cdd_date_id` varchar(12) ,
 `statusCode` varchar(50) 
)*/;

/*Table structure for table `iod_c_fact` */

DROP TABLE IF EXISTS `iod_c_fact`;

/*!50001 DROP VIEW IF EXISTS `iod_c_fact` */;
/*!50001 DROP TABLE IF EXISTS `iod_c_fact` */;

/*!50001 CREATE TABLE  `iod_c_fact`(
 `id` double ,
 `dlvryNumE` varchar(20) ,
 `statusCode` varchar(50) ,
 `cdd_date_id` varchar(12) ,
 `dlvryNum` varchar(20) ,
 `carrCode` varchar(34) ,
 `modeOfTrspn` varchar(40) ,
 `shipToCtryCd` varchar(6) 
)*/;

/*Table structure for table `orderstatus` */

DROP TABLE IF EXISTS `orderstatus`;

/*!50001 DROP VIEW IF EXISTS `orderstatus` */;
/*!50001 DROP TABLE IF EXISTS `orderstatus` */;

/*!50001 CREATE TABLE  `orderstatus`(
 `milestoneCode` varchar(50) ,
 `milestoneName` varchar(500) ,
 `statusCode` varchar(50) ,
 `milestoneCategory` varchar(50) ,
 `parentMilestoneCode` varchar(50) ,
 `parentMilestoneName` varchar(500) 
)*/;

/*View structure for view deliveryfactstatus */

/*!50001 DROP TABLE IF EXISTS `deliveryfactstatus` */;
/*!50001 DROP VIEW IF EXISTS `deliveryfactstatus` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `deliveryfactstatus` AS (select `d0`.`carrCode` AS `carrCode`,`d0`.`modeOfTrspn` AS `modeOfTrspn`,`d0`.`shipToCtryCd` AS `shipToCtryCd`,`d0`.`dlvryNum` AS `dlvryNum`,`d0`.`cdd_date_id` AS `cdd_date_id`,`s0`.`statusCode` AS `statusCode` from (`deliveryorderfact` `d0` join `orderstatus` `s0`)) */;

/*View structure for view iod_c_fact */

/*!50001 DROP TABLE IF EXISTS `iod_c_fact` */;
/*!50001 DROP VIEW IF EXISTS `iod_c_fact` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `iod_c_fact` AS (select ((`d`.`dlvryNum` + '_') + `d`.`statusCode`) AS `id`,`e`.`dlvryNum` AS `dlvryNumE`,`d`.`statusCode` AS `statusCode`,`d`.`cdd_date_id` AS `cdd_date_id`,`d`.`dlvryNum` AS `dlvryNum`,`d`.`carrCode` AS `carrCode`,`d`.`modeOfTrspn` AS `modeOfTrspn`,`d`.`shipToCtryCd` AS `shipToCtryCd` from (`deliveryfactstatus` `d` left join `doeventfact` `e` on(((`e`.`dlvryNum` = `d`.`dlvryNum`) and (`e`.`statusCode` = `d`.`statusCode`))))) */;

/*View structure for view orderstatus */

/*!50001 DROP TABLE IF EXISTS `orderstatus` */;
/*!50001 DROP VIEW IF EXISTS `orderstatus` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `orderstatus` AS (select `ordermilestone`.`milestoneCode` AS `milestoneCode`,`ordermilestone`.`milestoneName` AS `milestoneName`,`ordermilestone`.`statusCode` AS `statusCode`,`ordermilestone`.`milestoneCategory` AS `milestoneCategory`,`ordermilestone`.`parentMilestoneCode` AS `parentMilestoneCode`,`ordermilestone`.`parentMilestoneName` AS `parentMilestoneName` from `ordermilestone` where (`ordermilestone`.`statusCode` is not null)) */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
