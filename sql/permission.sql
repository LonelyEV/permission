/*
Navicat MySQL Data Transfer

Source Server         : local-server
Source Server Version : 50631
Source Host           : localhost:3306
Source Database       : permission

Target Server Type    : MYSQL
Target Server Version : 50631
File Encoding         : 65001

Date: 2018-07-09 17:18:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- CREATE DATABASE  `permission` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;


-- ----------------------------
-- Table structure for tbl_sys_acl
-- ----------------------------
DROP TABLE IF EXISTS `tbl_sys_acl`;
CREATE TABLE `tbl_sys_acl` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `code` varchar(20) NOT NULL DEFAULT '' COMMENT '权限码',
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '权限名称',
  `acl_module_id` int(11) NOT NULL DEFAULT '0' COMMENT '权限所在的权限模块id',
  `url` varchar(100) NOT NULL DEFAULT '' COMMENT '请求的url, 可以填正则表达式',
  `type` int(11) NOT NULL DEFAULT '3' COMMENT '类型，1：菜单，2：按钮，3：其他',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态，1：正常，0：冻结',
  `seq` int(11) NOT NULL DEFAULT '0' COMMENT '权限在当前模块下的顺序，由小到大',
  `remark` varchar(200) DEFAULT '' COMMENT '备注',
  `operator` varchar(20) NOT NULL DEFAULT '' COMMENT '操作者',
  `operate_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
  `operate_ip` varchar(20) NOT NULL DEFAULT '' COMMENT '最后一个更新者的ip地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tbl_sys_acl
-- ----------------------------
INSERT INTO `tbl_sys_acl` VALUES ('1', '20171015095130_26', '进入产品管理界面', '1', '/sys/product/product.page', '1', '1', '1', '', 'Admin', '2017-10-15 09:51:30', '0:0:0:0:0:0:0:1');
INSERT INTO `tbl_sys_acl` VALUES ('2', '20171015095322_14', '查询产品列表', '1', '/sys/product/page.json', '2', '1', '2', '', 'Admin', '2017-10-15 09:53:22', '0:0:0:0:0:0:0:1');
INSERT INTO `tbl_sys_acl` VALUES ('3', '20171015095350_69', '产品上架', '1', '/sys/product/online.json', '2', '1', '3', '', 'Admin', '2017-10-15 09:53:51', '0:0:0:0:0:0:0:1');
INSERT INTO `tbl_sys_acl` VALUES ('4', '20171015095420_7', '产品下架', '1', '/sys/product/offline.json', '2', '1', '4', '', 'Admin', '2017-10-15 10:11:28', '0:0:0:0:0:0:0:1');
INSERT INTO `tbl_sys_acl` VALUES ('5', '20171015212626_63', '进入订单页', '2', '/sys/order/order.page', '1', '1', '1', '', 'Admin', '2017-10-15 21:26:27', '0:0:0:0:0:0:0:1');
INSERT INTO `tbl_sys_acl` VALUES ('6', '20171015212657_12', '查询订单列表', '2', '/sys/order/list.json', '2', '1', '2', '', 'Admin', '2017-10-15 21:26:57', '0:0:0:0:0:0:0:1');
INSERT INTO `tbl_sys_acl` VALUES ('7', '20171015212907_36', '进入权限管理页', '7', '/sys/aclModule/acl.page', '1', '1', '1', '', 'Admin', '2017-10-15 21:29:07', '0:0:0:0:0:0:0:1');
INSERT INTO `tbl_sys_acl` VALUES ('8', '20171015212938_27', '进入角色管理页', '8', '/sys/role/role.page', '1', '1', '1', '', 'Admin', '2017-10-16 17:49:38', '0:0:0:0:0:0:0:1');
INSERT INTO `tbl_sys_acl` VALUES ('9', '20171015213009_0', '进入用户管理页', '9', '/sys/dept/dept.page', '1', '1', '1', '', 'Admin', '2017-10-15 21:30:09', '0:0:0:0:0:0:0:1');
INSERT INTO `tbl_sys_acl` VALUES ('10', '20171016230429_8', '进入权限更新记录页面', '11', '/sys/log/log.page', '1', '1', '1', '', 'Admin', '2017-10-16 23:04:49', '0:0:0:0:0:0:0:1');
INSERT INTO `tbl_sys_acl` VALUES ('11', '20180705163942_0', '测试测试12', '2', '/adadad/ada.json', '1', '1', '1', '村上春树', 'Admin', '2018-07-06 09:34:24', '127.0.0.1');
INSERT INTO `tbl_sys_acl` VALUES ('12', '20180709143151_0', '我的免费隧道11', '1', '/adadad/ada.json', '1', '1', '1', 'aaa', 'Admin', '2018-07-09 14:31:51', '127.0.0.1');

-- ----------------------------
-- Table structure for tbl_sys_acl_module
-- ----------------------------
DROP TABLE IF EXISTS `tbl_sys_acl_module`;
CREATE TABLE `tbl_sys_acl_module` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限模块id',
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '权限模块名称',
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '上级权限模块id',
  `level` varchar(200) NOT NULL DEFAULT '' COMMENT '权限模块层级',
  `seq` int(11) NOT NULL DEFAULT '0' COMMENT '权限模块在当前层级下的顺序，由小到大',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态，1：正常，0：冻结',
  `remark` varchar(200) DEFAULT '' COMMENT '备注',
  `operator` varchar(20) NOT NULL DEFAULT '' COMMENT '操作者',
  `operate_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次操作时间',
  `operate_ip` varchar(20) NOT NULL DEFAULT '' COMMENT '最后一次更新操作者的ip地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tbl_sys_acl_module
-- ----------------------------
INSERT INTO `tbl_sys_acl_module` VALUES ('1', '产品管理', '0', '0', '1', '1', 'product', 'Admin', '2017-10-14 21:13:15', '0:0:0:0:0:0:0:1');
INSERT INTO `tbl_sys_acl_module` VALUES ('2', '订单管理', '0', '0', '2', '1', '', 'Admin', '2017-10-14 20:17:11', '0:0:0:0:0:0:0:1');
INSERT INTO `tbl_sys_acl_module` VALUES ('3', '公告管理', '0', '0', '3', '1', '', 'Admin', '2017-10-14 20:17:21', '0:0:0:0:0:0:0:1');
INSERT INTO `tbl_sys_acl_module` VALUES ('4', '出售中产品管理', '1', '0.1', '2', '1', '', 'Admin', '2018-07-05 15:01:05', '127.0.0.1');
INSERT INTO `tbl_sys_acl_module` VALUES ('5', '下架产品管理', '1', '0.1', '1', '1', '', 'Admin', '2018-07-05 15:01:15', '127.0.0.1');
INSERT INTO `tbl_sys_acl_module` VALUES ('6', '权限管理', '0', '0', '4', '1', '', 'Admin', '2017-10-15 21:27:37', '0:0:0:0:0:0:0:1');
INSERT INTO `tbl_sys_acl_module` VALUES ('7', '权限管理', '6', '0.6', '1', '1', '', 'Admin', '2017-10-15 21:27:57', '0:0:0:0:0:0:0:1');
INSERT INTO `tbl_sys_acl_module` VALUES ('8', '角色管理', '6', '0.6', '2', '1', '', 'Admin', '2017-10-15 21:28:22', '0:0:0:0:0:0:0:1');
INSERT INTO `tbl_sys_acl_module` VALUES ('9', '用户管理', '6', '0.6', '2', '1', '', 'Admin', '2017-10-15 21:28:36', '0:0:0:0:0:0:0:1');
INSERT INTO `tbl_sys_acl_module` VALUES ('11', '权限更新记录管理', '6', '0.6', '4', '1', '', 'Admin', '2017-10-16 23:04:07', '0:0:0:0:0:0:0:1');
INSERT INTO `tbl_sys_acl_module` VALUES ('12', '测试2', '2', '0.2', '1', '1', '测试', 'Admin', '2018-07-06 09:34:02', '127.0.0.1');
INSERT INTO `tbl_sys_acl_module` VALUES ('14', '我的免费隧道112244', '1', '0.1', '1', '1', null, 'Admin', '2018-07-09 17:13:34', '127.0.0.1');

-- ----------------------------
-- Table structure for tbl_sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `tbl_sys_dept`;
CREATE TABLE `tbl_sys_dept` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '部门名称',
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '上级部门id',
  `level` varchar(200) NOT NULL DEFAULT '' COMMENT '部门层级',
  `seq` int(11) NOT NULL DEFAULT '0' COMMENT '部门在当前层级下的顺序，由小到大',
  `remark` varchar(200) DEFAULT '' COMMENT '备注',
  `operator` varchar(20) NOT NULL DEFAULT '' COMMENT '操作者',
  `operate_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次操作时间',
  `operate_ip` varchar(20) NOT NULL DEFAULT '' COMMENT '最后一次更新操作者的ip地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tbl_sys_dept
-- ----------------------------
INSERT INTO `tbl_sys_dept` VALUES ('20', '技术部', '0', '0', '1', '', 'system', '2018-07-03 15:01:53', '127.0.0.1');
INSERT INTO `tbl_sys_dept` VALUES ('22', '开发部', '20', '0.20', '2', '', 'system', '2018-07-03 15:12:51', '127.0.0.1');
INSERT INTO `tbl_sys_dept` VALUES ('23', 'UI部', '20', '0.20', '1', '', 'system', '2018-07-03 15:24:04', '127.0.0.1');
INSERT INTO `tbl_sys_dept` VALUES ('27', '测试22', '20', '0.20', '1', '测试', 'Admin', '2018-07-09 14:00:48', '127.0.0.1');

-- ----------------------------
-- Table structure for tbl_sys_log
-- ----------------------------
DROP TABLE IF EXISTS `tbl_sys_log`;
CREATE TABLE `tbl_sys_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(11) NOT NULL DEFAULT '0' COMMENT '权限更新的类型，1：部门，2：用户，3：权限模块，4：权限，5：角色，6：角色用户关系，7：角色权限关系',
  `target_id` int(11) NOT NULL COMMENT '基于type后指定的对象id，比如用户、权限、角色等表的主键',
  `old_value` varchar(1000) DEFAULT NULL COMMENT '旧值',
  `new_value` varchar(1000) DEFAULT NULL COMMENT '新值',
  `operator` varchar(20) NOT NULL DEFAULT '' COMMENT '操作者',
  `operate_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新的时间',
  `operate_ip` varchar(20) NOT NULL DEFAULT '' COMMENT '最后一次更新者的ip地址',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '当前是否复原过，0：没有，1：复原过',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tbl_sys_log
-- ----------------------------
INSERT INTO `tbl_sys_log` VALUES ('27', '1', '27', '', '{\"id\":27,\"name\":\"测试\",\"parentId\":20,\"level\":\"0.20\",\"seq\":1,\"remark\":\"测试\",\"operator\":\"Admin\",\"operateTime\":\"2018-07-09 14:00:31\",\"operateIp\":\"127.0.0.1\"}', 'Admin', '2018-07-09 14:00:32', '127.0.0.1', '1');
INSERT INTO `tbl_sys_log` VALUES ('28', '1', '27', '{\"id\":27,\"name\":\"测试\",\"parentId\":20,\"level\":\"0.20\",\"seq\":1,\"remark\":\"测试\",\"operator\":\"Admin\",\"operateTime\":\"2018-07-09 14:00:31.0\",\"operateIp\":\"127.0.0.1\"}', '{\"id\":27,\"name\":\"测试22\",\"parentId\":20,\"level\":\"0.20\",\"seq\":1,\"remark\":\"测试\",\"operator\":\"Admin\",\"operateTime\":\"2018-07-09 14:00:48\",\"operateIp\":\"127.0.0.1\"}', 'Admin', '2018-07-09 14:00:52', '127.0.0.1', '1');
INSERT INTO `tbl_sys_log` VALUES ('29', '2', '20', '', '{\"id\":20,\"username\":\"wushuang\",\"telephone\":\"13022188241\",\"mail\":\"21211@qq.com\",\"password\":\"6BACF61FB52BCF08E1283A41C8C01CAB\",\"deptId\":20,\"status\":1,\"remark\":\"www\",\"operator\":\"Admin\",\"operateTime\":\"2018-07-09 14:10:45\",\"operateIp\":\"127.0.0.1\"}', 'Admin', '2018-07-09 14:10:45', '127.0.0.1', '1');
INSERT INTO `tbl_sys_log` VALUES ('30', '2', '20', '{\"id\":20,\"username\":\"wushuang\",\"telephone\":\"13022188241\",\"mail\":\"21211@qq.com\",\"password\":\"6BACF61FB52BCF08E1283A41C8C01CAB\",\"deptId\":20,\"status\":1,\"remark\":\"www\",\"operator\":\"Admin\",\"operateTime\":\"2018-07-09 14:10:45.0\",\"operateIp\":\"127.0.0.1\"}', '{\"id\":20,\"username\":\"wushuang111\",\"telephone\":\"13022188241\",\"mail\":\"21211@qq.com\",\"password\":\"6BACF61FB52BCF08E1283A41C8C01CAB\",\"deptId\":20,\"status\":1,\"remark\":\"www\",\"operator\":\"Admin\",\"operateTime\":\"2018-07-09 14:24:22\",\"operateIp\":\"127.0.0.1\"}', 'Admin', '2018-07-09 14:24:22', '127.0.0.1', '1');
INSERT INTO `tbl_sys_log` VALUES ('31', '5', '7', '', '{\"id\":7,\"name\":\"我的免费隧道\",\"type\":1,\"status\":1,\"remark\":\"ww\",\"operator\":\"Admin\",\"operateTime\":\"2018-07-09 14:24:45\",\"operateIp\":\"127.0.0.1\"}', 'Admin', '2018-07-09 14:24:45', '127.0.0.1', '1');
INSERT INTO `tbl_sys_log` VALUES ('32', '5', '7', '{\"id\":7,\"name\":\"我的免费隧道\",\"type\":1,\"status\":1,\"remark\":\"ww\",\"operator\":\"Admin\",\"operateTime\":\"2018-07-09 14:24:45.0\",\"operateIp\":\"127.0.0.1\"}', '{\"id\":7,\"name\":\"我的免费隧道11\",\"type\":1,\"status\":1,\"remark\":\"ww\",\"operator\":\"Admin\",\"operateTime\":\"2018-07-09 14:24:56\",\"operateIp\":\"127.0.0.1\"}', 'Admin', '2018-07-09 14:24:56', '127.0.0.1', '1');
INSERT INTO `tbl_sys_log` VALUES ('33', '3', '13', '', '{\"id\":13,\"name\":\"我的免费隧道\",\"parentId\":1,\"level\":\"0.1\",\"seq\":1,\"status\":1,\"remark\":\"111\",\"operator\":\"Admin\",\"operateTime\":\"2018-07-09 14:25:49\",\"operateIp\":\"127.0.0.1\"}', 'Admin', '2018-07-09 14:25:49', '127.0.0.1', '1');
INSERT INTO `tbl_sys_log` VALUES ('34', '3', '13', '{\"id\":13,\"name\":\"我的免费隧道\",\"parentId\":1,\"level\":\"0.1\",\"seq\":1,\"status\":1,\"remark\":\"111\",\"operator\":\"Admin\",\"operateTime\":\"2018-07-09 14:25:49.0\",\"operateIp\":\"127.0.0.1\"}', '{\"id\":13,\"name\":\"我的免费隧道11\",\"parentId\":1,\"level\":\"0.1\",\"seq\":1,\"status\":1,\"remark\":\"111\",\"operator\":\"Admin\",\"operateTime\":\"2018-07-09 14:26:14\",\"operateIp\":\"127.0.0.1\"}', 'Admin', '2018-07-09 14:26:14', '127.0.0.1', '1');
INSERT INTO `tbl_sys_log` VALUES ('35', '6', '2', '[1,2,3,4,11,5,6,7,8,9,10]', '[7,8,9,10]', 'Admin', '2018-07-09 14:27:56', '127.0.0.1', '1');
INSERT INTO `tbl_sys_log` VALUES ('36', '7', '2', '[1,2,3,4,5,19]', '[1,2,3,4,5,19,20]', 'Admin', '2018-07-09 14:28:14', '127.0.0.1', '1');
INSERT INTO `tbl_sys_log` VALUES ('37', '7', '1', '[]', '[]', 'Admin', '2018-07-09 14:28:34', '127.0.0.1', '1');
INSERT INTO `tbl_sys_log` VALUES ('38', '7', '1', '[]', '[1,2,3,4,5,19,20]', 'Admin', '2018-07-09 14:28:43', '127.0.0.1', '1');
INSERT INTO `tbl_sys_log` VALUES ('39', '3', '14', '', '{\"id\":14,\"name\":\"我的免费隧道1122\",\"parentId\":1,\"level\":\"0.1\",\"seq\":1,\"status\":1,\"operator\":\"Admin\",\"operateTime\":\"2018-07-09 14:29:26\",\"operateIp\":\"127.0.0.1\"}', 'Admin', '2018-07-09 14:29:26', '127.0.0.1', '1');
INSERT INTO `tbl_sys_log` VALUES ('40', '3', '14', '{\"id\":14,\"name\":\"我的免费隧道1122\",\"parentId\":1,\"level\":\"0.1\",\"seq\":1,\"status\":1,\"operator\":\"Admin\",\"operateTime\":\"2018-07-09 14:29:26.0\",\"operateIp\":\"127.0.0.1\"}', '{\"id\":14,\"name\":\"我的免费隧道112244\",\"parentId\":1,\"level\":\"0.1\",\"seq\":1,\"status\":1,\"operator\":\"Admin\",\"operateTime\":\"2018-07-09 14:30:26\",\"operateIp\":\"127.0.0.1\"}', 'Admin', '2018-07-09 14:30:26', '127.0.0.1', '1');
INSERT INTO `tbl_sys_log` VALUES ('41', '3', '14', '{\"id\":14,\"name\":\"我的免费隧道112244\",\"parentId\":1,\"level\":\"0.1\",\"seq\":1,\"status\":1,\"operator\":\"Admin\",\"operateTime\":\"2018-07-09 14:30:26.0\",\"operateIp\":\"127.0.0.1\"}', '{\"id\":14,\"name\":\"我的免费隧道11224411\",\"parentId\":1,\"level\":\"0.1\",\"seq\":1,\"status\":1,\"operator\":\"Admin\",\"operateTime\":\"2018-07-09 14:31:12\",\"operateIp\":\"127.0.0.1\"}', 'Admin', '2018-07-09 14:31:12', '127.0.0.1', '1');
INSERT INTO `tbl_sys_log` VALUES ('42', '4', '12', '', '{\"id\":12,\"code\":\"20180709143151_0\",\"name\":\"我的免费隧道11\",\"aclModuleId\":1,\"url\":\"/adadad/ada.json\",\"type\":1,\"status\":1,\"seq\":1,\"remark\":\"aaa\",\"operator\":\"Admin\",\"operateTime\":\"2018-07-09 14:31:51\",\"operateIp\":\"127.0.0.1\"}', 'Admin', '2018-07-09 14:32:13', '127.0.0.1', '1');
INSERT INTO `tbl_sys_log` VALUES ('43', '3', '14', '{\"id\":14,\"name\":\"我的免费隧道11224411\",\"parentId\":1,\"level\":\"0.1\",\"seq\":1,\"status\":1,\"operator\":\"Admin\",\"operateTime\":\"2018-07-09 14:31:12.0\",\"operateIp\":\"127.0.0.1\"}', '{\"id\":14,\"name\":\"我的免费隧道112244\",\"parentId\":1,\"level\":\"0.1\",\"seq\":1,\"status\":1,\"operator\":\"Admin\",\"operateTime\":\"2018-07-09 17:13:34\",\"operateIp\":\"127.0.0.1\"}', 'Admin', '2018-07-09 17:13:34', '127.0.0.1', '1');

-- ----------------------------
-- Table structure for tbl_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `tbl_sys_role`;
CREATE TABLE `tbl_sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `name` varchar(20) NOT NULL,
  `type` int(11) NOT NULL DEFAULT '1' COMMENT '角色的类型，1：管理员角色，2：其他',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态，1：可用，0：冻结',
  `remark` varchar(200) DEFAULT '' COMMENT '备注',
  `operator` varchar(20) NOT NULL DEFAULT '' COMMENT '操作者',
  `operate_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新的时间',
  `operate_ip` varchar(20) NOT NULL DEFAULT '' COMMENT '最后一次更新者的ip地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tbl_sys_role
-- ----------------------------
INSERT INTO `tbl_sys_role` VALUES ('1', '产品管理员', '1', '1', '', 'Admin', '2017-10-15 12:42:47', '0:0:0:0:0:0:0:1');
INSERT INTO `tbl_sys_role` VALUES ('2', '订单管理员', '1', '1', '', 'Admin', '2017-10-15 12:18:59', '0:0:0:0:0:0:0:1');
INSERT INTO `tbl_sys_role` VALUES ('3', '公告管理员', '1', '1', '', 'Admin', '2017-10-15 12:19:10', '0:0:0:0:0:0:0:1');
INSERT INTO `tbl_sys_role` VALUES ('4', '权限管理员', '1', '1', '', 'Admin', '2017-10-15 21:30:36', '0:0:0:0:0:0:0:1');
INSERT INTO `tbl_sys_role` VALUES ('5', '运维管理员', '1', '1', '运维', 'Admin', '2017-10-17 00:23:28', '0:0:0:0:0:0:0:1');
INSERT INTO `tbl_sys_role` VALUES ('6', '测试1', '1', '1', '测试', 'Admin', '2018-07-06 10:30:33', '127.0.0.1');
INSERT INTO `tbl_sys_role` VALUES ('7', '我的免费隧道11', '1', '1', 'ww', 'Admin', '2018-07-09 14:24:56', '127.0.0.1');

-- ----------------------------
-- Table structure for tbl_sys_role_acl
-- ----------------------------
DROP TABLE IF EXISTS `tbl_sys_role_acl`;
CREATE TABLE `tbl_sys_role_acl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `acl_id` int(11) NOT NULL COMMENT '权限id',
  `operator` varchar(20) NOT NULL DEFAULT '' COMMENT '操作者',
  `operate_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新的时间',
  `operate_ip` varchar(200) NOT NULL DEFAULT '' COMMENT '最后一次更新者的ip',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tbl_sys_role_acl
-- ----------------------------
INSERT INTO `tbl_sys_role_acl` VALUES ('9', '4', '7', 'Admin', '2017-10-16 23:34:39', '0:0:0:0:0:0:0:1');
INSERT INTO `tbl_sys_role_acl` VALUES ('10', '4', '8', 'Admin', '2017-10-16 23:34:39', '0:0:0:0:0:0:0:1');
INSERT INTO `tbl_sys_role_acl` VALUES ('11', '4', '9', 'Admin', '2017-10-16 23:34:39', '0:0:0:0:0:0:0:1');
INSERT INTO `tbl_sys_role_acl` VALUES ('12', '4', '10', 'Admin', '2017-10-16 23:34:39', '0:0:0:0:0:0:0:1');
INSERT INTO `tbl_sys_role_acl` VALUES ('28', '2', '7', 'Admin', '2018-07-09 14:27:55', '127.0.0.1');
INSERT INTO `tbl_sys_role_acl` VALUES ('29', '2', '8', 'Admin', '2018-07-09 14:27:55', '127.0.0.1');
INSERT INTO `tbl_sys_role_acl` VALUES ('30', '2', '9', 'Admin', '2018-07-09 14:27:55', '127.0.0.1');
INSERT INTO `tbl_sys_role_acl` VALUES ('31', '2', '10', 'Admin', '2018-07-09 14:27:55', '127.0.0.1');

-- ----------------------------
-- Table structure for tbl_sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `tbl_sys_role_user`;
CREATE TABLE `tbl_sys_role_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `operator` varchar(20) NOT NULL DEFAULT '' COMMENT '操作者',
  `operate_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新的时间',
  `operate_ip` varchar(20) NOT NULL DEFAULT '' COMMENT '最后一次更新者的ip地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tbl_sys_role_user
-- ----------------------------
INSERT INTO `tbl_sys_role_user` VALUES ('18', '4', '1', 'Admin', '2018-07-08 15:48:47', '127.0.0.1');
INSERT INTO `tbl_sys_role_user` VALUES ('19', '4', '4', 'Admin', '2018-07-08 15:48:47', '127.0.0.1');
INSERT INTO `tbl_sys_role_user` VALUES ('20', '4', '2', 'Admin', '2018-07-08 15:48:47', '127.0.0.1');
INSERT INTO `tbl_sys_role_user` VALUES ('21', '4', '3', 'Admin', '2018-07-08 15:48:47', '127.0.0.1');
INSERT INTO `tbl_sys_role_user` VALUES ('22', '4', '5', 'Admin', '2018-07-08 15:48:47', '127.0.0.1');
INSERT INTO `tbl_sys_role_user` VALUES ('23', '4', '19', 'Admin', '2018-07-08 15:48:47', '127.0.0.1');
INSERT INTO `tbl_sys_role_user` VALUES ('30', '2', '1', 'Admin', '2018-07-09 14:28:14', '127.0.0.1');
INSERT INTO `tbl_sys_role_user` VALUES ('31', '2', '2', 'Admin', '2018-07-09 14:28:14', '127.0.0.1');
INSERT INTO `tbl_sys_role_user` VALUES ('32', '2', '3', 'Admin', '2018-07-09 14:28:14', '127.0.0.1');
INSERT INTO `tbl_sys_role_user` VALUES ('33', '2', '4', 'Admin', '2018-07-09 14:28:14', '127.0.0.1');
INSERT INTO `tbl_sys_role_user` VALUES ('34', '2', '5', 'Admin', '2018-07-09 14:28:14', '127.0.0.1');
INSERT INTO `tbl_sys_role_user` VALUES ('35', '2', '19', 'Admin', '2018-07-09 14:28:14', '127.0.0.1');
INSERT INTO `tbl_sys_role_user` VALUES ('36', '2', '20', 'Admin', '2018-07-09 14:28:14', '127.0.0.1');
INSERT INTO `tbl_sys_role_user` VALUES ('37', '1', '1', 'Admin', '2018-07-09 14:28:43', '127.0.0.1');
INSERT INTO `tbl_sys_role_user` VALUES ('38', '1', '2', 'Admin', '2018-07-09 14:28:43', '127.0.0.1');
INSERT INTO `tbl_sys_role_user` VALUES ('39', '1', '3', 'Admin', '2018-07-09 14:28:43', '127.0.0.1');
INSERT INTO `tbl_sys_role_user` VALUES ('40', '1', '4', 'Admin', '2018-07-09 14:28:43', '127.0.0.1');
INSERT INTO `tbl_sys_role_user` VALUES ('41', '1', '5', 'Admin', '2018-07-09 14:28:43', '127.0.0.1');
INSERT INTO `tbl_sys_role_user` VALUES ('42', '1', '19', 'Admin', '2018-07-09 14:28:43', '127.0.0.1');
INSERT INTO `tbl_sys_role_user` VALUES ('43', '1', '20', 'Admin', '2018-07-09 14:28:43', '127.0.0.1');

-- ----------------------------
-- Table structure for tbl_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `tbl_sys_user`;
CREATE TABLE `tbl_sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(20) NOT NULL DEFAULT '' COMMENT '用户名称',
  `telephone` varchar(13) NOT NULL DEFAULT '' COMMENT '手机号',
  `mail` varchar(20) NOT NULL DEFAULT '' COMMENT '邮箱',
  `password` varchar(40) NOT NULL DEFAULT '' COMMENT '加密后的密码',
  `dept_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户所在部门的id',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态，1：正常，0：冻结状态，2：删除',
  `remark` varchar(200) DEFAULT '' COMMENT '备注',
  `operator` varchar(20) NOT NULL DEFAULT '' COMMENT '操作者',
  `operate_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
  `operate_ip` varchar(20) NOT NULL DEFAULT '' COMMENT '最后一次更新者的ip地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tbl_sys_user
-- ----------------------------
INSERT INTO `tbl_sys_user` VALUES ('1', 'Admin', '18612344321', 'admin@qq.com', 'E10ADC3949BA59ABBE56E057F20F883E', '22', '1', 'admin', 'system', '2017-10-13 08:46:16', '127.0.0.1');
INSERT INTO `tbl_sys_user` VALUES ('2', 'Jimin', '13188889999', 'jimin@qq.com', '25D55AD283AA400AF464C76D713C07AD', '22', '1', 'jimin.zheng', 'Admin', '2017-10-14 14:45:19', '127.0.0.1');
INSERT INTO `tbl_sys_user` VALUES ('3', 'Jimmy', '13812344311', 'jimmy@qq.com', '25D55AD283AA400AF464C76D713C07AD', '22', '1', '', 'Admin', '2017-10-16 12:57:35', '0:0:0:0:0:0:0:1');
INSERT INTO `tbl_sys_user` VALUES ('4', 'Kate', '13144445555', 'kate@qq.com', '25D55AD283AA400AF464C76D713C07AD', '22', '1', 'sss', 'Admin', '2017-10-16 23:02:51', '0:0:0:0:0:0:0:1');
INSERT INTO `tbl_sys_user` VALUES ('5', '服务员A', '18677778888', 'service@qq.com', '25D55AD283AA400AF464C76D713C07AD', '23', '1', '', 'Admin', '2017-10-17 00:22:15', '0:0:0:0:0:0:0:1');
INSERT INTO `tbl_sys_user` VALUES ('19', 'quzhigang', '13022188211', '598700560@qq.com', '1AECFB5F0827DE712F64A951635BD914', '22', '1', 'cscs', 'system', '2018-07-04 17:47:03', '');
INSERT INTO `tbl_sys_user` VALUES ('20', 'wushuang111', '13022188241', '21211@qq.com', '6BACF61FB52BCF08E1283A41C8C01CAB', '20', '1', 'www', 'Admin', '2018-07-09 14:24:22', '127.0.0.1');
