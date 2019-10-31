DROP TABLE IF EXISTS `t_jt_equipment`;
CREATE TABLE `t_jt_equipment`
(
  `id`               int(20)      NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `equipment_id`     bigint(20)   NOT NULL COMMENT '设备id',
  `equipment_type`   int(2)       NOT NULL COMMENT '设备类型1：粮仓，2：天牛',
  `upload_period`    bigint(20)   NOT NULL DEFAULT '0' COMMENT '上传图片周期（秒）',
  `inversion_switch` tinyint(4)   NOT NULL DEFAULT '1' COMMENT '是否开启翻转，1：不开启，2：开启',
  `inversion_period` bigint(20)   NOT NULL DEFAULT '3600' COMMENT '翻转周期时间（秒），预留字段',
  `pic_dir`          varchar(255) NOT NULL DEFAULT 'C:\\Test' COMMENT '图片存储目录',
  `status`           int(2)       NOT NULL COMMENT '有效状态',
  `is_delete`        int(2)       NOT NULL COMMENT '删除状态',
  PRIMARY KEY (`id`),
  KEY `udx_equipment_id` (`equipment_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 0
  DEFAULT CHARSET = utf8
  COMMENT = '久天设备表';

DROP TABLE IF EXISTS `t_jt_picture`;
CREATE TABLE `t_jt_picture`
(
  `id`          int(20)    NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `file_path`    varchar(255)  DEFAULT NULL COMMENT '文件路径',
  `equipment_id`     bigint(22) NOT NULL COMMENT '设备id',
  `equipment_type`   int(2)     NOT NULL COMMENT '设备类型',
  `old_file_name` varchar(255) DEFAULT NULL COMMENT '老文件名',
  `voltage`     varchar(255)        DEFAULT NULL COMMENT '电压',
  `temp`        varchar(255)        DEFAULT NULL COMMENT '温度',
  `humi`        varchar(255)        DEFAULT NULL COMMENT '湿度',
  `status`      int(2)     NOT NULL COMMENT '有效状态',
  `is_delete`   int(2)     NOT NULL COMMENT '删除状态',
  `create_time` timestamp  NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT = '图片上传';