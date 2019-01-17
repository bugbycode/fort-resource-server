-- MySQL dump 10.13  Distrib 5.6.27, for Win64 (x86_64)
--
-- Host: dev.server    Database: new_resource
-- ------------------------------------------------------
-- Server version	5.7.23

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
-- Current Database: `new_resource`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `new_resource` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `new_resource`;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `account` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '账号',
  `password` text COLLATE utf8_unicode_ci COMMENT '密码',
  `type` tinyint(4) DEFAULT '0' COMMENT '账号类型（0：管理员账号，1：普通账号）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `network`
--

DROP TABLE IF EXISTS `network`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `network` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `type` int(11) DEFAULT '0',
  `description` text COLLATE utf8_unicode_ci,
  `client_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `client_secret` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `network`
--

LOCK TABLES `network` WRITE;
/*!40000 ALTER TABLE `network` DISABLE KEYS */;
INSERT INTO `network` VALUES (1,'本地局域网',0,NULL,NULL,NULL,'2019-01-16 16:19:39',NULL);
/*!40000 ALTER TABLE `network` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rel_server_account`
--

DROP TABLE IF EXISTS `rel_server_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rel_server_account` (
  `server_id` int(11) DEFAULT NULL,
  `account_id` int(11) DEFAULT NULL,
  KEY `server_id` (`server_id`),
  KEY `account_id` (`account_id`),
  CONSTRAINT `rel_server_account_ibfk_1` FOREIGN KEY (`server_id`) REFERENCES `resource_server` (`id`) ON DELETE CASCADE,
  CONSTRAINT `rel_server_account_ibfk_2` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rel_server_account`
--

LOCK TABLES `rel_server_account` WRITE;
/*!40000 ALTER TABLE `rel_server_account` DISABLE KEYS */;
/*!40000 ALTER TABLE `rel_server_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resource`
--

DROP TABLE IF EXISTS `resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '名称',
  `ip` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'ip地址',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  `type` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `os_type` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `network_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `network_id` (`network_id`),
  CONSTRAINT `resource_ibfk_1` FOREIGN KEY (`network_id`) REFERENCES `network` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resource`
--

LOCK TABLES `resource` WRITE;
/*!40000 ALTER TABLE `resource` DISABLE KEYS */;
INSERT INTO `resource` VALUES (1,'计算机','dev.server',1,'Windows','Windows 7',1,'2019-01-16 16:17:00',NULL);
/*!40000 ALTER TABLE `resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resource_server`
--

DROP TABLE IF EXISTS `resource_server`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resource_server` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `server_type` int(11) DEFAULT '0' COMMENT '服务类型（0：ssh或sftp，1：远程桌面，2：telnet，3：oracle，4、sqlserver，5：ftp）',
  `port` int(11) DEFAULT '22' COMMENT '端口号',
  `db_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '数据库名称',
  `res_id` int(11) DEFAULT NULL COMMENT '所属资产ID',
  `use` tinyint(4) DEFAULT '0' COMMENT '是否启用服务',
  PRIMARY KEY (`id`),
  KEY `res_id` (`res_id`),
  CONSTRAINT `resource_server_ibfk_1` FOREIGN KEY (`res_id`) REFERENCES `resource` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resource_server`
--

LOCK TABLES `resource_server` WRITE;
/*!40000 ALTER TABLE `resource_server` DISABLE KEYS */;
/*!40000 ALTER TABLE `resource_server` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-01-17 17:51:38
