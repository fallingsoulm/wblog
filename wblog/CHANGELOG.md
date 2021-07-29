## 20210725

### 报表系统
- 国产化报表输出
- 统计监控模块
- 优化文章/资讯入es的效率





##  20210625
1. 打通博客系统
2. 打通后台管理系统



## v1.1
### sql
```mysql

ALTER TABLE `j_job`
ADD COLUMN `params` varchar(1024) NULL COMMENT '参数' ;


ALTER TABLE `b_article_label`
ADD COLUMN `classify`  int(3) NULL COMMENT '分类' ;


UPDATE b_article_label SET classify  = 100;

CREATE TABLE `b_news_info` (
  `news_id` bigint(20) NOT NULL,
  `content` longtext COMMENT '内容',
  `type` int(3) DEFAULT NULL COMMENT '0:markdown 1:html',
  PRIMARY KEY (`news_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='资讯内容';

ALTER TABLE`b_news` 
ADD COLUMN `view` bigint(20) NULL COMMENT '访问量' ;

CREATE TABLE `m_notice_message` (
  `id` bigint(20) NOT NULL,
  `template_id` bigint(20) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `content` varchar(1024) DEFAULT NULL COMMENT '消息内容',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `send_time` datetime DEFAULT NULL COMMENT '发送时间',
  `template` int(3) DEFAULT NULL COMMENT '消息模板,html/json',
  `status` int(3) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='通知消息详情';

CREATE TABLE `m_notice_template` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `token` varchar(255) DEFAULT NULL COMMENT 'token,发送消息的凭证',
  `name` varchar(255) DEFAULT NULL COMMENT '消息名称',
  `notice_type` int(3) DEFAULT NULL COMMENT '通知的消息的类型',
  `invoke_target` varchar(1024) DEFAULT NULL COMMENT '调用对象',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '修改人',
  `send_type` int(20) DEFAULT NULL COMMENT '发送类型,0：立即发送, 1:定时发送',
  `send_time` varchar(255) DEFAULT NULL COMMENT '发送时间',
  `status` int(3) DEFAULT '101' COMMENT '状态',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='通知消息之消息模板';

```



### 功能
- 增加消息推送(钉钉机器人/邮件/企业微信/企业微信机器人)
- 第三方管理