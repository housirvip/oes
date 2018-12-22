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

 Date: 23/12/2018 00:00:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for paper
-- ----------------------------
DROP TABLE IF EXISTS `paper`;
CREATE TABLE `paper` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '试卷id',
  `name` varchar(255) DEFAULT NULL COMMENT '试卷名称',
  `type` varchar(255) DEFAULT NULL COMMENT '试卷类型',
  `group` varchar(255) DEFAULT NULL COMMENT '试卷分组',
  `enable` tinyint(1) NOT NULL DEFAULT 0 COMMENT '启用停用',
  `min_level` int(4) NOT NULL DEFAULT 0 COMMENT '限制等级',
  `total_score` float(255,0) DEFAULT NULL COMMENT '试卷总分',
  `pass_score` float(255,0) DEFAULT NULL COMMENT '及格分数',
  `avg_score` float(255,0) DEFAULT NULL COMMENT '平均分数',
  `duration` int(6) DEFAULT NULL COMMENT '时间限制',
  `description` varchar(255) DEFAULT NULL COMMENT '试卷描述',
  `sids` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '模块id列表',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE current_timestamp() COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

SET FOREIGN_KEY_CHECKS = 1;
