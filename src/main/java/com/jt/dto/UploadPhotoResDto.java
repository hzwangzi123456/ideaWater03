package com.jt.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangzi
 * @date 19/4/11 上午11:15.
 * 上传照片返回dto
 */
@Data
public class UploadPhotoResDto implements Serializable {
    private static final long serialVersionUID = 2463619360822741787L;

    /**
     * 0:成功,1:失败
     */
    private int result;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 响应时间 格式:yyyy-MM-dd HH:mm:ss
     */
    private String resultTime;

    /**
     * dto转map
     * @return
     */
    public Map dto2map() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("result", this.result);
        map.put("msg", this.msg);
        map.put("resultTime", this.resultTime);
        return map;
    }
}
