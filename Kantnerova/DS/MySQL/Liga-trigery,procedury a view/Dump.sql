CREATE DATABASE  IF NOT EXISTS `Liga` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `Liga`;
-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: 84.242.120.206    Database: Liga
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
-- Temporary view structure for view `Prestupy`
--

DROP TABLE IF EXISTS `Prestupy`;
/*!50001 DROP VIEW IF EXISTS `Prestupy`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `Prestupy` AS SELECT 
 1 AS `idzmeny_hraci`,
 1 AS `typ`,
 1 AS `datum`,
 1 AS `nazev_tymu`,
 1 AS `nazev_postu`,
 1 AS `jmeno`,
 1 AS `prijmeni`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `VsechnyInformace`
--

DROP TABLE IF EXISTS `VsechnyInformace`;
/*!50001 DROP VIEW IF EXISTS `VsechnyInformace`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `VsechnyInformace` AS SELECT 
 1 AS `Jmeno`,
 1 AS `Prijmeni`,
 1 AS `post`,
 1 AS `tym`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `hrac`
--

DROP TABLE IF EXISTS `hrac`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hrac` (
  `idhrac` int(11) NOT NULL AUTO_INCREMENT,
  `Jmeno` varchar(45) NOT NULL,
  `Prijmeni` varchar(45) NOT NULL,
  `Vek` int(11) NOT NULL,
  `tym_idtym` int(11) NOT NULL,
  `post_idpost` int(11) DEFAULT NULL,
  PRIMARY KEY (`idhrac`),
  KEY `fk_hrac_tym_idx` (`tym_idtym`),
  KEY `fk_hrac_post1_idx` (`post_idpost`),
  CONSTRAINT `fk_hrac_post1` FOREIGN KEY (`post_idpost`) REFERENCES `post` (`idpost`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hrac_tym` FOREIGN KEY (`tym_idtym`) REFERENCES `tym` (`idtym`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hrac`
--

LOCK TABLES `hrac` WRITE;
/*!40000 ALTER TABLE `hrac` DISABLE KEYS */;
INSERT INTO `hrac` VALUES (1,'Agnola','Astie',48,4,7),(2,'Gerhardine','Weiser',27,2,8),(3,'Lucille','Fibbens',28,7,4),(4,'Mickey','Grenter',39,8,4),(5,'Daniele','Landsman',33,10,10),(6,'Ogdan','Collcott',38,2,1),(7,'Alasdair','Cartan',21,4,6),(8,'Harli','Godson',20,5,9),(9,'Sofia','Micka',48,7,7),(10,'Caria','Scudders',21,1,7),(11,'Aymer','Ridgedell',19,10,1),(12,'Lora','McLese',20,8,7),(13,'Joby','Romagnosi',47,8,3),(14,'Ingrid','Di Giacomo',26,5,8),(15,'Vinson','Mosedill',37,5,9),(16,'Deane','Dot',24,5,5),(17,'Arleen','Blakesley',29,9,9),(18,'Roxanne','Tincknell',28,6,1),(19,'Almira','Buterton',28,7,10),(20,'Rois','Murt',26,6,8),(21,'Idknic','Bezprijmeni',44,13,11);
/*!40000 ALTER TABLE `hrac` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`admin`@`%`*/ /*!50003 TRIGGER `hrac_after_insert`
AFTER
INSERT  
ON `hrac` 
FOR EACH ROW
BEGIN
INSERT INTO
    zmeny_hraci (typ,datum,nazev_tymu,nazev_postu,jmeno,prijmeni)
VALUES
    ('prichod',
    NOW(),
    (select tym.nazev from tym where tym.idtym=NEW.tym_idtym),
    (select post.nazev from post where post.idpost=NEW.post_idpost),
    NEW.Jmeno,
    NEW.Prijmeni);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'IGNORE_SPACE,STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`admin`@`%`*/ /*!50003 TRIGGER `hrac_after_update`
AFTER
UPDATE  
ON `hrac` 
FOR EACH ROW
BEGIN
    IF(OLD.tym_idtym<>NEW.tym_idtym) THEN
        INSERT INTO
            zmeny_hraci (typ,datum,nazev_tymu,nazev_postu,jmeno,prijmeni)
        VALUES
            ('přestup',
            NOW(),
            (select tym.nazev from tym where tym.idtym=NEW.tym_idtym),
            (select post.nazev from post where post.idpost=NEW.post_idpost),
            NEW.Jmeno,
            NEW.Prijmeni);
    END IF;
    IF(OLD.post_idpost<>NEW.post_idpost) THEN
        INSERT INTO
            zmeny_hraci (typ,datum,nazev_tymu,nazev_postu,jmeno,prijmeni)
        VALUES
            ('zmena_postu',
            NOW(),
            (select tym.nazev from tym where tym.idtym=NEW.tym_idtym),
            (select post.nazev from post where post.idpost=NEW.post_idpost),
            NEW.Jmeno,
            NEW.Prijmeni);
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`admin`@`%`*/ /*!50003 TRIGGER `hrac_before_delete`
BEFORE
DELETE 
ON `hrac` 
FOR EACH ROW
BEGIN
INSERT INTO
    zmeny_hraci (typ,datum,nazev_tymu,nazev_postu,jmeno,prijmeni)
VALUES
    ('odchod',
    NOW(),
    (select tym.nazev from tym where tym.idtym=OLD.tym_idtym),
    (select post.nazev from post where post.idpost=OLD.post_idpost),
    OLD.Jmeno,
    OLD.Prijmeni);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post` (
  `idpost` int(11) NOT NULL,
  `nazev` varchar(45) NOT NULL,
  PRIMARY KEY (`idpost`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES (1,'doxepin hydrochloride'),(2,'Pancreas Equisetum'),(3,'TESTOSTERONE'),(4,'Enalapril Maleate'),(5,'Benzalkonium Chloride'),(6,'Aluminum Zirconium Tetrachlorohydrex GLY'),(7,'Lisinopril'),(8,'fexofenadine hydrochloride'),(9,'Calcium Carbonate'),(10,'Acetaminophen'),(11,'Brankar'),(12,'Kopac');
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tym`
--

DROP TABLE IF EXISTS `tym`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tym` (
  `idtym` int(11) NOT NULL,
  `nazev` varchar(45) NOT NULL,
  PRIMARY KEY (`idtym`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tym`
--

LOCK TABLES `tym` WRITE;
/*!40000 ALTER TABLE `tym` DISABLE KEYS */;
INSERT INTO `tym` VALUES (1,'REMEDYREPACK INC.'),(2,'Johnson & Johnson Consumer Products Company.'),(3,'ALK-Abello, Inc.'),(4,'MSD Consumer Care, Inc.'),(5,'Family Dollar'),(6,'State of Florida DOH Central Pharmacy'),(7,'Mutual Pharmaceutical Company, Inc.'),(8,'Shopko Stores Operating Co., LLC'),(9,'Washington Homeopathic Products'),(10,'St Marys Medical Park Pharmacy'),(12,'Prahajede'),(13,'Slavie');
/*!40000 ALTER TABLE `tym` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zmeny_hraci`
--

DROP TABLE IF EXISTS `zmeny_hraci`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `zmeny_hraci` (
  `idzmeny_hraci` int(11) NOT NULL AUTO_INCREMENT,
  `typ` varchar(45) NOT NULL,
  `datum` datetime NOT NULL,
  `nazev_tymu` varchar(45) NOT NULL,
  `nazev_postu` varchar(45) DEFAULT NULL,
  `jmeno` varchar(45) NOT NULL,
  `prijmeni` varchar(45) NOT NULL,
  PRIMARY KEY (`idzmeny_hraci`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zmeny_hraci`
--

LOCK TABLES `zmeny_hraci` WRITE;
/*!40000 ALTER TABLE `zmeny_hraci` DISABLE KEYS */;
INSERT INTO `zmeny_hraci` VALUES (1,'prichod','2020-05-09 13:52:18','MSD Consumer Care, Inc.','Lisinopril','Agnola','Astie'),(2,'prichod','2020-05-09 13:52:18','Johnson & Johnson Consumer Products Company.','fexofenadine hydrochloride','Gerhardine','Weiser'),(3,'prichod','2020-05-09 13:52:18','Mutual Pharmaceutical Company, Inc.','Enalapril Maleate','Lucille','Fibbens'),(4,'prichod','2020-05-09 13:52:18','Shopko Stores Operating Co., LLC','Enalapril Maleate','Mickey','Grenter'),(5,'prichod','2020-05-09 13:52:18','St Marys Medical Park Pharmacy','Acetaminophen','Daniele','Landsman'),(6,'prichod','2020-05-09 13:52:18','Johnson & Johnson Consumer Products Company.','doxepin hydrochloride','Ogdan','Collcott'),(7,'prichod','2020-05-09 13:52:18','MSD Consumer Care, Inc.','Aluminum Zirconium Tetrachlorohydrex GLY','Alasdair','Cartan'),(8,'prichod','2020-05-09 13:52:18','Family Dollar','Calcium Carbonate','Harli','Godson'),(9,'prichod','2020-05-09 13:52:18','Mutual Pharmaceutical Company, Inc.','Lisinopril','Sofia','Micka'),(10,'prichod','2020-05-09 13:52:18','REMEDYREPACK INC.','Lisinopril','Caria','Scudders'),(11,'prichod','2020-05-09 13:52:18','St Marys Medical Park Pharmacy','doxepin hydrochloride','Aymer','Ridgedell'),(12,'prichod','2020-05-09 13:52:18','Shopko Stores Operating Co., LLC','Lisinopril','Lora','McLese'),(13,'prichod','2020-05-09 13:52:18','Shopko Stores Operating Co., LLC','TESTOSTERONE','Joby','Romagnosi'),(14,'prichod','2020-05-09 13:52:18','Family Dollar','fexofenadine hydrochloride','Ingrid','Di Giacomo'),(15,'prichod','2020-05-09 13:52:18','Family Dollar','Calcium Carbonate','Vinson','Mosedill'),(16,'prichod','2020-05-09 13:52:18','Family Dollar','Benzalkonium Chloride','Deane','Dot'),(17,'prichod','2020-05-09 13:52:18','Washington Homeopathic Products','Calcium Carbonate','Arleen','Blakesley'),(18,'prichod','2020-05-09 13:52:18','State of Florida DOH Central Pharmacy','doxepin hydrochloride','Roxanne','Tincknell'),(19,'prichod','2020-05-09 13:52:18','Mutual Pharmaceutical Company, Inc.','Acetaminophen','Almira','Buterton'),(20,'prichod','2020-05-09 13:52:18','State of Florida DOH Central Pharmacy','fexofenadine hydrochloride','Rois','Murt'),(21,'prichod','2020-05-09 13:52:31','Prahajede',NULL,'Idknic','Bezprijmeni'),(22,'přestup','2020-05-09 13:55:18','Slavie','Kopac','Idknic','Bezprijmeni'),(23,'zmena_postu','2020-05-09 13:56:58','Slavie','Brankar','Idknic','Bezprijmeni');
/*!40000 ALTER TABLE `zmeny_hraci` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'Liga'
--

--
-- Dumping routines for database 'Liga'
--
/*!50003 DROP PROCEDURE IF EXISTS `PridejHrace` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`admin`@`%` PROCEDURE `PridejHrace`(
    IN nazev_tym varchar(45),
    IN prijmeni varchar(45),
    IN jmeno varchar(45),
    IN vek int(11)
)
BEGIN 
    DECLARE idTym int(11) default 0;
    set idTym=(select tym.idtym from tym where tym.nazev=nazev_tym);
    IF (idTym IS NULL ) THEN
        set idTym=(select max(tym.idtym)+1 from tym);
        insert into tym VALUES (idTym,nazev_tym);
    END IF;
    INSERT INTO hrac (Jmeno,Prijmeni,Vek,tym_idtym) VALUES (jmeno,prijmeni,vek,idTym);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ZmenPost` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`admin`@`%` PROCEDURE `ZmenPost`(
    IN nazev_tym varchar(45),
    IN prijmeni varchar(45),
    IN jmeno varchar(45),
    IN nazev_postu varchar(45)
)
BEGIN 
    DECLARE idHrac int(11) default 0;
    DECLARE idPost int(11) default 0;
    set idHrac=(select hrac.idhrac from hrac inner join tym on tym_idtym=tym.idtym
                where tym.nazev=nazev_tym and hrac.Jmeno=jmeno and hrac.Prijmeni=prijmeni);
    IF (idHrac IS NOT NULL ) THEN
        set idPost=(select post.idpost from post where post.nazev=nazev_postu);
        IF (idPost IS NULL ) THEN
             set idPost=(select max(post.idpost)+1 from post);
            insert into post VALUES (idPost,nazev_postu);
        END IF;
        UPDATE hrac SET post_idpost=idPost where hrac.idhrac=idHrac;
    END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ZmenTym` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`admin`@`%` PROCEDURE `ZmenTym`(
    IN nazev_tym_new varchar(45),
    IN nazev_tym_old varchar(45),
    IN prijmeni varchar(45),
    IN jmeno varchar(45)
)
BEGIN 
    DECLARE idHrac int(11) default 0;
    DECLARE idTym int(11) default 0;
    set idHrac=(select hrac.idhrac from hrac inner join tym on tym_idtym=tym.idtym
                where tym.nazev=nazev_tym_old and hrac.Jmeno=jmeno and hrac.Prijmeni=prijmeni);
    IF (idHrac IS NOT NULL ) THEN
        set idTym=(select tym.idtym from tym where tym.nazev=nazev_tym_new);
        IF (idTym IS NULL ) THEN
            set idTym=(select max(tym.idtym)+1 from tym);
            insert into tym VALUES (idTym,nazev_tym_new);
        END IF;
        UPDATE hrac SET tym_idtym=idTym where hrac.idhrac=idHrac;
    END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Final view structure for view `Prestupy`
--

/*!50001 DROP VIEW IF EXISTS `Prestupy`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`admin`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `Prestupy` AS select `zmeny_hraci`.`idzmeny_hraci` AS `idzmeny_hraci`,`zmeny_hraci`.`typ` AS `typ`,`zmeny_hraci`.`datum` AS `datum`,`zmeny_hraci`.`nazev_tymu` AS `nazev_tymu`,`zmeny_hraci`.`nazev_postu` AS `nazev_postu`,`zmeny_hraci`.`jmeno` AS `jmeno`,`zmeny_hraci`.`prijmeni` AS `prijmeni` from `zmeny_hraci` where `zmeny_hraci`.`typ` = 'přestup' */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `VsechnyInformace`
--

/*!50001 DROP VIEW IF EXISTS `VsechnyInformace`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`admin`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `VsechnyInformace` AS select `hrac`.`Jmeno` AS `Jmeno`,`hrac`.`Prijmeni` AS `Prijmeni`,`post`.`nazev` AS `post`,`tym`.`nazev` AS `tym` from ((`hrac` join `tym` on(`tym`.`idtym` = `hrac`.`tym_idtym`)) join `post` on(`post`.`idpost` = `hrac`.`post_idpost`)) order by `tym`.`nazev`,`hrac`.`Jmeno`,`hrac`.`Prijmeni`,`post`.`nazev` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-09 14:02:01
