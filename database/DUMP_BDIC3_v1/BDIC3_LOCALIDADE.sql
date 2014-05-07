CREATE DATABASE  IF NOT EXISTS `BDIC3` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `BDIC3`;
-- MySQL dump 10.13  Distrib 5.6.13, for Linux (x86_64)
--
-- Host: localhost    Database: BDIC3
-- ------------------------------------------------------
-- Server version	5.6.17-log

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
-- Table structure for table `LOCALIDADE`
--

DROP TABLE IF EXISTS `LOCALIDADE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `LOCALIDADE` (
  `loc_id` int(11) NOT NULL AUTO_INCREMENT,
  `loc_posicao` point NOT NULL,
  `loc_endereco` varchar(250) NOT NULL,
  `loc_numero` varchar(5) NOT NULL,
  `cid_id` int(11) NOT NULL,
  `loc_complemento` varchar(255) NOT NULL,
  `loc_cep` varchar(45) NOT NULL,
  `lot_id` int(11) NOT NULL,
  PRIMARY KEY (`loc_id`),
  KEY `fk_LOCALIDADE_LOCALIDADETIPO` (`lot_id`),
  KEY `fk_LOCALIDADE_CIDADE` (`cid_id`),
  CONSTRAINT `fk_LOCALIDADE_LOCALIDADETIPO` FOREIGN KEY (`lot_id`) REFERENCES `LOCALIDADETIPO` (`lot_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_LOCALIDADE_CIDADE` FOREIGN KEY (`cid_id`) REFERENCES `CIDADE` (`cid_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `LOCALIDADE`
--

LOCK TABLES `LOCALIDADE` WRITE;
/*!40000 ALTER TABLE `LOCALIDADE` DISABLE KEYS */;
/*!40000 ALTER TABLE `LOCALIDADE` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-05-04  7:43:32