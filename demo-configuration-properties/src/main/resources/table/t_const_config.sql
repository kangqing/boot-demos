/*
 Navicat Premium Data Transfer

 Source Server         : local80
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : demo

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 16/05/2020 21:33:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_const_config
-- ----------------------------
DROP TABLE IF EXISTS `t_const_config`;
CREATE TABLE `t_const_config`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `config_key` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '配置项',
  `config_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '配置值',
  `default_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '默认值',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '常量配置表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_const_config
-- ----------------------------
INSERT INTO `t_const_config` VALUES (1, 'com.yunqing.configA', '1234', '1234', 'configA', '2020-05-16 21:08:55', '2020-05-16 21:27:45');
INSERT INTO `t_const_config` VALUES (2, 'com.yunqing.configB', '5678', '5678', 'configB', '2020-05-16 21:09:19', '2020-05-16 21:27:52');

SET FOREIGN_KEY_CHECKS = 1;
