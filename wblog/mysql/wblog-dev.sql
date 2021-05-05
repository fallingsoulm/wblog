/*
 Navicat Premium Data Transfer

 Source Server         : 我的
 Source Server Type    : MySQL
 Source Server Version : 50733
 Source Host           : 39.99.137.69:3306
 Source Schema         : easy-archetype-dev

 Target Server Type    : MySQL
 Target Server Version : 50733
 File Encoding         : 65001

 Date: 05/05/2021 09:30:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for QRTZ_BLOB_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_BLOB_TRIGGERS`;
CREATE TABLE `QRTZ_BLOB_TRIGGERS`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `blob_data` blob NULL,
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  CONSTRAINT `QRTZ_BLOB_TRIGGERS_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `QRTZ_TRIGGERS` (`sched_name`, `trigger_name`, `trigger_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for QRTZ_CALENDARS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_CALENDARS`;
CREATE TABLE `QRTZ_CALENDARS`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `calendar_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `calendar` blob NOT NULL,
  PRIMARY KEY (`sched_name`, `calendar_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for QRTZ_CRON_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_CRON_TRIGGERS`;
CREATE TABLE `QRTZ_CRON_TRIGGERS`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `cron_expression` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `time_zone_id` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  CONSTRAINT `QRTZ_CRON_TRIGGERS_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `QRTZ_TRIGGERS` (`sched_name`, `trigger_name`, `trigger_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of QRTZ_CRON_TRIGGERS
-- ----------------------------
INSERT INTO `QRTZ_CRON_TRIGGERS` VALUES ('Scheduler', 'TASK_CLASS_NAME7d2a6d08963c4e799de8157d82d92807', 'DEFAULT', '0/15 * * * * ?', 'Asia/Shanghai');
INSERT INTO `QRTZ_CRON_TRIGGERS` VALUES ('Scheduler', 'TASK_CLASS_NAME8ef01eb1adb840c9bc8be51270493408', 'DEFAULT', '0/10 * * * * ?', 'Asia/Shanghai');
INSERT INTO `QRTZ_CRON_TRIGGERS` VALUES ('Scheduler', 'TASK_CLASS_NAMEf164e785452e40f1b43d2ad79f847554', 'DEFAULT', '0/20 * * * * ?', 'Asia/Shanghai');

-- ----------------------------
-- Table structure for QRTZ_FIRED_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_FIRED_TRIGGERS`;
CREATE TABLE `QRTZ_FIRED_TRIGGERS`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `entry_id` varchar(95) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `instance_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `fired_time` bigint(13) NOT NULL,
  `sched_time` bigint(13) NOT NULL,
  `priority` int(11) NOT NULL,
  `state` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `job_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `job_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `is_nonconcurrent` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `requests_recovery` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`sched_name`, `entry_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for QRTZ_JOB_DETAILS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_JOB_DETAILS`;
CREATE TABLE `QRTZ_JOB_DETAILS`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `job_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `job_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `description` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `job_class_name` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `is_durable` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `is_nonconcurrent` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `is_update_data` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `requests_recovery` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `job_data` blob NULL,
  PRIMARY KEY (`sched_name`, `job_name`, `job_group`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of QRTZ_JOB_DETAILS
-- ----------------------------
INSERT INTO `QRTZ_JOB_DETAILS` VALUES ('Scheduler', 'TASK_CLASS_NAME7d2a6d08963c4e799de8157d82d92807', 'DEFAULT', NULL, 'com.easy.archetype.job.utils.QuartzDisallowConcurrentExecution', '0', '1', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000F5441534B5F50524F5045525449455373720023636F6D2E656173792E6172636865747970652E6A6F622E656E746974792E4A6F62566F56E576E26EB3338802000D4C000A636F6E63757272656E747400124C6A6176612F6C616E672F537472696E673B4C0008637265617465427971007E00094C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C000E63726F6E45787072657373696F6E71007E00094C000C696E766F6B6554617267657471007E00094C000A696E766F6B655479706571007E00094C00086A6F6247726F757071007E00094C00056A6F62496471007E00094C00076A6F624E616D6571007E00094C000D6D697366697265506F6C69637971007E00094C000673746174757371007E00094C0008757064617465427971007E00094C000A75706461746554696D6571007E000A78707400013174000131737200126A6176612E73716C2E54696D657374616D702618D5C80153BF650200014900056E616E6F737872000E6A6176612E7574696C2E44617465686A81014B5974190300007870770800000178A75CF758780000000074000E302F3135202A202A202A202A203F74001764656D6F5461736B2E7279506172616D732827727927297400046265616E74000744454641554C547400203764326136643038393633633465373939646538313537643832643932383037740018E7B3BBE7BB9FE9BB98E8AEA4EFBC88E69C89E58F82EFBC897400013174000131740001317371007E000E770800000178A760EEF878000000007800);
INSERT INTO `QRTZ_JOB_DETAILS` VALUES ('Scheduler', 'TASK_CLASS_NAME8ef01eb1adb840c9bc8be51270493408', 'DEFAULT', NULL, 'com.easy.archetype.job.utils.QuartzJobExecution', '0', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000F5441534B5F50524F5045525449455373720023636F6D2E656173792E6172636865747970652E6A6F622E656E746974792E4A6F62566F56E576E26EB3338802000D4C000A636F6E63757272656E747400124C6A6176612F6C616E672F537472696E673B4C0008637265617465427971007E00094C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C000E63726F6E45787072657373696F6E71007E00094C000C696E766F6B6554617267657471007E00094C000A696E766F6B655479706571007E00094C00086A6F6247726F757071007E00094C00056A6F62496471007E00094C00076A6F624E616D6571007E00094C000D6D697366697265506F6C69637971007E00094C000673746174757371007E00094C0008757064617465427971007E00094C000A75706461746554696D6571007E000A78707400013074000131737200126A6176612E73716C2E54696D657374616D702618D5C80153BF650200014900056E616E6F737872000E6A6176612E7574696C2E44617465686A81014B5974190300007870770800000178A7560218780000000074000E302F3130202A202A202A202A203F74001364656D6F5461736B2E72794E6F506172616D737400046265616E74000744454641554C547400203865663031656231616462383430633962633862653531323730343933343038740018E7B3BBE7BB9FE9BB98E8AEA4EFBC88E697A0E58F82EFBC897400013174000131740001317371007E000E770800000178A756021878000000007800);
INSERT INTO `QRTZ_JOB_DETAILS` VALUES ('Scheduler', 'TASK_CLASS_NAMEf164e785452e40f1b43d2ad79f847554', 'DEFAULT', NULL, 'com.easy.archetype.job.utils.QuartzDisallowConcurrentExecution', '0', '1', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000F5441534B5F50524F5045525449455373720023636F6D2E656173792E6172636865747970652E6A6F622E656E746974792E4A6F62566F56E576E26EB3338802000D4C000A636F6E63757272656E747400124C6A6176612F6C616E672F537472696E673B4C0008637265617465427971007E00094C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C000E63726F6E45787072657373696F6E71007E00094C000C696E766F6B6554617267657471007E00094C000A696E766F6B655479706571007E00094C00086A6F6247726F757071007E00094C00056A6F62496471007E00094C00076A6F624E616D6571007E00094C000D6D697366697265506F6C69637971007E00094C000673746174757371007E00094C0008757064617465427971007E00094C000A75706461746554696D6571007E000A78707400013174000131737200126A6176612E73716C2E54696D657374616D702618D5C80153BF650200014900056E616E6F737872000E6A6176612E7574696C2E44617465686A81014B5974190300007870770800000178A76298C0780000000074000E302F3230202A202A202A202A203F74003C64656D6F5461736B2E72794D756C7469706C65506172616D73285C2772795C272C20747275652C20323030304C2C203331362E3530442C20313030297400046265616E74000744454641554C547400206631363465373835343532653430663162343364326164373966383437353534740018E7B3BBE7BB9FE9BB98E8AEA4EFBC88E5A49AE58F82EFBC897400013174000131740001317371007E000E770800000178A76298C078000000007800);

-- ----------------------------
-- Table structure for QRTZ_LOCKS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_LOCKS`;
CREATE TABLE `QRTZ_LOCKS`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `lock_name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`sched_name`, `lock_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of QRTZ_LOCKS
-- ----------------------------
INSERT INTO `QRTZ_LOCKS` VALUES ('Scheduler', 'STATE_ACCESS');
INSERT INTO `QRTZ_LOCKS` VALUES ('Scheduler', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for QRTZ_PAUSED_TRIGGER_GRPS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_PAUSED_TRIGGER_GRPS`;
CREATE TABLE `QRTZ_PAUSED_TRIGGER_GRPS`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`sched_name`, `trigger_group`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for QRTZ_SCHEDULER_STATE
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SCHEDULER_STATE`;
CREATE TABLE `QRTZ_SCHEDULER_STATE`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `instance_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `last_checkin_time` bigint(13) NOT NULL,
  `checkin_interval` bigint(13) NOT NULL,
  PRIMARY KEY (`sched_name`, `instance_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of QRTZ_SCHEDULER_STATE
-- ----------------------------
INSERT INTO `QRTZ_SCHEDULER_STATE` VALUES ('Scheduler', 'GTKVNBNILKRNSXG1619830122914', 1619833177176, 15000);

-- ----------------------------
-- Table structure for QRTZ_SIMPLE_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SIMPLE_TRIGGERS`;
CREATE TABLE `QRTZ_SIMPLE_TRIGGERS`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `repeat_count` bigint(7) NOT NULL,
  `repeat_interval` bigint(12) NOT NULL,
  `times_triggered` bigint(10) NOT NULL,
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  CONSTRAINT `QRTZ_SIMPLE_TRIGGERS_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `QRTZ_TRIGGERS` (`sched_name`, `trigger_name`, `trigger_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for QRTZ_SIMPROP_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SIMPROP_TRIGGERS`;
CREATE TABLE `QRTZ_SIMPROP_TRIGGERS`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `str_prop_1` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `str_prop_2` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `str_prop_3` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `int_prop_1` int(11) NULL DEFAULT NULL,
  `int_prop_2` int(11) NULL DEFAULT NULL,
  `long_prop_1` bigint(20) NULL DEFAULT NULL,
  `long_prop_2` bigint(20) NULL DEFAULT NULL,
  `dec_prop_1` decimal(13, 4) NULL DEFAULT NULL,
  `dec_prop_2` decimal(13, 4) NULL DEFAULT NULL,
  `bool_prop_1` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bool_prop_2` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  CONSTRAINT `QRTZ_SIMPROP_TRIGGERS_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `QRTZ_TRIGGERS` (`sched_name`, `trigger_name`, `trigger_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for QRTZ_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_TRIGGERS`;
CREATE TABLE `QRTZ_TRIGGERS`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `job_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `job_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `description` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `next_fire_time` bigint(13) NULL DEFAULT NULL,
  `prev_fire_time` bigint(13) NULL DEFAULT NULL,
  `priority` int(11) NULL DEFAULT NULL,
  `trigger_state` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `trigger_type` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `start_time` bigint(13) NOT NULL,
  `end_time` bigint(13) NULL DEFAULT NULL,
  `calendar_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `misfire_instr` smallint(2) NULL DEFAULT NULL,
  `job_data` blob NULL,
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  INDEX `sched_name`(`sched_name`, `job_name`, `job_group`) USING BTREE,
  CONSTRAINT `QRTZ_TRIGGERS_ibfk_1` FOREIGN KEY (`sched_name`, `job_name`, `job_group`) REFERENCES `QRTZ_JOB_DETAILS` (`sched_name`, `job_name`, `job_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of QRTZ_TRIGGERS
-- ----------------------------
INSERT INTO `QRTZ_TRIGGERS` VALUES ('Scheduler', 'TASK_CLASS_NAME7d2a6d08963c4e799de8157d82d92807', 'DEFAULT', 'TASK_CLASS_NAME7d2a6d08963c4e799de8157d82d92807', 'DEFAULT', NULL, 1619830125000, -1, 5, 'PAUSED', 'CRON', 1619830123000, 0, NULL, -1, '');
INSERT INTO `QRTZ_TRIGGERS` VALUES ('Scheduler', 'TASK_CLASS_NAME8ef01eb1adb840c9bc8be51270493408', 'DEFAULT', 'TASK_CLASS_NAME8ef01eb1adb840c9bc8be51270493408', 'DEFAULT', NULL, 1619830130000, -1, 5, 'PAUSED', 'CRON', 1619830124000, 0, NULL, -1, '');
INSERT INTO `QRTZ_TRIGGERS` VALUES ('Scheduler', 'TASK_CLASS_NAMEf164e785452e40f1b43d2ad79f847554', 'DEFAULT', 'TASK_CLASS_NAMEf164e785452e40f1b43d2ad79f847554', 'DEFAULT', NULL, 1619830140000, -1, 5, 'PAUSED', 'CRON', 1619830124000, 0, NULL, -1, '');

-- ----------------------------
-- Table structure for cc_config
-- ----------------------------
DROP TABLE IF EXISTS `cc_config`;
CREATE TABLE `cc_config`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `config_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'key名称',
  `config_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '配置的key',
  `config_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '配置的value值',
  `group_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '分组',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cc_config
-- ----------------------------
INSERT INTO `cc_config` VALUES (1, NULL, 'app.name', '易手架', 'default', NULL, NULL, NULL, NULL, '项目名称');
INSERT INTO `cc_config` VALUES (2, NULL, 'app.copyright', '晋ICP备17009011号 Copyright © 2019- 2021 Luyanan Designed by Luyanan', 'default', NULL, NULL, NULL, NULL, 'copyright');
INSERT INTO `cc_config` VALUES (3, NULL, 'sys.account.registerUser', 'true', 'default', NULL, NULL, NULL, NULL, '是否开启注册');
INSERT INTO `cc_config` VALUES (4, NULL, 'app.desp', '简单脚手架', 'default', NULL, NULL, NULL, NULL, '项目介绍');
INSERT INTO `cc_config` VALUES (6, '主框架页-默认皮肤样式名称', 'sys.index.skinName', 'skin-blue', 'Y', 'admin', '2021-04-22 13:40:25', '', NULL, '蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow');
INSERT INTO `cc_config` VALUES (7, '用户管理-账号初始密码', 'sys.user.initPassword', '123456', 'Y', 'admin', '2021-04-22 13:40:25', '', NULL, '初始化密码 123456');
INSERT INTO `cc_config` VALUES (8, '主框架页-侧边栏主题', 'sys.index.sideTheme', 'theme-dark', 'Y', 'admin', '2021-04-22 13:40:25', '', NULL, '深黑主题theme-dark，浅色主题theme-light，深蓝主题theme-blue');
INSERT INTO `cc_config` VALUES (9, '账号自助-是否开启用户注册功能', 'sys.account.registerUser', 'false', 'Y', 'admin', '2021-04-22 13:40:25', '', NULL, '是否开启注册用户功能（true开启，false关闭）');
INSERT INTO `cc_config` VALUES (10, '用户管理-密码字符范围', 'sys.account.chrtype', '0', 'Y', 'admin', '2021-04-22 13:40:25', '', NULL, '默认任意字符范围，0任意（密码可以输入任意字符），1数字（密码只能为0-9数字），2英文字母（密码只能为a-z和A-Z字母），3字母和数字（密码必须包含字母，数字）,4字母数字和特殊字符（目前支持的特殊字符包括：~!@#$%^&*()-=_+）');
INSERT INTO `cc_config` VALUES (11, '用户管理-初始密码修改策略', 'sys.account.initPasswordModify', '0', 'Y', 'admin', '2021-04-22 13:40:25', '', NULL, '0：初始密码修改策略关闭，没有任何提示，1：提醒用户，如果未修改初始密码，则在登录时就会提醒修改密码对话框');
INSERT INTO `cc_config` VALUES (12, '用户管理-账号密码更新周期', 'sys.account.passwordValidateDays', '0', 'Y', 'admin', '2021-04-22 13:40:25', '', NULL, '密码更新周期（填写数字，数据初始化值为0不限制，若修改必须为大于0小于365的正整数），如果超过这个周期登录系统时，则在登录时就会提醒修改密码对话框');
INSERT INTO `cc_config` VALUES (13, '主框架页-菜单导航显示风格', 'sys.index.menuStyle', 'default', 'Y', 'admin', '2021-04-22 13:40:25', '', NULL, '菜单导航显示风格（default为左侧导航菜单，topnav为顶部导航菜单）');
INSERT INTO `cc_config` VALUES (14, '主框架页-是否开启页脚', 'sys.index.ignoreFooter', 'true', 'Y', 'admin', '2021-04-22 13:49:34', '', NULL, '是否开启底部页脚显示（true显示，false隐藏）');

-- ----------------------------
-- Table structure for config_info
-- ----------------------------
DROP TABLE IF EXISTS `config_info`;
CREATE TABLE `config_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
  `src_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  `c_desc` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `c_use` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `effect` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `type` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `c_schema` text CHARACTER SET utf8 COLLATE utf8_bin NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfo_datagrouptenant`(`data_id`, `group_id`, `tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info
-- ----------------------------
INSERT INTO `config_info` VALUES (1, 'application.yml', 'DEFAULT_GROUP', 'feign:\n  sentinel:\n    enabled: true\n  okhttp:\n    enabled: true\n  httpclient:\n    enabled: false\n  client:\n    config:\n      default:\n        connectTimeout: 10000\n        readTimeout: 10000\n  compression:\n    request:\n      enabled: true\n    response:\n      enabled: true\n\nribbon:\n  ReadTimeout: 10000\n  ConnectTimeout: 10000\n\n\n\n\nserver:\n  #  tomcat:\n  #    uri-encoding: utf-8\n  #    max-threads: 800\n  #    min-spare-threads: 30\n  undertow:\n    accesslog:\n      enabled: false\n      dir: logs\n    #    io-threads: 8\n    #    worker-threads: 64\n    max-http-post-size: -1B\n    direct-buffers: true\n    buffer-size: 1024\n# Spring\nspring:\n  thymeleaf:\n    mode: HTML\n    encoding: UTF-8\n  messages:\n    basename: i18n/messages\n  jackson:\n    time-zone: GMT+8\n    date-format: yyyy-MM-dd HH:mm:ss\n  servlet:\n    multipart:\n      max-file-size: 100MB\n      max-request-size: 200MB\n      enabled: true\n  main:\n    allow-bean-definition-overriding: true\n  http:\n    encoding:\n      charset: UTF-8\n      enabled: true\n      force: true\n  session:\n    store-type: redis\n    timeout: 5h\n  cloud:\n    sentinel:\n      eager: true\n      filter:\n        url-patterns: \'/**\'\n      transport:\n        dashboard: http://localhost:8719\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  endpoint:\n    health:\n      show-details: ALWAYS\n## mybatis-plus\nmybatis-plus:\n  global-config:\n    banner: false\n    db-config:\n      id-type: auto\n      select-strategy: not_empty\n      update-strategy: not_empty\n      logic-delete-field: delFlag\n      logic-delete-value: 2\n      logic-not-delete-value: 0\n  mapper-locations: \'classpath*:mapper/**/*Mapper.xml\'\n', '1cd2aad060be1531c87aea09f8160eac', '2021-02-21 03:26:56', '2021-04-03 07:35:44', NULL, '172.23.0.1', '', 'dev', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (3, 'easy-archetype-cloud-auth-dev.yml', 'DEFAULT_GROUP', '## 日志配置\r\nlogging:\r\n  level:\r\n    com.easy: debug\r\n    org.springframework: warn\r\nspring:\r\n  thymeleaf:\r\n    # thymeleaf  禁用缓存\r\n    cache: false\r\n    # 服务模块\r\n  devtools:\r\n    restart:\r\n      # 热部署开关\r\n      enabled: true\r\n      ## 数据库配置\r\n  datasource:\r\n    driver-class-name: com.mysql.cj.jdbc.Driver\r\n    url: jdbc:mysql://easy-mysql:3306/easy-archetype-dev?characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false&allowMultiQueries=true\r\n    password: luyanan\r\n    username: luyanan\r\n  redis:\r\n    host: easy-mysql\r\n    password: luyanan\r\n    port: 6379\r\n    database: 3\r\n# 一些自定义的配置\r\neasy:\r\n  archetype:\r\n    framework:\r\n      enable: true\r\n      # Spring 增强\r\n      spring:\r\n        enable: true\r\n        # 开启cors\r\n        cors:\r\n          enable: true\r\n      # 缓存增强\r\n      cache:\r\n        enable: true\r\n      logger:\r\n        enable: true\r\n        # 是否开启日志打印\r\n        logger-print: true\r\n      mybatis-plus:\r\n        enable: false\r\n      thread:\r\n        enable: true\r\n    security:\r\n      oauth:\r\n        token-signing-key: easy-archetype\r\n        client:\r\n          authorization-server-host: http://localhost:9001/\r\n          client-id: c1\r\n          client-secret: secret\r\n          resource-id: res1\r\n      ignoring-login-url:\r\n        - /actuator/**\r\n        - /oauth/*\r\n        - /token/**\r\n      validatecode:\r\n        validate-code-filter: true\r\n        enable: true\r\n        validate-code-urls: /login\r\n        captcha-type: math\r\n# 接口文档\r\nknife4j:\r\n  enable: true\r\n  documents:\r\n    - group: 2.X版本\r\n      name: 接口签名\r\n      locations: classpath:sign/*\r\n  setting:\r\n    language: zh-CN\r\n    enableSwaggerModels: true\r\n    enableDocumentManage: true\r\n    swaggerModelName: 实体类列表\r\n    enableVersion: true\r\n    enableReloadCacheParameter: false\r\n    enableAfterScript: true\r\n    enableFilterMultipartApiMethodType: POST\r\n    enableFilterMultipartApis: false\r\n    enableRequestCache: true\r\n    enableHost: false\r\n    enableHostText: 192.168.0.193:8000\r\n    enableHomeCustom: true\r\n    homeCustomLocation: classpath:markdown/home.md\r\n    enableSearch: false\r\n    enableFooter: false\r\n    enableFooterCustom: true\r\n    footerCustomContent: Apache License 2.0 | Copyright  2019-[浙江八一菜刀股份有限公司](https://gitee.com/xiaoym/knife4j)\r\n    enableDynamicParameter: true\r\n    enableDebug: true\r\n    enableOpenApi: false\r\n    enableGroup: true\r\n  cors: false\r\n  production: false\r\n  basic:\r\n    enable: false\r\n    username: test\r\n    password: 12313', '9317691561e65144387bc018baad42f6', '2021-02-21 10:22:03', '2021-02-21 10:22:03', NULL, '0:0:0:0:0:0:0:1', '', 'dev', NULL, NULL, NULL, 'text', NULL);
INSERT INTO `config_info` VALUES (23, 'easy-archetype-cloud-archetype.yml', 'DEFAULT_GROUP', '## logger\nlogging:\n  level:\n    com.easy: debug\n    org.springframework: warn\nspring:\n  thymeleaf:\n    # thymeleaf  cache\n    cache: false\n  devtools:\n    restart:\n      enabled: true\n  datasource:\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://122.152.226.81:3306/easy-archetype-dev?characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false&allowMultiQueries=true\n    password: luyanan\n    username: luyanan\n  redis:\n    host: easy-mysql\n    password: luyanan\n    port: 6379\n    database: 3\n# customer config\neasy:\n  archetype:\n    framework:\n      enable: true\n      # Spring \n      spring:\n        enable: true\n        # cors\n        cors:\n          enable: true\n      #cache\n      cache:\n        enable: true\n      logger:\n        enable: true\n        # logger\n        logger-print: true\n      mybatis-plus:\n        enable: false\n      thread:\n        enable: true\n    security:\n      oauth:\n        token-signing-key: easy-archetype\n        client:\n          authorization-server-host: http://localhost:9001/\n          client-id: c1\n          client-secret: secret\n          resource-id: res1\n      ignoring-login-url:\n        - /actuator/**\n        - /oauth/*\n        - /token/**\n\n', 'c59176be394ef762cdecf098d4ea06ab', '2021-04-03 07:38:21', '2021-04-03 07:58:59', NULL, '172.18.0.1', '', 'dev', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (28, 'easy-archetype-cloud-gateway.yml', 'DEFAULT_GROUP', 'spring:\r\n  cloud:\r\n    gateway:\r\n      routes:\r\n        # 认证中心\r\n        - id: easy-archetype-cloud-auth\r\n          uri: lb://easy-archetype-cloud-auth\r\n          predicates:\r\n            - Path=/uaa/**\r\n        # 脚手架\r\n        - id: easy-archetype-cloud-archetype\r\n          uri: lb://easy-archetype-cloud-archetype\r\n          predicates:\r\n            - Path=/archetype/**\r\n', 'a0f572bb12339688eb14fc6ff5f8aac1', '2021-04-03 13:04:27', '2021-04-03 13:04:27', 'nacos', '0:0:0:0:0:0:0:1', '', 'dev', NULL, NULL, NULL, 'yaml', NULL);

-- ----------------------------
-- Table structure for config_info_aggr
-- ----------------------------
DROP TABLE IF EXISTS `config_info_aggr`;
CREATE TABLE `config_info_aggr`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `datum_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'datum_id',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '内容',
  `gmt_modified` datetime(0) NOT NULL COMMENT '修改时间',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfoaggr_datagrouptenantdatum`(`data_id`, `group_id`, `tenant_id`, `datum_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '增加租户字段' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for config_info_beta
-- ----------------------------
DROP TABLE IF EXISTS `config_info_beta`;
CREATE TABLE `config_info_beta`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `beta_ips` varchar(1024) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'betaIps',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
  `src_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfobeta_datagrouptenant`(`data_id`, `group_id`, `tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info_beta' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for config_info_tag
-- ----------------------------
DROP TABLE IF EXISTS `config_info_tag`;
CREATE TABLE `config_info_tag`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `tag_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'tag_id',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
  `src_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfotag_datagrouptenanttag`(`data_id`, `group_id`, `tenant_id`, `tag_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info_tag' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for config_tags_relation
-- ----------------------------
DROP TABLE IF EXISTS `config_tags_relation`;
CREATE TABLE `config_tags_relation`  (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `tag_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'tag_name',
  `tag_type` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'tag_type',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `nid` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`nid`) USING BTREE,
  UNIQUE INDEX `uk_configtagrelation_configidtag`(`id`, `tag_name`, `tag_type`) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_tag_relation' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for gen_table
-- ----------------------------
DROP TABLE IF EXISTS `gen_table`;
CREATE TABLE `gen_table`  (
  `table_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '表名称',
  `table_comment` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '表描述',
  `sub_table_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '关联子表的表名',
  `sub_table_fk_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '子表关联的外键名',
  `class_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '实体类名称',
  `tpl_category` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'crud' COMMENT '使用的模板（crud单表操作 tree树表操作）',
  `package_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成包路径',
  `module_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成模块名',
  `business_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成业务名',
  `function_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成功能名',
  `function_author` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成功能作者',
  `gen_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '生成代码方式（0zip压缩包 1自定义路径）',
  `gen_path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '/' COMMENT '生成路径（不填默认项目路径）',
  `options` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '其它生成选项',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`table_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '代码生成业务表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for gen_table_column
-- ----------------------------
DROP TABLE IF EXISTS `gen_table_column`;
CREATE TABLE `gen_table_column`  (
  `column_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '归属表编号',
  `column_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '列名称',
  `column_comment` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '列描述',
  `column_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '列类型',
  `java_type` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'JAVA类型',
  `java_field` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'JAVA字段名',
  `is_pk` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否主键（1是）',
  `is_increment` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否自增（1是）',
  `is_required` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否必填（1是）',
  `is_insert` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否为插入字段（1是）',
  `is_edit` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否编辑字段（1是）',
  `is_list` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否列表字段（1是）',
  `is_query` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否查询字段（1是）',
  `query_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'EQ' COMMENT '查询方式（等于、不等于、大于、小于、范围）',
  `html_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
  `dict_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`column_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '代码生成业务表字段' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for group_capacity
-- ----------------------------
DROP TABLE IF EXISTS `group_capacity`;
CREATE TABLE `group_capacity`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Group ID，空字符表示整个集群',
  `quota` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '配额，0表示使用默认值',
  `usage` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '使用量',
  `max_size` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '聚合子配置最大个数，，0表示使用默认值',
  `max_aggr_size` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最大变更历史数量',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_group_id`(`group_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '集群、各Group容量信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for his_config_info
-- ----------------------------
DROP TABLE IF EXISTS `his_config_info`;
CREATE TABLE `his_config_info`  (
  `id` bigint(64) UNSIGNED NOT NULL,
  `nid` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL,
  `src_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `op_type` char(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`nid`) USING BTREE,
  INDEX `idx_gmt_create`(`gmt_create`) USING BTREE,
  INDEX `idx_gmt_modified`(`gmt_modified`) USING BTREE,
  INDEX `idx_did`(`data_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '多租户改造' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of his_config_info
-- ----------------------------
INSERT INTO `his_config_info` VALUES (0, 5, 'easy-archetype-cloud-archetype.yml', 'DEFAULT_GROUP', '', '## 日志配置\r\nlogging:\r\n  level:\r\n    com.easy: debug\r\n    org.springframework: warn\r\nspring:\r\n  thymeleaf:\r\n    # thymeleaf  禁用缓存\r\n    cache: false\r\n    # 服务模块\r\n  devtools:\r\n    restart:\r\n      # 热部署开关\r\n      enabled: true\r\n      ## 数据库配置\r\n  datasource:\r\n    driver-class-name: com.mysql.cj.jdbc.Driver\r\n    url: jdbc:mysql://easy-mysql:3306/easy-archetype-dev?characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false&allowMultiQueries=true\r\n    password: luyanan\r\n    username: luyanan\r\n  redis:\r\n    host: easy-mysql\r\n    password: luyanan\r\n    port: 6379\r\n    database: 3\r\n# 一些自定义的配置\r\neasy:\r\n  archetype:\r\n    framework:\r\n      enable: true\r\n      # Spring 增强\r\n      spring:\r\n        enable: true\r\n        # 开启cors\r\n        cors:\r\n          enable: true\r\n      # 缓存增强\r\n      cache:\r\n        enable: true\r\n      logger:\r\n        enable: true\r\n        # 是否开启日志打印\r\n        logger-print: true\r\n      mybatis-plus:\r\n        enable: false\r\n      thread:\r\n        enable: true\r\n    security:\r\n      oauth:\r\n        token-signing-key: easy-archetype\r\n        client:\r\n          authorization-server-host: http://localhost:9001/\r\n          client-id: c1\r\n          client-secret: secret\r\n          resource-id: res1\r\n      ignoring-login-url:\r\n        - /actuator/**\r\n        - /oauth/*\r\n        - /token/**\r\n      validatecode:\r\n        validate-code-filter: true\r\n        enable: true\r\n        validate-code-urls: /login\r\n        captcha-type: math\r\n# 接口文档\r\nknife4j:\r\n  enable: true\r\n  documents:\r\n    - group: 2.X版本\r\n      name: 接口签名\r\n      locations: classpath:sign/*\r\n  setting:\r\n    language: zh-CN\r\n    enableSwaggerModels: true\r\n    enableDocumentManage: true\r\n    swaggerModelName: 实体类列表\r\n    enableVersion: true\r\n    enableReloadCacheParameter: false\r\n    enableAfterScript: true\r\n    enableFilterMultipartApiMethodType: POST\r\n    enableFilterMultipartApis: false\r\n    enableRequestCache: true\r\n    enableHost: false\r\n    enableHostText: 192.168.0.193:8000\r\n    enableHomeCustom: true\r\n    homeCustomLocation: classpath:markdown/home.md\r\n    enableSearch: false\r\n    enableFooter: false\r\n    enableFooterCustom: true\r\n    footerCustomContent: Apache License 2.0 | Copyright  2019-[浙江八一菜刀股份有限公司](https://gitee.com/xiaoym/knife4j)\r\n    enableDynamicParameter: true\r\n    enableDebug: true\r\n    enableOpenApi: false\r\n    enableGroup: true\r\n  cors: false\r\n  production: false\r\n  basic:\r\n    enable: false\r\n    username: test\r\n    password: 12313', '9317691561e65144387bc018baad42f6', '2021-03-25 12:45:26', '2021-03-25 12:45:31', NULL, '172.18.0.1', 'I', 'dev');
INSERT INTO `his_config_info` VALUES (0, 6, 'easy-archetype-cloud-archetype', 'DEFAULT_GROUP', '', '## 日志配置\r\nlogging:\r\n  level:\r\n    com.easy: debug\r\n    org.springframework: warn\r\nspring:\r\n  thymeleaf:\r\n    # thymeleaf  禁用缓存\r\n    cache: false\r\n    # 服务模块\r\n  devtools:\r\n    restart:\r\n      # 热部署开关\r\n      enabled: true\r\n      ## 数据库配置\r\n  datasource:\r\n    driver-class-name: com.mysql.cj.jdbc.Driver\r\n    url: jdbc:mysql://easy-mysql:3306/easy-archetype-dev?characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false&allowMultiQueries=true\r\n    password: luyanan\r\n    username: luyanan\r\n  redis:\r\n    host: easy-mysql\r\n    password: luyanan\r\n    port: 6379\r\n    database: 3\r\n# 一些自定义的配置\r\neasy:\r\n  archetype:\r\n    framework:\r\n      enable: true\r\n      # Spring 增强\r\n      spring:\r\n        enable: true\r\n        # 开启cors\r\n        cors:\r\n          enable: true\r\n      # 缓存增强\r\n      cache:\r\n        enable: true\r\n      logger:\r\n        enable: true\r\n        # 是否开启日志打印\r\n        logger-print: true\r\n      mybatis-plus:\r\n        enable: false\r\n      thread:\r\n        enable: true\r\n    security:\r\n      oauth:\r\n        token-signing-key: easy-archetype\r\n        client:\r\n          authorization-server-host: http://localhost:9001/\r\n          client-id: c1\r\n          client-secret: secret\r\n          resource-id: res1\r\n      ignoring-login-url:\r\n        - /actuator/**\r\n        - /oauth/*\r\n        - /token/**\r\n      validatecode:\r\n        validate-code-filter: true\r\n        enable: true\r\n        validate-code-urls: /login\r\n        captcha-type: math\r\n# 接口文档\r\nknife4j:\r\n  enable: true\r\n  documents:\r\n    - group: 2.X版本\r\n      name: 接口签名\r\n      locations: classpath:sign/*\r\n  setting:\r\n    language: zh-CN\r\n    enableSwaggerModels: true\r\n    enableDocumentManage: true\r\n    swaggerModelName: 实体类列表\r\n    enableVersion: true\r\n    enableReloadCacheParameter: false\r\n    enableAfterScript: true\r\n    enableFilterMultipartApiMethodType: POST\r\n    enableFilterMultipartApis: false\r\n    enableRequestCache: true\r\n    enableHost: false\r\n    enableHostText: 192.168.0.193:8000\r\n    enableHomeCustom: true\r\n    homeCustomLocation: classpath:markdown/home.md\r\n    enableSearch: false\r\n    enableFooter: false\r\n    enableFooterCustom: true\r\n    footerCustomContent: Apache License 2.0 | Copyright  2019-[浙江八一菜刀股份有限公司](https://gitee.com/xiaoym/knife4j)\r\n    enableDynamicParameter: true\r\n    enableDebug: true\r\n    enableOpenApi: false\r\n    enableGroup: true\r\n  cors: false\r\n  production: false\r\n  basic:\r\n    enable: false\r\n    username: test\r\n    password: 12313', '9317691561e65144387bc018baad42f6', '2021-03-25 12:52:06', '2021-03-25 12:52:11', NULL, '172.18.0.1', 'I', 'dev');
INSERT INTO `his_config_info` VALUES (4, 7, 'easy-archetype-cloud-archetype.yml', 'DEFAULT_GROUP', '', '## 日志配置\r\nlogging:\r\n  level:\r\n    com.easy: debug\r\n    org.springframework: warn\r\nspring:\r\n  thymeleaf:\r\n    # thymeleaf  禁用缓存\r\n    cache: false\r\n    # 服务模块\r\n  devtools:\r\n    restart:\r\n      # 热部署开关\r\n      enabled: true\r\n      ## 数据库配置\r\n  datasource:\r\n    driver-class-name: com.mysql.cj.jdbc.Driver\r\n    url: jdbc:mysql://easy-mysql:3306/easy-archetype-dev?characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false&allowMultiQueries=true\r\n    password: luyanan\r\n    username: luyanan\r\n  redis:\r\n    host: easy-mysql\r\n    password: luyanan\r\n    port: 6379\r\n    database: 3\r\n# 一些自定义的配置\r\neasy:\r\n  archetype:\r\n    framework:\r\n      enable: true\r\n      # Spring 增强\r\n      spring:\r\n        enable: true\r\n        # 开启cors\r\n        cors:\r\n          enable: true\r\n      # 缓存增强\r\n      cache:\r\n        enable: true\r\n      logger:\r\n        enable: true\r\n        # 是否开启日志打印\r\n        logger-print: true\r\n      mybatis-plus:\r\n        enable: false\r\n      thread:\r\n        enable: true\r\n    security:\r\n      oauth:\r\n        token-signing-key: easy-archetype\r\n        client:\r\n          authorization-server-host: http://localhost:9001/\r\n          client-id: c1\r\n          client-secret: secret\r\n          resource-id: res1\r\n      ignoring-login-url:\r\n        - /actuator/**\r\n        - /oauth/*\r\n        - /token/**\r\n      validatecode:\r\n        validate-code-filter: true\r\n        enable: true\r\n        validate-code-urls: /login\r\n        captcha-type: math\r\n# 接口文档\r\nknife4j:\r\n  enable: true\r\n  documents:\r\n    - group: 2.X版本\r\n      name: 接口签名\r\n      locations: classpath:sign/*\r\n  setting:\r\n    language: zh-CN\r\n    enableSwaggerModels: true\r\n    enableDocumentManage: true\r\n    swaggerModelName: 实体类列表\r\n    enableVersion: true\r\n    enableReloadCacheParameter: false\r\n    enableAfterScript: true\r\n    enableFilterMultipartApiMethodType: POST\r\n    enableFilterMultipartApis: false\r\n    enableRequestCache: true\r\n    enableHost: false\r\n    enableHostText: 192.168.0.193:8000\r\n    enableHomeCustom: true\r\n    homeCustomLocation: classpath:markdown/home.md\r\n    enableSearch: false\r\n    enableFooter: false\r\n    enableFooterCustom: true\r\n    footerCustomContent: Apache License 2.0 | Copyright  2019-[浙江八一菜刀股份有限公司](https://gitee.com/xiaoym/knife4j)\r\n    enableDynamicParameter: true\r\n    enableDebug: true\r\n    enableOpenApi: false\r\n    enableGroup: true\r\n  cors: false\r\n  production: false\r\n  basic:\r\n    enable: false\r\n    username: test\r\n    password: 12313', '9317691561e65144387bc018baad42f6', '2021-03-25 12:56:58', '2021-03-25 12:57:03', NULL, '172.18.0.1', 'D', 'dev');
INSERT INTO `his_config_info` VALUES (5, 8, 'easy-archetype-cloud-archetype', 'DEFAULT_GROUP', '', '## 日志配置\r\nlogging:\r\n  level:\r\n    com.easy: debug\r\n    org.springframework: warn\r\nspring:\r\n  thymeleaf:\r\n    # thymeleaf  禁用缓存\r\n    cache: false\r\n    # 服务模块\r\n  devtools:\r\n    restart:\r\n      # 热部署开关\r\n      enabled: true\r\n      ## 数据库配置\r\n  datasource:\r\n    driver-class-name: com.mysql.cj.jdbc.Driver\r\n    url: jdbc:mysql://easy-mysql:3306/easy-archetype-dev?characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false&allowMultiQueries=true\r\n    password: luyanan\r\n    username: luyanan\r\n  redis:\r\n    host: easy-mysql\r\n    password: luyanan\r\n    port: 6379\r\n    database: 3\r\n# 一些自定义的配置\r\neasy:\r\n  archetype:\r\n    framework:\r\n      enable: true\r\n      # Spring 增强\r\n      spring:\r\n        enable: true\r\n        # 开启cors\r\n        cors:\r\n          enable: true\r\n      # 缓存增强\r\n      cache:\r\n        enable: true\r\n      logger:\r\n        enable: true\r\n        # 是否开启日志打印\r\n        logger-print: true\r\n      mybatis-plus:\r\n        enable: false\r\n      thread:\r\n        enable: true\r\n    security:\r\n      oauth:\r\n        token-signing-key: easy-archetype\r\n        client:\r\n          authorization-server-host: http://localhost:9001/\r\n          client-id: c1\r\n          client-secret: secret\r\n          resource-id: res1\r\n      ignoring-login-url:\r\n        - /actuator/**\r\n        - /oauth/*\r\n        - /token/**\r\n      validatecode:\r\n        validate-code-filter: true\r\n        enable: true\r\n        validate-code-urls: /login\r\n        captcha-type: math\r\n# 接口文档\r\nknife4j:\r\n  enable: true\r\n  documents:\r\n    - group: 2.X版本\r\n      name: 接口签名\r\n      locations: classpath:sign/*\r\n  setting:\r\n    language: zh-CN\r\n    enableSwaggerModels: true\r\n    enableDocumentManage: true\r\n    swaggerModelName: 实体类列表\r\n    enableVersion: true\r\n    enableReloadCacheParameter: false\r\n    enableAfterScript: true\r\n    enableFilterMultipartApiMethodType: POST\r\n    enableFilterMultipartApis: false\r\n    enableRequestCache: true\r\n    enableHost: false\r\n    enableHostText: 192.168.0.193:8000\r\n    enableHomeCustom: true\r\n    homeCustomLocation: classpath:markdown/home.md\r\n    enableSearch: false\r\n    enableFooter: false\r\n    enableFooterCustom: true\r\n    footerCustomContent: Apache License 2.0 | Copyright  2019-[浙江八一菜刀股份有限公司](https://gitee.com/xiaoym/knife4j)\r\n    enableDynamicParameter: true\r\n    enableDebug: true\r\n    enableOpenApi: false\r\n    enableGroup: true\r\n  cors: false\r\n  production: false\r\n  basic:\r\n    enable: false\r\n    username: test\r\n    password: 12313', '9317691561e65144387bc018baad42f6', '2021-03-25 12:57:56', '2021-03-25 12:58:00', NULL, '172.18.0.1', 'U', 'dev');
INSERT INTO `his_config_info` VALUES (5, 9, 'easy-archetype-cloud-archetype', 'DEFAULT_GROUP', 'aaaa', '## 日志配置\nlogging:\n  level:\n    com.easy: debug\n    org.springframework: warn\nspring:\n  thymeleaf:\n    # thymeleaf  禁用缓存\n    cache: false\n    # 服务模块\n  devtools:\n    restart:\n      # 热部署开关\n      enabled: true\n      ## 数据库配置\n  datasource:\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://easy-mysql:3306/easy-archetype-dev?characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false&allowMultiQueries=true\n    password: luyanan\n    username: luyanan\n  redis:\n    host: easy-mysql\n    password: luyanan\n    port: 6379\n    database: 3\n# 一些自定义的配置\neasy:\n  archetype:\n    framework:\n      enable: true\n      # Spring 增强\n      spring:\n        enable: true\n        # 开启cors\n        cors:\n          enable: true\n      # 缓存增强\n      cache:\n        enable: true\n      logger:\n        enable: true\n        # 是否开启日志打印\n        logger-print: true\n      mybatis-plus:\n        enable: false\n      thread:\n        enable: true\n    security:\n      oauth:\n        token-signing-key: easy-archetype\n        client:\n          authorization-server-host: http://localhost:9001/\n          client-id: c1\n          client-secret: secret\n          resource-id: res1\n      ignoring-login-url:\n        - /actuator/**\n        - /oauth/*\n        - /token/**\n\n# 接口文档\nknife4j:\n  enable: true\n  documents:\n    - group: 2.X版本\n      name: 接口签名\n      locations: classpath:sign/*\n  setting:\n    language: zh-CN\n    enableSwaggerModels: true\n    enableDocumentManage: true\n    swaggerModelName: 实体类列表\n    enableVersion: true\n    enableReloadCacheParameter: false\n    enableAfterScript: true\n    enableFilterMultipartApiMethodType: POST\n    enableFilterMultipartApis: false\n    enableRequestCache: true\n    enableHost: false\n    enableHostText: 192.168.0.193:8000\n    enableHomeCustom: true\n    homeCustomLocation: classpath:markdown/home.md\n    enableSearch: false\n    enableFooter: false\n    enableFooterCustom: true\n    footerCustomContent: Apache License 2.0 | Copyright  2019-[浙江八一菜刀股份有限公司](https://gitee.com/xiaoym/knife4j)\n    enableDynamicParameter: true\n    enableDebug: true\n    enableOpenApi: false\n    enableGroup: true\n  cors: false\n  production: false\n  basic:\n    enable: false\n    username: test\n    password: 12313', '7ce1a77e26ec818c5af3d2f809570cbf', '2021-03-28 12:28:17', '2021-03-28 12:28:18', NULL, '172.18.0.1', 'D', 'dev');
INSERT INTO `his_config_info` VALUES (0, 10, 'easy-archetype-cloud-archetype', 'DEFAULT_GROUP', 'aaaa', '## 日志配置\nlogging:\n  level:\n    com.easy: debug\n    org.springframework: warn\nspring:\n  thymeleaf:\n    # thymeleaf  禁用缓存\n    cache: false\n    # 服务模块\n  devtools:\n    restart:\n      # 热部署开关\n      enabled: true\n      ## 数据库配置\n  datasource:\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://easy-mysql:3306/easy-archetype-dev?characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false&allowMultiQueries=true\n    password: luyanan\n    username: luyanan\n  redis:\n    host: easy-mysql\n    password: luyanan\n    port: 6379\n    database: 3\n# 一些自定义的配置\neasy:\n  archetype:\n    framework:\n      enable: true\n      # Spring 增强\n      spring:\n        enable: true\n        # 开启cors\n        cors:\n          enable: true\n      # 缓存增强\n      cache:\n        enable: true\n      logger:\n        enable: true\n        # 是否开启日志打印\n        logger-print: true\n      mybatis-plus:\n        enable: false\n      thread:\n        enable: true\n    security:\n      oauth:\n        token-signing-key: easy-archetype\n        client:\n          authorization-server-host: http://localhost:9001/\n          client-id: c1\n          client-secret: secret\n          resource-id: res1\n      ignoring-login-url:\n        - /actuator/**\n        - /oauth/*\n        - /token/**\n\n# 接口文档\nknife4j:\n  enable: true\n  documents:\n    - group: 2.X版本\n      name: 接口签名\n      locations: classpath:sign/*\n  setting:\n    language: zh-CN\n    enableSwaggerModels: true\n    enableDocumentManage: true\n    swaggerModelName: 实体类列表\n    enableVersion: true\n    enableReloadCacheParameter: false\n    enableAfterScript: true\n    enableFilterMultipartApiMethodType: POST\n    enableFilterMultipartApis: false\n    enableRequestCache: true\n    enableHost: false\n    enableHostText: 192.168.0.193:8000\n    enableHomeCustom: true\n    homeCustomLocation: classpath:markdown/home.md\n    enableSearch: false\n    enableFooter: false\n    enableFooterCustom: true\n    footerCustomContent: Apache License 2.0 | Copyright  2019-[浙江八一菜刀股份有限公司](https://gitee.com/xiaoym/knife4j)\n    enableDynamicParameter: true\n    enableDebug: true\n    enableOpenApi: false\n    enableGroup: true\n  cors: false\n  production: false\n  basic:\n    enable: false\n    username: test\n    password: 12313', '7ce1a77e26ec818c5af3d2f809570cbf', '2021-03-31 13:12:05', '2021-03-31 13:12:09', NULL, '172.26.0.1', 'I', 'dev');
INSERT INTO `his_config_info` VALUES (0, 11, 'easy-archetype-cloud-archetype.yml', 'DEFAULT_GROUP', 'aaaa', '## 日志配置\nlogging:\n  level:\n    com.easy: debug\n    org.springframework: warn\nspring:\n  thymeleaf:\n    # thymeleaf  禁用缓存\n    cache: false\n    # 服务模块\n  devtools:\n    restart:\n      # 热部署开关\n      enabled: true\n      ## 数据库配置\n  datasource:\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://easy-mysql:3306/easy-archetype-dev?characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false&allowMultiQueries=true\n    password: luyanan\n    username: luyanan\n  redis:\n    host: easy-mysql\n    password: luyanan\n    port: 6379\n    database: 3\n# 一些自定义的配置\neasy:\n  archetype:\n    framework:\n      enable: true\n      # Spring 增强\n      spring:\n        enable: true\n        # 开启cors\n        cors:\n          enable: true\n      # 缓存增强\n      cache:\n        enable: true\n      logger:\n        enable: true\n        # 是否开启日志打印\n        logger-print: true\n      mybatis-plus:\n        enable: false\n      thread:\n        enable: true\n    security:\n      oauth:\n        token-signing-key: easy-archetype\n        client:\n          authorization-server-host: http://localhost:9001/\n          client-id: c1\n          client-secret: secret\n          resource-id: res1\n      ignoring-login-url:\n        - /actuator/**\n        - /oauth/*\n        - /token/**\n\n# 接口文档\nknife4j:\n  enable: true\n  documents:\n    - group: 2.X版本\n      name: 接口签名\n      locations: classpath:sign/*\n  setting:\n    language: zh-CN\n    enableSwaggerModels: true\n    enableDocumentManage: true\n    swaggerModelName: 实体类列表\n    enableVersion: true\n    enableReloadCacheParameter: false\n    enableAfterScript: true\n    enableFilterMultipartApiMethodType: POST\n    enableFilterMultipartApis: false\n    enableRequestCache: true\n    enableHost: false\n    enableHostText: 192.168.0.193:8000\n    enableHomeCustom: true\n    homeCustomLocation: classpath:markdown/home.md\n    enableSearch: false\n    enableFooter: false\n    enableFooterCustom: true\n    footerCustomContent: Apache License 2.0 | Copyright  2019-[浙江八一菜刀股份有限公司](https://gitee.com/xiaoym/knife4j)\n    enableDynamicParameter: true\n    enableDebug: true\n    enableOpenApi: false\n    enableGroup: true\n  cors: false\n  production: false\n  basic:\n    enable: false\n    username: test\n    password: 12313', '7ce1a77e26ec818c5af3d2f809570cbf', '2021-04-01 14:11:14', '2021-04-01 14:11:17', NULL, '172.18.144.1', 'I', 'dev');
INSERT INTO `his_config_info` VALUES (8, 12, 'easy-archetype-cloud-archetype.yml', 'DEFAULT_GROUP', 'aaaa', '## 日志配置\nlogging:\n  level:\n    com.easy: debug\n    org.springframework: warn\nspring:\n  thymeleaf:\n    # thymeleaf  禁用缓存\n    cache: false\n    # 服务模块\n  devtools:\n    restart:\n      # 热部署开关\n      enabled: true\n      ## 数据库配置\n  datasource:\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://easy-mysql:3306/easy-archetype-dev?characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false&allowMultiQueries=true\n    password: luyanan\n    username: luyanan\n  redis:\n    host: easy-mysql\n    password: luyanan\n    port: 6379\n    database: 3\n# 一些自定义的配置\neasy:\n  archetype:\n    framework:\n      enable: true\n      # Spring 增强\n      spring:\n        enable: true\n        # 开启cors\n        cors:\n          enable: true\n      # 缓存增强\n      cache:\n        enable: true\n      logger:\n        enable: true\n        # 是否开启日志打印\n        logger-print: true\n      mybatis-plus:\n        enable: false\n      thread:\n        enable: true\n    security:\n      oauth:\n        token-signing-key: easy-archetype\n        client:\n          authorization-server-host: http://localhost:9001/\n          client-id: c1\n          client-secret: secret\n          resource-id: res1\n      ignoring-login-url:\n        - /actuator/**\n        - /oauth/*\n        - /token/**\n\n# 接口文档\nknife4j:\n  enable: true\n  documents:\n    - group: 2.X版本\n      name: 接口签名\n      locations: classpath:sign/*\n  setting:\n    language: zh-CN\n    enableSwaggerModels: true\n    enableDocumentManage: true\n    swaggerModelName: 实体类列表\n    enableVersion: true\n    enableReloadCacheParameter: false\n    enableAfterScript: true\n    enableFilterMultipartApiMethodType: POST\n    enableFilterMultipartApis: false\n    enableRequestCache: true\n    enableHost: false\n    enableHostText: 192.168.0.193:8000\n    enableHomeCustom: true\n    homeCustomLocation: classpath:markdown/home.md\n    enableSearch: false\n    enableFooter: false\n    enableFooterCustom: true\n    footerCustomContent: Apache License 2.0 | Copyright  2019-[浙江八一菜刀股份有限公司](https://gitee.com/xiaoym/knife4j)\n    enableDynamicParameter: true\n    enableDebug: true\n    enableOpenApi: false\n    enableGroup: true\n  cors: false\n  production: false\n  basic:\n    enable: false\n    username: test\n    password: 12313', '7ce1a77e26ec818c5af3d2f809570cbf', '2021-04-01 14:12:08', '2021-04-01 14:12:11', NULL, '172.18.144.1', 'D', 'dev');
INSERT INTO `his_config_info` VALUES (0, 13, 'easy-archetype-cloud-archetype.yml', 'DEFAULT_GROUP', '', '## 日志配置\r\nlogging:\r\n  level:\r\n    com.easy: debug\r\n    org.springframework: warn\r\nspring:\r\n  thymeleaf:\r\n    # thymeleaf  禁用缓存\r\n    cache: false\r\n    # 服务模块\r\n  devtools:\r\n    restart:\r\n      # 热部署开关\r\n      enabled: true\r\n      ## 数据库配置\r\n  datasource:\r\n    driver-class-name: com.mysql.cj.jdbc.Driver\r\n    url: jdbc:mysql://easy-mysql:3306/easy-archetype-dev?characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false&allowMultiQueries=true\r\n    password: luyanan\r\n    username: luyanan\r\n  redis:\r\n    host: easy-mysql\r\n    password: luyanan\r\n    port: 6379\r\n    database: 3\r\n# 一些自定义的配置\r\neasy:\r\n  archetype:\r\n    framework:\r\n      enable: true\r\n      # Spring 增强\r\n      spring:\r\n        enable: true\r\n        # 开启cors\r\n        cors:\r\n          enable: true\r\n      # 缓存增强\r\n      cache:\r\n        enable: true\r\n      logger:\r\n        enable: true\r\n        # 是否开启日志打印\r\n        logger-print: true\r\n      mybatis-plus:\r\n        enable: false\r\n      thread:\r\n        enable: true\r\n    security:\r\n      oauth:\r\n        token-signing-key: easy-archetype\r\n        client:\r\n          authorization-server-host: http://localhost:9001/\r\n          client-id: c1\r\n          client-secret: secret\r\n          resource-id: res1\r\n      ignoring-login-url:\r\n        - /actuator/**\r\n        - /oauth/*\r\n        - /token/**\r\n\r\n# 接口文档\r\nknife4j:\r\n  enable: true\r\n  documents:\r\n    - group: 2.X版本\r\n      name: 接口签名\r\n      locations: classpath:sign/*\r\n  setting:\r\n    language: zh-CN\r\n    enableSwaggerModels: true\r\n    enableDocumentManage: true\r\n    swaggerModelName: 实体类列表\r\n    enableVersion: true\r\n    enableReloadCacheParameter: false\r\n    enableAfterScript: true\r\n    enableFilterMultipartApiMethodType: POST\r\n    enableFilterMultipartApis: false\r\n    enableRequestCache: true\r\n    enableHost: false\r\n    enableHostText: 192.168.0.193:8000\r\n    enableHomeCustom: true\r\n    homeCustomLocation: classpath:markdown/home.md\r\n    enableSearch: false\r\n    enableFooter: false\r\n    enableFooterCustom: true\r\n    footerCustomContent: Apache License 2.0 | Copyright  2019-[浙江八一菜刀股份有限公司](https://gitee.com/xiaoym/knife4j)\r\n    enableDynamicParameter: true\r\n    enableDebug: true\r\n    enableOpenApi: false\r\n    enableGroup: true\r\n  cors: false\r\n  production: false\r\n  basic:\r\n    enable: false\r\n    username: test\r\n    password: 12313', 'f65c3e2ad3de614c7d903e728041f2dd', '2021-04-01 14:12:48', '2021-04-01 14:12:51', NULL, '172.18.144.1', 'I', 'dev');
INSERT INTO `his_config_info` VALUES (0, 14, 'easy-archetype-cloud-archetype', 'DEFAULT_GROUP', 'aaaa', '## 日志配置\nlogging:\n  level:\n    com.easy: debug\n    org.springframework: warn\nspring:\n  thymeleaf:\n    # thymeleaf  禁用缓存\n    cache: false\n    # 服务模块\n  devtools:\n    restart:\n      # 热部署开关\n      enabled: true\n      ## 数据库配置\n  datasource:\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://easy-mysql:3306/easy-archetype-dev?characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false&allowMultiQueries=true\n    password: luyanan\n    username: luyanan\n  redis:\n    host: easy-mysql\n    password: luyanan\n    port: 6379\n    database: 3\n# 一些自定义的配置\neasy:\n  archetype:\n    framework:\n      enable: true\n      # Spring 增强\n      spring:\n        enable: true\n        # 开启cors\n        cors:\n          enable: true\n      # 缓存增强\n      cache:\n        enable: true\n      logger:\n        enable: true\n        # 是否开启日志打印\n        logger-print: true\n      mybatis-plus:\n        enable: false\n      thread:\n        enable: true\n    security:\n      oauth:\n        token-signing-key: easy-archetype\n        client:\n          authorization-server-host: http://localhost:9001/\n          client-id: c1\n          client-secret: secret\n          resource-id: res1\n      ignoring-login-url:\n        - /actuator/**\n        - /oauth/*\n        - /token/**\n\n# 接口文档\nknife4j:\n  enable: true\n  documents:\n    - group: 2.X版本\n      name: 接口签名\n      locations: classpath:sign/*\n  setting:\n    language: zh-CN\n    enableSwaggerModels: true\n    enableDocumentManage: true\n    swaggerModelName: 实体类列表\n    enableVersion: true\n    enableReloadCacheParameter: false\n    enableAfterScript: true\n    enableFilterMultipartApiMethodType: POST\n    enableFilterMultipartApis: false\n    enableRequestCache: true\n    enableHost: false\n    enableHostText: 192.168.0.193:8000\n    enableHomeCustom: true\n    homeCustomLocation: classpath:markdown/home.md\n    enableSearch: false\n    enableFooter: false\n    enableFooterCustom: true\n    footerCustomContent: Apache License 2.0 | Copyright  2019-[浙江八一菜刀股份有限公司](https://gitee.com/xiaoym/knife4j)\n    enableDynamicParameter: true\n    enableDebug: true\n    enableOpenApi: false\n    enableGroup: true\n  cors: false\n  production: false\n  basic:\n    enable: false\n    username: test\n    password: 12313', '7ce1a77e26ec818c5af3d2f809570cbf', '2021-04-01 14:14:16', '2021-04-01 14:14:19', NULL, '172.18.144.1', 'I', '');
INSERT INTO `his_config_info` VALUES (0, 15, 'easy-archetype-cloud-archetype.yml', 'DEFAULT_GROUP', '', '## 日志配置\r\nlogging:\r\n  level:\r\n    com.easy: debug\r\n    org.springframework: warn\r\nspring:\r\n  thymeleaf:\r\n    # thymeleaf  禁用缓存\r\n    cache: false\r\n    # 服务模块\r\n  devtools:\r\n    restart:\r\n      # 热部署开关\r\n      enabled: true\r\n      ## 数据库配置\r\n  datasource:\r\n    driver-class-name: com.mysql.cj.jdbc.Driver\r\n    url: jdbc:mysql://easy-mysql:3306/easy-archetype-dev?characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false&allowMultiQueries=true\r\n    password: luyanan\r\n    username: luyanan\r\n  redis:\r\n    host: easy-mysql\r\n    password: luyanan\r\n    port: 6379\r\n    database: 3\r\n# 一些自定义的配置\r\neasy:\r\n  archetype:\r\n    framework:\r\n      enable: true\r\n      # Spring 增强\r\n      spring:\r\n        enable: true\r\n        # 开启cors\r\n        cors:\r\n          enable: true\r\n      # 缓存增强\r\n      cache:\r\n        enable: true\r\n      logger:\r\n        enable: true\r\n        # 是否开启日志打印\r\n        logger-print: true\r\n      mybatis-plus:\r\n        enable: false\r\n      thread:\r\n        enable: true\r\n    security:\r\n      oauth:\r\n        token-signing-key: easy-archetype\r\n        client:\r\n          authorization-server-host: http://localhost:9001/\r\n          client-id: c1\r\n          client-secret: secret\r\n          resource-id: res1\r\n      ignoring-login-url:\r\n        - /actuator/**\r\n        - /oauth/*\r\n        - /token/**\r\n\r\n# 接口文档\r\nknife4j:\r\n  enable: true\r\n  documents:\r\n    - group: 2.X版本\r\n      name: 接口签名\r\n      locations: classpath:sign/*\r\n  setting:\r\n    language: zh-CN\r\n    enableSwaggerModels: true\r\n    enableDocumentManage: true\r\n    swaggerModelName: 实体类列表\r\n    enableVersion: true\r\n    enableReloadCacheParameter: false\r\n    enableAfterScript: true\r\n    enableFilterMultipartApiMethodType: POST\r\n    enableFilterMultipartApis: false\r\n    enableRequestCache: true\r\n    enableHost: false\r\n    enableHostText: 192.168.0.193:8000\r\n    enableHomeCustom: true\r\n    homeCustomLocation: classpath:markdown/home.md\r\n    enableSearch: false\r\n    enableFooter: false\r\n    enableFooterCustom: true\r\n    footerCustomContent: Apache License 2.0 | Copyright  2019-[浙江八一菜刀股份有限公司](https://gitee.com/xiaoym/knife4j)\r\n    enableDynamicParameter: true\r\n    enableDebug: true\r\n    enableOpenApi: false\r\n    enableGroup: true\r\n  cors: false\r\n  production: false\r\n  basic:\r\n    enable: false\r\n    username: test\r\n    password: 12313', 'f65c3e2ad3de614c7d903e728041f2dd', '2021-04-01 14:14:16', '2021-04-01 14:14:19', NULL, '172.18.144.1', 'I', '');
INSERT INTO `his_config_info` VALUES (10, 16, 'easy-archetype-cloud-archetype', 'DEFAULT_GROUP', 'aaaa', '## 日志配置\nlogging:\n  level:\n    com.easy: debug\n    org.springframework: warn\nspring:\n  thymeleaf:\n    # thymeleaf  禁用缓存\n    cache: false\n    # 服务模块\n  devtools:\n    restart:\n      # 热部署开关\n      enabled: true\n      ## 数据库配置\n  datasource:\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://easy-mysql:3306/easy-archetype-dev?characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false&allowMultiQueries=true\n    password: luyanan\n    username: luyanan\n  redis:\n    host: easy-mysql\n    password: luyanan\n    port: 6379\n    database: 3\n# 一些自定义的配置\neasy:\n  archetype:\n    framework:\n      enable: true\n      # Spring 增强\n      spring:\n        enable: true\n        # 开启cors\n        cors:\n          enable: true\n      # 缓存增强\n      cache:\n        enable: true\n      logger:\n        enable: true\n        # 是否开启日志打印\n        logger-print: true\n      mybatis-plus:\n        enable: false\n      thread:\n        enable: true\n    security:\n      oauth:\n        token-signing-key: easy-archetype\n        client:\n          authorization-server-host: http://localhost:9001/\n          client-id: c1\n          client-secret: secret\n          resource-id: res1\n      ignoring-login-url:\n        - /actuator/**\n        - /oauth/*\n        - /token/**\n\n# 接口文档\nknife4j:\n  enable: true\n  documents:\n    - group: 2.X版本\n      name: 接口签名\n      locations: classpath:sign/*\n  setting:\n    language: zh-CN\n    enableSwaggerModels: true\n    enableDocumentManage: true\n    swaggerModelName: 实体类列表\n    enableVersion: true\n    enableReloadCacheParameter: false\n    enableAfterScript: true\n    enableFilterMultipartApiMethodType: POST\n    enableFilterMultipartApis: false\n    enableRequestCache: true\n    enableHost: false\n    enableHostText: 192.168.0.193:8000\n    enableHomeCustom: true\n    homeCustomLocation: classpath:markdown/home.md\n    enableSearch: false\n    enableFooter: false\n    enableFooterCustom: true\n    footerCustomContent: Apache License 2.0 | Copyright  2019-[浙江八一菜刀股份有限公司](https://gitee.com/xiaoym/knife4j)\n    enableDynamicParameter: true\n    enableDebug: true\n    enableOpenApi: false\n    enableGroup: true\n  cors: false\n  production: false\n  basic:\n    enable: false\n    username: test\n    password: 12313', '7ce1a77e26ec818c5af3d2f809570cbf', '2021-04-01 14:15:31', '2021-04-01 14:15:33', NULL, '172.18.144.1', 'D', '');
INSERT INTO `his_config_info` VALUES (9, 17, 'easy-archetype-cloud-archetype.yml', 'DEFAULT_GROUP', '', '## 日志配置\r\nlogging:\r\n  level:\r\n    com.easy: debug\r\n    org.springframework: warn\r\nspring:\r\n  thymeleaf:\r\n    # thymeleaf  禁用缓存\r\n    cache: false\r\n    # 服务模块\r\n  devtools:\r\n    restart:\r\n      # 热部署开关\r\n      enabled: true\r\n      ## 数据库配置\r\n  datasource:\r\n    driver-class-name: com.mysql.cj.jdbc.Driver\r\n    url: jdbc:mysql://easy-mysql:3306/easy-archetype-dev?characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false&allowMultiQueries=true\r\n    password: luyanan\r\n    username: luyanan\r\n  redis:\r\n    host: easy-mysql\r\n    password: luyanan\r\n    port: 6379\r\n    database: 3\r\n# 一些自定义的配置\r\neasy:\r\n  archetype:\r\n    framework:\r\n      enable: true\r\n      # Spring 增强\r\n      spring:\r\n        enable: true\r\n        # 开启cors\r\n        cors:\r\n          enable: true\r\n      # 缓存增强\r\n      cache:\r\n        enable: true\r\n      logger:\r\n        enable: true\r\n        # 是否开启日志打印\r\n        logger-print: true\r\n      mybatis-plus:\r\n        enable: false\r\n      thread:\r\n        enable: true\r\n    security:\r\n      oauth:\r\n        token-signing-key: easy-archetype\r\n        client:\r\n          authorization-server-host: http://localhost:9001/\r\n          client-id: c1\r\n          client-secret: secret\r\n          resource-id: res1\r\n      ignoring-login-url:\r\n        - /actuator/**\r\n        - /oauth/*\r\n        - /token/**\r\n\r\n# 接口文档\r\nknife4j:\r\n  enable: true\r\n  documents:\r\n    - group: 2.X版本\r\n      name: 接口签名\r\n      locations: classpath:sign/*\r\n  setting:\r\n    language: zh-CN\r\n    enableSwaggerModels: true\r\n    enableDocumentManage: true\r\n    swaggerModelName: 实体类列表\r\n    enableVersion: true\r\n    enableReloadCacheParameter: false\r\n    enableAfterScript: true\r\n    enableFilterMultipartApiMethodType: POST\r\n    enableFilterMultipartApis: false\r\n    enableRequestCache: true\r\n    enableHost: false\r\n    enableHostText: 192.168.0.193:8000\r\n    enableHomeCustom: true\r\n    homeCustomLocation: classpath:markdown/home.md\r\n    enableSearch: false\r\n    enableFooter: false\r\n    enableFooterCustom: true\r\n    footerCustomContent: Apache License 2.0 | Copyright  2019-[浙江八一菜刀股份有限公司](https://gitee.com/xiaoym/knife4j)\r\n    enableDynamicParameter: true\r\n    enableDebug: true\r\n    enableOpenApi: false\r\n    enableGroup: true\r\n  cors: false\r\n  production: false\r\n  basic:\r\n    enable: false\r\n    username: test\r\n    password: 12313', 'f65c3e2ad3de614c7d903e728041f2dd', '2021-04-01 14:17:38', '2021-04-01 14:17:41', NULL, '172.18.144.1', 'D', 'dev');
INSERT INTO `his_config_info` VALUES (7, 18, 'easy-archetype-cloud-archetype', 'DEFAULT_GROUP', 'aaaa', '## 日志配置\nlogging:\n  level:\n    com.easy: debug\n    org.springframework: warn\nspring:\n  thymeleaf:\n    # thymeleaf  禁用缓存\n    cache: false\n    # 服务模块\n  devtools:\n    restart:\n      # 热部署开关\n      enabled: true\n      ## 数据库配置\n  datasource:\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://easy-mysql:3306/easy-archetype-dev?characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false&allowMultiQueries=true\n    password: luyanan\n    username: luyanan\n  redis:\n    host: easy-mysql\n    password: luyanan\n    port: 6379\n    database: 3\n# 一些自定义的配置\neasy:\n  archetype:\n    framework:\n      enable: true\n      # Spring 增强\n      spring:\n        enable: true\n        # 开启cors\n        cors:\n          enable: true\n      # 缓存增强\n      cache:\n        enable: true\n      logger:\n        enable: true\n        # 是否开启日志打印\n        logger-print: true\n      mybatis-plus:\n        enable: false\n      thread:\n        enable: true\n    security:\n      oauth:\n        token-signing-key: easy-archetype\n        client:\n          authorization-server-host: http://localhost:9001/\n          client-id: c1\n          client-secret: secret\n          resource-id: res1\n      ignoring-login-url:\n        - /actuator/**\n        - /oauth/*\n        - /token/**\n\n# 接口文档\nknife4j:\n  enable: true\n  documents:\n    - group: 2.X版本\n      name: 接口签名\n      locations: classpath:sign/*\n  setting:\n    language: zh-CN\n    enableSwaggerModels: true\n    enableDocumentManage: true\n    swaggerModelName: 实体类列表\n    enableVersion: true\n    enableReloadCacheParameter: false\n    enableAfterScript: true\n    enableFilterMultipartApiMethodType: POST\n    enableFilterMultipartApis: false\n    enableRequestCache: true\n    enableHost: false\n    enableHostText: 192.168.0.193:8000\n    enableHomeCustom: true\n    homeCustomLocation: classpath:markdown/home.md\n    enableSearch: false\n    enableFooter: false\n    enableFooterCustom: true\n    footerCustomContent: Apache License 2.0 | Copyright  2019-[浙江八一菜刀股份有限公司](https://gitee.com/xiaoym/knife4j)\n    enableDynamicParameter: true\n    enableDebug: true\n    enableOpenApi: false\n    enableGroup: true\n  cors: false\n  production: false\n  basic:\n    enable: false\n    username: test\n    password: 12313', '7ce1a77e26ec818c5af3d2f809570cbf', '2021-04-01 14:17:54', '2021-04-01 14:17:56', NULL, '172.18.144.1', 'U', 'dev');
INSERT INTO `his_config_info` VALUES (11, 19, 'easy-archetype-cloud-archetype.yml', 'DEFAULT_GROUP', '', '## 日志配置\r\nlogging:\r\n  level:\r\n    com.easy: debug\r\n    org.springframework: warn\r\nspring:\r\n  thymeleaf:\r\n    # thymeleaf  禁用缓存\r\n    cache: false\r\n    # 服务模块\r\n  devtools:\r\n    restart:\r\n      # 热部署开关\r\n      enabled: true\r\n      ## 数据库配置\r\n  datasource:\r\n    driver-class-name: com.mysql.cj.jdbc.Driver\r\n    url: jdbc:mysql://easy-mysql:3306/easy-archetype-dev?characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false&allowMultiQueries=true\r\n    password: luyanan\r\n    username: luyanan\r\n  redis:\r\n    host: easy-mysql\r\n    password: luyanan\r\n    port: 6379\r\n    database: 3\r\n# 一些自定义的配置\r\neasy:\r\n  archetype:\r\n    framework:\r\n      enable: true\r\n      # Spring 增强\r\n      spring:\r\n        enable: true\r\n        # 开启cors\r\n        cors:\r\n          enable: true\r\n      # 缓存增强\r\n      cache:\r\n        enable: true\r\n      logger:\r\n        enable: true\r\n        # 是否开启日志打印\r\n        logger-print: true\r\n      mybatis-plus:\r\n        enable: false\r\n      thread:\r\n        enable: true\r\n    security:\r\n      oauth:\r\n        token-signing-key: easy-archetype\r\n        client:\r\n          authorization-server-host: http://localhost:9001/\r\n          client-id: c1\r\n          client-secret: secret\r\n          resource-id: res1\r\n      ignoring-login-url:\r\n        - /actuator/**\r\n        - /oauth/*\r\n        - /token/**\r\n\r\n# 接口文档\r\nknife4j:\r\n  enable: true\r\n  documents:\r\n    - group: 2.X版本\r\n      name: 接口签名\r\n      locations: classpath:sign/*\r\n  setting:\r\n    language: zh-CN\r\n    enableSwaggerModels: true\r\n    enableDocumentManage: true\r\n    swaggerModelName: 实体类列表\r\n    enableVersion: true\r\n    enableReloadCacheParameter: false\r\n    enableAfterScript: true\r\n    enableFilterMultipartApiMethodType: POST\r\n    enableFilterMultipartApis: false\r\n    enableRequestCache: true\r\n    enableHost: false\r\n    enableHostText: 192.168.0.193:8000\r\n    enableHomeCustom: true\r\n    homeCustomLocation: classpath:markdown/home.md\r\n    enableSearch: false\r\n    enableFooter: false\r\n    enableFooterCustom: true\r\n    footerCustomContent: Apache License 2.0 | Copyright  2019-[浙江八一菜刀股份有限公司](https://gitee.com/xiaoym/knife4j)\r\n    enableDynamicParameter: true\r\n    enableDebug: true\r\n    enableOpenApi: false\r\n    enableGroup: true\r\n  cors: false\r\n  production: false\r\n  basic:\r\n    enable: false\r\n    username: test\r\n    password: 12313', 'f65c3e2ad3de614c7d903e728041f2dd', '2021-04-03 07:15:28', '2021-04-03 07:15:28', NULL, '172.23.0.1', 'D', '');
INSERT INTO `his_config_info` VALUES (7, 20, 'easy-archetype-cloud-archetype', 'DEFAULT_GROUP', 'aaaa', '## 日志配置\nlogging:\n  level:\n    com.easy: debug\n    org.springframework: warn\nspring:\n  thymeleaf:\n    # thymeleaf  禁用缓存\n    cache: false\n    # 服务模块\n  devtools:\n    restart:\n      # 热部署开关\n      enabled: true\n      ## 数据库配置\n  datasource:\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://122.152.226.81:3306/easy-archetype-dev?characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false&allowMultiQueries=true\n    password: luyanan\n    username: luyanan\n  redis:\n    host: easy-mysql\n    password: luyanan\n    port: 6379\n    database: 3\n# 一些自定义的配置\neasy:\n  archetype:\n    framework:\n      enable: true\n      # Spring 增强\n      spring:\n        enable: true\n        # 开启cors\n        cors:\n          enable: true\n      # 缓存增强\n      cache:\n        enable: true\n      logger:\n        enable: true\n        # 是否开启日志打印\n        logger-print: true\n      mybatis-plus:\n        enable: false\n      thread:\n        enable: true\n    security:\n      oauth:\n        token-signing-key: easy-archetype\n        client:\n          authorization-server-host: http://localhost:9001/\n          client-id: c1\n          client-secret: secret\n          resource-id: res1\n      ignoring-login-url:\n        - /actuator/**\n        - /oauth/*\n        - /token/**\n\n# 接口文档\nknife4j:\n  enable: true\n  documents:\n    - group: 2.X版本\n      name: 接口签名\n      locations: classpath:sign/*\n  setting:\n    language: zh-CN\n    enableSwaggerModels: true\n    enableDocumentManage: true\n    swaggerModelName: 实体类列表\n    enableVersion: true\n    enableReloadCacheParameter: false\n    enableAfterScript: true\n    enableFilterMultipartApiMethodType: POST\n    enableFilterMultipartApis: false\n    enableRequestCache: true\n    enableHost: false\n    enableHostText: 192.168.0.193:8000\n    enableHomeCustom: true\n    homeCustomLocation: classpath:markdown/home.md\n    enableSearch: false\n    enableFooter: false\n    enableFooterCustom: true\n    footerCustomContent: Apache License 2.0 | Copyright  2019-[浙江八一菜刀股份有限公司](https://gitee.com/xiaoym/knife4j)\n    enableDynamicParameter: true\n    enableDebug: true\n    enableOpenApi: false\n    enableGroup: true\n  cors: false\n  production: false\n  basic:\n    enable: false\n    username: test\n    password: 12313', 'ae46a305df054247d3d0f6acbe5cc67b', '2021-04-03 07:16:47', '2021-04-03 07:16:47', NULL, '172.23.0.1', 'D', 'dev');
INSERT INTO `his_config_info` VALUES (0, 21, 'easy-archetype-cloud-archetype.yml', 'DEFAULT_GROUP', '', '## 日志配置\r\nlogging:\r\n  level:\r\n    com.easy: debug\r\n    org.springframework: warn\r\nspring:\r\n  thymeleaf:\r\n    # thymeleaf  禁用缓存\r\n    cache: false\r\n    # 服务模块\r\n  devtools:\r\n    restart:\r\n      # 热部署开关\r\n      enabled: true\r\n      ## 数据库配置\r\n  datasource:\r\n    driver-class-name: com.mysql.cj.jdbc.Driver\r\n    url: jdbc:mysql://122.152.226.81:3306/easy-archetype-dev?characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false&allowMultiQueries=true\r\n    password: luyanan\r\n    username: luyanan\r\n  redis:\r\n    host: easy-mysql\r\n    password: luyanan\r\n    port: 6379\r\n    database: 3\r\n# 一些自定义的配置\r\neasy:\r\n  archetype:\r\n    framework:\r\n      enable: true\r\n      # Spring 增强\r\n      spring:\r\n        enable: true\r\n        # 开启cors\r\n        cors:\r\n          enable: true\r\n      # 缓存增强\r\n      cache:\r\n        enable: true\r\n      logger:\r\n        enable: true\r\n        # 是否开启日志打印\r\n        logger-print: true\r\n      mybatis-plus:\r\n        enable: false\r\n      thread:\r\n        enable: true\r\n    security:\r\n      oauth:\r\n        token-signing-key: easy-archetype\r\n        client:\r\n          authorization-server-host: http://localhost:9001/\r\n          client-id: c1\r\n          client-secret: secret\r\n          resource-id: res1\r\n      ignoring-login-url:\r\n        - /actuator/**\r\n        - /oauth/*\r\n        - /token/**\r\n\r\n# 接口文档\r\nknife4j:\r\n  enable: true\r\n  documents:\r\n    - group: 2.X版本\r\n      name: 接口签名\r\n      locations: classpath:sign/*\r\n  setting:\r\n    language: zh-CN\r\n    enableSwaggerModels: true\r\n    enableDocumentManage: true\r\n    swaggerModelName: 实体类列表\r\n    enableVersion: true\r\n    enableReloadCacheParameter: false\r\n    enableAfterScript: true\r\n    enableFilterMultipartApiMethodType: POST\r\n    enableFilterMultipartApis: false\r\n    enableRequestCache: true\r\n    enableHost: false\r\n    enableHostText: 192.168.0.193:8000\r\n    enableHomeCustom: true\r\n    homeCustomLocation: classpath:markdown/home.md\r\n    enableSearch: false\r\n    enableFooter: false\r\n    enableFooterCustom: true\r\n    footerCustomContent: Apache License 2.0 | Copyright  2019-[浙江八一菜刀股份有限公司](https://gitee.com/xiaoym/knife4j)\r\n    enableDynamicParameter: true\r\n    enableDebug: true\r\n    enableOpenApi: false\r\n    enableGroup: true\r\n  cors: false\r\n  production: false\r\n  basic:\r\n    enable: false\r\n    username: test\r\n    password: 12313', 'b84d541a7d2070c6257f128dcfe0c1ce', '2021-04-03 07:17:04', '2021-04-03 07:17:04', NULL, '172.23.0.1', 'I', 'dev');
INSERT INTO `his_config_info` VALUES (13, 22, 'easy-archetype-cloud-archetype.yml', 'DEFAULT_GROUP', '', '## 日志配置\r\nlogging:\r\n  level:\r\n    com.easy: debug\r\n    org.springframework: warn\r\nspring:\r\n  thymeleaf:\r\n    # thymeleaf  禁用缓存\r\n    cache: false\r\n    # 服务模块\r\n  devtools:\r\n    restart:\r\n      # 热部署开关\r\n      enabled: true\r\n      ## 数据库配置\r\n  datasource:\r\n    driver-class-name: com.mysql.cj.jdbc.Driver\r\n    url: jdbc:mysql://122.152.226.81:3306/easy-archetype-dev?characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false&allowMultiQueries=true\r\n    password: luyanan\r\n    username: luyanan\r\n  redis:\r\n    host: easy-mysql\r\n    password: luyanan\r\n    port: 6379\r\n    database: 3\r\n# 一些自定义的配置\r\neasy:\r\n  archetype:\r\n    framework:\r\n      enable: true\r\n      # Spring 增强\r\n      spring:\r\n        enable: true\r\n        # 开启cors\r\n        cors:\r\n          enable: true\r\n      # 缓存增强\r\n      cache:\r\n        enable: true\r\n      logger:\r\n        enable: true\r\n        # 是否开启日志打印\r\n        logger-print: true\r\n      mybatis-plus:\r\n        enable: false\r\n      thread:\r\n        enable: true\r\n    security:\r\n      oauth:\r\n        token-signing-key: easy-archetype\r\n        client:\r\n          authorization-server-host: http://localhost:9001/\r\n          client-id: c1\r\n          client-secret: secret\r\n          resource-id: res1\r\n      ignoring-login-url:\r\n        - /actuator/**\r\n        - /oauth/*\r\n        - /token/**\r\n\r\n# 接口文档\r\nknife4j:\r\n  enable: true\r\n  documents:\r\n    - group: 2.X版本\r\n      name: 接口签名\r\n      locations: classpath:sign/*\r\n  setting:\r\n    language: zh-CN\r\n    enableSwaggerModels: true\r\n    enableDocumentManage: true\r\n    swaggerModelName: 实体类列表\r\n    enableVersion: true\r\n    enableReloadCacheParameter: false\r\n    enableAfterScript: true\r\n    enableFilterMultipartApiMethodType: POST\r\n    enableFilterMultipartApis: false\r\n    enableRequestCache: true\r\n    enableHost: false\r\n    enableHostText: 192.168.0.193:8000\r\n    enableHomeCustom: true\r\n    homeCustomLocation: classpath:markdown/home.md\r\n    enableSearch: false\r\n    enableFooter: false\r\n    enableFooterCustom: true\r\n    footerCustomContent: Apache License 2.0 | Copyright  2019-[浙江八一菜刀股份有限公司](https://gitee.com/xiaoym/knife4j)\r\n    enableDynamicParameter: true\r\n    enableDebug: true\r\n    enableOpenApi: false\r\n    enableGroup: true\r\n  cors: false\r\n  production: false\r\n  basic:\r\n    enable: false\r\n    username: test\r\n    password: 12313', 'b84d541a7d2070c6257f128dcfe0c1ce', '2021-04-03 07:18:45', '2021-04-03 07:18:44', NULL, '172.23.0.1', 'U', 'dev');
INSERT INTO `his_config_info` VALUES (13, 23, 'easy-archetype-cloud-archetype.yml', 'DEFAULT_GROUP', '', '## 日志配置\nlogging:\n  level:\n    com.easy: debug\n    org.springframework: warn\nspring:\n  thymeleaf:\n    # thymeleaf  禁用缓存\n    cache: false\n    # 服务模块\n  devtools:\n    restart:\n      # 热部署开关\n      enabled: true\n      ## 数据库配置\n  datasource:\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://122.152.226.81:3306/easy-archetype-dev?characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false&allowMultiQueries=true\n    password: luyanan\n    username: luyanan\n  redis:\n    host: easy-mysql\n    password: luyanan\n    port: 6379\n    database: 3\n# 一些自定义的配置\neasy:\n  archetype:\n    framework:\n      enable: true\n      # Spring 增强\n      spring:\n        enable: true\n        # 开启cors\n        cors:\n          enable: true\n      # 缓存增强\n      cache:\n        enable: true\n      logger:\n        enable: true\n        # 是否开启日志打印\n        logger-print: true\n      mybatis-plus:\n        enable: false\n      thread:\n        enable: true\n    security:\n      oauth:\n        token-signing-key: easy-archetype\n        client:\n          authorization-server-host: http://localhost:9001/\n          client-id: c1\n          client-secret: secret\n          resource-id: res1\n      ignoring-login-url:\n        - /actuator/**\n        - /oauth/*\n        - /token/**', '5c8aea5fbe91aa0f79ba37eaf3dc8a39', '2021-04-03 07:19:47', '2021-04-03 07:19:47', NULL, '172.23.0.1', 'U', 'dev');
INSERT INTO `his_config_info` VALUES (13, 24, 'easy-archetype-cloud-archetype.yml', 'DEFAULT_GROUP', '', '## 日志配置\nlogging:\n  level:\n    com.easy: debug\n    org.springframework: warn\nspring:\n  thymeleaf:\n    # thymeleaf  禁用缓存\n    cache: false\n    # 服务模块\n  devtools:\n    restart:\n      # 热部署开关\n      enabled: true\n      ## 数据库配置\n  datasource:\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://122.152.226.81:3306/easy-archetype-dev?characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false&allowMultiQueries=true\n    password: luyanan\n    username: luyanan\n  redis:\n    host: easy-mysql\n    password: luyanan\n    port: 6379\n    database: 3\n# 一些自定义的配置\neasy:\n  archetype:\n    framework:\n      enable: true\n      # Spring 增强\n      spring:\n        enable: true\n        # 开启cors\n        cors:\n          enable: true\n      # 缓存增强\n      cache:\n        enable: true\n      logger:\n        enable: true\n        # 是否开启日志打印\n        logger-print: true\n      mybatis-plus:\n        enable: false\n      thread:\n        enable: true\n    security:\n      oauth:\n        token-signing-key: easy-archetype\n        client:\n          authorization-server-host: \'http://localhost:9001/\'\n          client-id: c1\n          client-secret: secret\n          resource-id: res1\n      ignoring-login-url:\n        - /actuator/**\n        - /oauth/*\n        - /token/**', '0f217f88af6c11d0b00473385f03c98b', '2021-04-03 07:20:46', '2021-04-03 07:20:46', NULL, '172.23.0.1', 'U', 'dev');
INSERT INTO `his_config_info` VALUES (13, 25, 'easy-archetype-cloud-archetype.yml', 'DEFAULT_GROUP', '', '## 日志配置\nlogging:\n  level:\n    com.easy: debug\n    org.springframework: warn\nspring:\n  thymeleaf:\n    # thymeleaf  禁用缓存\n    cache: false\n    # 服务模块\n  devtools:\n    restart:\n      # 热部署开关\n      enabled: true\n      ## 数据库配置\n  datasource:\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://122.152.226.81:3306/easy-archetype-dev?characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false&allowMultiQueries=true\n    password: luyanan\n    username: luyanan\n  redis:\n    host: easy-mysql\n    password: luyanan\n    port: 6379\n    database: 3\n', '3718de91228731c879df69f899ecb333', '2021-04-03 07:21:26', '2021-04-03 07:21:26', NULL, '172.23.0.1', 'U', 'dev');
INSERT INTO `his_config_info` VALUES (13, 26, 'easy-archetype-cloud-archetype.yml', 'DEFAULT_GROUP', '', '\nspring:\n  datasource:\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://122.152.226.81:3306/easy-archetype-dev?characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false&allowMultiQueries=true\n    password: luyanan\n    username: luyanan\n  redis:\n    host: easy-mysql\n    password: luyanan\n    port: 6379\n    database: 3\n', 'c0d09334371e3483b4bbc30ab217d4a0', '2021-04-03 07:22:12', '2021-04-03 07:22:13', NULL, '172.23.0.1', 'D', 'dev');
INSERT INTO `his_config_info` VALUES (1, 27, 'application.yml', 'DEFAULT_GROUP', '', '# 项目全局配置\r\n## feign配置\r\nfeign:\r\n  sentinel:\r\n    enabled: true\r\n  okhttp:\r\n    enabled: true\r\n  httpclient:\r\n    enabled: false\r\n  client:\r\n    config:\r\n      default:\r\n        connectTimeout: 10000\r\n        readTimeout: 10000\r\n  compression:\r\n    request:\r\n      enabled: true\r\n    response:\r\n      enabled: true\r\n\r\nribbon:\r\n  ReadTimeout: 10000\r\n  ConnectTimeout: 10000\r\n\r\n\r\n\r\n\r\nserver:\r\n  #  tomcat:\r\n  #    # tomcat的URI 编码\r\n  #    uri-encoding: utf-8\r\n  #    # tomcat的最大线程池,默认为200\r\n  #    max-threads: 800\r\n  #    # tomcat 启动初始化的线程数量，默认为\r\n  #    min-spare-threads: 30\r\n  undertow:\r\n    accesslog:\r\n      # 是否打开 undertow 日志，默认为 false\r\n      enabled: false\r\n      # 设置访问日志所在目录\r\n      dir: logs\r\n      # 指定工作者线程的 I/0 线程数，默认为 2 或者 CPU 的个数\r\n    #    io-threads: 8\r\n    #    # 指定工作者线程个数，默认为 I/O 线程个数的 8 倍\r\n    #    worker-threads: 64\r\n    # 设置 HTTP POST 内容的最大长度，默认不做限制\r\n    max-http-post-size: -1B\r\n    # 是否分配的直接内存(NIO直接分配的堆外内存)\r\n    direct-buffers: true\r\n    # 以下的配置会影响buffer,这些buffer会用于服务器连接的IO操作,有点类似netty的池化内存管理\r\n    # 每块buffer的空间大小,越小的空间被利用越充分，不要设置太大，以免影响其他应用，合适即可\r\n    buffer-size: 1024\r\n# Spring的配置\r\nspring:\r\n  # 模板引擎配置\r\n  thymeleaf:\r\n    mode: HTML\r\n    # 编码\r\n    encoding: UTF-8\r\n    # 资源信息\r\n  messages:\r\n    # 国际化资源文件\r\n    basename: i18n/messages\r\n    # 序列化\r\n  jackson:\r\n    time-zone: GMT+8\r\n    date-format: yyyy-MM-dd HH:mm:ss\r\n  # 文件上传\r\n  servlet:\r\n    multipart:\r\n      # 单个文件大小\r\n      max-file-size: 100MB\r\n      # 设置总上传的文件大小\r\n      max-request-size: 200MB\r\n      enabled: true\r\n  main:\r\n    allow-bean-definition-overriding: true\r\n  http:\r\n    encoding:\r\n      charset: UTF-8\r\n      enabled: true\r\n      force: true\r\n  session:\r\n    store-type: redis\r\n    timeout: 5h\r\n  cloud:\r\n    ## sentinel 配置\r\n    sentinel:\r\n      ## #取消Sentinel控制台懒加载\r\n      eager: true\r\n      filter:\r\n        url-patterns: /**\r\n      transport:\r\n        ## 控制台地址\r\n        dashboard: http://localhost:8719\r\nmanagement:\r\n  endpoints:\r\n    web:\r\n      exposure:\r\n        include: \'*\'\r\n  endpoint:\r\n    health:\r\n      show-details: ALWAYS\r\n## mybatis-plus配置\r\nmybatis-plus:\r\n  global-config:\r\n    banner: false\r\n    db-config:\r\n      id-type: auto\r\n      select-strategy: not_empty\r\n      update-strategy: not_empty\r\n      logic-delete-field: delFlag\r\n      logic-delete-value: 2\r\n      logic-not-delete-value: 0\r\n  # 配置mapper的扫描，找到所有的mapper.xml映射文件\r\n  mapper-locations: classpath*:mapper/**/*Mapper.xml\r\n', 'b405deb83fa34197f2fd0a2f2f1ddd50', '2021-04-03 07:23:09', '2021-04-03 07:23:09', NULL, '172.23.0.1', 'U', 'dev');
INSERT INTO `his_config_info` VALUES (1, 28, 'application.yml', 'DEFAULT_GROUP', '', '# 项目全局配置\n## feign配置\nfeign:\n  sentinel:\n    enabled: true\n  okhttp:\n    enabled: true\n  httpclient:\n    enabled: false\n  client:\n    config:\n      default:\n        connectTimeout: 10000\n        readTimeout: 10000\n  compression:\n    request:\n      enabled: true\n    response:\n      enabled: true\n\nribbon:\n  ReadTimeout: 10000\n  ConnectTimeout: 10000\n\n\n\n\nserver:\n  #  tomcat:\n  #    # tomcat的URI 编码\n  #    uri-encoding: utf-8\n  #    # tomcat的最大线程池,默认为200\n  #    max-threads: 800\n  #    # tomcat 启动初始化的线程数量，默认为\n  #    min-spare-threads: 30\n  undertow:\n    accesslog:\n      # 是否打开 undertow 日志，默认为 false\n      enabled: false\n      # 设置访问日志所在目录\n      dir: logs\n      # 指定工作者线程的 I/0 线程数，默认为 2 或者 CPU 的个数\n    #    io-threads: 8\n    #    # 指定工作者线程个数，默认为 I/O 线程个数的 8 倍\n    #    worker-threads: 64\n    # 设置 HTTP POST 内容的最大长度，默认不做限制\n    max-http-post-size: -1B\n    # 是否分配的直接内存(NIO直接分配的堆外内存)\n    direct-buffers: true\n    # 以下的配置会影响buffer,这些buffer会用于服务器连接的IO操作,有点类似netty的池化内存管理\n    # 每块buffer的空间大小,越小的空间被利用越充分，不要设置太大，以免影响其他应用，合适即可\n    buffer-size: 1024\n# Spring的配置\nspring:\n  # 模板引擎配置\n  thymeleaf:\n    mode: HTML\n    # 编码\n    encoding: UTF-8\n    # 资源信息\n  messages:\n    # 国际化资源文件\n    basename: i18n/messages\n    # 序列化\n  jackson:\n    time-zone: GMT+8\n    date-format: yyyy-MM-dd HH:mm:ss\n  # 文件上传\n  servlet:\n    multipart:\n      # 单个文件大小\n      max-file-size: 100MB\n      # 设置总上传的文件大小\n      max-request-size: 200MB\n      enabled: true\n  main:\n    allow-bean-definition-overriding: true\n  http:\n    encoding:\n      charset: UTF-8\n      enabled: true\n      force: true\n  session:\n    store-type: redis\n    timeout: 5h\n  cloud:\n    ## sentinel 配置\n    sentinel:\n      ## #取消Sentinel控制台懒加载\n      eager: true\n      filter:\n        url-patterns: \'/**\'\n      transport:\n        ## 控制台地址\n        dashboard: http://localhost:8719\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  endpoint:\n    health:\n      show-details: ALWAYS\n## mybatis-plus配置\nmybatis-plus:\n  global-config:\n    banner: false\n    db-config:\n      id-type: auto\n      select-strategy: not_empty\n      update-strategy: not_empty\n      logic-delete-field: delFlag\n      logic-delete-value: 2\n      logic-not-delete-value: 0\n  # 配置mapper的扫描，找到所有的mapper.xml映射文件\n  mapper-locations: \'classpath*:mapper/**/*Mapper.xml\'\n', '9baf5c2040ca82d3e5c63c3d57a1d2c7', '2021-04-03 07:24:34', '2021-04-03 07:24:34', NULL, '172.23.0.1', 'U', 'dev');
INSERT INTO `his_config_info` VALUES (1, 29, 'application.yml', 'DEFAULT_GROUP', '', '# 项目全局配置\n## feign配置\nfeign:\n  sentinel:\n    enabled: true\n  okhttp:\n    enabled: true\n  httpclient:\n    enabled: false\n  client:\n    config:\n      default:\n        connectTimeout: 10000\n        readTimeout: 10000\n  compression:\n    request:\n      enabled: true\n    response:\n      enabled: true\n\nribbon:\n  ReadTimeout: 10000\n  ConnectTimeout: 10000', '3f18648ca5269bb7b8cb4ec2f325897c', '2021-04-03 07:25:20', '2021-04-03 07:25:20', NULL, '172.23.0.1', 'U', 'dev');
INSERT INTO `his_config_info` VALUES (1, 30, 'application.yml', 'DEFAULT_GROUP', '', 'feign:\n  sentinel:\n    enabled: true\n  okhttp:\n    enabled: true\n  httpclient:\n    enabled: false\n  client:\n    config:\n      default:\n        connectTimeout: 10000\n        readTimeout: 10000\n  compression:\n    request:\n      enabled: true\n    response:\n      enabled: true\n\nribbon:\n  ReadTimeout: 10000\n  ConnectTimeout: 10000', 'f1e1a48d234591029eea6fe9d886ac9c', '2021-04-03 07:33:04', '2021-04-03 07:33:04', NULL, '172.23.0.1', 'U', 'dev');
INSERT INTO `his_config_info` VALUES (1, 31, 'application.yml', 'DEFAULT_GROUP', '', 'feign:\n  sentinel:\n    enabled: true\n  okhttp:\n    enabled: true\n  httpclient:\n    enabled: false\n  client:\n    config:\n      default:\n        connectTimeout: 10000\n        readTimeout: 10000\n  compression:\n    request:\n      enabled: true\n    response:\n      enabled: true\n\nribbon:\n  ReadTimeout: 10000\n  ConnectTimeout: 10000\n\n\n\n\nserver:\n  #  tomcat:\n  #    # tomcat的URI 编码\n  #    uri-encoding: utf-8\n  #    # tomcat的最大线程池,默认为200\n  #    max-threads: 800\n  #    # tomcat 启动初始化的线程数量，默认为\n  #    min-spare-threads: 30\n  undertow:\n    accesslog:\n      # 是否打开 undertow 日志，默认为 false\n      enabled: false\n      # 设置访问日志所在目录\n      dir: logs\n      # 指定工作者线程的 I/0 线程数，默认为 2 或者 CPU 的个数\n    #    io-threads: 8\n    #    # 指定工作者线程个数，默认为 I/O 线程个数的 8 倍\n    #    worker-threads: 64\n    # 设置 HTTP POST 内容的最大长度，默认不做限制\n    max-http-post-size: -1B\n    # 是否分配的直接内存(NIO直接分配的堆外内存)\n    direct-buffers: true\n    # 以下的配置会影响buffer,这些buffer会用于服务器连接的IO操作,有点类似netty的池化内存管理\n    # 每块buffer的空间大小,越小的空间被利用越充分，不要设置太大，以免影响其他应用，合适即可\n    buffer-size: 1024\n# Spring的配置\nspring:\n  # 模板引擎配置\n  thymeleaf:\n    mode: HTML\n    # 编码\n    encoding: UTF-8\n    # 资源信息\n  messages:\n    # 国际化资源文件\n    basename: i18n/messages\n    # 序列化\n  jackson:\n    time-zone: GMT+8\n    date-format: yyyy-MM-dd HH:mm:ss\n  # 文件上传\n  servlet:\n    multipart:\n      # 单个文件大小\n      max-file-size: 100MB\n      # 设置总上传的文件大小\n      max-request-size: 200MB\n      enabled: true\n  main:\n    allow-bean-definition-overriding: true\n  http:\n    encoding:\n      charset: UTF-8\n      enabled: true\n      force: true\n  session:\n    store-type: redis\n    timeout: 5h\n  cloud:\n    ## sentinel 配置\n    sentinel:\n      ## #取消Sentinel控制台懒加载\n      eager: true\n      filter:\n        url-patterns: \'/**\'\n      transport:\n        ## 控制台地址\n        dashboard: http://localhost:8719\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  endpoint:\n    health:\n      show-details: ALWAYS\n## mybatis-plus配置\nmybatis-plus:\n  global-config:\n    banner: false\n    db-config:\n      id-type: auto\n      select-strategy: not_empty\n      update-strategy: not_empty\n      logic-delete-field: delFlag\n      logic-delete-value: 2\n      logic-not-delete-value: 0\n  # 配置mapper的扫描，找到所有的mapper.xml映射文件\n  mapper-locations: \'classpath*:mapper/**/*Mapper.xml\'\n', '658aaa126cde8dc964202f24f185d195', '2021-04-03 07:35:44', '2021-04-03 07:35:44', NULL, '172.23.0.1', 'U', 'dev');
INSERT INTO `his_config_info` VALUES (0, 32, 'easy-archetype-cloud-archetype.yml', 'DEFAULT_GROUP', '', '## logger\r\nlogging:\r\n  level:\r\n    com.easy: debug\r\n    org.springframework: warn\r\nspring:\r\n  thymeleaf:\r\n    # thymeleaf  cache\r\n    cache: false\r\n  devtools:\r\n    restart:\r\n      enabled: true\r\n  datasource:\r\n    driver-class-name: com.mysql.cj.jdbc.Driver\r\n    url: jdbc:mysql://122.152.226.81:3306/easy-archetype-dev?characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false&allowMultiQueries=true\r\n    password: luyanan\r\n    username: luyanan\r\n  redis:\r\n    host: easy-mysql\r\n    password: luyanan\r\n    port: 6379\r\n    database: 3\r\n# customer config\r\neasy:\r\n  archetype:\r\n    framework:\r\n      enable: true\r\n      # Spring \r\n      spring:\r\n        enable: true\r\n        # cors\r\n        cors:\r\n          enable: true\r\n      #cache\r\n      cache:\r\n        enable: true\r\n      logger:\r\n        enable: true\r\n        # logger\r\n        logger-print: true\r\n      mybatis-plus:\r\n        enable: false\r\n      thread:\r\n        enable: true\r\n    security:\r\n      oauth:\r\n        token-signing-key: easy-archetype\r\n        client:\r\n          authorization-server-host: http://localhost:9001/\r\n          client-id: c1\r\n          client-secret: secret\r\n          resource-id: res1\r\n      ignoring-login-url:\r\n        - /actuator/**\r\n        - /oauth/*\r\n        - /token/**\r\n\r\n# api\r\nknife4j:\r\n  enable: true\r\n  documents:\r\n    - group: 2.X版本\r\n      name: 接口签名\r\n      locations: classpath:sign/*\r\n  setting:\r\n    language: zh-CN\r\n    enableSwaggerModels: true\r\n    enableDocumentManage: true\r\n    swaggerModelName: 实体类列表\r\n    enableVersion: true\r\n    enableReloadCacheParameter: false\r\n    enableAfterScript: true\r\n    enableFilterMultipartApiMethodType: POST\r\n    enableFilterMultipartApis: false\r\n    enableRequestCache: true\r\n    enableHost: false\r\n    enableHostText: 192.168.0.193:8000\r\n    enableHomeCustom: true\r\n    homeCustomLocation: classpath:markdown/home.md\r\n    enableSearch: false\r\n    enableFooter: false\r\n    enableFooterCustom: true\r\n    footerCustomContent: Apache License 2.0 | Copyright  2019-[浙江八一菜刀股份有限公司](https://gitee.com/xiaoym/knife4j)\r\n    enableDynamicParameter: true\r\n    enableDebug: true\r\n    enableOpenApi: false\r\n    enableGroup: true\r\n  cors: false\r\n  production: false\r\n  basic:\r\n    enable: false\r\n    username: test\r\n    password: 12313', '512a6254df511c3d9fad4038ad9d7909', '2021-04-03 07:38:20', '2021-04-03 07:38:21', NULL, '172.23.0.1', 'I', 'dev');
INSERT INTO `his_config_info` VALUES (23, 33, 'easy-archetype-cloud-archetype.yml', 'DEFAULT_GROUP', '', '## logger\r\nlogging:\r\n  level:\r\n    com.easy: debug\r\n    org.springframework: warn\r\nspring:\r\n  thymeleaf:\r\n    # thymeleaf  cache\r\n    cache: false\r\n  devtools:\r\n    restart:\r\n      enabled: true\r\n  datasource:\r\n    driver-class-name: com.mysql.cj.jdbc.Driver\r\n    url: jdbc:mysql://122.152.226.81:3306/easy-archetype-dev?characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false&allowMultiQueries=true\r\n    password: luyanan\r\n    username: luyanan\r\n  redis:\r\n    host: easy-mysql\r\n    password: luyanan\r\n    port: 6379\r\n    database: 3\r\n# customer config\r\neasy:\r\n  archetype:\r\n    framework:\r\n      enable: true\r\n      # Spring \r\n      spring:\r\n        enable: true\r\n        # cors\r\n        cors:\r\n          enable: true\r\n      #cache\r\n      cache:\r\n        enable: true\r\n      logger:\r\n        enable: true\r\n        # logger\r\n        logger-print: true\r\n      mybatis-plus:\r\n        enable: false\r\n      thread:\r\n        enable: true\r\n    security:\r\n      oauth:\r\n        token-signing-key: easy-archetype\r\n        client:\r\n          authorization-server-host: http://localhost:9001/\r\n          client-id: c1\r\n          client-secret: secret\r\n          resource-id: res1\r\n      ignoring-login-url:\r\n        - /actuator/**\r\n        - /oauth/*\r\n        - /token/**\r\n\r\n# api\r\nknife4j:\r\n  enable: true\r\n  documents:\r\n    - group: 2.X版本\r\n      name: 接口签名\r\n      locations: classpath:sign/*\r\n  setting:\r\n    language: zh-CN\r\n    enableSwaggerModels: true\r\n    enableDocumentManage: true\r\n    swaggerModelName: 实体类列表\r\n    enableVersion: true\r\n    enableReloadCacheParameter: false\r\n    enableAfterScript: true\r\n    enableFilterMultipartApiMethodType: POST\r\n    enableFilterMultipartApis: false\r\n    enableRequestCache: true\r\n    enableHost: false\r\n    enableHostText: 192.168.0.193:8000\r\n    enableHomeCustom: true\r\n    homeCustomLocation: classpath:markdown/home.md\r\n    enableSearch: false\r\n    enableFooter: false\r\n    enableFooterCustom: true\r\n    footerCustomContent: Apache License 2.0 | Copyright  2019-[浙江八一菜刀股份有限公司](https://gitee.com/xiaoym/knife4j)\r\n    enableDynamicParameter: true\r\n    enableDebug: true\r\n    enableOpenApi: false\r\n    enableGroup: true\r\n  cors: false\r\n  production: false\r\n  basic:\r\n    enable: false\r\n    username: test\r\n    password: 12313', '512a6254df511c3d9fad4038ad9d7909', '2021-04-03 07:39:39', '2021-04-03 07:39:39', NULL, '172.23.0.1', 'U', 'dev');
INSERT INTO `his_config_info` VALUES (23, 34, 'easy-archetype-cloud-archetype.yml', 'DEFAULT_GROUP', '', '## logger\nlogging:\n  level:\n    com.easy: debug\n    org.springframework: warn\nspring:\n  thymeleaf:\n    # thymeleaf  cache\n    cache: false\n  devtools:\n    restart:\n      enabled: true\n  datasource:\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://122.152.226.81:3306/easy-archetype-dev?characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false&allowMultiQueries=true\n    password: luyanan\n    username: luyanan\n  redis:\n    host: easy-mysql\n    password: luyanan\n    port: 6379\n    database: 3\n# customer config\neasy:\n  archetype:\n    framework:\n      enable: true\n      # Spring \n      spring:\n        enable: true\n        # cors\n        cors:\n          enable: true\n      #cache\n      cache:\n        enable: true\n      logger:\n        enable: true\n        # logger\n        logger-print: true\n      mybatis-plus:\n        enable: false\n      thread:\n        enable: true\n    security:\n      oauth:\n        token-signing-key: easy-archetype\n        client:\n          authorization-server-host: http://localhost:9001/\n          client-id: c1\n          client-secret: secret\n          resource-id: res1\n      ignoring-login-url:\n        - /actuator/**\n        - /oauth/*\n        - /token/**\n\n# api\nknife4j:\n  enable: true\n  documents:\n    - group: \'2.X版本\'\n      name: \'接口签名\'\n      locations: classpath:sign/*\n  setting:\n    language: zh-CN\n    enableSwaggerModels: true\n    enableDocumentManage: true\n    swaggerModelName: \'实体类列表\'\n    enableVersion: true\n    enableReloadCacheParameter: false\n    enableAfterScript: true\n    enableFilterMultipartApiMethodType: POST\n    enableFilterMultipartApis: false\n    enableRequestCache: true\n    enableHost: false\n    enableHostText: 192.168.0.193:8000\n    enableHomeCustom: true\n    homeCustomLocation: classpath:markdown/home.md\n    enableSearch: false\n    enableFooter: false\n    enableFooterCustom: true\n    footerCustomContent: \'Apache License 2.0 | Copyright  2019-[浙江八一菜刀股份有限公司](https://gitee.com/xiaoym/knife4j)\'\n    enableDynamicParameter: true\n    enableDebug: true\n    enableOpenApi: false\n    enableGroup: true\n  cors: false\n  production: false\n  basic:\n    enable: false\n    username: test\n    password: 12313', '1404c29a278771b61ce2af4e978833d0', '2021-04-03 07:40:12', '2021-04-03 07:40:12', NULL, '172.23.0.1', 'U', 'dev');
INSERT INTO `his_config_info` VALUES (23, 35, 'easy-archetype-cloud-archetype.yml', 'DEFAULT_GROUP', '', '## logger\nlogging:\n  level:\n    com.easy: debug\n    org.springframework: warn\nspring:\n  thymeleaf:\n    # thymeleaf  cache\n    cache: false\n  devtools:\n    restart:\n      enabled: true\n  datasource:\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://122.152.226.81:3306/easy-archetype-dev?characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false&allowMultiQueries=true\n    password: luyanan\n    username: luyanan\n  redis:\n    host: easy-mysql\n    password: luyanan\n    port: 6379\n    database: 3\n# customer config\neasy:\n  archetype:\n    framework:\n      enable: true\n      # Spring \n      spring:\n        enable: true\n        # cors\n        cors:\n          enable: true\n      #cache\n      cache:\n        enable: true\n      logger:\n        enable: true\n        # logger\n        logger-print: true\n      mybatis-plus:\n        enable: false\n      thread:\n        enable: true\n    security:\n      oauth:\n        token-signing-key: easy-archetype\n        client:\n          authorization-server-host: http://localhost:9001/\n          client-id: c1\n          client-secret: secret\n          resource-id: res1\n      ignoring-login-url:\n        - /actuator/**\n        - /oauth/*\n        - /token/**\n\n', 'c59176be394ef762cdecf098d4ea06ab', '2021-04-03 07:58:22', '2021-04-03 07:58:21', NULL, '172.18.0.1', 'U', 'dev');
INSERT INTO `his_config_info` VALUES (23, 36, 'easy-archetype-cloud-archetype.yml', 'DEFAULT_GROUP', '', '## logger日志\nlogging:\n  level:\n    com.easy: debug\n    org.springframework: warn\nspring:\n  thymeleaf:\n    # thymeleaf  cache\n    cache: false\n  devtools:\n    restart:\n      enabled: true\n  datasource:\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://122.152.226.81:3306/easy-archetype-dev?characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false&allowMultiQueries=true\n    password: luyanan\n    username: luyanan\n  redis:\n    host: easy-mysql\n    password: luyanan\n    port: 6379\n    database: 3\n# customer config\neasy:\n  archetype:\n    framework:\n      enable: true\n      # Spring \n      spring:\n        enable: true\n        # cors\n        cors:\n          enable: true\n      #cache\n      cache:\n        enable: true\n      logger:\n        enable: true\n        # logger\n        logger-print: true\n      mybatis-plus:\n        enable: false\n      thread:\n        enable: true\n    security:\n      oauth:\n        token-signing-key: easy-archetype\n        client:\n          authorization-server-host: http://localhost:9001/\n          client-id: c1\n          client-secret: secret\n          resource-id: res1\n      ignoring-login-url:\n        - /actuator/**\n        - /oauth/*\n        - /token/**\n\n', 'f7f75d165856f71f922981077888fdd2', '2021-04-03 07:58:59', '2021-04-03 07:58:59', NULL, '172.18.0.1', 'U', 'dev');
INSERT INTO `his_config_info` VALUES (0, 37, 'easy-archetype-cloud-gateway.yml', 'DEFAULT_GROUP', '', 'spring:\r\n  cloud:\r\n    gateway:\r\n      routes:\r\n        # 认证中心\r\n        - id: easy-archetype-cloud-auth\r\n          uri: lb://easy-archetype-cloud-auth\r\n          predicates:\r\n            - Path=/uaa/**\r\n        # 脚手架\r\n        - id: easy-archetype-cloud-archetype\r\n          uri: lb://easy-archetype-cloud-archetype\r\n          predicates:\r\n            - Path=/archetype/**\r\n', 'a0f572bb12339688eb14fc6ff5f8aac1', '2021-04-03 13:04:27', '2021-04-03 13:04:27', 'nacos', '0:0:0:0:0:0:0:1', 'I', 'dev');

-- ----------------------------
-- Table structure for j_job
-- ----------------------------
DROP TABLE IF EXISTS `j_job`;
CREATE TABLE `j_job`  (
  `job_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务id',
  `job_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '任务名称',
  `job_group` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '任务分组',
  `invoke_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '调用类型',
  `invoke_target` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '调用目标字符串',
  `cron_expression` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'cron表达式',
  `misfire_policy` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '计划策略',
  `concurrent` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否允许并发',
  `status` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`job_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '定时任务' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of j_job
-- ----------------------------
INSERT INTO `j_job` VALUES ('7d2a6d08963c4e799de8157d82d92807', '系统默认（有参）', 'DEFAULT', 'bean', 'demoTask.ryParams(\'ry\')', '0/15 * * * * ?', '1', '1', '1', '1', '2021-04-06 21:26:31', '1', '2021-04-06 21:30:51');
INSERT INTO `j_job` VALUES ('8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', 'bean', 'demoTask.ryNoParams', '0/10 * * * * ?', '1', '0', '1', '1', '2021-04-06 21:18:55', '1', '2021-04-06 21:18:55');
INSERT INTO `j_job` VALUES ('f164e785452e40f1b43d2ad79f847554', '系统默认（多参）', 'DEFAULT', 'bean', 'demoTask.ryMultipleParams(\\\'ry\\\', true, 2000L, 316.50D, 100)', '0/20 * * * * ?', '1', '1', '1', '1', '2021-04-06 21:32:40', '1', '2021-04-06 21:32:40');

-- ----------------------------
-- Table structure for j_job_log
-- ----------------------------
DROP TABLE IF EXISTS `j_job_log`;
CREATE TABLE `j_job_log`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'id',
  `job_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '任务id',
  `job_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '任务名称',
  `job_group` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '任务分组',
  `invoke_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '调用类型',
  `invoke_target` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '调用目标字符串',
  `job_message` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '日志信息',
  `status` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '执行状态',
  `exception_info` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '异常信息',
  `start_time` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
  `elapsed_time` bigint(10) NULL DEFAULT NULL COMMENT '耗时',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '任务日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of j_job_log
-- ----------------------------
INSERT INTO `j_job_log` VALUES ('036aea4c53394b90b843841b9e01e06e', '7d2a6d08963c4e799de8157d82d92807', '系统默认（有参）', 'DEFAULT', NULL, 'demoTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:36:01', '2021-04-06 21:36:01', NULL);
INSERT INTO `j_job_log` VALUES ('04ea28b0bc8f40e2a722c4e46fb27197', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:32:32', '2021-04-06 21:32:32', NULL);
INSERT INTO `j_job_log` VALUES ('062b4f0177084ca3b55ab67481e8432f', 'f164e785452e40f1b43d2ad79f847554', '系统默认（多参）', 'DEFAULT', NULL, 'demoTask.ryMultipleParams(\\\'ry\\\', true, 2000L, 316.50D, 100)', '系统默认（多参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:33:22', '2021-04-06 21:33:22', NULL);
INSERT INTO `j_job_log` VALUES ('067bf60c6fb74c0ca00b648c96d1e9e0', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:27:30', '2021-04-06 21:27:30', NULL);
INSERT INTO `j_job_log` VALUES ('0b3783d44a264d8aaae30af5adb4530f', '7d2a6d08963c4e799de8157d82d92807', '系统默认（有参）', 'DEFAULT', NULL, 'demoTask.ryParams(\\\'ry\\\')', '系统默认（有参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:30:18', '2021-04-06 21:30:18', NULL);
INSERT INTO `j_job_log` VALUES ('0c7921e07d77424cbfb5809c0c194f03', '7d2a6d08963c4e799de8157d82d92807', '系统默认（有参）', 'DEFAULT', NULL, 'demoTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:33:01', '2021-04-06 21:33:01', NULL);
INSERT INTO `j_job_log` VALUES ('0ca44fd5790d461aba38390f387e3fa2', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:44:02', '2021-04-06 21:44:02', NULL);
INSERT INTO `j_job_log` VALUES ('0ce2b960ef334788adbb9344a0b5f696', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:32:02', '2021-04-06 21:32:02', NULL);
INSERT INTO `j_job_log` VALUES ('0daa4a56467b4ddfac77032248560e09', '7d2a6d08963c4e799de8157d82d92807', '系统默认（有参）', 'DEFAULT', NULL, 'demoTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:40:16', '2021-04-06 21:40:16', NULL);
INSERT INTO `j_job_log` VALUES ('1112975f314b430e9cb4bd80eb6441f6', 'f164e785452e40f1b43d2ad79f847554', '系统默认（多参）', 'DEFAULT', NULL, 'demoTask.ryMultipleParams(\\\'ry\\\', true, 2000L, 316.50D, 100)', '系统默认（多参） 总共耗时：1毫秒', '0', NULL, '2021-04-06 21:46:42', '2021-04-06 21:46:42', NULL);
INSERT INTO `j_job_log` VALUES ('11b6887dba3b4eefbbd2a78cc968f987', '7d2a6d08963c4e799de8157d82d92807', '系统默认（有参）', 'DEFAULT', NULL, 'demoTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:33:31', '2021-04-06 21:33:31', NULL);
INSERT INTO `j_job_log` VALUES ('12e4d4949bbf4424917478f2d149e5b8', NULL, NULL, NULL, NULL, NULL, 'null 总共耗时：1毫秒', '1', 'NullPointerException: null', '2021-04-06 21:20:14', '2021-04-06 21:20:14', NULL);
INSERT INTO `j_job_log` VALUES ('13cd423f992d4fb7ae89dac7f01fb6d0', '7d2a6d08963c4e799de8157d82d92807', '系统默认（有参）', 'DEFAULT', NULL, 'demoTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:46:16', '2021-04-06 21:46:16', NULL);
INSERT INTO `j_job_log` VALUES ('14ffed6bc85a4cbfbfab47da1bb3a05e', 'f164e785452e40f1b43d2ad79f847554', '系统默认（多参）', 'DEFAULT', NULL, 'demoTask.ryMultipleParams(\\\'ry\\\', true, 2000L, 316.50D, 100)', '系统默认（多参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:43:03', '2021-04-06 21:43:03', NULL);
INSERT INTO `j_job_log` VALUES ('1806932ee08c4a3e944858411a63b020', '7d2a6d08963c4e799de8157d82d92807', '系统默认（有参）', 'DEFAULT', NULL, 'demoTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:35:46', '2021-04-06 21:35:46', NULL);
INSERT INTO `j_job_log` VALUES ('1843ecfd2ca348d8bf49e29eed0cd4aa', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：7毫秒', '1', 'NullPointerException: null', '2021-04-06 21:22:37', '2021-04-06 21:22:37', NULL);
INSERT INTO `j_job_log` VALUES ('1954359caa98441eb18d59a9ec131cff', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:32:40', '2021-04-06 21:32:40', NULL);
INSERT INTO `j_job_log` VALUES ('19ad27565fc54f99a093400013ecede7', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:43:50', '2021-04-06 21:43:50', NULL);
INSERT INTO `j_job_log` VALUES ('1a43bd167754449ea303f7aafe762691', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:36:20', '2021-04-06 21:36:20', NULL);
INSERT INTO `j_job_log` VALUES ('1ab367fd44164f0bb56512146e96d833', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:39:20', '2021-04-06 21:39:20', NULL);
INSERT INTO `j_job_log` VALUES ('1ad0b27728d445bfa32963998f7021ce', '7d2a6d08963c4e799de8157d82d92807', '系统默认（有参）', 'DEFAULT', NULL, 'demoTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:43:31', '2021-04-06 21:43:31', NULL);
INSERT INTO `j_job_log` VALUES ('1aeb03e50d324466a4b716285a167eac', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:28:11', '2021-04-06 21:28:11', NULL);
INSERT INTO `j_job_log` VALUES ('1d5d824f6b694518abe07e7fe4ea9405', 'f164e785452e40f1b43d2ad79f847554', '系统默认（多参）', 'DEFAULT', NULL, 'demoTask.ryMultipleParams(\\\'ry\\\', true, 2000L, 316.50D, 100)', '系统默认（多参） 总共耗时：1毫秒', '0', NULL, '2021-04-06 21:45:22', '2021-04-06 21:45:22', NULL);
INSERT INTO `j_job_log` VALUES ('1f81af3537544f208a7bed15dab02a3f', '7d2a6d08963c4e799de8157d82d92807', '系统默认（有参）', 'DEFAULT', NULL, 'demoTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：1毫秒', '0', NULL, '2021-04-06 21:34:16', '2021-04-06 21:34:16', NULL);
INSERT INTO `j_job_log` VALUES ('207d6315a4ff408ca771f6ef10a59b90', '7d2a6d08963c4e799de8157d82d92807', '系统默认（有参）', 'DEFAULT', NULL, 'demoTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:39:16', '2021-04-06 21:39:16', NULL);
INSERT INTO `j_job_log` VALUES ('22fa523876774c36972519ac7ba6352d', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：1毫秒', '0', NULL, '2021-04-06 21:31:32', '2021-04-06 21:31:32', NULL);
INSERT INTO `j_job_log` VALUES ('232a026426b44217baf50e8d359a5301', '7d2a6d08963c4e799de8157d82d92807', '系统默认（有参）', 'DEFAULT', NULL, 'demoTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:41:01', '2021-04-06 21:41:01', NULL);
INSERT INTO `j_job_log` VALUES ('246c7f272f644363ae67fa3da47185fc', '7d2a6d08963c4e799de8157d82d92807', '系统默认（有参）', 'DEFAULT', NULL, 'demoTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:42:01', '2021-04-06 21:42:01', NULL);
INSERT INTO `j_job_log` VALUES ('24b8b2bdb7b048798d690529f809412a', 'f164e785452e40f1b43d2ad79f847554', '系统默认（多参）', 'DEFAULT', NULL, 'demoTask.ryMultipleParams(\\\'ry\\\', true, 2000L, 316.50D, 100)', '系统默认（多参） 总共耗时：1毫秒', '0', NULL, '2021-04-06 21:37:42', '2021-04-06 21:37:42', NULL);
INSERT INTO `j_job_log` VALUES ('250b0fcad29140dabab7139fd19a3e9f', '7d2a6d08963c4e799de8157d82d92807', '系统默认（有参）', 'DEFAULT', NULL, 'demoTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:34:01', '2021-04-06 21:34:01', NULL);
INSERT INTO `j_job_log` VALUES ('254ab9f3893c4623b2b03ed923d6a710', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:30:50', '2021-04-06 21:30:50', NULL);
INSERT INTO `j_job_log` VALUES ('258b89b2d63443f8a8fdbefe62d90823', 'f164e785452e40f1b43d2ad79f847554', '系统默认（多参）', 'DEFAULT', NULL, 'demoTask.ryMultipleParams(\\\'ry\\\', true, 2000L, 316.50D, 100)', '系统默认（多参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:35:03', '2021-04-06 21:35:03', NULL);
INSERT INTO `j_job_log` VALUES ('26e036a387f04955a43005d5521ba5d2', '7d2a6d08963c4e799de8157d82d92807', '系统默认（有参）', 'DEFAULT', NULL, 'demoTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:38:31', '2021-04-06 21:38:31', NULL);
INSERT INTO `j_job_log` VALUES ('2934121125724da783353fca9706e499', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:30:34', '2021-04-06 21:30:34', NULL);
INSERT INTO `j_job_log` VALUES ('29661122081d48a1a646d000586c15d0', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:30:10', '2021-04-06 21:30:10', NULL);
INSERT INTO `j_job_log` VALUES ('29d5e95eae584ef496314930b1010db8', 'f164e785452e40f1b43d2ad79f847554', '系统默认（多参）', 'DEFAULT', NULL, 'demoTask.ryMultipleParams(\\\'ry\\\', true, 2000L, 316.50D, 100)', '系统默认（多参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:37:03', '2021-04-06 21:37:03', NULL);
INSERT INTO `j_job_log` VALUES ('2bd265d8ccb14c24a535375a9d4057d2', 'f164e785452e40f1b43d2ad79f847554', '系统默认（多参）', 'DEFAULT', NULL, 'demoTask.ryMultipleParams(\\\'ry\\\', true, 2000L, 316.50D, 100)', '系统默认（多参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:41:22', '2021-04-06 21:41:22', NULL);
INSERT INTO `j_job_log` VALUES ('2c3fe49ec5a049dc81340c9c9b221419', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:46:20', '2021-04-06 21:46:20', NULL);
INSERT INTO `j_job_log` VALUES ('2d2c30bd7a6047af8d99124243073cdd', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:34:10', '2021-04-06 21:34:10', NULL);
INSERT INTO `j_job_log` VALUES ('2d48c4b5f9954421bef1df97fd2752fb', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:33:50', '2021-04-06 21:33:50', NULL);
INSERT INTO `j_job_log` VALUES ('2e581f96147145eaaa906ae66b243f67', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:34:40', '2021-04-06 21:34:40', NULL);
INSERT INTO `j_job_log` VALUES ('2eab5a9dac354c36bd294ff717ba5650', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:44:32', '2021-04-06 21:44:32', NULL);
INSERT INTO `j_job_log` VALUES ('2f456cff7f0343f5bfb4df65f3a76278', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:33:20', '2021-04-06 21:33:20', NULL);
INSERT INTO `j_job_log` VALUES ('2f675e86c61546de9ff3bafb5caf4be5', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:36:50', '2021-04-06 21:36:50', NULL);
INSERT INTO `j_job_log` VALUES ('3420f316699b439da6cf4cd004507eec', 'f164e785452e40f1b43d2ad79f847554', '系统默认（多参）', 'DEFAULT', NULL, 'demoTask.ryMultipleParams(\\\'ry\\\', true, 2000L, 316.50D, 100)', '系统默认（多参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:40:22', '2021-04-06 21:40:22', NULL);
INSERT INTO `j_job_log` VALUES ('34a2c561944045feb44d72ffc86e5668', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:35:40', '2021-04-06 21:35:40', NULL);
INSERT INTO `j_job_log` VALUES ('361b97a7db4c4690a79c49bd75cbc063', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:43:40', '2021-04-06 21:43:40', NULL);
INSERT INTO `j_job_log` VALUES ('3645b01c3a744059a31359681305ac13', '7d2a6d08963c4e799de8157d82d92807', '系统默认（有参）', 'DEFAULT', NULL, 'demoTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:33:46', '2021-04-06 21:33:46', NULL);
INSERT INTO `j_job_log` VALUES ('36f6d7f004bd4f159338d56eed974a2e', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:37:40', '2021-04-06 21:37:40', NULL);
INSERT INTO `j_job_log` VALUES ('375a858105b64b869f38f0e7b33f4714', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:46:40', '2021-04-06 21:46:40', NULL);
INSERT INTO `j_job_log` VALUES ('38e875227bc04b56934f1a8b872bbcd8', '7d2a6d08963c4e799de8157d82d92807', '系统默认（有参）', 'DEFAULT', NULL, 'demoTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：1毫秒', '0', NULL, '2021-04-06 21:35:16', '2021-04-06 21:35:16', NULL);
INSERT INTO `j_job_log` VALUES ('38e9e20e8f5f4fd6b13c6ecae5439813', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：1毫秒', '0', NULL, '2021-04-06 21:38:32', '2021-04-06 21:38:32', NULL);
INSERT INTO `j_job_log` VALUES ('397dcb6520994912a298716403c52f75', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:30:00', '2021-04-06 21:30:00', NULL);
INSERT INTO `j_job_log` VALUES ('3a5acb7d5bc14fa896e8fa12e1a7fc5c', '7d2a6d08963c4e799de8157d82d92807', '系统默认（有参）', 'DEFAULT', NULL, 'demoTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:45:46', '2021-04-06 21:45:46', NULL);
INSERT INTO `j_job_log` VALUES ('3b6b97736f4b4f4e8d36c2234c168bdc', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:25:40', '2021-04-06 21:25:40', NULL);
INSERT INTO `j_job_log` VALUES ('3b834c4334784ad2b2eda0d6488ea165', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:29:40', '2021-04-06 21:29:40', NULL);
INSERT INTO `j_job_log` VALUES ('3b93a9122bd84d44b7071c9c2df33319', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：1毫秒', '0', NULL, '2021-04-06 21:35:10', '2021-04-06 21:35:10', NULL);
INSERT INTO `j_job_log` VALUES ('3d3f8e8763104f7389e6e3ac70ec110f', '7d2a6d08963c4e799de8157d82d92807', '系统默认（有参）', 'DEFAULT', NULL, 'demoTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：1毫秒', '0', NULL, '2021-04-06 21:34:31', '2021-04-06 21:34:31', NULL);
INSERT INTO `j_job_log` VALUES ('3e4b674411ed42b7a115e4e6ace7424f', 'f164e785452e40f1b43d2ad79f847554', '系统默认（多参）', 'DEFAULT', NULL, 'demoTask.ryMultipleParams(\\\'ry\\\', true, 2000L, 316.50D, 100)', '系统默认（多参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:44:22', '2021-04-06 21:44:22', NULL);
INSERT INTO `j_job_log` VALUES ('3ef1548398944106b0d72e8b3234cfe3', '7d2a6d08963c4e799de8157d82d92807', '系统默认（有参）', 'DEFAULT', NULL, 'demoTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:40:01', '2021-04-06 21:40:01', NULL);
INSERT INTO `j_job_log` VALUES ('3fd1228a879c463b88eae045453baf35', '7d2a6d08963c4e799de8157d82d92807', '系统默认（有参）', 'DEFAULT', NULL, 'demoTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:31:22', '2021-04-06 21:31:22', NULL);
INSERT INTO `j_job_log` VALUES ('3ff1bb8085eb43cd88afc9f5f9b23b01', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:26:30', '2021-04-06 21:26:30', NULL);
INSERT INTO `j_job_log` VALUES ('4097ce678199489ca8ea5e35694dce44', '7d2a6d08963c4e799de8157d82d92807', '系统默认（有参）', 'DEFAULT', NULL, 'demoTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:36:31', '2021-04-06 21:36:31', NULL);
INSERT INTO `j_job_log` VALUES ('42bd7e28ff494dec811a77bee9a2c3c6', '7d2a6d08963c4e799de8157d82d92807', '系统默认（有参）', 'DEFAULT', NULL, 'demoTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:43:46', '2021-04-06 21:43:46', NULL);
INSERT INTO `j_job_log` VALUES ('42ee5b958b66412c83367c9362382407', '7d2a6d08963c4e799de8157d82d92807', '系统默认（有参）', 'DEFAULT', NULL, 'demoTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：1毫秒', '0', NULL, '2021-04-06 21:42:46', '2021-04-06 21:42:46', NULL);
INSERT INTO `j_job_log` VALUES ('43c8bf258e5940b4a5bc50e7f7e99f73', NULL, NULL, NULL, NULL, NULL, 'null 总共耗时：1毫秒', '1', 'NullPointerException: null', '2021-04-06 21:21:26', '2021-04-06 21:21:26', NULL);
INSERT INTO `j_job_log` VALUES ('43f70e212bab4359805a1df19274e3a9', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:45:02', '2021-04-06 21:45:02', NULL);
INSERT INTO `j_job_log` VALUES ('43f972bcfd8d4bcfbeaf96cdc08ea4a1', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:45:32', '2021-04-06 21:45:32', NULL);
INSERT INTO `j_job_log` VALUES ('443525b8065044858dd138b330e24b58', '7d2a6d08963c4e799de8157d82d92807', '系统默认（有参）', 'DEFAULT', NULL, 'demoTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:43:16', '2021-04-06 21:43:16', NULL);
INSERT INTO `j_job_log` VALUES ('4438dddbf50a45aaa2190a0c5bf41ff1', '7d2a6d08963c4e799de8157d82d92807', '系统默认（有参）', 'DEFAULT', NULL, 'demoTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：18毫秒', '0', NULL, '2021-05-01 09:05:31', '2021-05-01 09:05:31', NULL);
INSERT INTO `j_job_log` VALUES ('4474493ff9b44c7a8d02cf73700fdc82', '7d2a6d08963c4e799de8157d82d92807', '系统默认（有参）', 'DEFAULT', NULL, 'demoTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:38:01', '2021-04-06 21:38:01', NULL);
INSERT INTO `j_job_log` VALUES ('44c22340e39748e89070283a47621ee3', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:30:21', '2021-04-06 21:30:21', NULL);
INSERT INTO `j_job_log` VALUES ('48eb7993efc74daf9c8341f38f41de7b', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:26:10', '2021-04-06 21:26:10', NULL);
INSERT INTO `j_job_log` VALUES ('49f43c2ee7994d05b61f9b2465dab27e', 'f164e785452e40f1b43d2ad79f847554', '系统默认（多参）', 'DEFAULT', NULL, 'demoTask.ryMultipleParams(\\\'ry\\\', true, 2000L, 316.50D, 100)', '系统默认（多参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:45:42', '2021-04-06 21:45:42', NULL);
INSERT INTO `j_job_log` VALUES ('4a07bf5e741d49d1b9d105412d9d0905', '7d2a6d08963c4e799de8157d82d92807', '系统默认（有参）', 'DEFAULT', NULL, 'demoTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:46:31', '2021-04-06 21:46:31', NULL);
INSERT INTO `j_job_log` VALUES ('4b2139afbfc14b3b8653c5d2c4387a81', 'f164e785452e40f1b43d2ad79f847554', '系统默认（多参）', 'DEFAULT', NULL, 'demoTask.ryMultipleParams(\\\'ry\\\', true, 2000L, 316.50D, 100)', '系统默认（多参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:39:42', '2021-04-06 21:39:42', NULL);
INSERT INTO `j_job_log` VALUES ('4b389c267c3d42ee8522d6081bb5fe27', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:39:32', '2021-04-06 21:39:32', NULL);
INSERT INTO `j_job_log` VALUES ('4b8e4023594d44e1a1e1c24ffeac537f', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:36:40', '2021-04-06 21:36:40', NULL);
INSERT INTO `j_job_log` VALUES ('4c655ddade3744c39e3b29257d5a95dd', 'f164e785452e40f1b43d2ad79f847554', '系统默认（多参）', 'DEFAULT', NULL, 'demoTask.ryMultipleParams(\\\'ry\\\', true, 2000L, 316.50D, 100)', '系统默认（多参） 总共耗时：1毫秒', '0', NULL, '2021-04-06 21:45:03', '2021-04-06 21:45:03', NULL);
INSERT INTO `j_job_log` VALUES ('4c80bb58461940b78611fba26d354041', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:33:02', '2021-04-06 21:33:02', NULL);
INSERT INTO `j_job_log` VALUES ('4c9fc09c35bc48a1a0b6ce723329420f', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:27:50', '2021-04-06 21:27:50', NULL);
INSERT INTO `j_job_log` VALUES ('4ceb086ec03f4e5387f0972e14a1bf27', '7d2a6d08963c4e799de8157d82d92807', '系统默认（有参）', 'DEFAULT', NULL, 'demoTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:44:16', '2021-04-06 21:44:16', NULL);
INSERT INTO `j_job_log` VALUES ('4f9ca1e22f654c0794887ccd6a4c5560', 'f164e785452e40f1b43d2ad79f847554', '系统默认（多参）', 'DEFAULT', NULL, 'demoTask.ryMultipleParams(\\\'ry\\\', true, 2000L, 316.50D, 100)', '系统默认（多参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:42:22', '2021-04-06 21:42:22', NULL);
INSERT INTO `j_job_log` VALUES ('52a6c8c90ecb41c79336d591d2b1c8b8', NULL, NULL, NULL, NULL, NULL, 'null 总共耗时：1毫秒', '1', 'NullPointerException: null', '2021-04-06 21:20:15', '2021-04-06 21:20:15', NULL);
INSERT INTO `j_job_log` VALUES ('538b54a05ca04e489281cb298c16c638', NULL, NULL, NULL, NULL, NULL, 'null 总共耗时：0毫秒', '1', 'NullPointerException: null', '2021-04-06 21:21:21', '2021-04-06 21:21:21', NULL);
INSERT INTO `j_job_log` VALUES ('53b3b6f2e41c411f82f242920dfe1b6a', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:43:32', '2021-04-06 21:43:32', NULL);
INSERT INTO `j_job_log` VALUES ('540b1ce8fc2d49c5aca33d3b84c0d6f4', '7d2a6d08963c4e799de8157d82d92807', '系统默认（有参）', 'DEFAULT', NULL, 'demoTask.ryParams(\\\'ry\\\')', '系统默认（有参） 总共耗时：1毫秒', '0', NULL, '2021-04-06 21:30:15', '2021-04-06 21:30:15', NULL);
INSERT INTO `j_job_log` VALUES ('54313f256ff7436a93bf239e0dc733f4', 'f164e785452e40f1b43d2ad79f847554', '系统默认（多参）', 'DEFAULT', NULL, 'demoTask.ryMultipleParams(\\\'ry\\\', true, 2000L, 316.50D, 100)', '系统默认（多参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:43:22', '2021-04-06 21:43:22', NULL);
INSERT INTO `j_job_log` VALUES ('57075e428efe420599cab5c3ab30b132', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：83266毫秒', '1', 'NullPointerException: null', '2021-04-06 21:23:05', '2021-04-06 21:24:29', NULL);
INSERT INTO `j_job_log` VALUES ('570d50f4c0df47cd9aec5e75afff592f', 'f164e785452e40f1b43d2ad79f847554', '系统默认（多参）', 'DEFAULT', NULL, 'demoTask.ryMultipleParams(\\\'ry\\\', true, 2000L, 316.50D, 100)', '系统默认（多参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:34:42', '2021-04-06 21:34:42', NULL);
INSERT INTO `j_job_log` VALUES ('576144ad341b40e68f875c04f01ba9ba', '7d2a6d08963c4e799de8157d82d92807', '系统默认（有参）', 'DEFAULT', NULL, 'demoTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:31:46', '2021-04-06 21:31:46', NULL);
INSERT INTO `j_job_log` VALUES ('5c453b12fa0349859a640b07624c73ec', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:40:32', '2021-04-06 21:40:32', NULL);
INSERT INTO `j_job_log` VALUES ('5c8ed97cf50c40519caac0db47c3e12b', '7d2a6d08963c4e799de8157d82d92807', '系统默认（有参）', 'DEFAULT', NULL, 'demoTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:37:46', '2021-04-06 21:37:46', NULL);
INSERT INTO `j_job_log` VALUES ('5dd84092b2cf4a71871b56386671ac3a', '7d2a6d08963c4e799de8157d82d92807', '系统默认（有参）', 'DEFAULT', NULL, 'demoTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:36:46', '2021-04-06 21:36:46', NULL);
INSERT INTO `j_job_log` VALUES ('5e836a4b74f44c12b622c50c7851b034', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:28:20', '2021-04-06 21:28:20', NULL);
INSERT INTO `j_job_log` VALUES ('5eccc604a8b0466ca16ccd18d56fc59f', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:42:32', '2021-04-06 21:42:32', NULL);
INSERT INTO `j_job_log` VALUES ('5efe1d6338fc43bbbe27257e25996694', '7d2a6d08963c4e799de8157d82d92807', '系统默认（有参）', 'DEFAULT', NULL, 'demoTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：1毫秒', '0', NULL, '2021-04-06 21:45:01', '2021-04-06 21:45:01', NULL);
INSERT INTO `j_job_log` VALUES ('5f54d549aa984805a9755b3832c84e75', 'f164e785452e40f1b43d2ad79f847554', '系统默认（多参）', 'DEFAULT', NULL, 'demoTask.ryMultipleParams(\\\'ry\\\', true, 2000L, 316.50D, 100)', '系统默认（多参） 总共耗时：1毫秒', '0', NULL, '2021-04-06 21:46:22', '2021-04-06 21:46:22', NULL);
INSERT INTO `j_job_log` VALUES ('5fbb619b40e4445cb90dda40ef929ceb', '7d2a6d08963c4e799de8157d82d92807', '系统默认（有参）', 'DEFAULT', NULL, 'demoTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:39:31', '2021-04-06 21:39:31', NULL);
INSERT INTO `j_job_log` VALUES ('65b8712968dd46668cdc38c56cb7cb7f', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:41:50', '2021-04-06 21:41:50', NULL);
INSERT INTO `j_job_log` VALUES ('6713005e92764108886a41817ed42e58', NULL, NULL, NULL, NULL, NULL, 'null 总共耗时：1毫秒', '1', 'NullPointerException: null', '2021-04-06 21:21:30', '2021-04-06 21:21:30', NULL);
INSERT INTO `j_job_log` VALUES ('6791fa4963ce4cf684228c62ac774b87', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:34:32', '2021-04-06 21:34:32', NULL);
INSERT INTO `j_job_log` VALUES ('67b8e2822b0f4783922c34c816e3be28', '7d2a6d08963c4e799de8157d82d92807', '系统默认（有参）', 'DEFAULT', NULL, 'demoTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:39:01', '2021-04-06 21:39:01', NULL);
INSERT INTO `j_job_log` VALUES ('681f125ab1ab4a36a9f9153f3a75f038', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:27:20', '2021-04-06 21:27:20', NULL);
INSERT INTO `j_job_log` VALUES ('6855512291ba493faac4c81bfe27cbdd', '7d2a6d08963c4e799de8157d82d92807', '系统默认（有参）', 'DEFAULT', NULL, 'demoTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：1毫秒', '0', NULL, '2021-04-06 21:36:16', '2021-04-06 21:36:16', NULL);
INSERT INTO `j_job_log` VALUES ('6874cba646954c45ad825449d97f523a', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:41:32', '2021-04-06 21:41:32', NULL);
INSERT INTO `j_job_log` VALUES ('697ec18df8124c5380fd97448c626a79', '7d2a6d08963c4e799de8157d82d92807', '系统默认（有参）', 'DEFAULT', NULL, 'demoTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：1毫秒', '0', NULL, '2021-04-06 21:33:16', '2021-04-06 21:33:16', NULL);
INSERT INTO `j_job_log` VALUES ('69c1d0af34c24e619b4a9d3398f12461', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:34:20', '2021-04-06 21:34:20', NULL);
INSERT INTO `j_job_log` VALUES ('6a870f2f919e4dda93f065e7d19318a5', 'f164e785452e40f1b43d2ad79f847554', '系统默认（多参）', 'DEFAULT', NULL, 'demoTask.ryMultipleParams(\\\'ry\\\', true, 2000L, 316.50D, 100)', '系统默认（多参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:35:42', '2021-04-06 21:35:42', NULL);
INSERT INTO `j_job_log` VALUES ('6b20f9d575764a8ea8ab7a027d20afec', 'f164e785452e40f1b43d2ad79f847554', '系统默认（多参）', 'DEFAULT', NULL, 'demoTask.ryMultipleParams(\\\'ry\\\', true, 2000L, 316.50D, 100)', '系统默认（多参） 总共耗时：1毫秒', '0', NULL, '2021-04-06 21:39:03', '2021-04-06 21:39:03', NULL);
INSERT INTO `j_job_log` VALUES ('6c9ac7fc9cae42b2a8114466ed117ac7', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:32:50', '2021-04-06 21:32:50', NULL);
INSERT INTO `j_job_log` VALUES ('6cd4564fe08840a7acf7606246063b8b', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:35:32', '2021-04-06 21:35:32', NULL);
INSERT INTO `j_job_log` VALUES ('6d5e796c0cc34a81b6ce0ccda1123064', NULL, NULL, NULL, NULL, NULL, 'null 总共耗时：1毫秒', '1', 'NullPointerException: null', '2021-04-06 21:19:30', '2021-04-06 21:19:30', NULL);
INSERT INTO `j_job_log` VALUES ('6d78a3b8394e4c52827e85fb7adcea19', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：1毫秒', '0', NULL, '2021-04-06 21:43:02', '2021-04-06 21:43:02', NULL);
INSERT INTO `j_job_log` VALUES ('6de34049b3784466be19bc4b446539de', '7d2a6d08963c4e799de8157d82d92807', '系统默认（有参）', 'DEFAULT', NULL, 'demoTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:46:46', '2021-04-06 21:46:46', NULL);
INSERT INTO `j_job_log` VALUES ('6f61a12fdd834341a03d8f9a1e360fde', 'f164e785452e40f1b43d2ad79f847554', '系统默认（多参）', 'DEFAULT', NULL, 'demoTask.ryMultipleParams(\\\'ry\\\', true, 2000L, 316.50D, 100)', '系统默认（多参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:42:03', '2021-04-06 21:42:03', NULL);
INSERT INTO `j_job_log` VALUES ('7014ec5eab734c13a854df71b05ed811', 'f164e785452e40f1b43d2ad79f847554', '系统默认（多参）', 'DEFAULT', NULL, 'demoTask.ryMultipleParams(\\\'ry\\\', true, 2000L, 316.50D, 100)', '系统默认（多参） 总共耗时：1毫秒', '0', NULL, '2021-04-06 21:37:22', '2021-04-06 21:37:22', NULL);
INSERT INTO `j_job_log` VALUES ('7043f99d8fd44048bc35473b58653c03', '7d2a6d08963c4e799de8157d82d92807', '系统默认（有参）', 'DEFAULT', NULL, 'demoTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：1毫秒', '0', NULL, '2021-04-06 21:35:31', '2021-04-06 21:35:31', NULL);
INSERT INTO `j_job_log` VALUES ('727a793d7a6e43ab9ec4d1377e25d6ba', 'f164e785452e40f1b43d2ad79f847554', '系统默认（多参）', 'DEFAULT', NULL, 'demoTask.ryMultipleParams(\\\'ry\\\', true, 2000L, 316.50D, 100)', '系统默认（多参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:44:42', '2021-04-06 21:44:42', NULL);
INSERT INTO `j_job_log` VALUES ('74c172722dd94f0096a7b98aac70d578', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：7毫秒', '0', NULL, '2021-04-06 21:25:21', '2021-04-06 21:25:21', NULL);
INSERT INTO `j_job_log` VALUES ('74d68b83c23a469c9045ff0b7241d5a3', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:36:02', '2021-04-06 21:36:02', NULL);
INSERT INTO `j_job_log` VALUES ('753f758146d94b9cb25eb2cd437073ad', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:39:02', '2021-04-06 21:39:02', NULL);
INSERT INTO `j_job_log` VALUES ('76155387346a4f9fa6354d129061a93f', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:31:50', '2021-04-06 21:31:50', NULL);
INSERT INTO `j_job_log` VALUES ('763a2ba78cda4079ab170f08fd500dca', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:37:02', '2021-04-06 21:37:02', NULL);
INSERT INTO `j_job_log` VALUES ('76695c6022114e6b9fd1fc4b88bc20b2', '7d2a6d08963c4e799de8157d82d92807', '系统默认（有参）', 'DEFAULT', NULL, 'demoTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:44:31', '2021-04-06 21:44:31', NULL);
INSERT INTO `j_job_log` VALUES ('7694cad49f5d4561acfbe70fc06ff46c', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:31:10', '2021-04-06 21:31:10', NULL);
INSERT INTO `j_job_log` VALUES ('772862881ddb41c3b3fe4f357ca917b9', '7d2a6d08963c4e799de8157d82d92807', '系统默认（有参）', 'DEFAULT', NULL, 'demoTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:46:01', '2021-04-06 21:46:01', NULL);
INSERT INTO `j_job_log` VALUES ('78ed96e360d242af999cae41874c8a9a', 'f164e785452e40f1b43d2ad79f847554', '系统默认（多参）', 'DEFAULT', NULL, 'demoTask.ryMultipleParams(\\\'ry\\\', true, 2000L, 316.50D, 100)', '系统默认（多参） 总共耗时：1毫秒', '0', NULL, '2021-04-06 21:41:03', '2021-04-06 21:41:03', NULL);
INSERT INTO `j_job_log` VALUES ('7973ce5890bb4f15a18ed353b40c419f', 'f164e785452e40f1b43d2ad79f847554', '系统默认（多参）', 'DEFAULT', NULL, 'demoTask.ryMultipleParams(\\\'ry\\\', true, 2000L, 316.50D, 100)', '系统默认（多参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:44:03', '2021-04-06 21:44:03', NULL);
INSERT INTO `j_job_log` VALUES ('79fc2cc0b30946feb6833d76bac529ba', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:29:50', '2021-04-06 21:29:50', NULL);
INSERT INTO `j_job_log` VALUES ('7a1f80c277514e899279663796d24820', NULL, NULL, NULL, NULL, NULL, 'null 总共耗时：0毫秒', '1', 'NullPointerException: null', '2021-04-06 21:21:22', '2021-04-06 21:21:22', NULL);
INSERT INTO `j_job_log` VALUES ('7a44ee58140c4c78bc66131cac0e5a3a', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:38:50', '2021-04-06 21:38:50', NULL);
INSERT INTO `j_job_log` VALUES ('7b1b41ebf16140498b6179ad13b73afb', NULL, NULL, NULL, NULL, NULL, 'null 总共耗时：1毫秒', '1', 'NullPointerException: null', '2021-04-06 21:19:20', '2021-04-06 21:19:20', NULL);
INSERT INTO `j_job_log` VALUES ('7ba692bade3e4e3aa58a8fca25b08398', '7d2a6d08963c4e799de8157d82d92807', '系统默认（有参）', 'DEFAULT', NULL, 'demoTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：1毫秒', '0', NULL, '2021-04-06 21:40:46', '2021-04-06 21:40:46', NULL);
INSERT INTO `j_job_log` VALUES ('7ee8ac718a9644f08deb101a8a3f55e4', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：1毫秒', '0', NULL, '2021-04-06 21:37:32', '2021-04-06 21:37:32', NULL);
INSERT INTO `j_job_log` VALUES ('816e0e40c9f7488da025b3c241748e3b', NULL, NULL, NULL, NULL, NULL, 'null 总共耗时：1毫秒', '1', 'NullPointerException: null', '2021-04-06 21:21:23', '2021-04-06 21:21:23', NULL);
INSERT INTO `j_job_log` VALUES ('83f9fd4cbba745c8a66c5ba8e5961875', 'f164e785452e40f1b43d2ad79f847554', '系统默认（多参）', 'DEFAULT', NULL, 'demoTask.ryMultipleParams(\\\'ry\\\', true, 2000L, 316.50D, 100)', '系统默认（多参） 总共耗时：1毫秒', '0', NULL, '2021-04-06 21:36:42', '2021-04-06 21:36:42', NULL);
INSERT INTO `j_job_log` VALUES ('843f613de5e24b5a943d7ef0bf7ad2b5', '7d2a6d08963c4e799de8157d82d92807', '系统默认（有参）', 'DEFAULT', NULL, 'demoTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:42:16', '2021-04-06 21:42:16', NULL);
INSERT INTO `j_job_log` VALUES ('84406ab20a544e93b9b646b4fe98d2c5', 'f164e785452e40f1b43d2ad79f847554', '系统默认（多参）', 'DEFAULT', NULL, 'demoTask.ryMultipleParams(\\\'ry\\\', true, 2000L, 316.50D, 100)', '系统默认（多参） 总共耗时：1毫秒', '0', NULL, '2021-04-06 21:41:42', '2021-04-06 21:41:42', NULL);
INSERT INTO `j_job_log` VALUES ('87c5f663325142fe977cc885011473bc', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:41:40', '2021-04-06 21:41:40', NULL);
INSERT INTO `j_job_log` VALUES ('88bb4980d5d44830b8ceeab9cf38ab3e', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：1毫秒', '0', NULL, '2021-04-06 21:27:40', '2021-04-06 21:27:40', NULL);
INSERT INTO `j_job_log` VALUES ('88bd808ce538435c82a14ea2815cb467', NULL, NULL, NULL, NULL, NULL, 'null 总共耗时：0毫秒', '1', 'NullPointerException: null', '2021-04-06 21:21:25', '2021-04-06 21:21:25', NULL);
INSERT INTO `j_job_log` VALUES ('8c88eae9377c49f7902c16251643e902', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:42:02', '2021-04-06 21:42:02', NULL);
INSERT INTO `j_job_log` VALUES ('8d9386f6a1f042728d128f21ab5a62a0', '7d2a6d08963c4e799de8157d82d92807', '系统默认（有参）', 'DEFAULT', NULL, 'demoTask.ryParams(\\\'ry\\\')', '系统默认（有参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:30:20', '2021-04-06 21:30:20', NULL);
INSERT INTO `j_job_log` VALUES ('9078693e06334f4db18cd3e08e00d505', 'f164e785452e40f1b43d2ad79f847554', '系统默认（多参）', 'DEFAULT', NULL, 'demoTask.ryMultipleParams(\\\'ry\\\', true, 2000L, 316.50D, 100)', '系统默认（多参） 总共耗时：2毫秒', '0', NULL, '2021-04-06 21:32:48', '2021-04-06 21:32:48', NULL);
INSERT INTO `j_job_log` VALUES ('90ebfb524e8940348ef30b05d140fbfb', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:36:32', '2021-04-06 21:36:32', NULL);
INSERT INTO `j_job_log` VALUES ('92acff6722814fdeaa5c4af6a26b1ec9', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:41:20', '2021-04-06 21:41:20', NULL);
INSERT INTO `j_job_log` VALUES ('92ef05806445410eaca033e3f8dbca01', 'f164e785452e40f1b43d2ad79f847554', '系统默认（多参）', 'DEFAULT', NULL, 'demoTask.ryMultipleParams(\\\'ry\\\', true, 2000L, 316.50D, 100)', '系统默认（多参） 总共耗时：1毫秒', '0', NULL, '2021-04-06 21:36:03', '2021-04-06 21:36:03', NULL);
INSERT INTO `j_job_log` VALUES ('943e2296d8d74ea99875735597cd4a40', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:41:02', '2021-04-06 21:41:02', NULL);
INSERT INTO `j_job_log` VALUES ('960060e066c943ca8996fb51ac151da0', '7d2a6d08963c4e799de8157d82d92807', '系统默认（有参）', 'DEFAULT', NULL, 'demoTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：1毫秒', '0', NULL, '2021-04-06 21:32:46', '2021-04-06 21:32:46', NULL);
INSERT INTO `j_job_log` VALUES ('963381db806e4406a10bc59798ad6210', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:46:32', '2021-04-06 21:46:32', NULL);
INSERT INTO `j_job_log` VALUES ('96e8f0c3b71b41f497374b8522027ad3', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:26:20', '2021-04-06 21:26:20', NULL);
INSERT INTO `j_job_log` VALUES ('96f9f1f47b2d4b27aed0e4163a744471', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:27:10', '2021-04-06 21:27:10', NULL);
INSERT INTO `j_job_log` VALUES ('98029dfcab7a47d192669dc06df5dbe6', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:42:10', '2021-04-06 21:42:10', NULL);
INSERT INTO `j_job_log` VALUES ('9bd4513745ab45099fbb363e29f97cbc', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:35:50', '2021-04-06 21:35:50', NULL);
INSERT INTO `j_job_log` VALUES ('9c15a946931d4d37bbd198eca9719ee7', 'f164e785452e40f1b43d2ad79f847554', '系统默认（多参）', 'DEFAULT', NULL, 'demoTask.ryMultipleParams(\\\'ry\\\', true, 2000L, 316.50D, 100)', '系统默认（多参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:33:03', '2021-04-06 21:33:03', NULL);
INSERT INTO `j_job_log` VALUES ('9c52108f240e46bf804bafea748203da', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:35:20', '2021-04-06 21:35:20', NULL);
INSERT INTO `j_job_log` VALUES ('9d0caee819524582aaf7ba8310cab527', '7d2a6d08963c4e799de8157d82d92807', '系统默认（有参）', 'DEFAULT', NULL, 'demoTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:44:46', '2021-04-06 21:44:46', NULL);
INSERT INTO `j_job_log` VALUES ('9d48b9baf707413cba075bbd85e7af15', '7d2a6d08963c4e799de8157d82d92807', '系统默认（有参）', 'DEFAULT', NULL, 'demoTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:37:31', '2021-04-06 21:37:31', NULL);
INSERT INTO `j_job_log` VALUES ('9dac7df66e2c42d7b657959506274166', '7d2a6d08963c4e799de8157d82d92807', '系统默认（有参）', 'DEFAULT', NULL, 'demoTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：8毫秒', '0', NULL, '2021-04-06 21:38:46', '2021-04-06 21:38:46', NULL);
INSERT INTO `j_job_log` VALUES ('9e16999985c74196a9ef620fd843f922', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:26:40', '2021-04-06 21:26:40', NULL);
INSERT INTO `j_job_log` VALUES ('9e214aab133440f19e3029887325f262', '7d2a6d08963c4e799de8157d82d92807', '系统默认（有参）', 'DEFAULT', NULL, 'demoTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：1毫秒', '0', NULL, '2021-04-06 21:45:16', '2021-04-06 21:45:16', NULL);
INSERT INTO `j_job_log` VALUES ('9ec5dbb1f42c46288b4edc0d9eee0b2a', 'f164e785452e40f1b43d2ad79f847554', '系统默认（多参）', 'DEFAULT', NULL, 'demoTask.ryMultipleParams(\\\'ry\\\', true, 2000L, 316.50D, 100)', '系统默认（多参） 总共耗时：1毫秒', '0', NULL, '2021-04-06 21:36:22', '2021-04-06 21:36:22', NULL);
INSERT INTO `j_job_log` VALUES ('a0f63cf86f6341948e5d10bfacdd6cf8', '7d2a6d08963c4e799de8157d82d92807', '系统默认（有参）', 'DEFAULT', NULL, 'demoTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:31:20', '2021-04-06 21:31:20', NULL);
INSERT INTO `j_job_log` VALUES ('a1299de8d25c4cd1a49469cd484efa29', 'f164e785452e40f1b43d2ad79f847554', '系统默认（多参）', 'DEFAULT', NULL, 'demoTask.ryMultipleParams(\\\'ry\\\', true, 2000L, 316.50D, 100)', '系统默认（多参） 总共耗时：1毫秒', '0', NULL, '2021-04-06 21:38:22', '2021-04-06 21:38:22', NULL);
INSERT INTO `j_job_log` VALUES ('a20ccefeafc949c6a5fc7231e45affd1', '7d2a6d08963c4e799de8157d82d92807', '系统默认（有参）', 'DEFAULT', NULL, 'demoTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:45:31', '2021-04-06 21:45:31', NULL);
INSERT INTO `j_job_log` VALUES ('a305c05277eb4fdea21a8558c47d1a30', '7d2a6d08963c4e799de8157d82d92807', '系统默认（有参）', 'DEFAULT', NULL, 'demoTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：1毫秒', '0', NULL, '2021-04-06 21:34:46', '2021-04-06 21:34:46', NULL);
INSERT INTO `j_job_log` VALUES ('a3212a846f074539b171236926ecd42a', 'f164e785452e40f1b43d2ad79f847554', '系统默认（多参）', 'DEFAULT', NULL, 'demoTask.ryMultipleParams(\\\'ry\\\', true, 2000L, 316.50D, 100)', '系统默认（多参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:34:03', '2021-04-06 21:34:03', NULL);
INSERT INTO `j_job_log` VALUES ('a4d801cf30dd42b598bd7e57c50dae9e', 'f164e785452e40f1b43d2ad79f847554', '系统默认（多参）', 'DEFAULT', NULL, 'demoTask.ryMultipleParams(\\\'ry\\\', true, 2000L, 316.50D, 100)', '系统默认（多参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:38:42', '2021-04-06 21:38:42', NULL);
INSERT INTO `j_job_log` VALUES ('a5cf081966a546778dbe0346a091f64d', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:36:10', '2021-04-06 21:36:10', NULL);
INSERT INTO `j_job_log` VALUES ('a7754fa87f00402cb8abb45143051641', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:31:40', '2021-04-06 21:31:40', NULL);
INSERT INTO `j_job_log` VALUES ('a8233a8f82484732828e2e26cc813f0b', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:38:02', '2021-04-06 21:38:02', NULL);
INSERT INTO `j_job_log` VALUES ('a90da05195a8464ca5734b174ebe2073', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:44:40', '2021-04-06 21:44:40', NULL);
INSERT INTO `j_job_log` VALUES ('aaf8c89ecbcb440fa6fcec1e97e05df6', '7d2a6d08963c4e799de8157d82d92807', '系统默认（有参）', 'DEFAULT', NULL, 'demoTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:32:01', '2021-04-06 21:32:01', NULL);
INSERT INTO `j_job_log` VALUES ('ab7a32b3342c42e191844a87086875bd', 'f164e785452e40f1b43d2ad79f847554', '系统默认（多参）', 'DEFAULT', NULL, 'demoTask.ryMultipleParams(\\\'ry\\\', true, 2000L, 316.50D, 100)', '系统默认（多参） 总共耗时：4毫秒', '0', NULL, '2021-04-06 21:34:22', '2021-04-06 21:34:22', NULL);
INSERT INTO `j_job_log` VALUES ('abf58eafbdc4432aa7706269c3263262', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：1毫秒', '0', NULL, '2021-04-06 21:32:20', '2021-04-06 21:32:20', NULL);
INSERT INTO `j_job_log` VALUES ('abf8efa629054c7386826ed218a8cb17', NULL, NULL, NULL, NULL, NULL, 'null 总共耗时：1毫秒', '1', 'NullPointerException: null', '2021-04-06 21:19:22', '2021-04-06 21:19:22', NULL);
INSERT INTO `j_job_log` VALUES ('ac91e56f47df46a08bad1ad3922af4f4', 'f164e785452e40f1b43d2ad79f847554', '系统默认（多参）', 'DEFAULT', NULL, 'demoTask.ryMultipleParams(\\\'ry\\\', true, 2000L, 316.50D, 100)', '系统默认（多参） 总共耗时：1毫秒', '0', NULL, '2021-04-06 21:40:03', '2021-04-06 21:40:03', NULL);
INSERT INTO `j_job_log` VALUES ('ae1db679d87446508d61b558bc4318de', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:30:40', '2021-04-06 21:30:40', NULL);
INSERT INTO `j_job_log` VALUES ('ae4d2b71190246d0b6aa0a78e77871fb', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:40:20', '2021-04-06 21:40:20', NULL);
INSERT INTO `j_job_log` VALUES ('ae8142511a2e4b9e95b86b445b5081d3', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:28:01', '2021-04-06 21:28:01', NULL);
INSERT INTO `j_job_log` VALUES ('aedcb3bd43d645b1a893fe4c3d4f0949', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：1毫秒', '0', NULL, '2021-04-06 21:45:20', '2021-04-06 21:45:20', NULL);
INSERT INTO `j_job_log` VALUES ('aefb3171af0d44799734bc3dd191a8e4', NULL, NULL, NULL, NULL, NULL, 'null 总共耗时：0毫秒', '1', 'NullPointerException: null', '2021-04-06 21:21:25', '2021-04-06 21:21:25', NULL);
INSERT INTO `j_job_log` VALUES ('b0045214f63043ccb1cf7bf3f4f2824b', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:45:10', '2021-04-06 21:45:10', NULL);
INSERT INTO `j_job_log` VALUES ('b05d3932498a45299123dbf5b7a53a77', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:31:01', '2021-04-06 21:31:01', NULL);
INSERT INTO `j_job_log` VALUES ('b268dc12690645788536d303a4e2c86b', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:26:50', '2021-04-06 21:26:50', NULL);
INSERT INTO `j_job_log` VALUES ('b3b89b81394649e2b3d2ebe4b61b1b54', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:44:50', '2021-04-06 21:44:50', NULL);
INSERT INTO `j_job_log` VALUES ('b3ece3ba13b549d8b980f735b3fd6877', 'f164e785452e40f1b43d2ad79f847554', '系统默认（多参）', 'DEFAULT', NULL, 'demoTask.ryMultipleParams(\\\'ry\\\', true, 2000L, 316.50D, 100)', '系统默认（多参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:33:42', '2021-04-06 21:33:42', NULL);
INSERT INTO `j_job_log` VALUES ('b4c589fabf134a4a87c64d9b0c6ff9a2', '7d2a6d08963c4e799de8157d82d92807', '系统默认（有参）', 'DEFAULT', NULL, 'demoTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:32:31', '2021-04-06 21:32:31', NULL);
INSERT INTO `j_job_log` VALUES ('b6c000c3dab548509ddd78392142a58b', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:40:50', '2021-04-06 21:40:50', NULL);
INSERT INTO `j_job_log` VALUES ('bcc9e262e50d4e6ea920bf1baa75c97d', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:33:40', '2021-04-06 21:33:40', NULL);
INSERT INTO `j_job_log` VALUES ('bd120d6f85354eef99be55b682515707', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:37:21', '2021-04-06 21:37:21', NULL);
INSERT INTO `j_job_log` VALUES ('bd1671b817f049659c5f6a7e0ddb49d0', '7d2a6d08963c4e799de8157d82d92807', '系统默认（有参）', 'DEFAULT', NULL, 'demoTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:41:16', '2021-04-06 21:41:16', NULL);
INSERT INTO `j_job_log` VALUES ('bf0c444f167843339cb2dca75a35987a', '7d2a6d08963c4e799de8157d82d92807', '系统默认（有参）', 'DEFAULT', NULL, 'demoTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:37:01', '2021-04-06 21:37:01', NULL);
INSERT INTO `j_job_log` VALUES ('bf83d4de12564eff9371a79cbd2a3fd2', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:40:40', '2021-04-06 21:40:40', NULL);
INSERT INTO `j_job_log` VALUES ('c16ec24f5cc84358be73de1290e70971', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:46:02', '2021-04-06 21:46:02', NULL);
INSERT INTO `j_job_log` VALUES ('c2d84292a1d8493ca7040a3b1c4c848e', 'f164e785452e40f1b43d2ad79f847554', '系统默认（多参）', 'DEFAULT', NULL, 'demoTask.ryMultipleParams(\\\'ry\\\', true, 2000L, 316.50D, 100)', '系统默认（多参） 总共耗时：1毫秒', '0', NULL, '2021-04-06 21:38:03', '2021-04-06 21:38:03', NULL);
INSERT INTO `j_job_log` VALUES ('c32af613528f4705a5d142ac7d0d395f', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:41:10', '2021-04-06 21:41:10', NULL);
INSERT INTO `j_job_log` VALUES ('c39ec01cf8ec4af1a6c5cf231a9740fa', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：1毫秒', '0', NULL, '2021-04-06 21:39:50', '2021-04-06 21:39:50', NULL);
INSERT INTO `j_job_log` VALUES ('c3d140787b504277858a8ae635dc6892', '7d2a6d08963c4e799de8157d82d92807', '系统默认（有参）', 'DEFAULT', NULL, 'demoTask.ryParams(\\\'ry\\\')', '系统默认（有参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:30:17', '2021-04-06 21:30:17', NULL);
INSERT INTO `j_job_log` VALUES ('c656528ccab94180923c60ccf447d0e8', 'f164e785452e40f1b43d2ad79f847554', '系统默认（多参）', 'DEFAULT', NULL, 'demoTask.ryMultipleParams(\\\'ry\\\', true, 2000L, 316.50D, 100)', '系统默认（多参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:35:22', '2021-04-06 21:35:22', NULL);
INSERT INTO `j_job_log` VALUES ('c77b6679353f4c3997f426501a634b9a', '7d2a6d08963c4e799de8157d82d92807', '系统默认（有参）', 'DEFAULT', NULL, 'demoTask.ryParams(\\\'ry\\\')', '系统默认（有参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:30:31', '2021-04-06 21:30:31', NULL);
INSERT INTO `j_job_log` VALUES ('ca517a6b52a84c6384d2c75ff96cdaf7', '7d2a6d08963c4e799de8157d82d92807', '系统默认（有参）', 'DEFAULT', NULL, 'demoTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:41:31', '2021-04-06 21:41:31', NULL);
INSERT INTO `j_job_log` VALUES ('caab8ad2ed414d369d8219dfaa159062', '7d2a6d08963c4e799de8157d82d92807', '系统默认（有参）', 'DEFAULT', NULL, 'demoTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:31:31', '2021-04-06 21:31:31', NULL);
INSERT INTO `j_job_log` VALUES ('ccb0a822aea14131b8969fb5c3816eec', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：1毫秒', '0', NULL, '2021-04-06 21:32:10', '2021-04-06 21:32:10', NULL);
INSERT INTO `j_job_log` VALUES ('cd16e052e9fa4914ba121f7d4ba0bbd6', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:37:10', '2021-04-06 21:37:10', NULL);
INSERT INTO `j_job_log` VALUES ('cd343dcd366e49f98bc7de4300547715', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:28:30', '2021-04-06 21:28:30', NULL);
INSERT INTO `j_job_log` VALUES ('cd613fc6cf1645deaaf445cb8c1f25f3', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:38:20', '2021-04-06 21:38:20', NULL);
INSERT INTO `j_job_log` VALUES ('ce98b95fc76049c29182a404ad803ac3', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:45:50', '2021-04-06 21:45:50', NULL);
INSERT INTO `j_job_log` VALUES ('d07505174345463d844b5d996713aa03', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:39:40', '2021-04-06 21:39:40', NULL);
INSERT INTO `j_job_log` VALUES ('d18f0ef25af84f8c82ef544a1a4ac85b', '7d2a6d08963c4e799de8157d82d92807', '系统默认（有参）', 'DEFAULT', NULL, 'demoTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：1毫秒', '0', NULL, '2021-04-06 21:37:16', '2021-04-06 21:37:16', NULL);
INSERT INTO `j_job_log` VALUES ('d2a605e333694c1089cbec04a27649d7', 'f164e785452e40f1b43d2ad79f847554', '系统默认（多参）', 'DEFAULT', NULL, 'demoTask.ryMultipleParams(\\\'ry\\\', true, 2000L, 316.50D, 100)', '系统默认（多参） 总共耗时：1毫秒', '0', NULL, '2021-04-06 21:42:42', '2021-04-06 21:42:42', NULL);
INSERT INTO `j_job_log` VALUES ('d2e94dbb0cc44eb0976ae3673c89b7f0', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:42:50', '2021-04-06 21:42:50', NULL);
INSERT INTO `j_job_log` VALUES ('d2ed46d9efef4a618a9536af3766f70a', NULL, NULL, NULL, NULL, NULL, 'null 总共耗时：2023毫秒', '1', 'NullPointerException: null', '2021-04-06 21:20:12', '2021-04-06 21:20:14', NULL);
INSERT INTO `j_job_log` VALUES ('d375da25c652471086125ce1bab3c937', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:43:20', '2021-04-06 21:43:20', NULL);
INSERT INTO `j_job_log` VALUES ('d54467c7ee944325a980c6016113aaa6', '7d2a6d08963c4e799de8157d82d92807', '系统默认（有参）', 'DEFAULT', NULL, 'demoTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:42:31', '2021-04-06 21:42:31', NULL);
INSERT INTO `j_job_log` VALUES ('d6ee82e5594e4ab8a6a32211c2f2c340', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:31:21', '2021-04-06 21:31:21', NULL);
INSERT INTO `j_job_log` VALUES ('d7aca922280c4d67978a40ad673a7a81', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：1毫秒', '0', NULL, '2021-04-06 21:39:10', '2021-04-06 21:39:10', NULL);
INSERT INTO `j_job_log` VALUES ('d984caa660954a9cbae90bbc340e0770', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:37:50', '2021-04-06 21:37:50', NULL);
INSERT INTO `j_job_log` VALUES ('dbdb8d37bf8a494a9dbee3fccbc2f9da', NULL, NULL, NULL, NULL, NULL, 'null 总共耗时：0毫秒', '1', 'NullPointerException: null', '2021-04-06 21:21:24', '2021-04-06 21:21:24', NULL);
INSERT INTO `j_job_log` VALUES ('dcd5d398ae7e497797a0032a60b3bdbf', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:27:01', '2021-04-06 21:27:01', NULL);
INSERT INTO `j_job_log` VALUES ('dcfab46a44d040da9e4d6eedfc670297', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:33:32', '2021-04-06 21:33:32', NULL);
INSERT INTO `j_job_log` VALUES ('dd39e41058404a0080f9dbb4d0b47fb7', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：1毫秒', '0', NULL, '2021-04-06 21:28:40', '2021-04-06 21:28:40', NULL);
INSERT INTO `j_job_log` VALUES ('dff8db804e0d4e5da479aea514e66c86', 'f164e785452e40f1b43d2ad79f847554', '系统默认（多参）', 'DEFAULT', NULL, 'demoTask.ryMultipleParams(\\\'ry\\\', true, 2000L, 316.50D, 100)', '系统默认（多参） 总共耗时：1毫秒', '0', NULL, '2021-04-06 21:39:22', '2021-04-06 21:39:22', NULL);
INSERT INTO `j_job_log` VALUES ('e03552a2f3ca411eb080fe01644cbdfe', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:46:50', '2021-04-06 21:46:50', NULL);
INSERT INTO `j_job_log` VALUES ('e08c1a62cdf54898aa6326ecfa57c38e', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:38:10', '2021-04-06 21:38:10', NULL);
INSERT INTO `j_job_log` VALUES ('e2446555a0a5473b8cd35af1b5ba4150', '7d2a6d08963c4e799de8157d82d92807', '系统默认（有参）', 'DEFAULT', NULL, 'demoTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:40:31', '2021-04-06 21:40:31', NULL);
INSERT INTO `j_job_log` VALUES ('e2ee48a86286485dabdd71333a2d23a2', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：1毫秒', '0', NULL, '2021-04-06 21:42:20', '2021-04-06 21:42:20', NULL);
INSERT INTO `j_job_log` VALUES ('e36ba6317d2743e4b0190d74e741dae9', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：1毫秒', '0', NULL, '2021-04-06 21:25:30', '2021-04-06 21:25:30', NULL);
INSERT INTO `j_job_log` VALUES ('e4505cde5430409bb0b3d1619ca3b6fe', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：1毫秒', '0', NULL, '2021-04-06 21:38:40', '2021-04-06 21:38:40', NULL);
INSERT INTO `j_job_log` VALUES ('e479c51daf1f4023a254da330b968b39', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：1毫秒', '0', NULL, '2021-04-06 21:45:40', '2021-04-06 21:45:40', NULL);
INSERT INTO `j_job_log` VALUES ('e4eb3243e7c84521884cab959e93b3bf', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:44:10', '2021-04-06 21:44:10', NULL);
INSERT INTO `j_job_log` VALUES ('e8319ffe3b9e403fb214dc9415c5dcbe', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:40:02', '2021-04-06 21:40:02', NULL);
INSERT INTO `j_job_log` VALUES ('e874248a7b73427b9ad3373669c12641', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:44:20', '2021-04-06 21:44:20', NULL);
INSERT INTO `j_job_log` VALUES ('e925bf31cbf94b67bafad51c0ed391f4', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:26:00', '2021-04-06 21:26:00', NULL);
INSERT INTO `j_job_log` VALUES ('e95c2cb560f8462aaa99013428796ad7', 'f164e785452e40f1b43d2ad79f847554', '系统默认（多参）', 'DEFAULT', NULL, 'demoTask.ryMultipleParams(\\\'ry\\\', true, 2000L, 316.50D, 100)', '系统默认（多参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:40:42', '2021-04-06 21:40:42', NULL);
INSERT INTO `j_job_log` VALUES ('ecad2cb061b74614a2f732436e1c647c', 'f164e785452e40f1b43d2ad79f847554', '系统默认（多参）', 'DEFAULT', NULL, 'demoTask.ryMultipleParams(\\\'ry\\\', true, 2000L, 316.50D, 100)', '系统默认（多参） 总共耗时：1毫秒', '0', NULL, '2021-04-06 21:43:42', '2021-04-06 21:43:42', NULL);
INSERT INTO `j_job_log` VALUES ('ed0c879ca79c4366b773872b87052dbd', '7d2a6d08963c4e799de8157d82d92807', '系统默认（有参）', 'DEFAULT', NULL, 'demoTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:30:59', '2021-04-06 21:30:59', NULL);
INSERT INTO `j_job_log` VALUES ('ee12fef2665041baadef33fc0234cce4', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:34:02', '2021-04-06 21:34:02', NULL);
INSERT INTO `j_job_log` VALUES ('ee7eb49d4d5e40a1941c71d85cd68337', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:25:50', '2021-04-06 21:25:50', NULL);
INSERT INTO `j_job_log` VALUES ('ef9232fe29824efeb14aea626c94e35e', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:40:10', '2021-04-06 21:40:10', NULL);
INSERT INTO `j_job_log` VALUES ('efe112f0865b4aa4954b5630b622184b', 'f164e785452e40f1b43d2ad79f847554', '系统默认（多参）', 'DEFAULT', NULL, 'demoTask.ryMultipleParams(\\\'ry\\\', true, 2000L, 316.50D, 100)', '系统默认（多参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:46:03', '2021-04-06 21:46:03', NULL);
INSERT INTO `j_job_log` VALUES ('eff587801d9f41c1bab33890e8088cad', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:35:02', '2021-04-06 21:35:02', NULL);
INSERT INTO `j_job_log` VALUES ('f08a0a2d4fa449dbaf2432bfc42e3510', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:46:10', '2021-04-06 21:46:10', NULL);
INSERT INTO `j_job_log` VALUES ('f3e91cfbd31544bd8cf27834a1022a1f', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：4毫秒', '0', NULL, '2021-04-06 21:29:39', '2021-04-06 21:29:39', NULL);
INSERT INTO `j_job_log` VALUES ('f5201c1dbb954d6f87ab22fe9373dc6e', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：1毫秒', '0', NULL, '2021-04-06 21:33:10', '2021-04-06 21:33:10', NULL);
INSERT INTO `j_job_log` VALUES ('f6031d7dedf54de7932779a95afaf9ca', '7d2a6d08963c4e799de8157d82d92807', '系统默认（有参）', 'DEFAULT', NULL, 'demoTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:43:01', '2021-04-06 21:43:01', NULL);
INSERT INTO `j_job_log` VALUES ('f680a680652c4bf0a384ca28a44262d7', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:43:10', '2021-04-06 21:43:10', NULL);
INSERT INTO `j_job_log` VALUES ('f766af890d8445f38f49259f5b43b73e', '7d2a6d08963c4e799de8157d82d92807', '系统默认（有参）', 'DEFAULT', NULL, 'demoTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：1毫秒', '0', NULL, '2021-04-06 21:32:16', '2021-04-06 21:32:16', NULL);
INSERT INTO `j_job_log` VALUES ('f80ecf4e400b4bed941f3ea934130456', '7d2a6d08963c4e799de8157d82d92807', '系统默认（有参）', 'DEFAULT', NULL, 'demoTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:44:01', '2021-04-06 21:44:01', NULL);
INSERT INTO `j_job_log` VALUES ('f8ac9e3d29aa4217b261d2687cb7d2a0', NULL, NULL, NULL, NULL, NULL, 'null 总共耗时：0毫秒', '1', 'NullPointerException: null', '2021-04-06 21:19:21', '2021-04-06 21:19:21', NULL);
INSERT INTO `j_job_log` VALUES ('fb6755196df14ee288aa8889b77e9dff', '7d2a6d08963c4e799de8157d82d92807', '系统默认（有参）', 'DEFAULT', NULL, 'demoTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:39:46', '2021-04-06 21:39:46', NULL);
INSERT INTO `j_job_log` VALUES ('fc4a56fec08f45589fe0d805df7ff10b', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:34:50', '2021-04-06 21:34:50', NULL);
INSERT INTO `j_job_log` VALUES ('fe8feda59fee4db7a5e1e26f2677fc99', '7d2a6d08963c4e799de8157d82d92807', '系统默认（有参）', 'DEFAULT', NULL, 'demoTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:41:46', '2021-04-06 21:41:46', NULL);
INSERT INTO `j_job_log` VALUES ('fe972cd02e5d4e1ca171c77917ec2d5f', '7d2a6d08963c4e799de8157d82d92807', '系统默认（有参）', 'DEFAULT', NULL, 'demoTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:35:01', '2021-04-06 21:35:01', NULL);
INSERT INTO `j_job_log` VALUES ('ff9e3c1b5da543a49d57e6c07647f65e', '8ef01eb1adb840c9bc8be51270493408', '系统默认（无参）', 'DEFAULT', NULL, 'demoTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:42:40', '2021-04-06 21:42:40', NULL);
INSERT INTO `j_job_log` VALUES ('ffaee074c6f44ca5a2c40fdc1a7ed2c7', '7d2a6d08963c4e799de8157d82d92807', '系统默认（有参）', 'DEFAULT', NULL, 'demoTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', NULL, '2021-04-06 21:38:16', '2021-04-06 21:38:16', NULL);

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details`  (
  `client_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '客户端标\r\n识',
  `resource_ids` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接入资源列表',
  `client_secret` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户端秘钥',
  `scope` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `authorized_grant_types` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `web_server_redirect_uri` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `authorities` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `access_token_validity` int(11) NULL DEFAULT NULL,
  `refresh_token_validity` int(11) NULL DEFAULT NULL,
  `additional_information` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `archived` tinyint(4) NULL DEFAULT NULL,
  `trusted` tinyint(4) NULL DEFAULT NULL,
  `autoapprove` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`client_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '接入客户端信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES ('c1', 'res1', '$2a$10$H1wKlULSV5J9XsA9AACC9OsNtIYOaFEAH3gi5nYsIkWGkqiYLCwli', 'all', 'client_credentials,password,authorization_code,implicit,refresh_token', 'http://www.baidu.com', NULL, 7200, 259200, NULL, '2020-09-04 11:52:42', 0, 0, 'false');

-- ----------------------------
-- Table structure for oauth_code
-- ----------------------------
DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code`  (
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `authentication` blob NULL,
  INDEX `code_index`(`code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for permissions
-- ----------------------------
DROP TABLE IF EXISTS `permissions`;
CREATE TABLE `permissions`  (
  `role` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `resource` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `action` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  UNIQUE INDEX `uk_role_permission`(`role`, `resource`, `action`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for persistent_logins
-- ----------------------------
DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins`  (
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `series` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `token` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `last_used` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`series`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles`  (
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `role` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  UNIQUE INDEX `idx_user_role`(`username`, `role`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES ('nacos', 'ROLE_ADMIN');

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`  (
  `config_id` int(5) NOT NULL AUTO_INCREMENT COMMENT '参数主键',
  `config_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '参数名称',
  `config_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '参数键名',
  `config_value` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '参数键值',
  `config_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`config_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '参数配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES (1, '主框架页-默认皮肤样式名称', 'sys.index.skinName', 'skin-blue', 'Y', 'admin', '2021-02-03 13:29:25', '', NULL, '蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow');
INSERT INTO `sys_config` VALUES (2, '用户管理-账号初始密码', 'sys.user.initPassword', '123456', 'Y', 'admin', '2021-02-03 13:29:25', '', NULL, '初始化密码 123456');
INSERT INTO `sys_config` VALUES (3, '主框架页-侧边栏主题', 'sys.index.sideTheme', 'theme-dark', 'Y', 'admin', '2021-02-03 13:29:25', '', NULL, '深色主题theme-dark，浅色主题theme-light');
INSERT INTO `sys_config` VALUES (14, '主框架页-是否开启页脚', 'sys.index.ignoreFooter', 'true', 'Y', 'admin', '2021-04-22 13:40:25', '', NULL, '是否开启底部页脚显示（true显示，false隐藏）');

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父部门id',
  `ancestors` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '祖级列表',
  `dept_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '部门名称',
  `order_num` int(4) NULL DEFAULT 0 COMMENT '显示顺序',
  `leader` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '负责人',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 201 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (100, 0, '0', '若依科技', 0, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2021-02-03 13:29:12', '', NULL);
INSERT INTO `sys_dept` VALUES (101, 100, '0,100', '深圳总公司', 1, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2021-02-03 13:29:12', '', NULL);
INSERT INTO `sys_dept` VALUES (102, 100, '0,100', '长沙分公司', 2, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2021-02-03 13:29:12', '', NULL);
INSERT INTO `sys_dept` VALUES (103, 101, '0,100,101', '研发部门', 1, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2021-02-03 13:29:12', '', NULL);
INSERT INTO `sys_dept` VALUES (104, 101, '0,100,101', '市场部门', 2, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2021-02-03 13:29:12', '', NULL);
INSERT INTO `sys_dept` VALUES (105, 101, '0,100,101', '测试部门', 3, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2021-02-03 13:29:13', '', NULL);
INSERT INTO `sys_dept` VALUES (106, 101, '0,100,101', '财务部门', 4, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2021-02-03 13:29:13', '', NULL);
INSERT INTO `sys_dept` VALUES (107, 101, '0,100,101', '运维部门', 5, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2021-02-03 13:29:13', '', NULL);
INSERT INTO `sys_dept` VALUES (108, 102, '0,100,102', '市场部门', 1, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2021-02-03 13:29:13', '', NULL);
INSERT INTO `sys_dept` VALUES (109, 102, '0,100,102', '财务部门', 2, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2021-02-03 13:29:13', '', NULL);
INSERT INTO `sys_dept` VALUES (200, 101, '0,100,101', '开发部门11', 3, 'luyanan', '18135237517', 'luyanan0718@163.com', '0', '2', NULL, '2021-02-15 21:10:27', NULL, '2021-02-15 21:10:27');

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data`  (
  `dict_code` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_sort` int(4) NULL DEFAULT 0 COMMENT '字典排序',
  `dict_label` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '表格回显样式',
  `is_default` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典数据表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
INSERT INTO `sys_dict_data` VALUES (1, 1, '男', '0', 'sys_user_sex', '', '', 'Y', '0', 'admin', '2021-02-03 13:29:23', '', NULL, '性别男');
INSERT INTO `sys_dict_data` VALUES (2, 2, '女', '1', 'sys_user_sex', '', '', 'N', '0', 'admin', '2021-02-03 13:29:23', '', NULL, '性别女');
INSERT INTO `sys_dict_data` VALUES (3, 3, '未知', '2', 'sys_user_sex', '', '', 'N', '0', 'admin', '2021-02-03 13:29:23', '', NULL, '性别未知');
INSERT INTO `sys_dict_data` VALUES (4, 1, '显示', '0', 'sys_show_hide', '', 'primary', 'Y', '0', 'admin', '2021-02-03 13:29:23', '', NULL, '显示菜单');
INSERT INTO `sys_dict_data` VALUES (5, 2, '隐藏', '1', 'sys_show_hide', '', 'danger', 'N', '0', 'admin', '2021-02-03 13:29:23', '', NULL, '隐藏菜单');
INSERT INTO `sys_dict_data` VALUES (6, 1, '正常', '0', 'sys_normal_disable', '', 'primary', 'Y', '0', 'admin', '2021-02-03 13:29:24', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (7, 2, '停用', '1', 'sys_normal_disable', '', 'danger', 'N', '0', 'admin', '2021-02-03 13:29:24', '', NULL, '停用状态');
INSERT INTO `sys_dict_data` VALUES (8, 1, '正常', '0', 'sys_job_status', '', 'primary', 'Y', '0', 'admin', '2021-02-03 13:29:24', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (9, 2, '暂停', '1', 'sys_job_status', '', 'danger', 'N', '0', 'admin', '2021-02-03 13:29:24', '', NULL, '停用状态');
INSERT INTO `sys_dict_data` VALUES (10, 1, '默认', 'DEFAULT', 'sys_job_group', '', '', 'Y', '0', 'admin', '2021-02-03 13:29:24', '', NULL, '默认分组');
INSERT INTO `sys_dict_data` VALUES (11, 2, '系统', 'SYSTEM', 'sys_job_group', '', '', 'N', '0', 'admin', '2021-02-03 13:29:24', '', NULL, '系统分组');
INSERT INTO `sys_dict_data` VALUES (12, 1, '是', 'Y', 'sys_yes_no', '', 'primary', 'Y', '0', 'admin', '2021-02-03 13:29:24', '', NULL, '系统默认是');
INSERT INTO `sys_dict_data` VALUES (13, 2, '否', 'N', 'sys_yes_no', '', 'danger', 'N', '0', 'admin', '2021-02-03 13:29:24', '', NULL, '系统默认否');
INSERT INTO `sys_dict_data` VALUES (14, 1, '通知', '1', 'sys_notice_type', '', 'warning', 'Y', '0', 'admin', '2021-02-03 13:29:24', '', NULL, '通知');
INSERT INTO `sys_dict_data` VALUES (15, 2, '公告', '2', 'sys_notice_type', '', 'success', 'N', '0', 'admin', '2021-02-03 13:29:24', '', NULL, '公告');
INSERT INTO `sys_dict_data` VALUES (16, 1, '正常', '0', 'sys_notice_status', '', 'primary', 'Y', '0', 'admin', '2021-02-03 13:29:24', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (17, 2, '关闭', '1', 'sys_notice_status', '', 'danger', 'N', '0', 'admin', '2021-02-03 13:29:24', '', NULL, '关闭状态');
INSERT INTO `sys_dict_data` VALUES (18, 1, '新增', '1', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2021-02-03 13:29:24', '', NULL, '新增操作');
INSERT INTO `sys_dict_data` VALUES (19, 2, '修改', '2', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2021-02-03 13:29:24', '', NULL, '修改操作');
INSERT INTO `sys_dict_data` VALUES (20, 3, '删除', '3', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2021-02-03 13:29:24', '', NULL, '删除操作');
INSERT INTO `sys_dict_data` VALUES (21, 4, '授权', '4', 'sys_oper_type', '', 'primary', 'N', '0', 'admin', '2021-02-03 13:29:24', '', NULL, '授权操作');
INSERT INTO `sys_dict_data` VALUES (22, 5, '导出', '5', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2021-02-03 13:29:24', '', NULL, '导出操作');
INSERT INTO `sys_dict_data` VALUES (23, 6, '导入', '6', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2021-02-03 13:29:24', '', NULL, '导入操作');
INSERT INTO `sys_dict_data` VALUES (24, 7, '强退', '7', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2021-02-03 13:29:24', '', NULL, '强退操作');
INSERT INTO `sys_dict_data` VALUES (25, 8, '生成代码', '8', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2021-02-03 13:29:24', '', NULL, '生成操作');
INSERT INTO `sys_dict_data` VALUES (26, 9, '清空数据', '9', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2021-02-03 13:29:24', '', NULL, '清空操作');
INSERT INTO `sys_dict_data` VALUES (27, 1, '成功', '0', 'sys_common_status', '', 'primary', 'N', '0', 'admin', '2021-02-03 13:29:25', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (28, 2, '失败', '1', 'sys_common_status', '', 'danger', 'N', '0', 'admin', '2021-02-03 13:29:25', '', NULL, '停用状态');

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type`  (
  `dict_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `dict_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_id`) USING BTREE,
  UNIQUE INDEX `dict_type`(`dict_type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type` VALUES (1, '用户性别', 'sys_user_sex', '0', 'admin', '2021-02-03 13:29:23', '', NULL, '用户性别列表');
INSERT INTO `sys_dict_type` VALUES (2, '菜单状态', 'sys_show_hide', '0', 'admin', '2021-02-03 13:29:23', '', NULL, '菜单状态列表');
INSERT INTO `sys_dict_type` VALUES (3, '系统开关', 'sys_normal_disable', '0', 'admin', '2021-02-03 13:29:23', '', NULL, '系统开关列表');
INSERT INTO `sys_dict_type` VALUES (4, '任务状态', 'sys_job_status', '0', 'admin', '2021-02-03 13:29:23', '', NULL, '任务状态列表');
INSERT INTO `sys_dict_type` VALUES (5, '任务分组', 'sys_job_group', '0', 'admin', '2021-02-03 13:29:23', '', NULL, '任务分组列表');
INSERT INTO `sys_dict_type` VALUES (6, '系统是否', 'sys_yes_no', '0', 'admin', '2021-02-03 13:29:23', '', NULL, '系统是否列表');
INSERT INTO `sys_dict_type` VALUES (7, '通知类型', 'sys_notice_type', '0', 'admin', '2021-02-03 13:29:23', '', NULL, '通知类型列表');
INSERT INTO `sys_dict_type` VALUES (8, '通知状态', 'sys_notice_status', '0', 'admin', '2021-02-03 13:29:23', '', NULL, '通知状态列表');
INSERT INTO `sys_dict_type` VALUES (9, '操作类型', 'sys_oper_type', '0', 'admin', '2021-02-03 13:29:23', '', NULL, '操作类型列表');
INSERT INTO `sys_dict_type` VALUES (10, '系统状态', 'sys_common_status', '0', 'admin', '2021-02-03 13:29:23', '', NULL, '登录状态列表');

-- ----------------------------
-- Table structure for sys_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_job`;
CREATE TABLE `sys_job`  (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务ID',
  `job_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '任务名称',
  `job_group` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'DEFAULT' COMMENT '任务组名',
  `invoke_target` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调用目标字符串',
  `cron_expression` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT 'cron执行表达式',
  `misfire_policy` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '3' COMMENT '计划执行错误策略（1立即执行 2执行一次 3放弃执行）',
  `concurrent` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '是否并发执行（0允许 1禁止）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1暂停）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '备注信息',
  PRIMARY KEY (`job_id`, `job_name`, `job_group`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '定时任务调度表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_job
-- ----------------------------
INSERT INTO `sys_job` VALUES (1, '系统默认（无参）', 'DEFAULT', 'ryTask.ryNoParams', '0/10 * * * * ?', '3', '1', '1', 'admin', '2021-02-03 13:29:25', '', NULL, '');
INSERT INTO `sys_job` VALUES (2, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '0/15 * * * * ?', '3', '1', '1', 'admin', '2021-02-03 13:29:25', '', NULL, '');
INSERT INTO `sys_job` VALUES (3, '系统默认（多参）', 'DEFAULT', 'ryTask.ryMultipleParams(\'ry\', true, 2000L, 316.50D, 100)', '0/20 * * * * ?', '3', '1', '1', 'admin', '2021-02-03 13:29:25', '', NULL, '');

-- ----------------------------
-- Table structure for sys_job_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_job_log`;
CREATE TABLE `sys_job_log`  (
  `job_log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志ID',
  `job_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务名称',
  `job_group` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务组名',
  `invoke_target` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调用目标字符串',
  `job_message` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '日志信息',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '执行状态（0正常 1失败）',
  `exception_info` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '异常信息',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_log_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '定时任务调度日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_logininfor
-- ----------------------------
DROP TABLE IF EXISTS `sys_logininfor`;
CREATE TABLE `sys_logininfor`  (
  `info_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '访问ID',
  `user_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '用户账号',
  `ipaddr` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '操作系统',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
  `msg` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '提示消息',
  `login_time` datetime(0) NULL DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`info_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统访问记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名称',
  `parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父菜单ID',
  `order_num` int(4) NULL DEFAULT 0 COMMENT '显示顺序',
  `path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组件路径',
  `is_frame` int(1) NULL DEFAULT 1 COMMENT '是否为外链（0是 1否）',
  `is_cache` int(1) NULL DEFAULT 0 COMMENT '是否缓存（0缓存 1不缓存）',
  `menu_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  `perms` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1061 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '系统管理', 0, 1, 'system', NULL, 1, 0, 'M', '0', '0', '', 'system', 'admin', '2021-02-03 13:29:14', '', NULL, '系统管理目录');
INSERT INTO `sys_menu` VALUES (2, '系统监控', 0, 2, 'monitor', NULL, 1, 0, 'M', '0', '0', '', 'monitor', 'admin', '2021-02-03 13:29:14', '', NULL, '系统监控目录');
INSERT INTO `sys_menu` VALUES (3, '系统工具', 0, 3, 'tool', NULL, 1, 0, 'M', '0', '0', '', 'tool', 'admin', '2021-02-03 13:29:14', '', NULL, '系统工具目录');
INSERT INTO `sys_menu` VALUES (100, '用户管理', 1, 1, 'user', 'system/user/index', 1, 0, 'C', '0', '0', 'system:user:list', 'user', 'admin', '2021-02-03 13:29:14', '', NULL, '用户管理菜单');
INSERT INTO `sys_menu` VALUES (101, '角色管理', 1, 2, 'role', 'system/role/index', 1, 0, 'C', '0', '0', 'system:role:list', 'peoples', 'admin', '2021-02-03 13:29:14', '', NULL, '角色管理菜单');
INSERT INTO `sys_menu` VALUES (102, '菜单管理', 1, 3, 'menu', 'system/menu/index', 1, 0, 'C', '0', '0', 'system:menu:list', 'tree-table', 'admin', '2021-02-03 13:29:14', '', NULL, '菜单管理菜单');
INSERT INTO `sys_menu` VALUES (103, '部门管理', 1, 4, 'dept', 'system/dept/index', 1, 0, 'C', '0', '0', 'system:dept:list', 'tree', 'admin', '2021-02-03 13:29:14', '', NULL, '部门管理菜单');
INSERT INTO `sys_menu` VALUES (104, '岗位管理', 1, 5, 'post', 'system/post/index', 1, 0, 'C', '0', '0', 'system:post:list', 'post', 'admin', '2021-02-03 13:29:14', '', NULL, '岗位管理菜单');
INSERT INTO `sys_menu` VALUES (105, '字典管理', 1, 6, 'dict', 'system/dict/index', 1, 0, 'C', '0', '0', 'system:dict:list', 'dict', 'admin', '2021-02-03 13:29:14', '', NULL, '字典管理菜单');
INSERT INTO `sys_menu` VALUES (106, '参数设置', 1, 7, 'config', 'system/config/index', 1, 0, 'C', '0', '0', 'system:config:list', 'edit', 'admin', '2021-02-03 13:29:14', '', NULL, '参数设置菜单');
INSERT INTO `sys_menu` VALUES (107, '通知公告', 1, 8, 'notice', 'system/notice/index', 1, 0, 'C', '0', '0', 'system:notice:list', 'message', 'admin', '2021-02-03 13:29:14', '', NULL, '通知公告菜单');
INSERT INTO `sys_menu` VALUES (108, '日志管理', 1, 9, 'log', '', 1, 0, 'M', '0', '0', '', 'log', 'admin', '2021-02-03 13:29:14', '', NULL, '日志管理菜单');
INSERT INTO `sys_menu` VALUES (109, '在线用户', 2, 1, 'online', 'monitor/online/index', 1, 0, 'C', '0', '0', 'monitor:online:list', 'online', 'admin', '2021-02-03 13:29:14', '', NULL, '在线用户菜单');
INSERT INTO `sys_menu` VALUES (110, '定时任务', 2, 2, 'job', 'monitor/job/index', 1, 0, 'C', '0', '0', 'monitor:job:list', 'job', 'admin', '2021-02-03 13:29:14', '', NULL, '定时任务菜单');
INSERT INTO `sys_menu` VALUES (111, '数据监控', 2, 3, 'druid', 'monitor/druid/index', 1, 0, 'C', '0', '0', 'monitor:druid:list', 'druid', 'admin', '2021-02-03 13:29:14', '', NULL, '数据监控菜单');
INSERT INTO `sys_menu` VALUES (112, '服务监控', 2, 4, 'server', 'monitor/server/index', 1, 0, 'C', '0', '0', 'monitor:server:list', 'server', 'admin', '2021-02-03 13:29:14', '', NULL, '服务监控菜单');
INSERT INTO `sys_menu` VALUES (113, '缓存监控', 2, 5, 'cache', 'monitor/cache/index', 1, 0, 'C', '0', '0', 'monitor:cache:list', 'redis', 'admin', '2021-02-03 13:29:14', '', NULL, '缓存监控菜单');
INSERT INTO `sys_menu` VALUES (114, '表单构建', 3, 1, 'build', 'tool/build/index', 1, 0, 'C', '0', '0', 'tool:build:list', 'build', 'admin', '2021-02-03 13:29:14', '', NULL, '表单构建菜单');
INSERT INTO `sys_menu` VALUES (115, '代码生成', 3, 2, 'gen', 'tool/gen/index', 1, 0, 'C', '0', '0', 'tool:gen:list', 'code', 'admin', '2021-02-03 13:29:15', '', NULL, '代码生成菜单');
INSERT INTO `sys_menu` VALUES (116, '系统接口', 3, 3, 'swagger', 'tool/swagger/index', 1, 0, 'C', '0', '0', 'tool:swagger:list', 'swagger', 'admin', '2021-02-03 13:29:15', '', NULL, '系统接口菜单');
INSERT INTO `sys_menu` VALUES (500, '操作日志', 108, 1, 'operlog', 'monitor/operlog/index', 1, 0, 'C', '0', '0', 'monitor:operlog:list', 'form', 'admin', '2021-02-03 13:29:15', '', NULL, '操作日志菜单');
INSERT INTO `sys_menu` VALUES (501, '登录日志', 108, 2, 'logininfor', 'monitor/logininfor/index', 1, 0, 'C', '0', '0', 'monitor:logininfor:list', 'logininfor', 'admin', '2021-02-03 13:29:15', '', NULL, '登录日志菜单');
INSERT INTO `sys_menu` VALUES (1001, '用户查询', 100, 1, '', '', 1, 0, 'F', '0', '0', 'system:user:query', '#', 'admin', '2021-02-03 13:29:15', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1002, '用户新增', 100, 2, '', '', 1, 0, 'F', '0', '0', 'system:user:add', '#', 'admin', '2021-02-03 13:29:15', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1003, '用户修改', 100, 3, '', '', 1, 0, 'F', '0', '0', 'system:user:edit', '#', 'admin', '2021-02-03 13:29:15', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1004, '用户删除', 100, 4, '', '', 1, 0, 'F', '0', '0', 'system:user:remove', '#', 'admin', '2021-02-03 13:29:15', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1005, '用户导出', 100, 5, '', '', 1, 0, 'F', '0', '0', 'system:user:export', '#', 'admin', '2021-02-03 13:29:15', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1006, '用户导入', 100, 6, '', '', 1, 0, 'F', '0', '0', 'system:user:import', '#', 'admin', '2021-02-03 13:29:15', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1007, '重置密码', 100, 7, '', '', 1, 0, 'F', '0', '0', 'system:user:resetPwd', '#', 'admin', '2021-02-03 13:29:15', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1008, '角色查询', 101, 1, '', '', 1, 0, 'F', '0', '0', 'system:role:query', '#', 'admin', '2021-02-03 13:29:15', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1009, '角色新增', 101, 2, '', '', 1, 0, 'F', '0', '0', 'system:role:add', '#', 'admin', '2021-02-03 13:29:15', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1010, '角色修改', 101, 3, '', '', 1, 0, 'F', '0', '0', 'system:role:edit', '#', 'admin', '2021-02-03 13:29:15', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1011, '角色删除', 101, 4, '', '', 1, 0, 'F', '0', '0', 'system:role:remove', '#', 'admin', '2021-02-03 13:29:15', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1012, '角色导出', 101, 5, '', '', 1, 0, 'F', '0', '0', 'system:role:export', '#', 'admin', '2021-02-03 13:29:15', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1013, '菜单查询', 102, 1, '', '', 1, 0, 'F', '0', '0', 'system:menu:query', '#', 'admin', '2021-02-03 13:29:15', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1014, '菜单新增', 102, 2, '', '', 1, 0, 'F', '0', '0', 'system:menu:add', '#', 'admin', '2021-02-03 13:29:15', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1015, '菜单修改', 102, 3, '', '', 1, 0, 'F', '0', '0', 'system:menu:edit', '#', 'admin', '2021-02-03 13:29:15', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1016, '菜单删除', 102, 4, '', '', 1, 0, 'F', '0', '0', 'system:menu:remove', '#', 'admin', '2021-02-03 13:29:15', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1017, '部门查询', 103, 1, '', '', 1, 0, 'F', '0', '0', 'system:dept:query', '#', 'admin', '2021-02-03 13:29:15', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1018, '部门新增', 103, 2, '', '', 1, 0, 'F', '0', '0', 'system:dept:add', '#', 'admin', '2021-02-03 13:29:16', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1019, '部门修改', 103, 3, '', '', 1, 0, 'F', '0', '0', 'system:dept:edit', '#', 'admin', '2021-02-03 13:29:16', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1020, '部门删除', 103, 4, '', '', 1, 0, 'F', '0', '0', 'system:dept:remove', '#', 'admin', '2021-02-03 13:29:16', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1021, '岗位查询', 104, 1, '', '', 1, 0, 'F', '0', '0', 'system:post:query', '#', 'admin', '2021-02-03 13:29:16', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1022, '岗位新增', 104, 2, '', '', 1, 0, 'F', '0', '0', 'system:post:add', '#', 'admin', '2021-02-03 13:29:16', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1023, '岗位修改', 104, 3, '', '', 1, 0, 'F', '0', '0', 'system:post:edit', '#', 'admin', '2021-02-03 13:29:16', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1024, '岗位删除', 104, 4, '', '', 1, 0, 'F', '0', '0', 'system:post:remove', '#', 'admin', '2021-02-03 13:29:16', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1025, '岗位导出', 104, 5, '', '', 1, 0, 'F', '0', '0', 'system:post:export', '#', 'admin', '2021-02-03 13:29:16', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1026, '字典查询', 105, 1, '#', '', 1, 0, 'F', '0', '0', 'system:dict:query', '#', 'admin', '2021-02-03 13:29:16', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1027, '字典新增', 105, 2, '#', '', 1, 0, 'F', '0', '0', 'system:dict:add', '#', 'admin', '2021-02-03 13:29:16', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1028, '字典修改', 105, 3, '#', '', 1, 0, 'F', '0', '0', 'system:dict:edit', '#', 'admin', '2021-02-03 13:29:16', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1029, '字典删除', 105, 4, '#', '', 1, 0, 'F', '0', '0', 'system:dict:remove', '#', 'admin', '2021-02-03 13:29:16', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1030, '字典导出', 105, 5, '#', '', 1, 0, 'F', '0', '0', 'system:dict:export', '#', 'admin', '2021-02-03 13:29:16', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1031, '参数查询', 106, 1, '#', '', 1, 0, 'F', '0', '0', 'system:config:query', '#', 'admin', '2021-02-03 13:29:16', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1032, '参数新增', 106, 2, '#', '', 1, 0, 'F', '0', '0', 'system:config:add', '#', 'admin', '2021-02-03 13:29:16', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1033, '参数修改', 106, 3, '#', '', 1, 0, 'F', '0', '0', 'system:config:edit', '#', 'admin', '2021-02-03 13:29:16', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1034, '参数删除', 106, 4, '#', '', 1, 0, 'F', '0', '0', 'system:config:remove', '#', 'admin', '2021-02-03 13:29:16', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1035, '参数导出', 106, 5, '#', '', 1, 0, 'F', '0', '0', 'system:config:export', '#', 'admin', '2021-02-03 13:29:16', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1036, '公告查询', 107, 1, '#', '', 1, 0, 'F', '0', '0', 'system:notice:query', '#', 'admin', '2021-02-03 13:29:16', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1037, '公告新增', 107, 2, '#', '', 1, 0, 'F', '0', '0', 'system:notice:add', '#', 'admin', '2021-02-03 13:29:16', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1038, '公告修改', 107, 3, '#', '', 1, 0, 'F', '0', '0', 'system:notice:edit', '#', 'admin', '2021-02-03 13:29:16', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1039, '公告删除', 107, 4, '#', '', 1, 0, 'F', '0', '0', 'system:notice:remove', '#', 'admin', '2021-02-03 13:29:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1040, '操作查询', 500, 1, '#', '', 1, 0, 'F', '0', '0', 'monitor:operlog:query', '#', 'admin', '2021-02-03 13:29:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1041, '操作删除', 500, 2, '#', '', 1, 0, 'F', '0', '0', 'monitor:operlog:remove', '#', 'admin', '2021-02-03 13:29:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1042, '日志导出', 500, 4, '#', '', 1, 0, 'F', '0', '0', 'monitor:operlog:export', '#', 'admin', '2021-02-03 13:29:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1043, '登录查询', 501, 1, '#', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:query', '#', 'admin', '2021-02-03 13:29:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1044, '登录删除', 501, 2, '#', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:remove', '#', 'admin', '2021-02-03 13:29:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1045, '日志导出', 501, 3, '#', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:export', '#', 'admin', '2021-02-03 13:29:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1046, '在线查询', 109, 1, '#', '', 1, 0, 'F', '0', '0', 'monitor:online:query', '#', 'admin', '2021-02-03 13:29:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1047, '批量强退', 109, 2, '#', '', 1, 0, 'F', '0', '0', 'monitor:online:batchLogout', '#', 'admin', '2021-02-03 13:29:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1048, '单条强退', 109, 3, '#', '', 1, 0, 'F', '0', '0', 'monitor:online:forceLogout', '#', 'admin', '2021-02-03 13:29:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1049, '任务查询', 110, 1, '#', '', 1, 0, 'F', '0', '0', 'monitor:job:query', '#', 'admin', '2021-02-03 13:29:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1050, '任务新增', 110, 2, '#', '', 1, 0, 'F', '0', '0', 'monitor:job:add', '#', 'admin', '2021-02-03 13:29:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1051, '任务修改', 110, 3, '#', '', 1, 0, 'F', '0', '0', 'monitor:job:edit', '#', 'admin', '2021-02-03 13:29:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1052, '任务删除', 110, 4, '#', '', 1, 0, 'F', '0', '0', 'monitor:job:remove', '#', 'admin', '2021-02-03 13:29:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1053, '状态修改', 110, 5, '#', '', 1, 0, 'F', '0', '0', 'monitor:job:changeStatus', '#', 'admin', '2021-02-03 13:29:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1054, '任务导出', 110, 7, '#', '', 1, 0, 'F', '0', '0', 'monitor:job:export', '#', 'admin', '2021-02-03 13:29:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1055, '生成查询', 115, 1, '#', '', 1, 0, 'F', '0', '0', 'tool:gen:query', '#', 'admin', '2021-02-03 13:29:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1056, '生成修改', 115, 2, '#', '', 1, 0, 'F', '0', '0', 'tool:gen:edit', '#', 'admin', '2021-02-03 13:29:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1057, '生成删除', 115, 3, '#', '', 1, 0, 'F', '0', '0', 'tool:gen:remove', '#', 'admin', '2021-02-03 13:29:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1058, '导入代码', 115, 2, '#', '', 1, 0, 'F', '0', '0', 'tool:gen:import', '#', 'admin', '2021-02-03 13:29:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1059, '预览代码', 115, 4, '#', '', 1, 0, 'F', '0', '0', 'tool:gen:preview', '#', 'admin', '2021-02-03 13:29:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1060, '生成代码', 115, 5, '#', '', 1, 0, 'F', '0', '0', 'tool:gen:code', '#', 'admin', '2021-02-03 13:29:18', '', NULL, '');

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`  (
  `notice_id` int(4) NOT NULL AUTO_INCREMENT COMMENT '公告ID',
  `notice_title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '公告标题',
  `notice_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '公告类型（1通知 2公告）',
  `notice_content` longblob NULL COMMENT '公告内容',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`notice_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '通知公告表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_notice
-- ----------------------------
INSERT INTO `sys_notice` VALUES (1, '温馨提醒：2018-07-01 若依新版本发布啦', '2', 0xE696B0E78988E69CACE58685E5AEB9, '0', 'admin', '2021-02-03 13:29:26', '', NULL, '管理员');
INSERT INTO `sys_notice` VALUES (2, '维护通知：2018-07-01 若依系统凌晨维护', '1', 0xE7BBB4E68AA4E58685E5AEB9, '0', 'admin', '2021-02-03 13:29:26', '', NULL, '管理员');

-- ----------------------------
-- Table structure for sys_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_log`;
CREATE TABLE `sys_oper_log`  (
  `oper_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '模块标题',
  `business_type` int(2) NULL DEFAULT 0 COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '方法名称',
  `request_method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '请求方式',
  `operator_type` int(1) NULL DEFAULT 0 COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '操作人员',
  `dept_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '部门名称',
  `oper_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '请求URL',
  `oper_ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '主机地址',
  `oper_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '操作地点',
  `oper_param` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '请求参数',
  `json_result` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '返回参数',
  `status` int(1) NULL DEFAULT 0 COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '错误消息',
  `oper_time` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`oper_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '操作日志记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post`  (
  `post_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
  `post_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '岗位编码',
  `post_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '岗位名称',
  `post_sort` int(4) NOT NULL COMMENT '显示顺序',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`post_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '岗位信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_post
-- ----------------------------
INSERT INTO `sys_post` VALUES (1, 'ceo', '董事长', 1, '0', 'admin', '2021-02-03 13:29:13', '', NULL, '');
INSERT INTO `sys_post` VALUES (2, 'se', '项目经理', 2, '0', 'admin', '2021-02-03 13:29:13', '', NULL, '');
INSERT INTO `sys_post` VALUES (3, 'hr', '人力资源', 3, '0', 'admin', '2021-02-03 13:29:13', '', NULL, '');
INSERT INTO `sys_post` VALUES (4, 'user', '普通员工', 4, '0', 'admin', '2021-02-03 13:29:13', '', NULL, '');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色权限字符串',
  `role_sort` int(4) NOT NULL COMMENT '显示顺序',
  `data_scope` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  `menu_check_strictly` tinyint(1) NULL DEFAULT 1 COMMENT '菜单树选择项是否关联显示',
  `dept_check_strictly` tinyint(1) NULL DEFAULT 1 COMMENT '部门树选择项是否关联显示',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 101 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', 'admin', 1, '1', 1, 1, '0', '0', 'admin', '2021-02-03 13:29:13', '', '2021-02-15 21:32:16', '超级管理员');
INSERT INTO `sys_role` VALUES (2, '普通角色', 'common', 2, '3', 1, 1, '0', '0', 'admin', '2021-02-03 13:29:13', '', '2021-02-15 20:39:10', '普通角色');
INSERT INTO `sys_role` VALUES (100, '开发角色', 'kaifa', 3, '3', 0, 1, '0', '2', NULL, '2021-02-15 21:05:14', NULL, '2021-02-15 21:05:29', NULL);

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept`  (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `dept_id` bigint(20) NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`role_id`, `dept_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色和部门关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------
INSERT INTO `sys_role_dept` VALUES (2, 100);
INSERT INTO `sys_role_dept` VALUES (2, 102);
INSERT INTO `sys_role_dept` VALUES (2, 108);
INSERT INTO `sys_role_dept` VALUES (2, 109);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色和菜单关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (1, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2);
INSERT INTO `sys_role_menu` VALUES (1, 3);
INSERT INTO `sys_role_menu` VALUES (1, 100);
INSERT INTO `sys_role_menu` VALUES (1, 101);
INSERT INTO `sys_role_menu` VALUES (1, 102);
INSERT INTO `sys_role_menu` VALUES (1, 103);
INSERT INTO `sys_role_menu` VALUES (1, 104);
INSERT INTO `sys_role_menu` VALUES (1, 105);
INSERT INTO `sys_role_menu` VALUES (1, 106);
INSERT INTO `sys_role_menu` VALUES (1, 110);
INSERT INTO `sys_role_menu` VALUES (1, 114);
INSERT INTO `sys_role_menu` VALUES (1, 1001);
INSERT INTO `sys_role_menu` VALUES (1, 1002);
INSERT INTO `sys_role_menu` VALUES (1, 1003);
INSERT INTO `sys_role_menu` VALUES (1, 1004);
INSERT INTO `sys_role_menu` VALUES (1, 1005);
INSERT INTO `sys_role_menu` VALUES (1, 1006);
INSERT INTO `sys_role_menu` VALUES (1, 1007);
INSERT INTO `sys_role_menu` VALUES (1, 1008);
INSERT INTO `sys_role_menu` VALUES (1, 1009);
INSERT INTO `sys_role_menu` VALUES (1, 1010);
INSERT INTO `sys_role_menu` VALUES (1, 1011);
INSERT INTO `sys_role_menu` VALUES (1, 1012);
INSERT INTO `sys_role_menu` VALUES (1, 1013);
INSERT INTO `sys_role_menu` VALUES (1, 1014);
INSERT INTO `sys_role_menu` VALUES (1, 1015);
INSERT INTO `sys_role_menu` VALUES (1, 1016);
INSERT INTO `sys_role_menu` VALUES (1, 1017);
INSERT INTO `sys_role_menu` VALUES (1, 1018);
INSERT INTO `sys_role_menu` VALUES (1, 1019);
INSERT INTO `sys_role_menu` VALUES (1, 1020);
INSERT INTO `sys_role_menu` VALUES (1, 1021);
INSERT INTO `sys_role_menu` VALUES (1, 1022);
INSERT INTO `sys_role_menu` VALUES (1, 1023);
INSERT INTO `sys_role_menu` VALUES (1, 1024);
INSERT INTO `sys_role_menu` VALUES (1, 1025);
INSERT INTO `sys_role_menu` VALUES (1, 1026);
INSERT INTO `sys_role_menu` VALUES (1, 1027);
INSERT INTO `sys_role_menu` VALUES (1, 1028);
INSERT INTO `sys_role_menu` VALUES (1, 1029);
INSERT INTO `sys_role_menu` VALUES (1, 1030);
INSERT INTO `sys_role_menu` VALUES (1, 1031);
INSERT INTO `sys_role_menu` VALUES (1, 1032);
INSERT INTO `sys_role_menu` VALUES (1, 1033);
INSERT INTO `sys_role_menu` VALUES (1, 1034);
INSERT INTO `sys_role_menu` VALUES (1, 1035);
INSERT INTO `sys_role_menu` VALUES (1, 1049);
INSERT INTO `sys_role_menu` VALUES (1, 1050);
INSERT INTO `sys_role_menu` VALUES (1, 1051);
INSERT INTO `sys_role_menu` VALUES (1, 1052);
INSERT INTO `sys_role_menu` VALUES (1, 1053);
INSERT INTO `sys_role_menu` VALUES (1, 1054);
INSERT INTO `sys_role_menu` VALUES (2, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2);
INSERT INTO `sys_role_menu` VALUES (2, 3);
INSERT INTO `sys_role_menu` VALUES (2, 100);
INSERT INTO `sys_role_menu` VALUES (2, 101);
INSERT INTO `sys_role_menu` VALUES (2, 102);
INSERT INTO `sys_role_menu` VALUES (2, 103);
INSERT INTO `sys_role_menu` VALUES (2, 104);
INSERT INTO `sys_role_menu` VALUES (2, 105);
INSERT INTO `sys_role_menu` VALUES (2, 106);
INSERT INTO `sys_role_menu` VALUES (2, 107);
INSERT INTO `sys_role_menu` VALUES (2, 108);
INSERT INTO `sys_role_menu` VALUES (2, 109);
INSERT INTO `sys_role_menu` VALUES (2, 110);
INSERT INTO `sys_role_menu` VALUES (2, 111);
INSERT INTO `sys_role_menu` VALUES (2, 112);
INSERT INTO `sys_role_menu` VALUES (2, 113);
INSERT INTO `sys_role_menu` VALUES (2, 114);
INSERT INTO `sys_role_menu` VALUES (2, 115);
INSERT INTO `sys_role_menu` VALUES (2, 116);
INSERT INTO `sys_role_menu` VALUES (2, 500);
INSERT INTO `sys_role_menu` VALUES (2, 501);
INSERT INTO `sys_role_menu` VALUES (2, 1001);
INSERT INTO `sys_role_menu` VALUES (2, 1002);
INSERT INTO `sys_role_menu` VALUES (2, 1003);
INSERT INTO `sys_role_menu` VALUES (2, 1004);
INSERT INTO `sys_role_menu` VALUES (2, 1005);
INSERT INTO `sys_role_menu` VALUES (2, 1006);
INSERT INTO `sys_role_menu` VALUES (2, 1007);
INSERT INTO `sys_role_menu` VALUES (2, 1008);
INSERT INTO `sys_role_menu` VALUES (2, 1009);
INSERT INTO `sys_role_menu` VALUES (2, 1010);
INSERT INTO `sys_role_menu` VALUES (2, 1011);
INSERT INTO `sys_role_menu` VALUES (2, 1012);
INSERT INTO `sys_role_menu` VALUES (2, 1013);
INSERT INTO `sys_role_menu` VALUES (2, 1014);
INSERT INTO `sys_role_menu` VALUES (2, 1015);
INSERT INTO `sys_role_menu` VALUES (2, 1016);
INSERT INTO `sys_role_menu` VALUES (2, 1017);
INSERT INTO `sys_role_menu` VALUES (2, 1018);
INSERT INTO `sys_role_menu` VALUES (2, 1019);
INSERT INTO `sys_role_menu` VALUES (2, 1020);
INSERT INTO `sys_role_menu` VALUES (2, 1021);
INSERT INTO `sys_role_menu` VALUES (2, 1022);
INSERT INTO `sys_role_menu` VALUES (2, 1023);
INSERT INTO `sys_role_menu` VALUES (2, 1024);
INSERT INTO `sys_role_menu` VALUES (2, 1025);
INSERT INTO `sys_role_menu` VALUES (2, 1026);
INSERT INTO `sys_role_menu` VALUES (2, 1027);
INSERT INTO `sys_role_menu` VALUES (2, 1028);
INSERT INTO `sys_role_menu` VALUES (2, 1029);
INSERT INTO `sys_role_menu` VALUES (2, 1030);
INSERT INTO `sys_role_menu` VALUES (2, 1031);
INSERT INTO `sys_role_menu` VALUES (2, 1032);
INSERT INTO `sys_role_menu` VALUES (2, 1033);
INSERT INTO `sys_role_menu` VALUES (2, 1034);
INSERT INTO `sys_role_menu` VALUES (2, 1035);
INSERT INTO `sys_role_menu` VALUES (2, 1036);
INSERT INTO `sys_role_menu` VALUES (2, 1037);
INSERT INTO `sys_role_menu` VALUES (2, 1038);
INSERT INTO `sys_role_menu` VALUES (2, 1039);
INSERT INTO `sys_role_menu` VALUES (2, 1040);
INSERT INTO `sys_role_menu` VALUES (2, 1041);
INSERT INTO `sys_role_menu` VALUES (2, 1042);
INSERT INTO `sys_role_menu` VALUES (2, 1043);
INSERT INTO `sys_role_menu` VALUES (2, 1044);
INSERT INTO `sys_role_menu` VALUES (2, 1045);
INSERT INTO `sys_role_menu` VALUES (2, 1046);
INSERT INTO `sys_role_menu` VALUES (2, 1047);
INSERT INTO `sys_role_menu` VALUES (2, 1048);
INSERT INTO `sys_role_menu` VALUES (2, 1049);
INSERT INTO `sys_role_menu` VALUES (2, 1050);
INSERT INTO `sys_role_menu` VALUES (2, 1051);
INSERT INTO `sys_role_menu` VALUES (2, 1052);
INSERT INTO `sys_role_menu` VALUES (2, 1053);
INSERT INTO `sys_role_menu` VALUES (2, 1054);
INSERT INTO `sys_role_menu` VALUES (2, 1055);
INSERT INTO `sys_role_menu` VALUES (2, 1056);
INSERT INTO `sys_role_menu` VALUES (2, 1057);
INSERT INTO `sys_role_menu` VALUES (2, 1058);
INSERT INTO `sys_role_menu` VALUES (2, 1059);
INSERT INTO `sys_role_menu` VALUES (2, 1060);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '部门ID',
  `user_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户账号',
  `nick_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户昵称',
  `user_type` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '00' COMMENT '用户类型（00系统用户）',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '用户邮箱',
  `phonenumber` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '手机号码',
  `sex` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '头像地址',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '密码',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `login_ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '最后登录IP',
  `login_date` datetime(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 101 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 103, 'admin', '若依', '00', 'ry@163.com', '15888888888', '0', 'aaa', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', '2021-02-03 13:29:13', 'admin', '2021-02-03 13:29:13', '', '2021-02-15 21:44:30', '管理员');
INSERT INTO `sys_user` VALUES (2, 105, 'ry', '若依', '00', 'ry@qq.com', '15666666666', '1', 'aaaa', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', '2021-02-03 13:29:13', 'admin', '2021-02-03 13:29:13', '', NULL, '测试员');
INSERT INTO `sys_user` VALUES (100, 103, 'luyanan', 'luyanan', '00', 'luyanan@qq.com', '18135237517', '0', 'aa', '$2a$10$Bc9FKgyzGP8fMNNdN3fap.dOlwBFafuEXkh/8/fEuELfe6PRZuKnm', '0', '2', '', NULL, NULL, NULL, NULL, '2021-02-15 17:15:46', NULL);

-- ----------------------------
-- Table structure for sys_user_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_post`;
CREATE TABLE `sys_user_post`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `post_id` bigint(20) NOT NULL COMMENT '岗位ID',
  PRIMARY KEY (`user_id`, `post_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户与岗位关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_post
-- ----------------------------
INSERT INTO `sys_user_post` VALUES (1, 1);
INSERT INTO `sys_user_post` VALUES (2, 2);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户和角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (2, 2);
INSERT INTO `sys_user_role` VALUES (100, 1);

-- ----------------------------
-- Table structure for tenant_capacity
-- ----------------------------
DROP TABLE IF EXISTS `tenant_capacity`;
CREATE TABLE `tenant_capacity`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Tenant ID',
  `quota` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '配额，0表示使用默认值',
  `usage` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '使用量',
  `max_size` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '聚合子配置最大个数',
  `max_aggr_size` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最大变更历史数量',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '租户容量信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tenant_info
-- ----------------------------
DROP TABLE IF EXISTS `tenant_info`;
CREATE TABLE `tenant_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `kp` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'kp',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `tenant_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_name',
  `tenant_desc` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'tenant_desc',
  `create_source` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'create_source',
  `gmt_create` bigint(20) NOT NULL COMMENT '创建时间',
  `gmt_modified` bigint(20) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_tenant_info_kptenantid`(`kp`, `tenant_id`) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'tenant_info' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tenant_info
-- ----------------------------
INSERT INTO `tenant_info` VALUES (1, '1', 'dev', 'dev', 'dev分支', 'nacos', 1613480282241, 1613480282241);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('nacos', '$2a$10$EuWPZHzz32dJN7jexM34MOeYirDdFAZm2kuWj7VEOJhhZkDrxfvUu', 1);

SET FOREIGN_KEY_CHECKS = 1;
