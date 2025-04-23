-- MySQL dump 10.13  Distrib 8.0.41, for Linux (x86_64)
--
-- Host: localhost    Database: inv
-- ------------------------------------------------------
-- Server version	8.0.41-0ubuntu0.22.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `icambios`
--

DROP TABLE IF EXISTS `icambios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `icambios` (
  `id` int NOT NULL AUTO_INCREMENT,
  `inventario_id` int NOT NULL,
  `cantidad_anterior` int NOT NULL,
  `cantidad_nueva` int NOT NULL,
  `fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `icambios`
--

LOCK TABLES `icambios` WRITE;
/*!40000 ALTER TABLE `icambios` DISABLE KEYS */;
INSERT INTO `icambios` VALUES (4,3,1,0,'2025-04-23 01:32:58'),(5,2,0,100,'2025-04-23 01:33:48'),(6,3,0,150,'2025-04-23 01:45:11'),(7,4,0,20,'2025-04-23 01:46:13'),(8,5,0,10,'2025-04-23 01:46:36'),(9,6,0,50,'2025-04-23 01:47:06'),(10,7,0,25,'2025-04-23 01:47:34'),(11,2,100,85,'2025-04-23 01:48:31'),(12,3,150,130,'2025-04-23 01:52:49'),(13,4,20,4,'2025-04-23 01:53:28'),(14,5,10,3,'2025-04-23 01:54:28'),(15,6,50,18,'2025-04-23 01:54:56'),(16,7,25,3,'2025-04-23 01:55:23');
/*!40000 ALTER TABLE `icambios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inventario`
--

DROP TABLE IF EXISTS `inventario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inventario` (
  `id` int NOT NULL AUTO_INCREMENT,
  `producto_id` int DEFAULT NULL,
  `cantidad` int DEFAULT '0',
  `provedor_id` int DEFAULT NULL,
  `ultima_actualizacion` date DEFAULT (curdate()),
  PRIMARY KEY (`id`),
  KEY `producto_id` (`producto_id`),
  KEY `provedor_id` (`provedor_id`),
  CONSTRAINT `inventario_ibfk_1` FOREIGN KEY (`producto_id`) REFERENCES `productos` (`id`),
  CONSTRAINT `inventario_ibfk_2` FOREIGN KEY (`provedor_id`) REFERENCES `provedores` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventario`
--

LOCK TABLES `inventario` WRITE;
/*!40000 ALTER TABLE `inventario` DISABLE KEYS */;
INSERT INTO `inventario` VALUES (2,2,85,2,'2025-04-22'),(3,1,130,3,'2025-04-22'),(4,6,4,3,'2025-04-22'),(5,3,3,1,'2025-04-22'),(6,5,18,3,'2025-04-22'),(7,4,3,1,'2025-04-22');
/*!40000 ALTER TABLE `inventario` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `inventario_cambios` AFTER UPDATE ON `inventario` FOR EACH ROW BEGIN
    IF OLD.cantidad <> NEW.cantidad THEN
        INSERT INTO icambios (
            inventario_id,
            cantidad_anterior,
            cantidad_nueva,
            fecha
        )
        VALUES (
            OLD.id,
            OLD.cantidad,
            NEW.cantidad,
            NOW()
        );
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `movimientos`
--

DROP TABLE IF EXISTS `movimientos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movimientos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `producto_id` int DEFAULT NULL,
  `tipo_movimiento` enum('venta','compra') NOT NULL,
  `fecha_movimiento` date DEFAULT (curdate()),
  `cantidad` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `producto_id` (`producto_id`),
  CONSTRAINT `movimientos_ibfk_1` FOREIGN KEY (`producto_id`) REFERENCES `productos` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movimientos`
--

LOCK TABLES `movimientos` WRITE;
/*!40000 ALTER TABLE `movimientos` DISABLE KEYS */;
INSERT INTO `movimientos` VALUES (2,2,'compra','2025-04-22',100),(3,1,'compra','2025-04-22',150),(4,6,'compra','2025-04-22',20),(5,3,'compra','2025-04-22',10),(6,5,'compra','2025-04-22',50),(7,4,'compra','2025-04-22',25),(8,2,'venta','2025-04-22',15),(9,1,'venta','2025-04-22',20),(10,6,'venta','2025-04-22',16),(11,3,'venta','2025-04-22',7),(12,5,'venta','2025-04-22',32),(13,4,'venta','2025-04-22',22);
/*!40000 ALTER TABLE `movimientos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `precio` decimal(10,2) DEFAULT NULL,
  `categoria` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` VALUES (1,'Dell Latitude Laptop',4000.00,'Electronica'),(2,'Cuaderno de 100 hojas',250.00,'Papeleria'),(3,'Escritorio para computadora',2750.00,'Muebles'),(4,'Silla para escritorio',2000.00,'Muebles'),(5,'Mouse alambrico',300.00,'Electronica'),(6,'Disco duro externo',2500.00,'Electronica');
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `provedores`
--

DROP TABLE IF EXISTS `provedores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `provedores` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `provedores`
--

LOCK TABLES `provedores` WRITE;
/*!40000 ALTER TABLE `provedores` DISABLE KEYS */;
INSERT INTO `provedores` VALUES (1,'Muebleria ABC','muebleria@abc.com','686 111 2222'),(2,'Papeleria Zonic','manuel@zonic.com','686 222 3333'),(3,'Electronica xyz','electronica@xyz.com','686 333 4444');
/*!40000 ALTER TABLE `provedores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ragtime_migrations`
--

DROP TABLE IF EXISTS `ragtime_migrations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ragtime_migrations` (
  `id` varchar(255) DEFAULT NULL,
  `created_at` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ragtime_migrations`
--

LOCK TABLES `ragtime_migrations` WRITE;
/*!40000 ALTER TABLE `ragtime_migrations` DISABLE KEYS */;
INSERT INTO `ragtime_migrations` VALUES ('001-users','2025-04-22T15:21:06.515'),('002-users_view','2025-04-22T15:21:06.643'),('003-productos','2025-04-22T15:21:06.866'),('004-provedores','2025-04-22T15:21:07.069'),('005-inventario','2025-04-22T15:21:07.240'),('006-movimientos','2025-04-22T15:21:07.425'),('007-icambios','2025-04-22T15:21:07.552'),('008-inventario_cambios','2025-04-22T15:21:07.647');
/*!40000 ALTER TABLE `ragtime_migrations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `lastname` varchar(50) DEFAULT NULL,
  `firstname` varchar(50) DEFAULT NULL,
  `username` varchar(200) DEFAULT NULL,
  `password` text,
  `dob` date DEFAULT NULL,
  `cell` varchar(50) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `fax` varchar(50) DEFAULT NULL,
  `email` varchar(200) DEFAULT NULL,
  `level` char(1) DEFAULT NULL COMMENT 'A=Administrador,U=Usuario,S=Sistema',
  `active` char(1) DEFAULT NULL COMMENT 'T=Activo,F=Inactivo',
  `imagen` text,
  `last_login` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'User','Regular','user@gmail.com','$s0$e0801$yhBYNPrZ3ewG+BrKBhLcxA==$oo3D3bHe3kpevDy0LWUqz5QTbw1O4JdFpfdvXEcAMy4=','1957-02-07',NULL,NULL,NULL,'user@gmail.com','U','T',NULL,'2025-04-22 22:21:28'),(2,'User','Admin','admin@gmail.com','$s0$e0801$Uvs9t919BuSlomVoYkVOfQ==$ZB57SAMh2sUdx0AhayTwiYO5sm3AxE1BHAu72q41bmg=','1957-02-07',NULL,NULL,NULL,'admin@gmail.com','A','T',NULL,'2025-04-22 22:21:28'),(3,'User','System','sistema@gmail.com','$s0$e0801$bj78HbdAJSkmKXAhig3Bxg==$kCymLaGQJtKrEfFO4fpQ5aj6qhnSXJme7kbqHm9nSrU=','1957-02-07',NULL,NULL,NULL,'sistema@gmail.com','S','T',NULL,'2025-04-22 22:21:28');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `users_view`
--

DROP TABLE IF EXISTS `users_view`;
/*!50001 DROP VIEW IF EXISTS `users_view`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `users_view` AS SELECT 
 1 AS `id`,
 1 AS `lastname`,
 1 AS `firstname`,
 1 AS `username`,
 1 AS `dob`,
 1 AS `cell`,
 1 AS `phone`,
 1 AS `fax`,
 1 AS `email`,
 1 AS `level`,
 1 AS `active`,
 1 AS `imagen`,
 1 AS `last_login`,
 1 AS `dob_formatted`,
 1 AS `level_formatted`,
 1 AS `active_formatted`*/;
SET character_set_client = @saved_cs_client;

--
-- Dumping routines for database 'inv'
--

--
-- Final view structure for view `users_view`
--

/*!50001 DROP VIEW IF EXISTS `users_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `users_view` AS select `users`.`id` AS `id`,`users`.`lastname` AS `lastname`,`users`.`firstname` AS `firstname`,`users`.`username` AS `username`,`users`.`dob` AS `dob`,`users`.`cell` AS `cell`,`users`.`phone` AS `phone`,`users`.`fax` AS `fax`,`users`.`email` AS `email`,`users`.`level` AS `level`,`users`.`active` AS `active`,`users`.`imagen` AS `imagen`,`users`.`last_login` AS `last_login`,date_format(`users`.`dob`,'%d/%m/%Y') AS `dob_formatted`,(case when (`users`.`level` = 'U') then 'Usuario' when (`users`.`level` = 'A') then 'Administrador' else 'Sistema' end) AS `level_formatted`,(case when (`users`.`active` = 'T') then 'Activo' else 'Inactivo' end) AS `active_formatted` from `users` order by `users`.`lastname`,`users`.`firstname` */;
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

-- Dump completed on 2025-04-22 22:04:17
