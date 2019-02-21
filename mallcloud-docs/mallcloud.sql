/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : mallcloud

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2019-02-20 23:36:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `dept_id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `del_flag` char(1) DEFAULT '0' COMMENT '是否删除  -1：已删除  0：正常',
  `parent_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='部门管理';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('10', '顶级部门', '0', '2018-08-20 11:46:57', null, '0', '0');
INSERT INTO `sys_dept` VALUES ('11', '一级部门', '0', '2018-08-20 11:47:10', null, '0', '10');
INSERT INTO `sys_dept` VALUES ('12', '二级部门', '1', '2018-08-20 11:47:19', null, '0', '11');

-- ----------------------------
-- Table structure for sys_dept_relation
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept_relation`;
CREATE TABLE `sys_dept_relation` (
  `ancestor` int(11) NOT NULL COMMENT '祖先节点',
  `descendant` int(11) NOT NULL COMMENT '后代节点',
  PRIMARY KEY (`ancestor`,`descendant`),
  KEY `idx1` (`ancestor`) USING BTREE,
  KEY `idx2` (`descendant`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of sys_dept_relation
-- ----------------------------
INSERT INTO `sys_dept_relation` VALUES ('10', '10');
INSERT INTO `sys_dept_relation` VALUES ('10', '11');
INSERT INTO `sys_dept_relation` VALUES ('10', '12');
INSERT INTO `sys_dept_relation` VALUES ('11', '11');
INSERT INTO `sys_dept_relation` VALUES ('11', '12');
INSERT INTO `sys_dept_relation` VALUES ('12', '12');

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` int(64) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `value` varchar(100) NOT NULL COMMENT '数据值',
  `label` varchar(100) NOT NULL COMMENT '标签名',
  `type` varchar(100) NOT NULL COMMENT '类型',
  `description` varchar(100) NOT NULL COMMENT '描述',
  `sort` decimal(10,0) NOT NULL COMMENT '排序（升序）',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `sys_dict_value` (`value`) USING BTREE,
  KEY `sys_dict_label` (`label`) USING BTREE,
  KEY `sys_dict_del_flag` (`del_flag`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典表';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `type` varchar(50) NOT NULL COMMENT '日志类型',
  `title` varchar(255) DEFAULT '' COMMENT '日志标题',
  `service_id` varchar(32) DEFAULT NULL COMMENT '服务ID',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remote_addr` varchar(255) DEFAULT NULL COMMENT '操作IP地址',
  `user_agent` varchar(1000) DEFAULT NULL COMMENT '用户代理',
  `request_uri` varchar(255) DEFAULT NULL COMMENT '请求URI',
  `method` varchar(10) DEFAULT NULL COMMENT '操作方式',
  `params` text COMMENT '操作提交的数据',
  `time` mediumtext COMMENT '执行时间',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标记',
  `exception` text COMMENT '异常信息',
  PRIMARY KEY (`id`),
  KEY `sys_log_create_by` (`create_by`) USING BTREE,
  KEY `sys_log_request_uri` (`request_uri`) USING BTREE,
  KEY `sys_log_type` (`type`) USING BTREE,
  KEY `sys_log_create_date` (`create_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='日志表';

-- ----------------------------
-- Records of sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` int(11) NOT NULL COMMENT '菜单ID',
  `name` varchar(32) NOT NULL COMMENT '菜单名称',
  `permission` varchar(32) DEFAULT NULL COMMENT '菜单权限标识',
  `path` varchar(128) DEFAULT NULL COMMENT '前端URL',
  `url` varchar(128) DEFAULT NULL COMMENT '请求链接',
  `method` varchar(32) DEFAULT NULL COMMENT '请求方法',
  `parent_id` int(11) DEFAULT NULL COMMENT '父菜单ID',
  `icon` varchar(32) DEFAULT NULL COMMENT '图标',
  `component` varchar(64) DEFAULT NULL COMMENT 'VUE页面',
  `sort` int(11) DEFAULT '1' COMMENT '排序值',
  `type` char(1) DEFAULT NULL COMMENT '菜单类型 （0菜单 1按钮）',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` char(1) DEFAULT '0' COMMENT '0--正常 1--删除',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单权限表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '系统管理', null, '/admin', null, null, '-1', 'wordpress', 'Layout', '1', '0', '2017-11-07 20:56:00', '2018-09-08 01:46:08', '0');
