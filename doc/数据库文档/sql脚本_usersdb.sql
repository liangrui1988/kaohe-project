/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost:6688
 Source Schema         : usersdb

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 16/02/2021 18:15:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL COMMENT '名称',
  `value` varchar(100) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL COMMENT '值',
  `type` varchar(100) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL COMMENT '类型',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '父id',
  `status` int(11) NULL DEFAULT 0 COMMENT '状态',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `create_by_id` int(11) NULL DEFAULT NULL COMMENT '创建者',
  `update_by_id` int(11) NULL DEFAULT NULL COMMENT '更新者',
  `remake` varchar(200) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `dict_Index_type`(`type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = gbk COLLATE = gbk_chinese_ci COMMENT = '部门' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL COMMENT '菜单',
  `type` char(1) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL COMMENT '类型',
  `ip` varchar(100) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL COMMENT '操作ip',
  `method` varchar(50) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT '0' COMMENT '请求方式',
  `uri` varchar(200) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL COMMENT 'uri',
  `parameters` varchar(200) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL COMMENT '参数',
  `agent` varchar(300) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL COMMENT '代理',
  `create_by_id` int(11) NULL DEFAULT NULL COMMENT '创建者',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `remake` varchar(300) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL COMMENT '备注',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
  `result` varchar(500) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `Index_create_time`(`create_time`) USING BTREE,
  INDEX `Index_create_user`(`create_by_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = gbk COLLATE = gbk_chinese_ci COMMENT = '系统日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES (1, '', NULL, '127.0.0.1', 'GET', '/zdata/', '', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36', 1, '2021-02-08 11:07:14', NULL, NULL, NULL);
