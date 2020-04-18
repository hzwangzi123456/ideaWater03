package com.yasi.bo.impl;

import com.common.system.SysRuntimeException;
import com.common.util.CheckUtil;
import com.yasi.bo.YascmfDatasBo;
import com.yasi.dao.YascmfDatasDao;
import com.yasi.vo.YascmfDatas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * @author wangzi
 * @date 17/10/19 下午11:25.
 */
@Service
public class YascmfDatasBoImpl implements YascmfDatasBo {

	@Autowired
	private YascmfDatasDao yascmfDatasDao;

	@Override
	public List<YascmfDatas> findYascmfDatasByPojo(YascmfDatas vo)
			throws SysRuntimeException {
		return yascmfDatasDao.findYascmfDatasByPojo(vo);
	}

	@Override
	public List<YascmfDatas> findYascmfDatasByTime(String instrumentId,
												   String start, String end) throws SysRuntimeException {
		System.out.println("");
		return yascmfDatasDao.findYascmfDatasByTime(instrumentId, start, end);
	}

	@Override
	public void yascmfDatasBoTest() throws SysRuntimeException {
		System.out.println("yascmfDatasBoTest");
	}

	@Override
	public int findYascmfDatasCountByPojo(YascmfDatas vo)
			throws SysRuntimeException {
		return yascmfDatasDao.findYascmfDatasCountByPojo(vo);
	}

	@Override
	public void insertYascmfDatas(YascmfDatas vo) throws SysRuntimeException {
		yascmfDatasDao.insertYascmfDatas(vo);
	}

	@Override
	public boolean processTCPDatas(String log) throws SysRuntimeException {
		log = log.trim();

		//去掉心跳包
		while(log.startsWith("0123")) {
			log = log.substring(4);
		}
		System.out.println("processTCPDatas:" + log);
		String str = log;
		String regEx = "^[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:{0,1} {0,1}[0-9]{2} DO:[0-9]{2}.[0-9]{1}mg/L CDT:[0-9]{5}us/cm PH:[0-9]{2}.[0-9]{2} T[0-9]{2}.[0-9]{1} NTU:[0-9]{3} NH3N:[0-9]{2}.[0-9]{3}mg/L P:[0-9]{2}.[0-9]{3}mg/L [A-Z][0-9][0-9]$";
		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = pattern.matcher(str);
		// 字符串是否与正则表达式相匹配
		boolean rs = matcher.matches();

		//不合法直接返回
		if(rs == false)
		{
			return false;
		}
		//获取上传数据中的每个字段
		String date = str.substring(0,13);
		date += ":";
		date += str.substring(14,16);
		date += ":00";
		String dissolvedOxygen = str.substring(20,24);//"mg/L"
		String conductivity = str.substring(33,38);//"us/cm"
		String ph = str.substring(47,52);
		String waterTemperature = str.substring(54,58);
		String ntu = str.substring(63,66);
		String ammoniaNitrogen = str.substring(72,78);//"mg/L"
		String p = str.substring(85,91);//"mg/L "
		String instrumentId = str.substring(95,99);//W01
		//创建vo对象
		YascmfDatas yascmfDatas = new YascmfDatas();
		yascmfDatas.setDateTime(date);
		yascmfDatas.setDissolvedOxygen(Double.parseDouble(dissolvedOxygen));
		yascmfDatas.setConductivity(Double.parseDouble(conductivity));
		yascmfDatas.setPh(Double.parseDouble(ph));
		yascmfDatas.setWaterTemperature(Double.parseDouble(waterTemperature));
		yascmfDatas.setNtu(Double.parseDouble(ntu));
		yascmfDatas.setP(Double.parseDouble(p));
		yascmfDatas.setAmmoniaNitrogen(Double.parseDouble(ammoniaNitrogen));
		yascmfDatas.setP(Double.parseDouble(p));
		yascmfDatas.setInstrumentId(instrumentId);
		CheckUtil.Look(yascmfDatas);
		//插入vo对象
		yascmfDatasDao.insertYascmfDatas(yascmfDatas);
		return true;
	}
	
//	@Test
//	public void test(){
//		System.out.println(new YascmfDatasBoImpl().processTCPDatas("2017-09-26 22 11 DO:00.0mg/L CDT:12345us/cm PH:07.00 T25.0 NTU:165 NH3N:00.250mg/L P:00.250mg/L W01"));
//	}

}
