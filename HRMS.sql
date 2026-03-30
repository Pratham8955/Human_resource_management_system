-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: hrms
-- ------------------------------------------------------
-- Server version	8.0.35

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
-- Table structure for table `applicationsleave`
--

DROP TABLE IF EXISTS `applicationsleave`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `applicationsleave` (
  `status` varchar(50) CHARACTER SET latin1 COLLATE latin1_general_cs DEFAULT 'PENDING',
  `application_id` int NOT NULL AUTO_INCREMENT,
  `request_date` date NOT NULL,
  `leave_type` varchar(50) CHARACTER SET latin1 COLLATE latin1_general_cs NOT NULL,
  `leave_duration` varchar(50) CHARACTER SET latin1 COLLATE latin1_general_cs NOT NULL,
  `from_date` datetime NOT NULL,
  `to_date` datetime NOT NULL,
  `reason` varchar(100) CHARACTER SET latin1 COLLATE latin1_general_cs NOT NULL,
  `manager_name` varchar(25) CHARACTER SET latin1 COLLATE latin1_general_cs DEFAULT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`application_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `applicationsleave_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `hrmsemployees` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=latin1 COLLATE=latin1_general_cs;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `applicationsleave`
--

LOCK TABLES `applicationsleave` WRITE;
/*!40000 ALTER TABLE `applicationsleave` DISABLE KEYS */;
INSERT INTO `applicationsleave` VALUES ('Pending',43,'2024-12-23','Preplanned','Short Leave','2024-12-23 00:00:00','2024-12-24 00:00:00','feaver','Asti',2360),('Approved',44,'2024-12-25','Preplanned','Full Leave','2024-12-25 00:00:00','2024-12-26 00:00:00','Feaver','Pavan',2363),('Approved',45,'2024-12-25','Immediate','Short Leave','2024-12-25 00:00:00','2024-12-26 00:00:00','feaver','Pavan',2361),('Approved',46,'2024-12-26','Preplanned','Full Leave','2024-12-26 00:00:00','2024-12-31 00:00:00','feaver','Pavan',2361),('Approved',49,'2024-12-26','Preplanned','Full Leave','2024-12-26 00:00:00','2024-12-27 00:00:00','123','Pavan',2361),('Approved',50,'2024-12-26','Preplanned','Full Leave','2024-12-26 00:00:00','2024-12-28 00:00:00','123','Pavan',2361),('Approved',51,'2024-12-26','Immediate','Short Leave','2024-12-26 00:00:00','2024-12-27 00:00:00','feaver','Pavan',2361),(NULL,52,'2024-12-26','Immediate','Full Leave','2024-12-26 00:00:00','2024-12-27 00:00:00','123','Pavan',2361);
/*!40000 ALTER TABLE `applicationsleave` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attendance`
--

DROP TABLE IF EXISTS `attendance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attendance` (
  `user_id` int NOT NULL,
  `dept_name` varchar(50) CHARACTER SET latin1 COLLATE latin1_general_cs NOT NULL,
  `time_in` varchar(50) COLLATE latin1_general_cs DEFAULT NULL,
  `time_out` time DEFAULT NULL,
  `punch_date` date DEFAULT NULL,
  `a_id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`a_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `attendance_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `hrmsemployees` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1 COLLATE=latin1_general_cs;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attendance`
--

LOCK TABLES `attendance` WRITE;
/*!40000 ALTER TABLE `attendance` DISABLE KEYS */;
INSERT INTO `attendance` VALUES (2363,'School','18:25','18:25:39','2024-12-25',6),(2361,'Marketing','10:00','03:31:50','2024-12-26',11);
/*!40000 ALTER TABLE `attendance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_master`
--

DROP TABLE IF EXISTS `group_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `group_master` (
  `group_id` int NOT NULL,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_master`
--

LOCK TABLES `group_master` WRITE;
/*!40000 ALTER TABLE `group_master` DISABLE KEYS */;
INSERT INTO `group_master` VALUES (1,'Admin'),(2,'Employee'),(3,'Manager');
/*!40000 ALTER TABLE `group_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hrms_department`
--

DROP TABLE IF EXISTS `hrms_department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hrms_department` (
  `dep_id` int NOT NULL AUTO_INCREMENT,
  `dep_name` varchar(45) NOT NULL,
  `manager_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`dep_id`)
) ENGINE=InnoDB AUTO_INCREMENT=105 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hrms_department`
--

LOCK TABLES `hrms_department` WRITE;
/*!40000 ALTER TABLE `hrms_department` DISABLE KEYS */;
INSERT INTO `hrms_department` VALUES (96,'IT','Asti'),(97,'HR','Pratham'),(98,'Finance','Meet'),(100,'Admin','Admin'),(101,'Marketing','Niraj'),(104,'School','Pavan');
/*!40000 ALTER TABLE `hrms_department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hrms_traing_type`
--

DROP TABLE IF EXISTS `hrms_traing_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hrms_traing_type` (
  `t_id` int NOT NULL AUTO_INCREMENT,
  `trainer` varchar(45) NOT NULL,
  `description` varchar(45) NOT NULL,
  `status` varchar(45) DEFAULT 'pending',
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hrms_traing_type`
--

LOCK TABLES `hrms_traing_type` WRITE;
/*!40000 ALTER TABLE `hrms_traing_type` DISABLE KEYS */;
INSERT INTO `hrms_traing_type` VALUES (5,'Pratham','IT Training','Unscheduled');
/*!40000 ALTER TABLE `hrms_traing_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hrms_training`
--

DROP TABLE IF EXISTS `hrms_training`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hrms_training` (
  `t_id` int NOT NULL,
  `user_id` int NOT NULL,
  `time_duration` varchar(50) DEFAULT NULL,
  `description` varchar(45) NOT NULL,
  `training_id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`training_id`),
  KEY `t_id_idx` (`t_id`),
  KEY `fk_user_id` (`user_id`),
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `hrmsemployees` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `t_id` FOREIGN KEY (`t_id`) REFERENCES `hrms_traing_type` (`t_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hrms_training`
--

LOCK TABLES `hrms_training` WRITE;
/*!40000 ALTER TABLE `hrms_training` DISABLE KEYS */;
INSERT INTO `hrms_training` VALUES (5,2360,'2Hrs Training','xyz',1);
/*!40000 ALTER TABLE `hrms_training` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hrmsadmin`
--

DROP TABLE IF EXISTS `hrmsadmin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hrmsadmin` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `a_name` varchar(50) CHARACTER SET latin1 COLLATE latin1_general_cs NOT NULL,
  `a_password` varchar(50) CHARACTER SET latin1 COLLATE latin1_general_cs NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=205 DEFAULT CHARSET=latin1 COLLATE=latin1_general_cs;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hrmsadmin`
--

LOCK TABLES `hrmsadmin` WRITE;
/*!40000 ALTER TABLE `hrmsadmin` DISABLE KEYS */;
/*!40000 ALTER TABLE `hrmsadmin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hrmsemployees`
--

DROP TABLE IF EXISTS `hrmsemployees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hrmsemployees` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `emp_firstname` varchar(50) CHARACTER SET latin1 COLLATE latin1_general_cs NOT NULL,
  `emp_lastname` varchar(50) CHARACTER SET latin1 COLLATE latin1_general_cs NOT NULL,
  `emp_email` varchar(50) CHARACTER SET latin1 COLLATE latin1_general_cs NOT NULL,
  `emp_phone` varchar(10) CHARACTER SET latin1 COLLATE latin1_general_cs NOT NULL,
  `emp_address` varchar(300) CHARACTER SET latin1 COLLATE latin1_general_cs NOT NULL,
  `emp_gender` varchar(10) CHARACTER SET latin1 COLLATE latin1_general_cs NOT NULL,
  `emp_password` varchar(500) COLLATE latin1_general_cs DEFAULT NULL,
  `hire_date` date NOT NULL,
  `salary` int NOT NULL,
  `dep_id` int NOT NULL,
  `manager_name` varchar(45) CHARACTER SET latin1 COLLATE latin1_general_cs DEFAULT NULL,
  `group_id` int DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `dep_id` (`dep_id`),
  KEY `group_id_idx` (`group_id`),
  CONSTRAINT `group_id_fk` FOREIGN KEY (`group_id`) REFERENCES `group_master` (`group_id`),
  CONSTRAINT `hrmsemployees_ibfk_1` FOREIGN KEY (`dep_id`) REFERENCES `hrms_department` (`dep_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2364 DEFAULT CHARSET=latin1 COLLATE=latin1_general_cs;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hrmsemployees`
--

LOCK TABLES `hrmsemployees` WRITE;
/*!40000 ALTER TABLE `hrmsemployees` DISABLE KEYS */;
INSERT INTO `hrmsemployees` VALUES (101,'Admin','a','a@gmail.com','1234567892','a','Male','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','2024-10-01',1,100,NULL,1),(2352,'Pratham','sali','Prathamsali107@gmail.com','9876545677','Brc,prabhunagr,udhna,surat.','Male','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','2021-10-01',25000,97,NULL,3),(2353,'Niraj','Oza','niraj@gmail.com','9876543210','Begampura','Male','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','2021-10-01',25000,101,NULL,3),(2354,'Asti','Paladiya','Asti@gmail.com','9876545677','Varacha','Female','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','2021-09-30',25000,96,NULL,3),(2355,'Meet','Barucha','meet@gmail.com','9876567843','Bhestan','Male','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','2021-10-01',25000,98,NULL,3),(2360,'Krish','Lambole','krish@gmail.com','9876545677','Brc,prabhunagr,udhna,surat.','Male','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','2021-10-01',12800,96,'Asti',2),(2361,'Vaibhav','Jadhav','vaibhav@gmail.com','9876545677','Katargam','Male','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','2021-09-30',25000,101,'Pavan',2),(2362,'Pavan','Mali','pavan@gmail.com','6576878765','Pandesara','Male','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','2021-10-01',25000,104,NULL,3),(2363,'Adity','Pathak','a@gmail.com','6576435674','pandesara','Male','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','2021-10-01',1234,104,'Pavan',2);
/*!40000 ALTER TABLE `hrmsemployees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resignation`
--

DROP TABLE IF EXISTS `resignation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `resignation` (
  `status` varchar(50) CHARACTER SET latin1 COLLATE latin1_general_cs DEFAULT 'PENDING',
  `user_id` int NOT NULL,
  `emp_firstname` varchar(50) CHARACTER SET latin1 COLLATE latin1_general_cs NOT NULL,
  `emp_lastname` varchar(50) CHARACTER SET latin1 COLLATE latin1_general_cs NOT NULL,
  `date_of_resign` date NOT NULL,
  `reason` varchar(50) CHARACTER SET latin1 COLLATE latin1_general_cs NOT NULL,
  `feedback` varchar(50) CHARACTER SET latin1 COLLATE latin1_general_cs NOT NULL,
  `company_property_return` varchar(50) CHARACTER SET latin1 COLLATE latin1_general_cs DEFAULT NULL,
  `manager` varchar(50) CHARACTER SET latin1 COLLATE latin1_general_cs DEFAULT NULL,
  `resig_id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`resig_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `resignation_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `hrmsemployees` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1 COLLATE=latin1_general_cs;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resignation`
--

LOCK TABLES `resignation` WRITE;
/*!40000 ALTER TABLE `resignation` DISABLE KEYS */;
INSERT INTO `resignation` VALUES ('Approved',2360,'Krish','Lambole','2024-10-09','better luck next time','good','true','Asti',7),('Approved',2360,'Krish','Lambole','2024-10-09','xz','xz','true','Asti',10),(NULL,2360,'Krish','Lambole','2024-10-09','feaver','good','true','Asti',11),('Approved',2363,'Adity','Pathak','2024-12-25','For better Future','good','true','Pavan',12),('Approved',2361,'Vaibhav','Jadhav','2024-12-25','xyz','xyz','true','Pavan',13);
/*!40000 ALTER TABLE `resignation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task_sheet`
--

DROP TABLE IF EXISTS `task_sheet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `task_sheet` (
  `user_id` int NOT NULL,
  `emp_name` varchar(50) CHARACTER SET latin1 COLLATE latin1_general_cs DEFAULT NULL,
  `entry_date` date NOT NULL,
  `task` varchar(50) CHARACTER SET latin1 COLLATE latin1_general_cs NOT NULL,
  `action_time` varchar(50) CHARACTER SET latin1 COLLATE latin1_general_cs DEFAULT NULL,
  `Status` varchar(50) COLLATE latin1_general_cs DEFAULT NULL,
  `task_id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`task_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `task_sheet_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `hrmsemployees` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1 COLLATE=latin1_general_cs;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task_sheet`
--

LOCK TABLES `task_sheet` WRITE;
/*!40000 ALTER TABLE `task_sheet` DISABLE KEYS */;
INSERT INTO `task_sheet` VALUES (2360,'Krish','2024-12-23','Entry','2hrs','Pending',12);
/*!40000 ALTER TABLE `task_sheet` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-26  3:45:31