INSERT INTO `sys_menu` VALUES ('2', '用户管理', null, 'user', '', null, '1', 'file-o', 'views/admin/user/index', '2', '0', '2017-11-02 22:24:37', '2018-09-08 02:01:44', '0');
INSERT INTO `sys_menu` VALUES ('3', '菜单管理', null, 'menu', '', null, '1', 'file-o', 'views/admin/menu/index', '3', '0', '2017-11-08 09:57:27', '2018-09-08 02:01:45', '0');
INSERT INTO `sys_menu` VALUES ('4', '角色管理', null, 'role', null, null, '1', 'file-o', 'views/admin/role/index', '4', '0', '2017-11-08 10:13:37', '2018-09-08 02:01:49', '0');
INSERT INTO `sys_menu` VALUES ('5', '日志管理', null, 'log', null, null, '1', 'file-o', 'views/admin/log/index', '5', '0', '2017-11-20 14:06:22', '2018-09-08 02:01:49', '0');
INSERT INTO `sys_menu` VALUES ('6', '字典管理', null, 'dict', null, null, '1', 'file-o', 'views/admin/dict/index', '6', '0', '2017-11-29 11:30:52', '2018-09-08 02:01:49', '0');
INSERT INTO `sys_menu` VALUES ('7', '部门管理', null, 'dept', null, null, '1', 'file-o', 'views/admin/dept/index', '7', '0', '2018-01-20 13:17:19', '2018-09-08 02:01:49', '0');
INSERT INTO `sys_menu` VALUES ('8', '系统监控', null, '/mallcloud-admin', null, null, '-1', 'pie-chart', 'Layout', '8', '0', '2018-01-22 12:30:41', '2018-09-08 01:59:13', '0');
INSERT INTO `sys_menu` VALUES ('14', '接口文档', null, 'mallcloud-api', '', null, '8', 'file-text-o', 'views/service/swagger/index', '2', '0', '2018-01-23 10:56:43', '2018-09-10 06:58:57', '0');
INSERT INTO `sys_menu` VALUES ('21', '用户查看', '', null, '/admin/user/**', 'GET', '2', null, null, null, '1', '2017-11-07 20:58:05', '2018-02-04 14:28:49', '0');
INSERT INTO `sys_menu` VALUES ('22', '用户新增', 'sys_user_add', null, '/admin/user/*', 'POST', '2', null, null, null, '1', '2017-11-08 09:52:09', '2017-12-04 16:31:10', '0');
INSERT INTO `sys_menu` VALUES ('23', '用户修改', 'sys_user_upd', null, '/admin/user/**', 'PUT', '2', null, null, null, '1', '2017-11-08 09:52:48', '2018-01-17 17:40:01', '0');
INSERT INTO `sys_menu` VALUES ('24', '用户删除', 'sys_user_del', null, '/admin/user/*', 'DELETE', '2', null, null, null, '1', '2017-11-08 09:54:01', '2017-12-04 16:31:18', '0');
INSERT INTO `sys_menu` VALUES ('25', '服务监控', null, 'service', null, null, '8', 'server', 'views/service/index', '1', '0', '2018-08-10 08:09:16', '2018-09-08 01:59:53', '0');
INSERT INTO `sys_menu` VALUES ('28', '调用链监控', null, 'zipkin', null, null, '8', 'bar-chart-o', 'views/service/zipkin/index', '3', '0', '2018-09-18 17:18:53', '2018-09-18 17:21:40', '0');
INSERT INTO `sys_menu` VALUES ('31', '菜单查看', null, null, '/admin/menu/**', 'GET', '3', null, null, null, '1', '2017-11-08 09:57:56', '2017-11-14 17:29:17', '0');
INSERT INTO `sys_menu` VALUES ('32', '菜单新增', 'sys_menu_add', null, '/admin/menu/*', 'POST', '3', null, null, null, '1', '2017-11-08 10:15:53', '2018-01-20 14:37:50', '0');
INSERT INTO `sys_menu` VALUES ('33', '菜单修改', 'sys_menu_edit', null, '/admin/menu/*', 'PUT', '3', null, null, null, '1', '2017-11-08 10:16:23', '2018-01-20 14:37:56', '0');
INSERT INTO `sys_menu` VALUES ('34', '菜单删除', 'sys_menu_del', null, '/admin/menu/*', 'DELETE', '3', null, null, null, '1', '2017-11-08 10:16:43', '2018-01-20 14:38:03', '0');
INSERT INTO `sys_menu` VALUES ('41', '角色查看', null, null, '/admin/role/**', 'GET', '4', null, null, null, '1', '2017-11-08 10:14:01', '2018-02-04 13:55:06', '0');
INSERT INTO `sys_menu` VALUES ('42', '角色新增', 'sys_role_add', null, '/admin/role/*', 'POST', '4', null, null, null, '1', '2017-11-08 10:14:18', '2018-04-20 07:21:38', '0');
INSERT INTO `sys_menu` VALUES ('43', '角色修改', 'sys_role_edit', null, '/admin/role/*', 'PUT', '4', null, null, null, '1', '2017-11-08 10:14:41', '2018-04-20 07:21:50', '0');
INSERT INTO `sys_menu` VALUES ('44', '角色删除', 'sys_role_del', null, '/admin/role/*', 'DELETE', '4', null, null, null, '1', '2017-11-08 10:14:59', '2018-04-20 07:22:00', '0');
INSERT INTO `sys_menu` VALUES ('45', '分配权限', 'sys_role_perm', null, '/admin/role/*', 'PUT', '4', null, null, null, '1', '2018-04-20 07:22:55', '2018-04-20 07:24:40', '0');
INSERT INTO `sys_menu` VALUES ('51', '日志查看', null, null, '/admin/log/**', 'GET', '5', null, null, null, '1', '2017-11-20 14:07:25', '2018-02-04 14:28:53', '0');
INSERT INTO `sys_menu` VALUES ('52', '日志删除', 'sys_log_del', null, '/admin/log/*', 'DELETE', '5', null, null, null, '1', '2017-11-20 20:37:37', '2017-11-28 17:36:52', '0');
INSERT INTO `sys_menu` VALUES ('61', '字典查看', null, null, '/admin/dict/**', 'GET', '6', null, null, null, '1', '2017-11-19 22:04:24', '2017-11-29 11:31:24', '0');
INSERT INTO `sys_menu` VALUES ('62', '字典删除', 'sys_dict_del', null, '/admin/dict/**', 'DELETE', '6', null, null, null, '1', '2017-11-29 11:30:11', '2018-01-07 15:40:51', '0');
INSERT INTO `sys_menu` VALUES ('63', '字典新增', 'sys_dict_add', null, '/admin/dict/**', 'POST', '6', null, null, null, '1', '2018-05-11 22:34:55', null, '0');
INSERT INTO `sys_menu` VALUES ('64', '字典修改', 'sys_dict_upd', null, '/admin/dict/**', 'PUT', '6', null, null, null, '1', '2018-05-11 22:36:03', null, '0');
INSERT INTO `sys_menu` VALUES ('71', '部门查看', '', null, '/admin/dept/**', 'GET', '7', null, '', null, '1', '2018-01-20 13:17:19', '2018-01-20 14:55:24', '0');
INSERT INTO `sys_menu` VALUES ('72', '部门新增', 'sys_dept_add', null, '/admin/dept/**', 'POST', '7', null, null, null, '1', '2018-01-20 14:56:16', '2018-01-20 21:17:57', '0');
INSERT INTO `sys_menu` VALUES ('73', '部门修改', 'sys_dept_edit', null, '/admin/dept/**', 'PUT', '7', null, null, null, '1', '2018-01-20 14:56:59', '2018-01-20 21:17:59', '0');
INSERT INTO `sys_menu` VALUES ('74', '部门删除', 'sys_dept_del', null, '/admin/dept/**', 'DELETE', '7', null, null, null, '1', '2018-01-20 14:57:28', '2018-01-20 21:18:05', '0');
INSERT INTO `sys_menu` VALUES ('100', '客户端管理', '', 'client', '', '', '1', 'file-o', 'views/admin/client/index', '9', '0', '2018-01-20 13:17:19', '2018-09-08 02:02:02', '0');
INSERT INTO `sys_menu` VALUES ('101', '客户端新增', 'sys_client_add', null, '/admin/client/**', 'POST', '100', '1', null, null, '1', '2018-05-15 21:35:18', '2018-05-16 10:35:26', '0');
INSERT INTO `sys_menu` VALUES ('102', '客户端修改', 'sys_client_upd', null, '/admin/client/**', 'PUT', '100', null, null, null, '1', '2018-05-15 21:37:06', '2018-05-15 21:52:30', '0');
INSERT INTO `sys_menu` VALUES ('103', '客户端删除', 'sys_client_del', null, '/admin/client/**', 'DELETE', '100', null, null, null, '1', '2018-05-15 21:39:16', '2018-05-15 21:52:34', '0');
INSERT INTO `sys_menu` VALUES ('104', '客户端查看', null, null, '/admin/client/**', 'GET', '100', null, null, null, '1', '2018-05-15 21:39:57', '2018-05-15 21:52:40', '0');
INSERT INTO `sys_menu` VALUES ('110', '路由管理', null, 'route', null, null, '1', 'file-o', 'views/admin/route/index', '8', '0', '2018-05-15 21:44:51', '2018-09-08 02:02:07', '0');
INSERT INTO `sys_menu` VALUES ('111', '路由查看', null, null, '/admin/route/**', 'GET', '110', null, null, null, '1', '2018-05-15 21:45:59', '2018-05-16 07:23:04', '0');
INSERT INTO `sys_menu` VALUES ('112', '路由新增', 'sys_route_add', null, '/admin/route/**', 'POST', '110', null, null, null, '1', '2018-05-15 21:52:22', '2018-05-15 21:53:46', '0');
INSERT INTO `sys_menu` VALUES ('113', '路由修改', 'sys_route_upd', null, '/admin/route/**', 'PUT', '110', null, null, null, '1', '2018-05-15 21:55:38', null, '0');
INSERT INTO `sys_menu` VALUES ('114', '路由删除', 'sys_route_del', null, '/admin/route/**', 'DELETE', '110', null, null, null, '1', '2018-05-15 21:56:45', null, '0');
INSERT INTO `sys_menu` VALUES ('251', '服务查询', null, null, '/mallcloud-admin/api/**', 'GET', '25', null, null, '1', '1', '2018-08-10 08:25:50', '2018-08-10 08:26:05', '0');
INSERT INTO `sys_menu` VALUES ('252', '设置权重和标签', 'mallcloud_admin_set_weight', null, '/mallcloud-registry/eureka/apps/**', 'PUT', '25', null, null, '2', '1', '2018-08-10 08:54:10', '2018-08-10 08:54:47', '0');
INSERT INTO `sys_menu` VALUES ('253', '服务日志级别设置', null, null, '/mallcloud-admin/api/applications/*/loggers/*', 'POST', '25', null, null, '1', '1', '2018-08-10 09:17:06', '2018-09-18 17:37:31', '0');
INSERT INTO `sys_menu` VALUES ('254', '删除服务', null, null, '/mallcloud-admin/api/applications/*', 'DELETE', '25', '', null, '4', '1', '2018-09-18 17:37:08', '2018-09-18 17:37:57', '0');

