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
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '账号',
  `password` text COLLATE utf8_unicode_ci COMMENT '密码',
  `type` tinyint(4) DEFAULT NULL COMMENT '账号类型',
  `resource_id` int(11) DEFAULT NULL COMMENT '资产ID',
  `server_type` int(11) DEFAULT NULL COMMENT '所属服务类型',
  PRIMARY KEY (`id`),
  KEY `resource_id` (`resource_id`),
  CONSTRAINT `account_ibfk_1` FOREIGN KEY (`resource_id`) REFERENCES `resource` (`id`) ON DELETE CASCADE
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
-- Table structure for table `os_type`
--

DROP TABLE IF EXISTS `os_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `os_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `type` int(11) DEFAULT '0',
  `os_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `os_type`
--

LOCK TABLES `os_type` WRITE;
/*!40000 ALTER TABLE `os_type` DISABLE KEYS */;
INSERT INTO `os_type` VALUES (1,'Linux',0,0),(2,'CentOs',1,1),(3,'Debain',1,1),(4,'RHEL',1,1),(5,'Ubuntu',1,1),(6,'Fedora',1,1),(7,'OpenSUSE',1,1),(8,'Mindriva',1,1),(9,'Gentoo',1,1),(10,'Arch',1,1),(11,'Slackware',1,1),(12,'linspire',1,1),(13,'Windows',0,0),(14,'Windows 1.0',1,13),(15,'Windows 95',1,13),(16,'Windows 98',1,13),(17,'Windows ME',1,13),(18,'Windows 2000',1,13),(19,'Windows 2003',1,13),(20,'Windows XP',1,13),(21,'Windows Vista',1,13),(22,'Windows 7',1,13),(23,'Windows 8',1,13),(24,'Windows 8.1',1,13),(25,'Windows 10',1,13),(26,'Windows Server 2003',1,13),(27,'Windows Server 2008',1,13),(28,'Windows Server 2012',1,13),(29,'Windows Server 2016',1,13),(30,'WindowsMobile',1,13),(31,'WindowsPhone',1,13),(32,'Windows10Mobile',1,13),(33,'Unix',0,0),(34,'SCO',1,33),(35,'HP',1,33),(36,'SUN',1,33),(37,'IBM',1,33),(38,'FreeBSD',1,33),(39,'OpenBSD',1,33),(40,'NetBSD',1,33),(41,'Apple',1,33);
/*!40000 ALTER TABLE `os_type` ENABLE KEYS */;
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
  `type_id` int(11) DEFAULT NULL COMMENT '操作系统类型ID',
  `os_id` int(11) DEFAULT NULL COMMENT '操作系统ID',
  `group_id` int(11) DEFAULT NULL COMMENT '分组ID',
  `use_ssh` tinyint(4) DEFAULT '0' COMMENT '是否开启SSH协议',
  `ssh_port` int(11) DEFAULT '22' COMMENT 'SSH协议端口号',
  `use_oracle` tinyint(4) DEFAULT '0',
  `oracle_port` int(11) DEFAULT '1521',
  `oracle_sid` varchar(255) COLLATE utf8_unicode_ci DEFAULT '0',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `use_rdp` tinyint(4) DEFAULT '0',
  `rdp_port` int(11) DEFAULT '3389',
  PRIMARY KEY (`id`),
  KEY `type_id` (`type_id`),
  KEY `os_id` (`os_id`),
  KEY `group_id` (`group_id`),
  CONSTRAINT `resource_ibfk_1` FOREIGN KEY (`type_id`) REFERENCES `os_type` (`id`) ON DELETE SET NULL,
  CONSTRAINT `resource_ibfk_2` FOREIGN KEY (`os_id`) REFERENCES `os_type` (`id`) ON DELETE SET NULL,
  CONSTRAINT `resource_ibfk_3` FOREIGN KEY (`group_id`) REFERENCES `resource_group` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resource`
--

LOCK TABLES `resource` WRITE;
/*!40000 ALTER TABLE `resource` DISABLE KEYS */;
/*!40000 ALTER TABLE `resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resource_group`
--

DROP TABLE IF EXISTS `resource_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resource_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `description` text COLLATE utf8_unicode_ci,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resource_group`
--

LOCK TABLES `resource_group` WRITE;
/*!40000 ALTER TABLE `resource_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `resource_group` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-01-10 18:43:17
