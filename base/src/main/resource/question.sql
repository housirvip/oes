/*
 Navicat Premium Data Transfer

 Source Server         : mariadb
 Source Server Type    : MariaDB
 Source Server Version : 100309
 Source Host           : localhost:3306
 Source Schema         : oes

 Target Server Type    : MariaDB
 Target Server Version : 100309
 File Encoding         : 65001

 Date: 17/12/2018 21:40:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '题目id',
  `type` varchar(255) DEFAULT NULL COMMENT '题目类型',
  `stem` text DEFAULT NULL COMMENT '题干',
  `pic` varchar(255) DEFAULT NULL COMMENT '题干图片',
  `answer` varchar(255) DEFAULT NULL COMMENT '选择题答案',
  `answer_full` text DEFAULT NULL COMMENT '简答题答案',
  `selections` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '选项',
  `explain` text DEFAULT NULL COMMENT '解析',
  `exp_pic` varchar(255) DEFAULT NULL COMMENT '解析图片',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

SET FOREIGN_KEY_CHECKS = 1;
