package com.yasi.web;

import com.common.base.BaseController;
import com.yasi.bo.YascmfDatasBo;
import com.yasi.vo.YascmfDatas;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author wangzi
 * @date 17/10/19 下午11:25.
 */
@Controller
@Scope("prototype")
@RequestMapping("/YascmfDatasController")
public class YascmfDatasController extends BaseController {
    private static Logger logger = Logger.getLogger(YascmfDatasController.class);

    /**
     * 注入业务接口层
     **/
    @Autowired
    private YascmfDatasBo yascmfDatasBo;

    /**
     * 定义参数
     **/
    private YascmfDatas yascmfDatas;

    private String start;
    private String end;

    public YascmfDatasBo getYascmfDatasBo() {
        return yascmfDatasBo;
    }

    @ModelAttribute
    public void setParaVal(YascmfDatas yascmfDatas, String start, String end) {
        this.yascmfDatas = yascmfDatas;
        this.start = start;
        this.start += " 00:00:00";
        this.end = end;
        this.end += " 23:59:59";
    }

    @RequestMapping("/jsp.do")
    public String jps01() {
        return "jsp01";
    }

    @RequestMapping("/findYascmfDatasByPojo.do")
    public void findYascmfDatasByPojo() {
        List<YascmfDatas> result = yascmfDatasBo.findYascmfDatasByPojo(yascmfDatas);
        if (result == null || result.size() == 0) {
            setFailure("未找到数据");
            return;
        }
        setAjaxObject(result);
    }

    @RequestMapping("/findYascmfDatasByTime.do")
    public void findYascmfDatasByTime() {
        List<YascmfDatas> result = yascmfDatasBo.findYascmfDatasByTime(yascmfDatas.getInstrumentId(), start, end);
        if (result == null || result.size() == 0) {
            setFailure("未找到数据");
            return;
        }
        setAjaxObject(result);
    }

//	@RequestMapping("/processTCPDatas.do")
//	public void processTCPDatas(String datas) {
//		YascmfTcplogs vo = new YascmfTcplogs();
//		vo.setDate(DateUtil.getCurDateStrMiao_());
//		vo.setLog(datas);
//		yascmfTcplogsBo.insertYascmfTcplogs(vo);
//		
//		setAjaxMsg("receive message:" + datas);
//	}

//	@RequestMapping("/findTCPClient.do")
//	public void findTCPClient() throws Exception{
//		Socket s = new Socket("10.211.55.4",5566); //创建一个Socket对象，连接IP地址为10.211.55.4的服务器的5566端口  
//	    DataOutputStream dos = new DataOutputStream(s.getOutputStream()); //获取Socket对象的输出流，并且在外边包一层DataOutputStream管道，方便输出数据  
//	    dos.writeUTF("1"); //DataOutputStream对象的writeUTF()方法可以输出数据，并且支持中文
//	    
//	    BufferedInputStream bufferInput = new BufferedInputStream(
//				s.getInputStream());
//		byte[] bty = new byte[100];
//		int len = bufferInput.read(bty);
//		String str = new String(bty, 0, len);
//		System.out.println(str.trim());
//		
//		String date = str.substring(0,16);
//		date += ":00";
//		String dissolvedOxygen = str.substring(20,24);//"mg/L"
//		String conductivity = str.substring(33,38);//"us/cm"
//		String ph = str.substring(47,52);
//		String waterTemperature = str.substring(54,58);
//		String ntu = str.substring(63,66);
//		String ammoniaNitrogen = str.substring(69,74);//"mg/L"
//		String p = str.substring(81,86);//"mg/L"
//		
//		System.out.println(date + " " + dissolvedOxygen + " " + conductivity + " " + ph + " " + waterTemperature + " " + ntu + " " + ammoniaNitrogen + " " + p);
//		
//	    dos.flush(); //确保所有数据都已经输出  
//	    dos.close(); //关闭输出流  
//	    s.close(); //关闭Socket连接  
//	}

}
