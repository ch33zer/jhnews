CREATE DATABASE  IF NOT EXISTS `jhnews` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `jhnews`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: localhost    Database: jhnews
-- ------------------------------------------------------
-- Server version	5.5.28

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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `username` varchar(25) NOT NULL,
  `hash` mediumtext NOT NULL,
  `firstName` varchar(25) NOT NULL,
  `lastName` varchar(25) NOT NULL,
  `email` mediumtext NOT NULL,
  `isAdmin` tinyint(1) NOT NULL,
  `uTag1` tinyint(1) NOT NULL,
  `uTag2` tinyint(1) NOT NULL,
  `uTag3` tinyint(1) NOT NULL,
  `uTag4` tinyint(1) NOT NULL,
  `uTag5` tinyint(1) NOT NULL,
  `uTag6` tinyint(1) NOT NULL,
  `uID` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`uID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('wayne','$2a$10$ZlnwhGKha28atSI.Uby3COY8.31PkGJs84Xjs/FycW33WK/H7JelW','Wayne','Chen','wchen52@jhu.edu',1,0,1,1,1,0,1,1),('nir','$2a$10$cjD1LErRFrPeRcttje3Bd.g3mTb6iUaqBc.4Dkhz4WOS01omOJIyC','Nir','Rattner','nrattne1@jhu.edu',1,1,1,0,0,1,1,2),('blaise','$2a$10$Etrs8xR2WCImi046owNKtuvNd40t80F/jHtt67h17WgGtktGG./dS','Blaise','Watson','ch33zer@gmail.com',1,0,0,0,0,0,0,3);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `announcement`
--

DROP TABLE IF EXISTS `announcement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `announcement` (
  `aID` int(11) NOT NULL AUTO_INCREMENT,
  `submitter` int(11) NOT NULL,
  `title` varchar(25) NOT NULL,
  `location` varchar(25) NOT NULL,
  `briefDescription` mediumtext NOT NULL,
  `longDescription` mediumtext,
  `eventDate` DATETIME NOT NULL,
  `approved` tinyint(1) NOT NULL,
  `hasEventTime` tinyint(1) NOT NULL,
  `aTag1` tinyint(1) NOT NULL,
  `aTag2` tinyint(1) NOT NULL,
  `aTag3` tinyint(1) NOT NULL,
  `aTag4` tinyint(1) NOT NULL,
  `aTag5` tinyint(1) NOT NULL,
  `toFreshman` tinyint(1) NOT NULL,
  `toSophomore` tinyint(1) NOT NULL,
  `toJunior` tinyint(1) NOT NULL,
  `toSenior` tinyint(1) NOT NULL,
  `toGraduate` tinyint(1) NOT NULL,
  `toFaculty` tinyint(1) NOT NULL,
  PRIMARY KEY (`aID`),
  UNIQUE KEY `aID_UNIQUE` (`aID`),
  INDEX `FK_SUBMITTER` (`submitter`),
  CONSTRAINT `FK_SUBMITTER` FOREIGN KEY (`submitter`) REFERENCES `user` (`uID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `announcement`
--

LOCK TABLES `announcement` WRITE;
/*!40000 ALTER TABLE `announcement` DISABLE KEYS */;
INSERT INTO `announcement` VALUES (10,1,'Title #10','Location #10','Brief description #10 ','Long description #10','2012-12-31 11:30:45',1,1,1,1,1,0,0,1,1,0,0,0,1),(11,1,'Title #11','Location #11','Brief description #11','Long description #11','2012-12-31 11:30:45',1,1,1,1,0,0,0,1,1,1,1,0,1),(12,2,'Title #12','Location #12','Brief description #12','Long description #12','2012-12-31 11:30:45',0,0,0,0,1,1,0,0,0,0,1,0,1);
/*!40000 ALTER TABLE `announcement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `session`
--

DROP TABLE IF EXISTS `session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `session` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `sessionID` varchar(50) NOT NULL,
  `expireDate` DATETIME NOT NULL,
  `uID2` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`),
  INDEX `FK_UID2` (`uID2`),
  CONSTRAINT `FK_UID2` FOREIGN KEY (`uID2`) REFERENCES `user` (`uID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `session`
--

LOCK TABLES `session` WRITE;
/*!40000 ALTER TABLE `session` DISABLE KEYS */;
INSERT INTO `session` VALUES ('1','SESSION1','2012-12-31 11:30:45',1),('2','SESSION2','2012-12-31 11:30:45',2),('3','SESSION3','2012-12-31 11:30:45',3);
/*!40000 ALTER TABLE `session` ENABLE KEYS */;
UNLOCK TABLES;


/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-11-15  0:00:10
