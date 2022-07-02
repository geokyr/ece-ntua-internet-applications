/*
MySQL Data Transfer
Source Host: localhost
Source Database: hw2db
Target Host: localhost
Target Database: hw2db
Date: 6/3/2021 9:41:47 PM
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `FULL_NAME` varchar(255) NOT NULL,
  `USERNAME` varchar(20) NOT NULL,
  `PASSWORD_SHA256` varchar(64) NOT NULL,
  `DATE_UPDATED` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`ID`),
  UNIQUE KEY `USERNAME` (`USERNAME`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for status
-- ----------------------------
DROP TABLE IF EXISTS `status`;
CREATE TABLE `status` (
  `ID` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `NAME` varchar(20) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for task
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `USER_ID` int(10) unsigned DEFAULT NULL,
  `DESCRIPTION` text NOT NULL,
  `STATUS_ID` smallint(5) unsigned NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `USER_ID` (`USER_ID`),
  KEY `STATUS_ID` (`STATUS_ID`),
  CONSTRAINT `task_ibfk_1` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`ID`),
  CONSTRAINT `task_ibfk_2` FOREIGN KEY (`STATUS_ID`) REFERENCES `status` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;



-- ----------------------------
-- Records 
-- ----------------------------

INSERT INTO `user` VALUES ('1', 'Network User1', 'p@ss1', '4dd8dc477bc8b9cc3ef47610d4146bcddd228636320290f3f8788494b6ffffc2', '2021-06-03 21:25:12');

INSERT INTO `status` VALUES ('1', 'Assigned');
INSERT INTO `status` VALUES ('2', 'Under Process');
INSERT INTO `status` VALUES ('3', 'Completed');

INSERT INTO `task` VALUES ('1', '1', 'This is the 1st task that you have to do. Ideally, we would like to have an analytical description of this task so that the user knows exactly what he/she should do.', '2');
INSERT INTO `task` VALUES ('2', '1', 'This is the 2nd task that you have to do. As you can notice we have added a few characters so that the description of this task being quite large and hence only a small part of this message should be presented in the users screen (at least in the screen with the whole tasks). Of course in the other page the user should be able to see all this description. ', '1');
INSERT INTO `task` VALUES ('4', '1', 'This is the 3rd task. Please proceed with this task when finish with the other ones. ', '1');

