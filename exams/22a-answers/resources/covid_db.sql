/* WEB APPLICATION EXAM JULY 2022 DB */

SET FOREIGN_KEY_CHECKS=0;

-- ============================
-- Database Schema 
-- ============================

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `ID` smallint(10) unsigned NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(15) NOT NULL,
  `PASSWORD_HASH` varchar(64) NOT NULL COMMENT 'SHA-256',
  `DATE_CREATED` datetime NOT NULL,
  `ROLE_ID` smallint(6) NOT NULL DEFAULT 1 COMMENT '1 stands for normal users and 2 for administrators',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `USERNAME` (`USERNAME`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for diagnosis_methods
-- ----------------------------
DROP TABLE IF EXISTS `diagnosis_methods`;
CREATE TABLE `diagnosis_methods` (
  `ID` tinyint(3) unsigned NOT NULL AUTO_INCREMENT,
  `NAME` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for cases
-- ----------------------------
DROP TABLE IF EXISTS `cases`;
CREATE TABLE `cases` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `USER_ID` smallint(10) unsigned NOT NULL,
  `DIAGNOSIS_DATE` datetime NOT NULL,
  `DIAGNOSIS_METHOD_ID` tinyint(3) unsigned NOT NULL,
  `DIAGNOSIS_LOCATION` varchar(100) DEFAULT '' COMMENT 'The city where the rapid test took place',
  `DIAGNOSIS_REPORT_UID` varchar(20) DEFAULT '' COMMENT 'The Unique ID of the PCR test document',
  PRIMARY KEY (`ID`),
  KEY `USER_ID` (`USER_ID`),
  KEY `DIAGNOSIS_METHOD_ID` (`DIAGNOSIS_METHOD_ID`),
  CONSTRAINT `cases_ibfk_1` FOREIGN KEY (`USER_ID`) REFERENCES `users` (`ID`),
  CONSTRAINT `cases_ibfk_2` FOREIGN KEY (`DIAGNOSIS_METHOD_ID`) REFERENCES `diagnosis_methods` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;


-- ============================
-- Records 
-- ============================

INSERT INTO `users` VALUES ('1', 'user1', '0a041b9462caa4a31bac3567e0b6e6fd9100787db2ab433d96f6d178cabfce90', '2022-06-30 00:00:00', '1');
INSERT INTO `users` VALUES ('2', 'user2', '6025d18fe48abd45168528f18a82e265dd98d421a7084aa09f61b341703901a3', '2022-06-30 00:00:00', '1');
INSERT INTO `users` VALUES ('3', 'admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', '2022-06-30 00:00:00', '2');

INSERT INTO `diagnosis_methods` VALUES ('1', 'SELF TEST');
INSERT INTO `diagnosis_methods` VALUES ('2', 'RAPID TEST');
INSERT INTO `diagnosis_methods` VALUES ('3', 'PCR TEST');

INSERT INTO `cases` VALUES ('2', '1', '2022-06-30 00:00:00', '1', null, null);
INSERT INTO `cases` VALUES ('5', '1', '2021-12-15 00:00:00', '2', 'ZOGRAFOU', '');
INSERT INTO `cases` VALUES ('8', '1', '2021-03-22 00:00:00', '3', null, 'B12345678');

INSERT INTO `cases` VALUES ('12', '2', '2022-06-30 00:00:00', '1', null, null);
INSERT INTO `cases` VALUES ('15', '2', '2021-12-15 00:00:00', '2', 'PAGRATI', '');


