-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: contractdb
-- ------------------------------------------------------
-- Server version	5.7.17-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `t_contract`
--

DROP TABLE IF EXISTS `t_contract`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_contract` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `customer` varchar(40) DEFAULT NULL,
  `num` varchar(20) NOT NULL,
  `name` varchar(40) NOT NULL,
  `beginTime` date NOT NULL,
  `endTime` date NOT NULL,
  `content` text NOT NULL,
  `del` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_contract`
--

LOCK TABLES `t_contract` WRITE;
/*!40000 ALTER TABLE `t_contract` DISABLE KEYS */;
INSERT INTO `t_contract` VALUES (1,2,'郝经理','2017061208373051048','sample contract','2017-06-12','2017-06-13','郝经理学习。',0),(2,2,'常昊','2017061210112262864','为常昊买金匮肾气丸','2017-06-10','2017-06-15','紧急！常昊要不行了！',0),(3,2,'sss','2017061311395568707','hggfdsaa','1997-01-01','2017-12-31','hhh',0),(4,2,'人','2017061403024127888','班车','2017-06-06','2016-06-05','开车',0),(5,2,'学生们','2017061804432292458','学习','2017-06-18','2017-06-29','进行深入学习',0),(6,2,'关宸','2017062011472148990','阿斯达','2017-06-20','2017-06-21','自行车',0);
/*!40000 ALTER TABLE `t_contract` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_contract_attachment`
--

DROP TABLE IF EXISTS `t_contract_attachment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_contract_attachment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `con_id` int(11) NOT NULL,
  `fileName` varchar(40) NOT NULL,
  `path` varchar(100) NOT NULL,
  `type` varchar(10) NOT NULL,
  `uploadTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `del` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_contract_attachment`
--

LOCK TABLES `t_contract_attachment` WRITE;
/*!40000 ALTER TABLE `t_contract_attachment` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_contract_attachment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_contract_process`
--

DROP TABLE IF EXISTS `t_contract_process`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_contract_process` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `con_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  `state` int(11) NOT NULL DEFAULT '0',
  `content` text,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `del` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK_contract_process` (`con_id`),
  KEY `FK_process_user` (`user_id`),
  CONSTRAINT `FK_contract_process` FOREIGN KEY (`con_id`) REFERENCES `t_contract` (`id`),
  CONSTRAINT `FK_process_user` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_contract_process`
--

LOCK TABLES `t_contract_process` WRITE;
/*!40000 ALTER TABLE `t_contract_process` DISABLE KEYS */;
INSERT INTO `t_contract_process` VALUES (1,1,2,1,1,'ok, very good!','2017-06-12 12:58:52',0),(2,1,2,2,2,'不好。','2017-06-18 09:44:06',0),(3,1,2,3,0,'','2017-06-12 12:58:16',0),(4,2,2,1,1,'要买，要尽快买！','2017-06-13 03:14:38',0),(5,2,2,2,1,'好！快帮常昊','2017-06-19 13:09:30',0),(6,2,2,3,1,'帮助常昊，人人有责。','2017-06-20 05:07:58',0),(7,5,2,1,1,'一定要学习','2017-06-20 04:04:07',0),(8,5,2,2,0,'','2017-06-18 08:44:17',0),(9,5,2,3,0,'','2017-06-18 08:44:17',0),(10,4,2,1,1,'开吧','2017-06-20 04:03:57',0),(11,4,2,2,0,'','2017-06-20 03:47:54',0),(12,4,2,3,0,'','2017-06-20 03:47:54',0),(13,6,2,1,1,'行','2017-06-20 04:03:47',0),(14,6,3,2,1,'批准！','2017-06-20 04:59:24',0),(15,6,2,3,1,'好得很，签订了','2017-06-20 04:59:44',0);
/*!40000 ALTER TABLE `t_contract_process` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_contract_state`
--

DROP TABLE IF EXISTS `t_contract_state`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_contract_state` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `con_id` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `del` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK_belong` (`con_id`),
  CONSTRAINT `FK_belong` FOREIGN KEY (`con_id`) REFERENCES `t_contract` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_contract_state`
--

LOCK TABLES `t_contract_state` WRITE;
/*!40000 ALTER TABLE `t_contract_state` DISABLE KEYS */;
INSERT INTO `t_contract_state` VALUES (1,1,1,'2017-06-12 12:37:30',0),(2,1,2,'2017-06-12 12:58:52',0),(3,2,1,'2017-06-12 14:11:22',0),(4,2,2,'2017-06-13 03:14:38',0),(5,2,3,'2017-06-13 03:15:26',0),(6,3,1,'2017-06-13 03:39:55',0),(7,4,1,'2017-06-14 07:02:41',0),(8,1,3,'2017-06-18 08:18:12',0),(9,5,1,'2017-06-18 08:43:23',0),(10,2,4,'2017-06-19 13:09:31',0),(11,6,1,'2017-06-20 03:47:21',0),(12,6,2,'2017-06-20 04:03:47',0),(13,4,2,'2017-06-20 04:03:57',0),(14,5,2,'2017-06-20 04:04:07',0),(15,4,3,'2017-06-20 04:09:17',0),(16,6,3,'2017-06-20 04:57:27',0),(17,6,4,'2017-06-20 04:59:25',0),(18,6,5,'2017-06-20 04:59:43',0),(19,6,5,'2017-06-20 04:59:45',0),(20,2,5,'2017-06-20 05:07:58',0);
/*!40000 ALTER TABLE `t_contract_state` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_customer`
--

DROP TABLE IF EXISTS `t_customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `num` varchar(20) NOT NULL,
  `name` varchar(40) NOT NULL,
  `address` varchar(200) NOT NULL,
  `tel` varchar(20) NOT NULL,
  `fax` varchar(20) DEFAULT NULL,
  `code` varchar(10) DEFAULT NULL,
  `bank` varchar(50) DEFAULT NULL,
  `account` varchar(50) DEFAULT NULL,
  `del` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_customer`
--

LOCK TABLES `t_customer` WRITE;
/*!40000 ALTER TABLE `t_customer` DISABLE KEYS */;
INSERT INTO `t_customer` VALUES (1,'Cus20131211182300001','HB company','hubei wuhan','11111111111','12121212','430000','Bank Of China','621661***',0),(2,'Cus20131211182300002','BJ company','beijing','22222222','34213467','100000','Agricultural Bank of China','622848***',0),(3,'Cus20131211182300003','Jack Wang','Shanghai','14231116','45678234','200000','Industrial and Commercial Bank of China Limited','530990***',0);
/*!40000 ALTER TABLE `t_customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_function`
--

DROP TABLE IF EXISTS `t_function`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_function` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `num` varchar(10) NOT NULL,
  `name` varchar(40) NOT NULL,
  `URL` varchar(200) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `del` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_function`
--

LOCK TABLES `t_function` WRITE;
/*!40000 ALTER TABLE `t_function` DISABLE KEYS */;
INSERT INTO `t_function` VALUES (1,'001','QCHT',NULL,NULL,0),(2,'002','DGHT',NULL,NULL,0),(3,'003','CXHT',NULL,NULL,0),(4,'004','SCHT',NULL,NULL,0),(5,'005','HQHT',NULL,NULL,0),(6,'006','SPHT',NULL,NULL,0),(7,'007','QDHT',NULL,NULL,0),(8,'008','FPHQ',NULL,NULL,0),(9,'009','FPSP',NULL,NULL,0),(10,'010','FPQD',NULL,NULL,0),(11,'011','LCCX',NULL,NULL,0),(12,'012','XZYH',NULL,NULL,0),(13,'013','BJYH',NULL,NULL,0),(14,'014','CXYH',NULL,NULL,0),(15,'015','SCYH',NULL,NULL,0),(16,'016','XZJS',NULL,NULL,0),(17,'017','BJJS',NULL,NULL,0),(18,'018','CXJS',NULL,NULL,0),(19,'019','SCJS',NULL,NULL,0),(20,'020','XZGN',NULL,NULL,0),(21,'021','BJGN',NULL,NULL,0),(22,'022','CXGN',NULL,NULL,0),(23,'023','SCGN',NULL,NULL,0),(24,'024','PZQX',NULL,NULL,0),(25,'025','XZKH',NULL,NULL,0),(26,'026','BJKH',NULL,NULL,0),(27,'027','CXKH',NULL,NULL,0),(28,'028','SCKH',NULL,NULL,0),(29,'029','CXRZ',NULL,NULL,0),(30,'030','SCRZ',NULL,NULL,0);
/*!40000 ALTER TABLE `t_function` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_log`
--

DROP TABLE IF EXISTS `t_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `content` text NOT NULL,
  `del` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_log`
--

LOCK TABLES `t_log` WRITE;
/*!40000 ALTER TABLE `t_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_right`
--

DROP TABLE IF EXISTS `t_right`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_right` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `del` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK_right_u` (`user_id`),
  KEY `FK_right_r` (`role_id`),
  CONSTRAINT `FK_right_r` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`),
  CONSTRAINT `FK_right_u` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_right`
--

LOCK TABLES `t_right` WRITE;
/*!40000 ALTER TABLE `t_right` DISABLE KEYS */;
INSERT INTO `t_right` VALUES (1,1,1,'admin',0),(2,2,2,'operator test',0),(3,3,2,'',0);
/*!40000 ALTER TABLE `t_right` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_role`
--

DROP TABLE IF EXISTS `t_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `function_ids` varchar(500) DEFAULT NULL,
  `del` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role`
--

LOCK TABLES `t_role` WRITE;
/*!40000 ALTER TABLE `t_role` DISABLE KEYS */;
INSERT INTO `t_role` VALUES (1,'admin','To implement the system management and contract management','003,004,008,009,010,011,012,013,014,015,016,017,018,019,020,021,022,023,024,025,026,027,028,029,030',0),(2,'operator','operate contract','001,002,003,005,006,007,011,027',0);
/*!40000 ALTER TABLE `t_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  `password` varchar(20) NOT NULL,
  `del` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES (1,'admin','123456',0),(2,'test','testpass',0),(3,'op1','123',0);
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-06-20 21:54:21
