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

 Date: 07/01/2019 22:17:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for exam
-- ----------------------------
DROP TABLE IF EXISTS `exam`;
CREATE TABLE `exam` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '记录id',
  `uid` int(11) NOT NULL COMMENT '用户id',
  `pid` int(11) DEFAULT NULL COMMENT '试卷id',
  `name` varchar(255) DEFAULT NULL COMMENT '试卷名称',
  `score` float(255,2) NOT NULL DEFAULT 0.00 COMMENT '考试得分',
  `duration` int(6) DEFAULT NULL COMMENT '考试时长',
  `user_answer` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '考生作答',
  `section_score` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '模块得分',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `uid` int(11) DEFAULT NULL COMMENT '用户id',
  `product_name` varchar(255) DEFAULT NULL COMMENT '商品名',
  `product_id` int(11) DEFAULT NULL COMMENT '商品id',
  `price` int(11) DEFAULT NULL COMMENT '商品价格',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
  `total_score` float(255,2) DEFAULT NULL COMMENT '试卷总分',
  `pass_score` float(255,2) DEFAULT NULL COMMENT '及格分数',
  `avg_score` float(255,2) DEFAULT NULL COMMENT '平均分数',
  `duration` int(6) DEFAULT NULL COMMENT '时间限制',
  `description` varchar(255) DEFAULT NULL COMMENT '试卷描述',
  `sids` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '模块id列表',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE current_timestamp() COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `enable` tinyint(1) DEFAULT 0 COMMENT '启用停用',
  `price` int(11) NOT NULL DEFAULT 0 COMMENT '价格',
  `name` varchar(255) DEFAULT NULL COMMENT '商品名',
  `description` varchar(255) DEFAULT NULL COMMENT '商品描述',
  `min_level` int(4) DEFAULT NULL COMMENT '最低购买等级',
  `max_level` int(4) DEFAULT NULL COMMENT '最高购买等级',
  `level_up` int(4) DEFAULT NULL COMMENT '升级点数',
  `level_to` int(4) DEFAULT NULL COMMENT '升级至',
  `group_to` varchar(255) DEFAULT NULL COMMENT '用户组变更',
  `group_limit` varchar(255) DEFAULT NULL COMMENT '用户组购买限制',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE current_timestamp() COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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

-- ----------------------------
-- Table structure for recharge
-- ----------------------------
DROP TABLE IF EXISTS `recharge`;
CREATE TABLE `recharge` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '记录id',
  `uid` int(11) DEFAULT NULL COMMENT '用户id',
  `coin` int(11) NOT NULL DEFAULT 0 COMMENT '充值数量',
  `name` varchar(255) DEFAULT NULL COMMENT '充值名称',
  `status` varchar(255) DEFAULT NULL COMMENT '状态',
  `qr_id` varchar(255) DEFAULT NULL COMMENT '二维码id',
  `qr_pay_id` varchar(255) DEFAULT NULL COMMENT '二维码付款id',
  `tid` varchar(255) DEFAULT NULL COMMENT '交易id',
  `pay_type` varchar(255) DEFAULT NULL COMMENT '付款类型',
  `pay_type_code` int(8) DEFAULT NULL COMMENT '付款类型代码',
  `yz_msg` longtext DEFAULT NULL COMMENT '有赞存根',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for section
-- ----------------------------
DROP TABLE IF EXISTS `section`;
CREATE TABLE `section` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '模块id',
  `name` varchar(255) DEFAULT NULL COMMENT '模块名称',
  `duration` int(6) DEFAULT NULL COMMENT '时间限制',
  `total_score` float(255,2) DEFAULT NULL COMMENT '总分',
  `every_score` float(255,2) DEFAULT NULL COMMENT '每题得分',
  `deduct` tinyint(1) DEFAULT NULL COMMENT '倒扣分',
  `type` varchar(255) DEFAULT NULL COMMENT '模块类型',
  `group` int(4) DEFAULT NULL COMMENT '每组显示数量',
  `qids` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '试题id列表',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE current_timestamp() COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(16) DEFAULT NULL COMMENT '用户名',
  `email` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(16) DEFAULT NULL COMMENT '手机号码',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `role` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '角色',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE current_timestamp() COMMENT '更新时间',
  `enable` tinyint(1) DEFAULT 1 COMMENT '可用状态',
  `level` int(4) NOT NULL DEFAULT 0 COMMENT '用户等级',
  `group` varchar(64) DEFAULT NULL COMMENT '所属组',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户详情id',
  `uid` int(11) NOT NULL COMMENT '用户id',
  `nickname` varchar(64) DEFAULT NULL COMMENT '昵称',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `gender` varchar(255) DEFAULT NULL COMMENT '性别',
  `school` varchar(255) DEFAULT NULL COMMENT '学校',
  `major` varchar(255) DEFAULT NULL COMMENT '专业',
  `province` varchar(255) DEFAULT NULL COMMENT '省份',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE current_timestamp() COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for wallet
-- ----------------------------
DROP TABLE IF EXISTS `wallet`;
CREATE TABLE `wallet` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '钱包id',
  `uid` int(11) NOT NULL COMMENT '用户id',
  `coin` int(11) NOT NULL DEFAULT 0 COMMENT '钱包余额',
  `freeze` int(11) NOT NULL DEFAULT 0 COMMENT '冻结金额',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE current_timestamp() COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

SET FOREIGN_KEY_CHECKS = 1;
