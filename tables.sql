CREATE TABLE `tb_fault` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `when` timestamp NULL DEFAULT NULL,
  `where` int(11) DEFAULT NULL,
  `content` text COLLATE utf8mb4_unicode_ci,
  `what` int(11) DEFAULT NULL,
  `what_extra` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `why` text COLLATE utf8mb4_unicode_ci,
  `get` text COLLATE utf8mb4_unicode_ci,
  `create_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='故障记录表';

CREATE TABLE `tb_fault_status` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `code` int(11) DEFAULT NULL,
  `describe` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `create_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `tb_fault_what` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `type` int(11) DEFAULT NULL,
  `describe` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `create_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='故障类型定义';

CREATE TABLE `tb_fault_where` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `source` int(11) DEFAULT NULL,
  `describe` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `create_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='故障来源定义';

-- 初始化数据
INSERT INTO `tb_fault_status` (`id`, `code`, `describe`, `create_at`, `update_at`)
VALUES
	(1, 0, '解决中', '2021-01-26 17:02:01', '2021-01-26 17:02:01'),
	(2, 1, '已解决', '2021-01-26 17:02:07', '2021-01-26 17:02:07'),
	(3, 2, '未解决', '2021-01-26 17:02:13', '2021-01-26 17:02:13');

INSERT INTO `tb_fault_what` (`id`, `type`, `describe`, `create_at`, `update_at`)
VALUES
	(1, 1, '终端故障', '2021-01-25 16:38:50', '2021-01-25 16:38:50'),
	(2, 2, '认知故障', '2021-01-25 16:39:12', '2021-01-25 16:40:58'),
	(3, 3, '上游故障', '2021-01-25 16:39:19', '2021-01-25 16:40:59'),
	(4, 4, '自身故障', '2021-01-25 16:39:49', '2021-01-25 16:41:00'),
	(5, 5, '下游故障', '2021-01-25 16:39:57', '2021-01-25 16:41:01');

INSERT INTO `tb_fault_where` (`id`, `source`, `describe`, `create_at`, `update_at`)
VALUES
	(1, 1, '客户投诉', '2021-01-25 14:56:16', '2021-01-25 14:56:16'),
	(2, 2, '告警发现', '2021-01-25 14:56:23', '2021-01-25 14:56:23'),
	(3, 3, 'RD 发现', '2021-01-25 14:56:30', '2021-01-25 14:56:30'),
	(4, 4, '其它', '2021-01-25 14:56:42', '2021-01-25 14:56:42');

INSERT INTO `tb_fault` (`id`, `when`, `where`, `content`, `what`, `what_extra`, `status`, `why`, `get`, `create_at`, `update_at`)
VALUES
	(1, '2021-01-18 17:47:24', 1, '任务下发，设置了通知群，但群里未见通知消息', 2, NULL, 1, '发送指定群组，需要在公众号后台添加权限。产品不可随意指定群，需通知研发群组 id，逐一添加', NULL, '2021-01-25 16:41:38', '2021-01-26 17:48:16'),
	(2, '2021-01-19 17:47:24', 2, 'dw_mysql 对账数据延迟较大', 4, 'storm 消费能力不足', 1, 'mysql 日志突增，作业消费能力不够。', NULL, '2021-01-25 16:42:49', '2021-01-27 13:23:11'),
	(3, '2021-01-20 17:27:24', 3, 'SQL update 语句更新后，RDS 平台查询仍为旧数据。\n\n从监控中发现，数据库集群主从延迟较大。', 3, 'zebra 组件故障', 1, 'rds 平台人员移除 延迟节点', NULL, '2021-01-25 16:43:43', '2021-01-27 17:37:35'),
	(4, '2021-01-27 12:41:56', 2, '作业[dw_mysql]延迟超过10分钟\n监控项: kafka_spout_lag_10m\nTopic: org.sec_kernel_sql/auth\n落后总条数: 88,587,220\n报警时间: 2021-01-27 12:30:08', 4, '', 1, 'mysql插件日志再次突增，服务消费能力不足', '', '2021-01-27 12:43:04', '2021-01-27 17:38:21');