INSERT INTO `sys_log` VALUES (2, '', NULL, '127.0.0.1', 'GET', '/zdata/sys/role/indexRoleMark', 'groupId=2&status=0', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36', 1, '2021-02-08 11:07:15', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` varchar(200) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL,
  `name` varchar(200) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL COMMENT '名称',
  `types` varchar(200) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  `sort_no` int(11) NULL DEFAULT NULL,
  `parent_id` varchar(200) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  `parent_ids` varchar(200) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  `permission` varchar(200) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL COMMENT '权限',
  `status` int(11) NULL DEFAULT 0 COMMENT '状态',
  `icon` varchar(200) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL COMMENT '图标',
  `href` varchar(200) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL COMMENT '链接',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `create_by_id` int(11) NULL DEFAULT NULL COMMENT '创建者',
  `update_by_id` int(11) NULL DEFAULT NULL COMMENT '更新者',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `menu_Index_create`(`create_time`) USING BTREE,
  INDEX `menu_Index_sort`(`sort_no`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = gbk COLLATE = gbk_chinese_ci COMMENT = '菜单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('index', '首页', NULL, 1, '', NULL, NULL, 0, NULL, 'ShowIndex', '2021-02-08 11:05:10', '2021-02-15 15:10:58', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('manager', '管理页', NULL, 1, '', NULL, NULL, 0, NULL, 'ShowManager', '2021-02-08 11:05:10', '2021-02-15 15:11:08', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('manager:showUpdate', '修改', NULL, 2, 'manager', NULL, NULL, 1, NULL, 'ShowUpdateUser', '2021-02-08 11:05:10', '2021-02-15 15:12:11', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('manager:update', '修改', NULL, 1, 'manager', NULL, NULL, 1, NULL, 'UpdateUser', '2021-02-08 11:05:10', '2021-02-15 15:12:07', NULL, NULL);

-- ----------------------------
-- Table structure for sys_parameter
-- ----------------------------
DROP TABLE IF EXISTS `sys_parameter`;
CREATE TABLE `sys_parameter`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `keyx` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `value` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sort` int(11) NULL DEFAULT NULL,
  `status` tinyint(4) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `create_by_id` int(11) NULL DEFAULT NULL,
  `update_by_id` int(11) NULL DEFAULT NULL,
  `remake` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统参数表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL COMMENT '角色名称',
  `status` int(11) NULL DEFAULT 0 COMMENT '状态',
  `types` varchar(100) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL COMMENT '类型',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `create_by_id` int(11) NULL DEFAULT NULL COMMENT '创建者',
  `update_by_id` int(11) NULL DEFAULT NULL COMMENT '更新者',
  `group_id` int(11) NULL DEFAULT 1 COMMENT '角色组',
  `remake` varchar(200) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `role_name_unique`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = gbk COLLATE = gbk_chinese_ci COMMENT = '角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '系统管理', 0, '1', '2021-02-08 11:05:10', '2021-02-08 11:05:10', 1, NULL, 1, 'remake');
INSERT INTO `sys_role` VALUES (2, '普通角色', 0, NULL, '2021-02-08 11:05:11', '2021-02-09 16:10:47', NULL, NULL, 1, 'remake');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NULL DEFAULT NULL,
  `menu_id` varchar(200) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = gbk COLLATE = gbk_chinese_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (1, 1, 'manager');
INSERT INTO `sys_role_menu` VALUES (2, 1, 'manager:showUpdate');
INSERT INTO `sys_role_menu` VALUES (3, 1, 'manager:update');
INSERT INTO `sys_role_menu` VALUES (4, 1, 'index');
INSERT INTO `sys_role_menu` VALUES (5, 2, 'index');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `department_id` int(11) NULL DEFAULT NULL COMMENT '部门id',
  `user_name` varchar(20) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL COMMENT '登陆名',
  `password` varchar(50) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL COMMENT '密码',
  `full_name` varchar(50) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL COMMENT '姓名',
  `type` varchar(100) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL COMMENT '类型',
  `email` varchar(100) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL COMMENT '邮箱',
  `status` int(11) NULL DEFAULT 0 COMMENT '状态',
  `create_by_id` int(11) NULL DEFAULT NULL COMMENT '创建者',
  `update_by_id` int(11) NULL DEFAULT NULL COMMENT '更新者',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `remake` varchar(200) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL COMMENT '备注',
  `last_login_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后登陆时间',
  `code` varchar(50) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL COMMENT '邮箱验证码',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_name_unique`(`user_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = gbk COLLATE = gbk_chinese_ci COMMENT = '用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 1, 'admin', 'g10lna3646il56liig59g057r20r883g', '林冲', '1', '1067165280@qq.com', 0, 1, NULL, '2021-02-08 11:05:12', '2021-02-15 22:59:59', '系统初始用户', '2021-02-08 11:05:11', NULL);
INSERT INTO `sys_user` VALUES (4, NULL, 'test1', 'pass', NULL, NULL, 'ruiliang@smartbi.com', 0, NULL, NULL, '2021-02-09 16:45:32', '2021-02-09 16:45:32', NULL, '2021-02-09 16:45:32', NULL);
INSERT INTO `sys_user` VALUES (5, NULL, 'liangrui', '123456', NULL, NULL, '1067165280@qq.com', 1, NULL, NULL, '2021-02-14 14:53:32', '2021-02-14 19:43:30', NULL, '2021-02-14 14:53:32', NULL);
INSERT INTO `sys_user` VALUES (6, NULL, 'abc', 'g10lna3646il56liig59g057r20r883g', NULL, NULL, 'direport@mozat.com', 1, NULL, NULL, '2021-02-15 13:53:35', '2021-02-15 19:01:34', NULL, '2021-02-15 13:53:35', NULL);
INSERT INTO `sys_user` VALUES (8, NULL, 'test2', 'g10lna3646il56liig59g057r20r883g', NULL, NULL, '1067165280@qq.com', 0, NULL, NULL, '2021-02-15 16:46:22', '2021-02-15 19:01:26', NULL, '2021-02-15 16:46:22', NULL);
INSERT INTO `sys_user` VALUES (9, NULL, 'test3', 'g10lna3646il56liig59g057r20r883g', NULL, NULL, '1067165280@qq.com', 0, NULL, NULL, '2021-02-15 19:00:52', '2021-02-15 19:00:52', NULL, '2021-02-15 19:00:52', NULL);
INSERT INTO `sys_user` VALUES (13, NULL, 'test5', 'g10lna3646il56liig59g057r20r883g', NULL, NULL, '1067165280@qq.com', 0, NULL, NULL, '2021-02-15 19:07:47', '2021-02-15 19:07:47', NULL, '2021-02-15 19:07:47', NULL);
INSERT INTO `sys_user` VALUES (14, NULL, 'test6', 'g10lna3646il56liig59g057r20r883g', NULL, NULL, '1067165280@qq.com', 0, NULL, NULL, '2021-02-15 22:51:08', '2021-02-15 22:56:10', NULL, '2021-02-15 22:51:08', '6a9819a9rr045474552n646gg3rnlgg1');
INSERT INTO `sys_user` VALUES (15, NULL, 'test7', 'g10lna3646il56liig59g057r20r883g', NULL, NULL, '1067165280@qq.com', -1, NULL, NULL, '2021-02-15 22:56:44', '2021-02-15 22:56:44', NULL, '2021-02-15 22:56:44', '4rngl9g356n998l40i2848i5lgra9g66');
INSERT INTO `sys_user` VALUES (16, NULL, 'test8', 'g10lna3646il56liig59g057r20r883g', NULL, NULL, '1067165280@qq.com', 0, NULL, NULL, '2021-02-15 22:57:45', '2021-02-15 22:58:41', NULL, '2021-02-15 22:57:45', 'g5a8rg2nr6i3n21i01i78l703923rgia');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL,
  `role_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = gbk COLLATE = gbk_chinese_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1, 1);
INSERT INTO `sys_user_role` VALUES (2, 4, 2);
INSERT INTO `sys_user_role` VALUES (3, 5, 2);
INSERT INTO `sys_user_role` VALUES (4, 6, 2);
INSERT INTO `sys_user_role` VALUES (5, 8, 2);
INSERT INTO `sys_user_role` VALUES (6, 9, 2);
INSERT INTO `sys_user_role` VALUES (7, 13, 2);
INSERT INTO `sys_user_role` VALUES (8, 14, 2);
INSERT INTO `sys_user_role` VALUES (9, 15, 2);
INSERT INTO `sys_user_role` VALUES (10, 16, 2);

SET FOREIGN_KEY_CHECKS = 1;
