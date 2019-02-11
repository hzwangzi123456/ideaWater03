package com.yasi.dto;

import com.yasi.bean.Instruments;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * @author wangzi
 * @date 19/2/11 上午10:16.
 * 设备Resdto
 */
@Data
public class InstrumentsResDto implements Serializable {

    private static final long serialVersionUID = -4470918444409727415L;

    /**
     * 0:成功,1:失败
     */
    private int result;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 设备列表
     */
    private List<Instruments> instrumentsList;

    public HashMap<String, Object> dto2map() {
        HashMap<String, Object> map = new HashMap<>();

        map.put("result", result);
        map.put("msg", msg);
        map.put("list", instrumentsList);

        return map;
    }
}
