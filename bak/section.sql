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

 Date: 17/12/2018 21:40:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for section
-- ----------------------------
DROP TABLE IF EXISTS `section`;
CREATE TABLE `section` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '模块id',
  `name` varchar(255) DEFAULT NULL COMMENT '模块名称',
  `duration` int(6) DEFAULT NULL COMMENT '时间限制',
  `total_score` float(255,0) DEFAULT NULL COMMENT '总分',
  `every_score` float(255,0) DEFAULT NULL COMMENT '每题得分',
  `deduct` tinyint(1) DEFAULT NULL COMMENT '倒扣分',
  `type` varchar(255) DEFAULT NULL COMMENT '模块类型',
  `group` int(4) DEFAULT NULL COMMENT '每组显示数量',
  `qids` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '试题id列表',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE current_timestamp() COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

SET FOREIGN_KEY_CHECKS = 1;
