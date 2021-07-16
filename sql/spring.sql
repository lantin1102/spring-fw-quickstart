/*
 Navicat Premium Data Transfer

 Source Server         : windows-mysql
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : 127.0.0.1:3306
 Source Schema         : spring

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 16/07/2021 21:41:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `username` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户姓名',
  `money` double NOT NULL COMMENT '账户余额',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户账户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES (1, 'zhangsan', 1000);
INSERT INTO `account` VALUES (2, '李四', 1200);
INSERT INTO `account` VALUES (3, 'wangwu', 10000);
INSERT INTO `account` VALUES (4, 'wangwu11', 10000);
INSERT INTO `account` VALUES (5, 'wangwu1122', 10000);
INSERT INTO `account` VALUES (8, 'wangwu3', 10000);
INSERT INTO `account` VALUES (9, 'wangwu4', 10000);
INSERT INTO `account` VALUES (10, 'wangwu5', 10000);
INSERT INTO `account` VALUES (11, 'wangwu6', 10000);
INSERT INTO `account` VALUES (12, 'wangwu7', 10000);
INSERT INTO `account` VALUES (17, '111111111111111', 0);

SET FOREIGN_KEY_CHECKS = 1;
