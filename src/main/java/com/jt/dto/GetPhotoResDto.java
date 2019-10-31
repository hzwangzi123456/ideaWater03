package com.jt.dto;

import com.jt.bean.PictureVO;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangzi
 * @date 19/5/11 上午11:37.
 */
@Data
public class GetPhotoResDto implements Serializable {

    private static final long serialVersionUID = 7005204394017388638L;

    /**
     * 0:成功,1:失败
     */
    private int result;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 查询结果
     */
    private List< PictureVO > lists;

    /**
     * dto转map
     * @return
     */
    public Map dto2map() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("result", this.result);
        map.put("msg", this.msg);
        map.put("list",this.lists);
        return map;
    }
}
