/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

CREATE DATABASE IF NOT EXISTS core_db;

DROP TABLE IF EXISTS `cd_region`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cd_region` (
  `record_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `record_key` char(36) NOT NULL,
  `region_type_rid` bigint(20) NOT NULL,
  `parent_region_rid` bigint(20) DEFAULT NULL,
  `name` varchar(255) NOT NULL COMMENT 'ISO3166-1 Short name\nor\nISO3166-2 Subdivision name',
  `name_lc` varchar(255) DEFAULT NULL COMMENT 'ISO3166 Short name lower case',
  `name_full` varchar(255) DEFAULT NULL COMMENT 'ISO3166 Full name',
  `name_common` varchar(255) DEFAULT NULL COMMENT 'Based on ISO3166-1 Short name lower case or ISO3166-2 Subdivision name. Data modified to remove parenthesis, brackets and massage naming to better meet common country representations.',
  `code_alpha` varchar(10) DEFAULT NULL COMMENT 'ISO 3166-1 alpha-2 code \nor\nISO 3166-2 code',
  `code_alpha_alt` varchar(10) DEFAULT NULL COMMENT 'ISO 3166-1 alpha-3 code',
  `code_numeric` varchar(10) DEFAULT NULL COMMENT 'ISO 3166-1 numeric code',
  `active_flag` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`record_id`),
  UNIQUE KEY `uix_record_key` (`record_key`),
  KEY `fk_region_type` (`region_type_rid`),
  KEY `fk_region_parent` (`parent_region_rid`),
  KEY `ix_active_flag` (`active_flag`),
  CONSTRAINT `fk_parent` FOREIGN KEY (`parent_region_rid`) REFERENCES `cd_region` (`record_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_region_type` FOREIGN KEY (`region_type_rid`) REFERENCES `cd_region_type` (`record_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5299 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `cd_region_path`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cd_region_path` (
  `region_rid` bigint(20) NOT NULL,
  `node_rid` bigint(20) NOT NULL,
  `node_level` bigint(20) NOT NULL,
  PRIMARY KEY (`region_rid`,`node_level`,`node_rid`),
  KEY `fk_region_branch` (`node_rid`),
  CONSTRAINT `fk_region_branch` FOREIGN KEY (`node_rid`) REFERENCES `cd_region` (`record_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_region_leaf` FOREIGN KEY (`region_rid`) REFERENCES `cd_region` (`record_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `cd_region_path_v`;
/*!50001 DROP VIEW IF EXISTS `cd_region_path_v`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `cd_region_path_v` (
  `record_id` tinyint NOT NULL,
  `record_key` tinyint NOT NULL,
  `name_common` tinyint NOT NULL,
  `type_record_id` tinyint NOT NULL,
  `type_record_key` tinyint NOT NULL,
  `type_name` tinyint NOT NULL,
  `node_record_id` tinyint NOT NULL,
  `node_record_key` tinyint NOT NULL,
  `node_name` tinyint NOT NULL,
  `node_type_record_id` tinyint NOT NULL,
  `node_type_record_key` tinyint NOT NULL,
  `node_type_name` tinyint NOT NULL,
  `node_level` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

DROP TABLE IF EXISTS `cd_region_region_type_v`;
/*!50001 DROP VIEW IF EXISTS `cd_region_region_type_v`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `cd_region_region_type_v` (
  `record_id` tinyint NOT NULL,
  `record_key` tinyint NOT NULL,
  `name_common` tinyint NOT NULL,
  `region_type_rid` tinyint NOT NULL,
  `region_type_key` tinyint NOT NULL,
  `region_type_name` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

DROP TABLE IF EXISTS `cd_region_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cd_region_type` (
  `record_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `record_key` char(36) NOT NULL,
  `name` varchar(45) NOT NULL,
  `active_flag` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`record_id`),
  UNIQUE KEY `uix_record_key` (`record_key`)
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!50001 DROP TABLE IF EXISTS `cd_region_path_v`*/;
/*!50001 DROP VIEW IF EXISTS `cd_region_path_v`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
  /*!50013 DEFINER=`root`@`%` SQL SECURITY DEFINER */
  /*!50001 VIEW `cd_region_path_v` AS select `leaf`.`record_id` AS `record_id`,`leaf`.`record_key` AS `record_key`,`leaf`.`name_common` AS `name_common`,`leaf_type`.`record_id` AS `type_record_id`,`leaf_type`.`record_key` AS `type_record_key`,`leaf_type`.`name` AS `type_name`,`branch`.`record_id` AS `node_record_id`,`branch`.`record_key` AS `node_record_key`,`branch`.`name_common` AS `node_name`,`branch_type`.`record_id` AS `node_type_record_id`,`branch_type`.`record_key` AS `node_type_record_key`,`branch_type`.`name` AS `node_type_name`,`path`.`node_level` AS `node_level` from ((((`cd_region_path` `path` join `cd_region` `leaf` on((`path`.`region_rid` = `leaf`.`record_id`))) join `cd_region_type` `leaf_type` on((`leaf`.`region_type_rid` = `leaf_type`.`record_id`))) join `cd_region` `branch` on((`path`.`node_rid` = `branch`.`record_id`))) join `cd_region_type` `branch_type` on((`branch`.`region_type_rid` = `branch_type`.`record_id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

/*!50001 DROP TABLE IF EXISTS `cd_region_region_type_v`*/;
/*!50001 DROP VIEW IF EXISTS `cd_region_region_type_v`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
  /*!50013 DEFINER=`root`@`%` SQL SECURITY DEFINER */
  /*!50001 VIEW `cd_region_region_type_v` AS select `region`.`record_id` AS `record_id`,`region`.`record_key` AS `record_key`,`region`.`name_common` AS `name_common`,`region_type`.`record_id` AS `region_type_rid`,`region_type`.`record_key` AS `region_type_key`,`region_type`.`name` AS `region_type_name` from (`cd_region` `region` join `cd_region_type` `region_type` on((`region`.`region_type_rid` = `region_type`.`record_id`))) */;
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
