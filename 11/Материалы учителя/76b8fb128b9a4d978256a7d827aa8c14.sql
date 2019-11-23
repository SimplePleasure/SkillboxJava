-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: learn
-- ------------------------------------------------------
-- Server version	5.7.9-log

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
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `head_id` int(11) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `description` text,
  PRIMARY KEY (`id`),
  UNIQUE KEY `head_id_UNIQUE` (`head_id`),
  KEY `head_idx` (`head_id`),
  CONSTRAINT `head` FOREIGN KEY (`head_id`) REFERENCES `employee` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,2,'Отдел продаж','Отдел занимается созданием каналов продаж продукции, совершает холодные звонки и привлекает новых клиентов.'),(2,3,'Отдел маркетинга','Отдел разрабатывает, запускает и отслеживает рекламные кампании и проводит маркетинговые исследования'),(3,9,'Отдел разработки','Сотрудники отдела осуществляют доработку и поддержку созданного компаний программного обеспечения'),(4,15,'Отдел кадров','Отдел занимается учётом сотрудников компании, оформлением их при приёме на работу и увольнении, а также распределением отпусков'),(5,11,'Бухгалтерия','Ведёт учёт финансовой деятельности, доходов и расходов, распределяет и выплачивает зарплату'),(6,10,'IT-отдел','Отдел обеспечивает IT-инфраструктуру компании: поддерживает компьютеры и орг. технику, настраивает локальные сети, консультирует работников');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `department_id` int(11) NOT NULL,
  `hire_date` date DEFAULT NULL,
  `salary` int(11) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `department` (`department_id`),
  CONSTRAINT `department` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,2,'2014-04-20',39000,'Алексеев Сергей'),(2,1,'2010-03-20',118000,'Сергеева Анна'),(3,2,'2011-12-20',110000,'Гандлевский Дмитрий'),(4,1,'2005-06-20',92000,'Максимов Андрей'),(5,1,'2007-08-20',38000,'Алифиренко Константин'),(6,5,'2015-09-20',75000,'Марьянова Марина'),(7,4,'2009-09-20',50000,'Гершикова Татьяна'),(8,3,'2009-09-20',65000,'Сенаторов Леонид'),(9,3,'2015-08-20',180000,'Меламед Алевтина'),(10,6,'2011-11-20',111356,'Коршунов Григорий'),(11,2,'2008-07-20',48000,'Леонтович Руслан'),(12,6,'2012-03-20',36000,'Белов Александр'),(13,5,'2011-11-20',75000,'Мелихов Антон'),(14,5,'2010-02-20',130000,'Самойлова Екатерина'),(15,1,'2010-01-20',115000,'Ряднов Пётр');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-10-29  8:35:01
