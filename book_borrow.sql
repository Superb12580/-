/*
Navicat MySQL Data Transfer

Source Server         : KD
Source Server Version : 50562
Source Host           : localhost:3306
Source Database       : zua

Target Server Type    : MYSQL
Target Server Version : 50562
File Encoding         : 65001

Date: 2021-02-06 10:41:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for book_borrow
-- ----------------------------
DROP TABLE IF EXISTS `book_borrow`;
CREATE TABLE `book_borrow` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '记录id',
  `book_id` bigint(20) DEFAULT NULL COMMENT '图书id',
  `borrower_name` varchar(35) DEFAULT NULL COMMENT '借阅人姓名',
  `borrower_phone` varchar(35) DEFAULT NULL COMMENT '借阅人手机号',
  `begin_date` varchar(35) DEFAULT NULL COMMENT '借阅时间',
  `end_date` varchar(35) DEFAULT NULL COMMENT '计划还书时间',
  `real_date` varchar(35) DEFAULT '0' COMMENT '实际还书时间',
  `latefee` bigint(20) DEFAULT '0' COMMENT '滞纳金',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of book_borrow
-- ----------------------------
INSERT INTO `book_borrow` VALUES ('48', '1', '陈苏凡', '15896735842', '2020-12-03', '2020-12-04', '2020-12-30', '26');
INSERT INTO `book_borrow` VALUES ('49', '2', '杜兰特', '15896735842', '2020-12-03', '2020-12-05', '2020-12-30', '25');
INSERT INTO `book_borrow` VALUES ('50', '1', '翁炳昊', '15789632143', '2020-12-03', '2020-12-12', '0', '0');
INSERT INTO `book_borrow` VALUES ('51', '15', '张三', '15896735842', '2021-01-01', '2021-01-05', '2021-01-07', '28');
INSERT INTO `book_borrow` VALUES ('52', '15', '张三', '15896735842', '2021-01-01', '2021-01-05', '2021-01-07', '2');
INSERT INTO `book_borrow` VALUES ('53', '1', '张三', '15896735842', '2021-01-01', '2021-01-04', '2021-01-07', '38');
INSERT INTO `book_borrow` VALUES ('54', '2', '张三', '15896735842', '2021-01-06', '2021-01-08', '2021-01-07', '0');
INSERT INTO `book_borrow` VALUES ('55', '2', '张三', '15896735842', '2021-01-06', '2021-01-07', '0', '0');

-- ----------------------------
-- Table structure for book_info
-- ----------------------------
DROP TABLE IF EXISTS `book_info`;
CREATE TABLE `book_info` (
  `book_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '图书id',
  `book_type` int(11) DEFAULT NULL COMMENT '图书类别',
  `book_name` varchar(35) DEFAULT NULL COMMENT '图书名称',
  `author` varchar(35) DEFAULT NULL COMMENT '作者',
  `cbs` varchar(35) DEFAULT NULL COMMENT '出版社',
  `cbsj` varchar(35) DEFAULT NULL COMMENT '出版时间',
  `price` double DEFAULT NULL COMMENT '单价',
  `stock` int(11) DEFAULT NULL COMMENT '库存',
  `deleted` tinyint(4) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`book_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of book_info
-- ----------------------------
INSERT INTO `book_info` VALUES ('1', '1', '小黄书', '李天然', '黄河出版社', '2020-11-04', '789', '1', '0');
INSERT INTO `book_info` VALUES ('2', '1', '葵花宝典', '李天然', '黄河出版社', '2020-02-26', '233', '58', '0');
INSERT INTO `book_info` VALUES ('3', '1', '红楼梦', '李天然', '黄河出版社', '2020-11-04', '3', '0', '0');
INSERT INTO `book_info` VALUES ('4', '2', '花里胡哨', '李天然', '黄河出版社', '2020-11-04', '4', '14', '0');
INSERT INTO `book_info` VALUES ('14', '5', '倚天屠龙记', '李天然', '黄河不出版', '2020-11-04', '1', '10', '0');
INSERT INTO `book_info` VALUES ('15', '4', '微信读书', '李天然', '黄河出版社', '2020-11-04', '1', '14', '0');
INSERT INTO `book_info` VALUES ('20', '3', '图书名称 图书名称', '作者', '出版社', '2021-01-05', '12', '5', '0');

-- ----------------------------
-- Table structure for book_type
-- ----------------------------
DROP TABLE IF EXISTS `book_type`;
CREATE TABLE `book_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '类型id',
  `type_name` varchar(35) DEFAULT NULL COMMENT '图书类型',
  `deleted` tinyint(4) DEFAULT '0' COMMENT '逻辑删除',
  `czr` varchar(255) DEFAULT NULL COMMENT '提交',
  `czsj` varchar(255) DEFAULT NULL COMMENT '提交时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of book_type
-- ----------------------------
INSERT INTO `book_type` VALUES ('1', '教育', '1', 'admin', '2020-12-30');
INSERT INTO `book_type` VALUES ('2', '科技', '0', 'admin', '2020-11-04');
INSERT INTO `book_type` VALUES ('3', '文学', '0', 'admin', '2020-11-04');
INSERT INTO `book_type` VALUES ('4', '小说', '0', 'admin', '2021-01-07');
INSERT INTO `book_type` VALUES ('5', '漫画', '0', 'admin', '2020-11-04');