-- ----------------------------
-- Table structure for sys_oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `sys_oauth_client_details`;
CREATE TABLE `sys_oauth_client_details` (
  `client_id` varchar(40) NOT NULL,
  `resource_ids` varchar(256) DEFAULT NULL,
  `client_secret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `authorized_grant_types` varchar(256) DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_oauth_client_details
-- ----------------------------
INSERT INTO `sys_oauth_client_details` VALUES ('sso-demo1', null, '$2a$10$pgzIKH1c2/7jS2eYk0bK9OnShYXvWiZXd3firLJkaF2q9Ct.WVtSe', 'read,write', 'password,refresh_token,authorization_code', '', null, null, null, null, 'true');
INSERT INTO `sys_oauth_client_details` VALUES ('sso-demo2', '', '$2a$10$0fl9mlBjfg0E48Thhe73Sekw0qdR9OTvXLl3nn8isgwc1LnDyuSD2', 'read,write', 'password,refresh_token,authorization_code', '', '', null, null, null, 'true');
INSERT INTO `sys_oauth_client_details` VALUES ('mallcloud', null, '$2a$10$drYsSntNKIr.cCiAQip0uOp5VtZl2FWZ4WvNYgLcMb19ri66mVzRS', 'server', 'password,refresh_token,authorization_code', null, null, null, null, null, 'false');

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `role_id` int(20) DEFAULT NULL COMMENT '角色ID',
  `dept_id` int(20) DEFAULT NULL COMMENT '部门ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='角色与部门对应关系';

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------
INSERT INTO `sys_role_dept` VALUES ('14', '14', '1');
INSERT INTO `sys_role_dept` VALUES ('16', '1', '10');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `menu_id` int(11) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色菜单表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('1', '1');
INSERT INTO `sys_role_menu` VALUES ('1', '2');
INSERT INTO `sys_role_menu` VALUES ('1', '3');
INSERT INTO `sys_role_menu` VALUES ('1', '4');
INSERT INTO `sys_role_menu` VALUES ('1', '5');
INSERT INTO `sys_role_menu` VALUES ('1', '6');
INSERT INTO `sys_role_menu` VALUES ('1', '7');
INSERT INTO `sys_role_menu` VALUES ('1', '8');
INSERT INTO `sys_role_menu` VALUES ('1', '14');
INSERT INTO `sys_role_menu` VALUES ('1', '21');
INSERT INTO `sys_role_menu` VALUES ('1', '22');
INSERT INTO `sys_role_menu` VALUES ('1', '23');
INSERT INTO `sys_role_menu` VALUES ('1', '24');
INSERT INTO `sys_role_menu` VALUES ('1', '25');
INSERT INTO `sys_role_menu` VALUES ('1', '28');
INSERT INTO `sys_role_menu` VALUES ('1', '31');
INSERT INTO `sys_role_menu` VALUES ('1', '32');
INSERT INTO `sys_role_menu` VALUES ('1', '33');
INSERT INTO `sys_role_menu` VALUES ('1', '34');
INSERT INTO `sys_role_menu` VALUES ('1', '41');
INSERT INTO `sys_role_menu` VALUES ('1', '42');
INSERT INTO `sys_role_menu` VALUES ('1', '43');
INSERT INTO `sys_role_menu` VALUES ('1', '44');
INSERT INTO `sys_role_menu` VALUES ('1', '45');
INSERT INTO `sys_role_menu` VALUES ('1', '51');
INSERT INTO `sys_role_menu` VALUES ('1', '52');
INSERT INTO `sys_role_menu` VALUES ('1', '61');
INSERT INTO `sys_role_menu` VALUES ('1', '62');
INSERT INTO `sys_role_menu` VALUES ('1', '63');
INSERT INTO `sys_role_menu` VALUES ('1', '64');
INSERT INTO `sys_role_menu` VALUES ('1', '71');
INSERT INTO `sys_role_menu` VALUES ('1', '72');
INSERT INTO `sys_role_menu` VALUES ('1', '73');
INSERT INTO `sys_role_menu` VALUES ('1', '74');
INSERT INTO `sys_role_menu` VALUES ('1', '100');
INSERT INTO `sys_role_menu` VALUES ('1', '101');
INSERT INTO `sys_role_menu` VALUES ('1', '102');
INSERT INTO `sys_role_menu` VALUES ('1', '103');
INSERT INTO `sys_role_menu` VALUES ('1', '104');
INSERT INTO `sys_role_menu` VALUES ('1', '110');
INSERT INTO `sys_role_menu` VALUES ('1', '111');
INSERT INTO `sys_role_menu` VALUES ('1', '112');
INSERT INTO `sys_role_menu` VALUES ('1', '113');
INSERT INTO `sys_role_menu` VALUES ('1', '114');
INSERT INTO `sys_role_menu` VALUES ('1', '251');
INSERT INTO `sys_role_menu` VALUES ('1', '252');
INSERT INTO `sys_role_menu` VALUES ('1', '253');
INSERT INTO `sys_role_menu` VALUES ('1', '254');
INSERT INTO `sys_role_menu` VALUES ('14', '1');
INSERT INTO `sys_role_menu` VALUES ('14', '2');
INSERT INTO `sys_role_menu` VALUES ('14', '3');
INSERT INTO `sys_role_menu` VALUES ('14', '4');
INSERT INTO `sys_role_menu` VALUES ('14', '5');
INSERT INTO `sys_role_menu` VALUES ('14', '6');
INSERT INTO `sys_role_menu` VALUES ('14', '7');
INSERT INTO `sys_role_menu` VALUES ('14', '8');
INSERT INTO `sys_role_menu` VALUES ('14', '14');
INSERT INTO `sys_role_menu` VALUES ('14', '21');
INSERT INTO `sys_role_menu` VALUES ('14', '31');
INSERT INTO `sys_role_menu` VALUES ('14', '41');
INSERT INTO `sys_role_menu` VALUES ('14', '51');
INSERT INTO `sys_role_menu` VALUES ('14', '61');
INSERT INTO `sys_role_menu` VALUES ('14', '71');
