DROP TABLE IF EXISTS `t_jt_equipment`;
CREATE TABLE `t_jt_equipment`
(
  `id`               int(20)      NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `equipment_id`     varchar(255) NOT NULL COMMENT '设备id',
  `equipment_type`   int(20)      NOT NULL COMMENT '设备类型1：粮仓，2：天牛',
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

-- 设备类型 粮仓

INSERT INTO `t_jt_equipment`
(`equipment_id`,`equipment_type`,`upload_period`,`inversion_switch`,`inversion_period`,`pic_dir`,`status`,`is_delete`)
  value ('898602b86819f1703859',1,1800,2,1800,'C:\\home\\jt\\picture\\1\\',1,0);

INSERT INTO `t_jt_equipment`
(`equipment_id`,`equipment_type`,`upload_period`,`inversion_switch`,`inversion_period`,`pic_dir`,`status`,`is_delete`)
  value ('898602b86819f1704419',1,1800,2,1800,'C:\\home\\jt\\picture\\1\\',1,0);

INSERT INTO `t_jt_equipment`
(`equipment_id`,`equipment_type`,`upload_period`,`inversion_switch`,`inversion_period`,`pic_dir`,`status`,`is_delete`)
  value ('898602b86819f1704454',1,1800,2,1800,'C:\\home\\jt\\picture\\1\\',1,0);

INSERT INTO `t_jt_equipment`
(`equipment_id`,`equipment_type`,`upload_period`,`inversion_switch`,`inversion_period`,`pic_dir`,`status`,`is_delete`)
  value ('898602b86819f1704884',1,1800,2,1800,'C:\\home\\jt\\picture\\1\\',1,0);

-- 设备类型是天牛

INSERT INTO `t_jt_equipment`
(`equipment_id`,`equipment_type`,`upload_period`,`inversion_switch`,`inversion_period`,`pic_dir`,`status`,`is_delete`)
  value ('89860413161891821357',2,1800,2,1800,'C:\\home\\jt\\picture\\2\\',1,0);



DROP TABLE IF EXISTS `t_jt_picture`;
CREATE TABLE `t_jt_picture`
(
  `id`             int(20)      NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `file_path`      varchar(255)          DEFAULT NULL COMMENT '文件路径',
  `equipment_id`   varchar(255) NOT NULL COMMENT '设备id',
  `equipment_type` int(20)      NOT NULL COMMENT '设备类型',
  `old_file_name`  varchar(255)          DEFAULT NULL COMMENT '老文件名',
  `voltage`        varchar(255)          DEFAULT NULL COMMENT '电压',
  `temp`           varchar(255)          DEFAULT NULL COMMENT '温度',
  `humi`           varchar(255)          DEFAULT NULL COMMENT '湿度',
  `status`         int(2)       NOT NULL COMMENT '有效状态',
  `is_delete`      int(2)       NOT NULL COMMENT '删除状态',
  `create_time`    timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT = '图片上传';


DROP TABLE IF EXISTS `nineskydata`;
CREATE TABLE `nineskydata` (
   `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
   `conductivity` int(11) DEFAULT NULL COMMENT '导电性',
   `temperature` int(11) DEFAULT NULL COMMENT '温度',
   `ph` int(11) DEFAULT NULL COMMENT 'ph',
   `ntu` int(11) DEFAULT NULL COMMENT '浊度\n',
   `dissolved_oxygen` int(11) DEFAULT NULL COMMENT '溶解氧',
   `flow` int(11) DEFAULT NULL COMMENT '巴歇尔槽明渠流量流量',
   `ct_state` int(11) DEFAULT NULL COMMENT '电导率、温度传感器电源状态位',
   `ph_state` int(11) DEFAULT NULL COMMENT 'ph传感器电源状态位',
   `ntu_state` int(11) DEFAULT NULL COMMENT '浊度传感器电源状态位',
   `do_state` int(11) DEFAULT NULL COMMENT '溶解氧传感器电源状态位',
   `fl_state` int(11) DEFAULT NULL COMMENT '流量传感器电源状态位',
   `date_time` varchar(255) DEFAULT NULL COMMENT '时间 举例：2018-01-01 23:59:59',
   `timestamp` varchar(255) DEFAULT NULL COMMENT '时间戳',
   `instrument_id` varchar(255) DEFAULT NULL COMMENT '设备id',
   `location` varchar(255) DEFAULT NULL COMMENT '位置',
   `alert` varchar(255) DEFAULT NULL,
   `water_level` int(20) NOT NULL DEFAULT '0' COMMENT '水位',
   `ammonia` int(20) NOT NULL DEFAULT '0' COMMENT '氨氮',
   `longitude` varchar(1024) NOT NULL DEFAULT '' COMMENT '经度',
   `dimensionality` varchar(1024) NOT NULL DEFAULT '' COMMENT '维度',
   PRIMARY KEY (`id`),
   UNIQUE KEY `nineskydata_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=898201 DEFAULT CHARSET=utf8;