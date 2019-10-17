DROP TABLE IF EXISTS `t_jt_equipment`;
CREATE TABLE `t_jt_equipment`
(
  `id`               int(20)      NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `equipment_id`     bigint(20)   NOT NULL COMMENT '设备id',
  `upload_period`    bigint(20)   NOT NULL DEFAULT '0' COMMENT '上传图片周期（秒）',
  `inversion_switch` tinyint(4)   NOT NULL DEFAULT '1' COMMENT '是否开启翻转，1：不开启，2：开启',
  `inversion_period` bigint(20)   NOT NULL DEFAULT '3600' COMMENT '翻转周期时间（秒），预留字段',
  `pic_dir`          varchar(255) NOT NULL DEFAULT 'C:\\Test' COMMENT '图片存储目录',
  `status`           tinyint(4)   NOT NULL DEFAULT '1' COMMENT '状态1-显示 0-隐藏',
  `is_delete`        tinyint(4)   NOT NULL DEFAULT '0' COMMENT '是否逻辑删除, 1已经删除 0未删除',
  PRIMARY KEY (`id`),
  KEY `udx_equipment_id` (`equipment_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 0
  DEFAULT CHARSET = utf8
  COMMENT = '久天设备表';

DROP TABLE IF EXISTS `picture`;
CREATE TABLE `picture`
(
  `id`          int(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `filePath`    varchar(255) DEFAULT NULL,
  `picId`       varchar(255) DEFAULT NULL,
  `date`        varchar(255) DEFAULT NULL,
  `oldFilename` varchar(255) DEFAULT NULL,
  `voltage`     varchar(255) DEFAULT NULL,
  `temp`        varchar(255) DEFAULT NULL,
  `humi`        varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;