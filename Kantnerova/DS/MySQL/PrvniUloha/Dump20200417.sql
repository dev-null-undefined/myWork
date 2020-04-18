CREATE DATABASE  IF NOT EXISTS `Kantnerova` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `Kantnerova`;
-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: 84.242.120.206    Database: Kantnerova
-- ------------------------------------------------------
-- Server version	5.5.5-10.3.22-MariaDB-0+deb10u1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Pouziti`
--

DROP TABLE IF EXISTS `Pouziti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Pouziti` (
  `id` int(11) NOT NULL,
  `Zamestnanec_id` int(11) NOT NULL,
  `Stroj_id` int(11) NOT NULL,
  `Datum` datetime NOT NULL,
  `Pocet_hodin` int(11) DEFAULT 1,
  PRIMARY KEY (`id`),
  KEY `Zamestnanec_id` (`Zamestnanec_id`),
  KEY `Stroj_id` (`Stroj_id`),
  CONSTRAINT `Pouziti_ibfk_1` FOREIGN KEY (`Zamestnanec_id`) REFERENCES `Zamestnanec` (`id`),
  CONSTRAINT `Pouziti_ibfk_2` FOREIGN KEY (`Stroj_id`) REFERENCES `Stroj` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Pouziti`
--

LOCK TABLES `Pouziti` WRITE;
/*!40000 ALTER TABLE `Pouziti` DISABLE KEYS */;
INSERT INTO `Pouziti` VALUES (1,21,14,'2019-06-01 00:00:00',6),(2,6,14,'2019-12-21 00:00:00',NULL),(3,16,11,'2019-12-03 00:00:00',4),(4,31,14,'2019-06-02 00:00:00',5),(5,4,2,'2020-03-24 00:00:00',NULL),(6,28,2,'2019-06-17 00:00:00',7),(7,27,1,'2019-10-31 00:00:00',6),(8,21,5,'2019-09-03 00:00:00',NULL),(9,10,13,'2020-03-03 00:00:00',7),(10,8,8,'2019-08-14 00:00:00',NULL),(11,18,7,'2020-03-29 00:00:00',NULL),(12,12,7,'2019-10-24 00:00:00',NULL),(13,20,4,'2020-03-21 00:00:00',NULL),(14,12,7,'2019-07-27 00:00:00',NULL),(15,31,15,'2019-05-06 00:00:00',NULL),(16,8,3,'2019-11-30 00:00:00',NULL),(17,7,7,'2020-03-30 00:00:00',NULL),(18,34,15,'2019-04-23 00:00:00',NULL),(19,32,13,'2020-02-25 00:00:00',NULL),(20,34,4,'2019-12-10 00:00:00',NULL),(21,6,13,'2019-06-20 00:00:00',NULL),(22,29,12,'2019-08-11 00:00:00',4),(23,8,5,'2019-07-07 00:00:00',5);
/*!40000 ALTER TABLE `Pouziti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Stroj`
--

DROP TABLE IF EXISTS `Stroj`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Stroj` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Nazev` varchar(45) NOT NULL,
  `Cenna` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Stroj`
--

LOCK TABLES `Stroj` WRITE;
/*!40000 ALTER TABLE `Stroj` DISABLE KEYS */;
INSERT INTO `Stroj` VALUES (1,'Wrapsafe',NULL),(2,'Latlux',NULL),(3,'Aerified',NULL),(4,'Tin',149363),(5,'Pannier',NULL),(6,'Fintone',NULL),(7,'Subin',262649),(8,'Tresom',957708),(9,'Holdlamis',NULL),(10,'Tampflex',903899),(11,'Vagram',748124),(12,'Stringtough',162921),(13,'Overhold',748153),(14,'Temp',NULL),(15,'Solarbreeze',NULL),(16,'It',448987),(17,'Tampflex',NULL);
/*!40000 ALTER TABLE `Stroj` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Zamestnanec`
--

DROP TABLE IF EXISTS `Zamestnanec`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Zamestnanec` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Jmeno` varchar(45) NOT NULL,
  `Prijmeni` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Zamestnanec`
--

LOCK TABLES `Zamestnanec` WRITE;
/*!40000 ALTER TABLE `Zamestnanec` DISABLE KEYS */;
INSERT INTO `Zamestnanec` VALUES (1,'Baxy','Wheelband'),(2,'Merry','Pressland'),(3,'Anatollo','Breinlein'),(4,'Carmelle','Darbey'),(5,'Remus','Tatam'),(6,'Lindsey','Le Hucquet'),(7,'Adrianna','Harriot'),(8,'Afton','Tregidgo'),(9,'Aile','Frogley'),(10,'Leann','Orgill'),(11,'Rhett','St Quenin'),(12,'Crysta','Nazer'),(13,'Enrika','Khosa'),(14,'Tresa','Tarrier'),(15,'Hardy','Pywell'),(16,'Hobart','Acedo'),(17,'Fredric','Adds'),(18,'Porty','Petrosian'),(19,'Ignaz','Pickance'),(20,'Ann','Mulroy'),(21,'Corrina','Rackley'),(22,'Leonid','Rouff'),(23,'Giffy','Weld'),(24,'Craggie','Wilstead'),(25,'Demott','McIlmorow'),(26,'Rena','Buzza'),(27,'Darby','Paute'),(28,'Perrine','Moores'),(29,'Sherill','Happert'),(30,'Ogden','Matyugin'),(31,'Letizia','Crossgrove'),(32,'Charline','Strutley'),(33,'Odetta','Alcide'),(34,'Jobey','Presshaugh'),(35,'Mada','Dunniom');
/*!40000 ALTER TABLE `Zamestnanec` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-17 21:29:24
