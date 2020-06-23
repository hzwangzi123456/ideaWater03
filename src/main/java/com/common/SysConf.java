package com.common;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class SysConf {
    @Value("#{sysconf.path}")
    private String PATH;//上传照片存储路径

    @Value("#{sysconf.uploadperiod}")
    private Integer uploadPeriod;

    @Value("#{sysconf.granaryDataCheckPeriod}")
    private Integer granaryDataCheckPeriod;
}
