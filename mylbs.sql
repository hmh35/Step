/*
Navicat MySQL Data Transfer

Source Server         : safeonline
Source Server Version : 50173
Source Host           : localhost:3306
Source Database       : mylbs

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2016-08-13 17:14:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_activities`
-- ----------------------------
DROP TABLE IF EXISTS `t_activities`;
CREATE TABLE `t_activities` (
  `ACT_NO` int(11) NOT NULL AUTO_INCREMENT,
  `ACT_NAME` varchar(32) DEFAULT NULL,
  `DESCRIPTION` varchar(512) DEFAULT NULL,
  `ADDR_LONG` double(32,10) DEFAULT NULL,
  `ADDR_LAT` double(32,10) DEFAULT NULL,
  `ADDRESS` varchar(64) DEFAULT NULL,
  `APLY_UPPLMT` timestamp NULL DEFAULT NULL,
  `APLY_LOWLMT` timestamp NULL DEFAULT NULL,
  `ACT_STATUS` tinyint(4) DEFAULT NULL,
  `CREATOR_NO` varchar(8) DEFAULT NULL,
  `CREATE_NAME` varchar(32) DEFAULT NULL,
  `CREATE_TIME` timestamp NULL DEFAULT NULL,
  `UPDATE_TIME` timestamp NULL DEFAULT NULL,
  `ACT_RANGE` int(11) DEFAULT '20',
  `PUSH_OBJECT` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`ACT_NO`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_activities
-- ----------------------------
INSERT INTO `t_activities` VALUES ('10', '测试推送', '测试推送', '119.3987798735', '25.8999545796', '来咯后', '2016-04-05 17:09:00', '2016-04-05 17:09:00', '0', '1', '杜导', null, null, '20', null);
INSERT INTO `t_activities` VALUES ('11', '测试活动', '测试', '119.3982139410', '25.9001170864', '测试地址', '2016-04-15 21:26:00', '2016-04-15 21:26:00', '0', '1', '杜导', null, null, '20', null);
INSERT INTO `t_activities` VALUES ('12', '‘’‘', '模具\n', '119.2209063995', '26.0241654488', '南门\n', '2016-07-22 12:08:00', '2016-07-21 12:08:00', '0', '2', '王爸爸', null, null, '20', null);
INSERT INTO `t_activities` VALUES ('14', '晚点', '晚点', '0.0089740749', '0.0035091474', '软楼507', '2016-07-30 21:30:00', '2016-07-30 20:30:00', '0', '1', '周导', null, null, '200', null);
INSERT INTO `t_activities` VALUES ('18', '安全讲座', '校组织讲座', '119.2196010000', '26.0274660000', '图书馆大礼堂', '2017-08-02 17:23:00', '2016-08-02 14:20:00', '0', '1', '周导', '1970-08-01 14:24:00', null, '200', '支书');
INSERT INTO `t_activities` VALUES ('34', '早点', '新生教育', '119.2196170000', '26.0277610000', '知名1-205', '2017-08-13 15:26:00', '2016-08-13 16:26:00', '0', '1', '周导', '2016-08-13 15:26:55', null, '100', '班长');
INSERT INTO `t_activities` VALUES ('44', '早点', '新生教育', '119.2195920000', '26.0277690000', '知名1-205', '2017-08-15 15:45:00', '2016-08-16 15:45:00', '0', '1', '周导', '2016-08-13 15:45:18', null, '100', '所有人');
INSERT INTO `t_activities` VALUES ('47', '早点', '新生教育', '119.2196320000', '26.0277170000', '知名1-205', '2018-08-14 16:00:00', '2016-08-15 16:00:00', '0', '1', '周导', '2016-08-13 16:00:54', null, '100', '所有人');

-- ----------------------------
-- Table structure for `t_config`
-- ----------------------------
DROP TABLE IF EXISTS `t_config`;
CREATE TABLE `t_config` (
  `CONF_NO` int(11) NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(32) DEFAULT NULL,
  `IS_MONITORED` tinyint(4) DEFAULT NULL,
  `IS_WARNING` tinyint(4) DEFAULT NULL,
  `IS_ALARM` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`CONF_NO`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_config
-- ----------------------------
INSERT INTO `t_config` VALUES ('1', '123012012130', '0', '0', '0');
INSERT INTO `t_config` VALUES ('2', '123012014049', '0', '0', '0');
INSERT INTO `t_config` VALUES ('4', '123012014100', '0', '0', '0');
INSERT INTO `t_config` VALUES ('5', '123012014018', '0', '0', '0');
INSERT INTO `t_config` VALUES ('6', '123012014039', '0', '0', '0');
INSERT INTO `t_config` VALUES ('7', '123012014030', null, null, null);

-- ----------------------------
-- Table structure for `t_contacts`
-- ----------------------------
DROP TABLE IF EXISTS `t_contacts`;
CREATE TABLE `t_contacts` (
  `CONTACT_NO` int(11) NOT NULL AUTO_INCREMENT,
  `MONITORED_NO` varchar(8) DEFAULT NULL,
  `CONTACT_NAME` varchar(32) DEFAULT NULL,
  `CONTACT_PHONE` varchar(32) DEFAULT NULL,
  `CONTACT_RELATION` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`CONTACT_NO`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_contacts
-- ----------------------------
INSERT INTO `t_contacts` VALUES ('35', '29', '唐文明', '13675093810', '室友');
INSERT INTO `t_contacts` VALUES ('68', '29', '刘勤', '13600000000', '舍友');
INSERT INTO `t_contacts` VALUES ('69', '29', '帅气陈', '13655026593', '无');
INSERT INTO `t_contacts` VALUES ('70', '30', '王爸爸', '18859139989', '同学');
INSERT INTO `t_contacts` VALUES ('71', '26', '王爸爸', '18859139989', '同学');
INSERT INTO `t_contacts` VALUES ('72', '26', '华哥', '13075861664', '爸爸');
INSERT INTO `t_contacts` VALUES ('73', '26', '吼吼', '15880106516', '同学');
INSERT INTO `t_contacts` VALUES ('74', '26', '吼吼', '15880106516', '同学');

-- ----------------------------
-- Table structure for `t_login_log`
-- ----------------------------
DROP TABLE IF EXISTS `t_login_log`;
CREATE TABLE `t_login_log` (
  `LOGIN_LOG_NO` int(11) NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(32) DEFAULT NULL,
  `ACCESS_TOKEN` varchar(128) DEFAULT NULL,
  `CREATE_TIME` timestamp NULL DEFAULT NULL,
  `UPDATE_TIME` timestamp NULL DEFAULT NULL,
  `EFFICTIVE_TIME` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`LOGIN_LOG_NO`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_login_log
-- ----------------------------
INSERT INTO `t_login_log` VALUES ('30', '123012012130', '2A7BF97DCD51475B4CD8490682A10202', '2016-03-06 10:29:01', '2016-07-21 14:58:37', '2016-08-21 14:58:37');
INSERT INTO `t_login_log` VALUES ('33', '123012014049', 'B77FD2BB3B05EC1213F382C98DDF8C51', '2016-03-08 19:54:26', '2016-08-13 16:22:53', '2016-09-13 16:22:53');
INSERT INTO `t_login_log` VALUES ('34', '123012014100', '9FED0F8CE1FE8222E9718A3AFB4A25AB', '2016-03-09 14:32:23', '2016-07-21 11:58:06', '2016-08-21 11:58:06');
INSERT INTO `t_login_log` VALUES ('36', '123012014030', '4C3EED08D14C6BD0FB27B1B75E798330', '2016-03-22 22:13:35', '2016-07-21 15:02:08', '2016-08-21 15:02:08');
INSERT INTO `t_login_log` VALUES ('37', '123012014018', 'F5E620358BDD34D0B6DC7FD64F4ECFC8', '2016-03-22 22:16:16', '2016-07-21 12:02:37', '2016-08-21 12:02:37');
INSERT INTO `t_login_log` VALUES ('38', '123012014039', '2BD801C0D4B13D9BACCA5B85484154E5', '2016-03-22 22:17:11', '2016-08-13 17:12:16', '2016-09-13 17:12:16');

-- ----------------------------
-- Table structure for `t_monitor`
-- ----------------------------
DROP TABLE IF EXISTS `t_monitor`;
CREATE TABLE `t_monitor` (
  `MONITOR_NO` int(11) NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(32) DEFAULT NULL,
  `USER_PWD` varchar(32) DEFAULT NULL,
  `REAL_NAME` varchar(32) DEFAULT NULL,
  `SEX` int(11) DEFAULT NULL,
  `TITLE` varchar(32) DEFAULT NULL,
  `PHONE_NUMBER` varchar(32) DEFAULT NULL,
  `IDENTITY_CARD_NO` varchar(32) DEFAULT NULL,
  `STATUS` tinyint(4) DEFAULT NULL,
  `ACCESS_RIGHT` tinyint(4) DEFAULT NULL,
  `CREATE_TIME` timestamp NULL DEFAULT NULL,
  `UPDATE_TIME` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`MONITOR_NO`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_monitor
-- ----------------------------
INSERT INTO `t_monitor` VALUES ('1', '123012014049', 'E10ADC3949BA59ABBE56E057F20F883E', '周导', '1', '辅导员', '13075861664', '35012119930203****', '0', '1', '2016-03-06 19:46:34', '2016-03-31 21:23:16');
INSERT INTO `t_monitor` VALUES ('2', '123012014018', 'E10ADC3949BA59ABBE56E057F20F883E', '王爸爸', '1', '辅导员', '18859139989', '350128199408045417', '0', '1', '2016-03-06 23:44:51', null);

-- ----------------------------
-- Table structure for `t_monitored`
-- ----------------------------
DROP TABLE IF EXISTS `t_monitored`;
CREATE TABLE `t_monitored` (
  `MONITORED_NO` int(55) NOT NULL AUTO_INCREMENT,
  `STUDENT_NO` varchar(16) DEFAULT NULL,
  `PASSWORD` varchar(32) DEFAULT NULL,
  `REAL_NAME` varchar(32) DEFAULT NULL,
  `AGE` int(4) DEFAULT NULL,
  `SEX` tinyint(4) DEFAULT NULL,
  `PHONE_NUMBER` varchar(32) DEFAULT NULL,
  `IDENTITY_CARD_NO` varchar(32) DEFAULT NULL,
  `STATUS` tinyint(4) DEFAULT NULL,
  `ADMISSION_TIME` date DEFAULT NULL,
  `LENGTH_OF_SCHOOLING` int(4) DEFAULT NULL,
  `CHANNEL_ID` varchar(32) DEFAULT NULL,
  `CREATE_TIME` timestamp NULL DEFAULT NULL,
  `UPDATE_TIME` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`MONITORED_NO`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_monitored
-- ----------------------------
INSERT INTO `t_monitored` VALUES ('26', '123012014039', 'E10ADC3949BA59ABBE56E057F20F883E', '聪狗', '25', '1', '13774527103', '45022219920305****', '0', '2016-03-05', '4', '3784978333030206785', '2016-03-05 00:00:00', '2016-03-05 10:25:26');
INSERT INTO `t_monitored` VALUES ('29', '123012012130', '8B476B1F36745B2647550CB0C00D5A21', '林秋', '25', '0', '15659928899', '35012119920305****', '0', '2016-03-05', '4', '', '2016-03-05 16:06:27', '2016-07-21 11:22:43');
INSERT INTO `t_monitored` VALUES ('30', '123012014100', 'E10ADC3949BA59ABBE56E057F20F883E', '娴静', '25', '1', '13655026893', '35012119920305****', '0', '2016-03-05', '4', '4306744217829536351', '2016-03-05 20:50:40', null);
INSERT INTO `t_monitored` VALUES ('34', '123012014030', 'E10ADC3949BA59ABBE56E057F20F883E', '雨婷', '25', '1', '15959065719', '4564', '0', '2016-07-21', '4', null, '2016-07-21 11:41:58', '2016-07-21 11:42:01');
INSERT INTO `t_monitored` VALUES ('35', '123012012112', 'E10ADC3949BA59ABBE56E057F20F883E', '洪小隆', null, null, '13822222222', null, '0', null, null, null, null, null);
INSERT INTO `t_monitored` VALUES ('36', '123012012122', 'E10ADC3949BA59ABBE56E057F20F883E', '郑震培', null, null, '13155555555', null, '0', null, null, null, null, null);

-- ----------------------------
-- Table structure for `t_monitored_and_monitor`
-- ----------------------------
DROP TABLE IF EXISTS `t_monitored_and_monitor`;
CREATE TABLE `t_monitored_and_monitor` (
  `MM_NO` int(11) NOT NULL AUTO_INCREMENT,
  `MONITORED_NO` varchar(8) DEFAULT NULL,
  `MONITOR_NO` varchar(8) DEFAULT NULL,
  `CREATE_TIME` timestamp NULL DEFAULT NULL,
  `UPDATE_TIME` timestamp NULL DEFAULT NULL,
  `RELATION_SHIP` varchar(15),
  PRIMARY KEY (`MM_NO`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_monitored_and_monitor
-- ----------------------------
INSERT INTO `t_monitored_and_monitor` VALUES ('1', '26', '1', '2016-03-07 22:43:38', '2016-03-07 22:43:41', '学生');
INSERT INTO `t_monitored_and_monitor` VALUES ('2', '29', '1', '2016-03-07 22:46:52', '2016-03-07 22:46:54', '学生');
INSERT INTO `t_monitored_and_monitor` VALUES ('3', '30', '1', '2016-03-23 22:47:02', '2016-03-24 22:47:05', '学生');
INSERT INTO `t_monitored_and_monitor` VALUES ('5', '34', '1', '2016-03-22 22:14:22', '2016-03-22 22:14:22', '学生');
INSERT INTO `t_monitored_and_monitor` VALUES ('6', '35', '1', '2016-03-22 22:16:16', '2016-03-22 22:16:16', '班长');
INSERT INTO `t_monitored_and_monitor` VALUES ('7', '36', '1', '2016-03-22 22:17:11', '2016-03-22 22:17:11', '支书');

-- ----------------------------
-- Table structure for `t_mon_act`
-- ----------------------------
DROP TABLE IF EXISTS `t_mon_act`;
CREATE TABLE `t_mon_act` (
  `MON_ACT_NO` int(11) NOT NULL AUTO_INCREMENT,
  `ACT_NO` varchar(16) DEFAULT NULL,
  `MONITORED_NO` varchar(8) DEFAULT NULL,
  `MONITOR` varchar(8) DEFAULT NULL,
  `CREATE_TIME` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`MON_ACT_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_mon_act
-- ----------------------------

-- ----------------------------
-- Table structure for `t_position`
-- ----------------------------
DROP TABLE IF EXISTS `t_position`;
CREATE TABLE `t_position` (
  `POSITION_NO` int(11) NOT NULL AUTO_INCREMENT,
  `MONITORED_NO` varchar(32) DEFAULT NULL,
  `ADDR_LONG` double(32,10) DEFAULT NULL,
  `ADDR_LAT` double(32,10) DEFAULT NULL,
  `ADDRESS` varchar(64) DEFAULT NULL,
  `STUDENT_NO` varchar(16) DEFAULT NULL,
  `REAL_NAME` varchar(32) DEFAULT NULL,
  `CREATE_TIME` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`POSITION_NO`)
) ENGINE=InnoDB AUTO_INCREMENT=431 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_position
-- ----------------------------
INSERT INTO `t_position` VALUES ('341', '29', '119.2196340000', '26.0277490000', '中国福建省福州市闽侯县法政路', '123012012130', '林秋', '2016-04-02 16:30:51');
INSERT INTO `t_position` VALUES ('342', '29', '119.2196200000', '26.0277300000', '中国福建省福州市闽侯县法政路', '123012012130', '林秋', '2016-04-02 16:30:52');
INSERT INTO `t_position` VALUES ('343', '29', '119.2195300000', '26.0277330000', '中国福建省福州市闽侯县法政路', '123012012130', '林秋', '2016-04-02 16:30:59');
INSERT INTO `t_position` VALUES ('372', '29', '119.3978210000', '25.8980260000', '中国福建省福州市闽侯县青闸线', '123012012130', '林秋', '2016-04-06 14:58:00');
INSERT INTO `t_position` VALUES ('373', '29', '119.3978400000', '25.8980290000', '中国福建省福州市闽侯县青闸线', '123012012130', '林秋', '2016-04-06 16:45:42');
INSERT INTO `t_position` VALUES ('374', '29', '119.3978620000', '25.8980160000', '中国福建省福州市闽侯县青闸线', '123012012130', '林秋', '2016-04-06 17:03:01');
INSERT INTO `t_position` VALUES ('375', '29', '119.3977980000', '25.8980440000', '中国福建省福州市闽侯县青闸线', '123012012130', '林秋', '2016-04-06 17:05:27');
INSERT INTO `t_position` VALUES ('376', '29', '119.3978100000', '25.8980500000', '中国福建省福州市闽侯县青闸线', '123012012130', '林秋', '2016-04-06 17:16:31');
INSERT INTO `t_position` VALUES ('377', '29', '119.3978180000', '25.8980240000', '中国福建省福州市闽侯县青闸线', '123012012130', '林秋', '2016-04-06 17:22:20');
INSERT INTO `t_position` VALUES ('378', '29', '119.3978810000', '25.8980140000', '中国福建省福州市闽侯县青闸线', '123012012130', '林秋', '2016-04-06 17:36:01');
INSERT INTO `t_position` VALUES ('379', '29', '119.3978810000', '25.8980140000', '中国福建省福州市闽侯县青闸线', '123012012130', '林秋', '2016-04-06 17:37:03');
INSERT INTO `t_position` VALUES ('380', '29', '119.3980080000', '25.8979920000', '中国福建省福州市闽侯县青闸线', '123012012130', '林秋', '2016-04-13 15:10:47');
INSERT INTO `t_position` VALUES ('381', '29', '119.3980040000', '25.8979960000', '中国福建省福州市闽侯县青闸线', '123012012130', '林秋', '2016-04-13 15:12:59');
INSERT INTO `t_position` VALUES ('382', '29', '119.3980060000', '25.8979970000', '中国福建省福州市闽侯县青闸线', '123012012130', '林秋', '2016-04-13 15:16:16');
INSERT INTO `t_position` VALUES ('383', '29', '119.3980060000', '25.8979960000', '中国福建省福州市闽侯县青闸线', '123012012130', '林秋', '2016-04-13 15:17:01');
INSERT INTO `t_position` VALUES ('384', '29', '119.3980100000', '25.8979890000', '中国福建省福州市闽侯县青闸线', '123012012130', '林秋', '2016-04-13 15:17:47');
INSERT INTO `t_position` VALUES ('385', '29', '119.3980020000', '25.8980000000', '中国福建省福州市闽侯县青闸线', '123012012130', '林秋', '2016-04-13 15:19:06');
INSERT INTO `t_position` VALUES ('386', '29', '119.3979970000', '25.8980030000', '中国福建省福州市闽侯县青闸线', '123012012130', '林秋', '2016-04-13 15:21:37');
INSERT INTO `t_position` VALUES ('387', '29', '119.3980100000', '25.8979950000', '中国福建省福州市闽侯县青闸线', '123012012130', '林秋', '2016-04-13 15:23:58');
INSERT INTO `t_position` VALUES ('388', '29', '119.3980090000', '25.8979930000', '中国福建省福州市闽侯县青闸线', '123012012130', '林秋', '2016-04-13 15:25:52');
INSERT INTO `t_position` VALUES ('389', '29', '119.3980090000', '25.8979930000', '中国福建省福州市闽侯县青闸线', '123012012130', '林秋', '2016-04-13 15:27:23');
INSERT INTO `t_position` VALUES ('390', '29', '119.3979960000', '25.8980050000', '中国福建省福州市闽侯县青闸线', '123012012130', '林秋', '2016-04-13 15:30:07');
INSERT INTO `t_position` VALUES ('391', '29', '119.3979960000', '25.8980050000', '中国福建省福州市闽侯县青闸线', '123012012130', '林秋', '2016-04-13 15:41:08');
INSERT INTO `t_position` VALUES ('392', '29', '119.3980070000', '25.8980140000', '中国福建省福州市闽侯县青闸线', '123012012130', '林秋', '2016-04-13 15:46:03');
INSERT INTO `t_position` VALUES ('393', '29', '119.3980070000', '25.8980140000', '中国福建省福州市闽侯县青闸线', '123012012130', '林秋', '2016-04-13 15:47:11');
INSERT INTO `t_position` VALUES ('394', '29', '119.3980040000', '25.8979980000', '中国福建省福州市闽侯县青闸线', '123012012130', '林秋', '2016-04-13 15:49:27');
INSERT INTO `t_position` VALUES ('395', '29', '119.3980060000', '25.8979990000', '中国福建省福州市闽侯县青闸线', '123012012130', '林秋', '2016-04-13 15:51:01');
INSERT INTO `t_position` VALUES ('396', '29', '119.3980100000', '25.8979920000', '中国福建省福州市闽侯县青闸线', '123012012130', '林秋', '2016-04-13 15:56:58');
INSERT INTO `t_position` VALUES ('397', '29', '119.3980100000', '25.8979900000', '中国福建省福州市闽侯县青闸线', '123012012130', '林秋', '2016-04-13 15:58:40');
INSERT INTO `t_position` VALUES ('398', '29', '119.3980080000', '25.8979950000', '中国福建省福州市闽侯县青闸线', '123012012130', '林秋', '2016-04-13 17:53:57');
INSERT INTO `t_position` VALUES ('399', '29', '119.3980100000', '25.8979860000', '中国福建省福州市闽侯县青闸线', '123012012130', '林秋', '2016-04-13 20:24:23');
INSERT INTO `t_position` VALUES ('400', '29', '119.3980110000', '25.8980000000', '中国福建省福州市闽侯县青闸线', '123012012130', '林秋', '2016-04-15 15:00:35');
INSERT INTO `t_position` VALUES ('401', '29', '119.3980080000', '25.8979910000', '中国福建省福州市闽侯县青闸线', '123012012130', '林秋', '2016-04-15 15:29:58');
INSERT INTO `t_position` VALUES ('402', '29', '119.3980080000', '25.8979910000', '中国福建省福州市闽侯县青闸线', '123012012130', '林秋', '2016-04-15 15:31:10');
INSERT INTO `t_position` VALUES ('403', '29', '119.3980080000', '25.8979950000', '中国福建省福州市闽侯县青闸线', '123012012130', '林秋', '2016-04-15 15:34:17');
INSERT INTO `t_position` VALUES ('404', '29', '119.3980080000', '25.8979950000', '中国福建省福州市闽侯县青闸线', '123012012130', '林秋', '2016-04-15 15:36:06');
INSERT INTO `t_position` VALUES ('405', '29', '119.3980110000', '25.8979910000', '中国福建省福州市闽侯县青闸线', '123012012130', '林秋', '2016-04-15 15:41:36');
INSERT INTO `t_position` VALUES ('406', '29', '119.3980110000', '25.8979910000', '中国福建省福州市闽侯县青闸线', '123012012130', '林秋', '2016-04-15 15:44:34');
INSERT INTO `t_position` VALUES ('407', '29', '119.3980110000', '25.8979910000', '中国福建省福州市闽侯县青闸线', '123012012130', '林秋', '2016-04-15 15:45:03');
INSERT INTO `t_position` VALUES ('408', '29', '119.3980110000', '25.8979910000', '中国福建省福州市闽侯县青闸线', '123012012130', '林秋', '2016-04-15 15:46:49');
INSERT INTO `t_position` VALUES ('409', '29', '119.3980110000', '25.8979910000', '中国福建省福州市闽侯县青闸线', '123012012130', '林秋', '2016-04-15 15:52:48');
INSERT INTO `t_position` VALUES ('410', '26', '119.2184720000', '26.0272420000', '福建师范大学', '123012012131', '唐文明', '2016-04-15 16:03:16');
INSERT INTO `t_position` VALUES ('411', '26', '119.2187680000', '26.0273390000', '福建师范大学', '123012012131', '唐文明', '2016-04-15 16:04:03');
INSERT INTO `t_position` VALUES ('412', '26', '119.2192890000', '26.0274850000', '福建师范大学', '123012012131', '唐文明', '2016-04-15 16:04:37');
INSERT INTO `t_position` VALUES ('413', '26', '119.2195500000', '26.0272420000', '福建师范大学', '123012012131', '唐文明', '2016-04-15 16:05:16');
INSERT INTO `t_position` VALUES ('414', '26', '119.2196950000', '26.0267790000', '福建师范大学', '123012012131', '唐文明', '2016-04-15 16:06:23');
INSERT INTO `t_position` VALUES ('415', '26', '119.2202690000', '26.0269660000', '福建师范大学', '123012012131', '唐文明', '2016-04-15 16:06:51');
INSERT INTO `t_position` VALUES ('416', '29', '119.3979600000', '25.8980120000', '中国福建省福州市闽侯县青闸线', '123012012130', '林秋', '2016-04-15 17:43:20');
INSERT INTO `t_position` VALUES ('417', '29', '119.3980110000', '25.8979860000', '中国福建省福州市闽侯县青闸线', '123012012130', '林秋', '2016-04-15 21:28:31');
INSERT INTO `t_position` VALUES ('418', '29', '119.3978650000', '25.8980100000', '中国福建省福州市闽侯县青闸线', '123012012130', '林秋', '2016-04-16 05:36:17');
INSERT INTO `t_position` VALUES ('419', '29', '119.3978780000', '25.8980070000', '中国福建省福州市闽侯县青闸线', '123012012130', '林秋', '2016-04-16 05:46:07');
INSERT INTO `t_position` VALUES ('420', '29', '119.2196460000', '26.0277650000', '中国福建省福州市闽侯县仓山大道', '123012012130', '林秋', '2016-07-21 11:20:52');
INSERT INTO `t_position` VALUES ('421', '29', '119.2180220000', '26.0262150000', '中国福建省福州市闽侯县学府南路', '123012012130', '林秋', '2016-07-21 11:27:58');
INSERT INTO `t_position` VALUES ('422', '29', '119.2180220000', '26.0262150000', '中国福建省福州市闽侯县学府南路', '123012012130', '林秋', '2016-07-21 11:37:08');
INSERT INTO `t_position` VALUES ('423', '29', '119.2196440000', '26.0277610000', '中国福建省福州市闽侯县仓山大道', '123012012130', '林秋', '2016-07-21 11:47:21');
INSERT INTO `t_position` VALUES ('424', '30', '119.2196490000', '26.0277600000', '中国福建省福州市闽侯县仓山大道', '123012014100', '娴静', '2016-07-21 11:58:43');
INSERT INTO `t_position` VALUES ('425', '30', '119.2196310000', '26.0277640000', '中国福建省福州市闽侯县仓山大道', '123012014100', '娴静', '2016-07-21 12:02:48');
INSERT INTO `t_position` VALUES ('426', '30', '119.2196460000', '26.0277650000', '中国福建省福州市闽侯县仓山大道', '123012014100', '娴静', '2016-07-21 12:07:47');
INSERT INTO `t_position` VALUES ('427', '29', '119.2180220000', '26.0262150000', '中国福建省福州市闽侯县学府南路', '123012012130', '林秋', '2016-07-21 12:12:29');
INSERT INTO `t_position` VALUES ('428', '26', '119.2180220000', '26.0262150000', '中国福建省福州市闽侯县学府南路', '123012014039', '聪狗', '2016-07-21 12:15:48');
INSERT INTO `t_position` VALUES ('429', '29', '119.2196430000', '26.0277580000', '中国福建省福州市闽侯县仓山大道', '123012012130', '林秋', '2016-07-21 14:55:07');
INSERT INTO `t_position` VALUES ('430', '29', '119.2196500000', '26.0277610000', '中国福建省福州市闽侯县仓山大道', '123012012130', '林秋', '2016-07-21 14:59:10');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1234567897 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('2', 'heiheiaaaaaaaaaaaaa', null, null);
INSERT INTO `user` VALUES ('3', 'heiheiaaaaaaaaaaaaa', null, null);
INSERT INTO `user` VALUES ('4', 'heiheiaaaaaaaaaaaaa1111', null, null);
INSERT INTO `user` VALUES ('6', 'heiheiaaaaaaaaaaaaa1111', null, null);
INSERT INTO `user` VALUES ('7', 'heiheiaaaaaaaaaaaaa1111', null, null);
INSERT INTO `user` VALUES ('9', 'name222', '222', null);
INSERT INTO `user` VALUES ('10', 'heiheservice', null, null);
INSERT INTO `user` VALUES ('1234567891', 'heiheservice2222', '2', null);
INSERT INTO `user` VALUES ('1234567892', 'heiheservice2222', '2', null);
INSERT INTO `user` VALUES ('1234567893', 'heiheservice2222', '2', null);
INSERT INTO `user` VALUES ('1234567894', 'heiheservice2222', '2', null);
INSERT INTO `user` VALUES ('1234567895', 'heiheservice2222', '2', '2016-03-05 00:33:49');
INSERT INTO `user` VALUES ('1234567896', 'dadasd', '2', '2016-03-05 00:36:43');
