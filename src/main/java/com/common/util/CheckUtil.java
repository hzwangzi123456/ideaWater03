package com.common.util;
 
import java.text.DateFormat;
import java.text.ParseException; 
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.alibaba.fastjson.JSON;
/**
 * @author wangzi
 * @date 17/10/19 下午11:25.
 */
public class CheckUtil {
	
	public static void Look(Object object){
		System.out.println(JSON.toJSONString(object).toString());
	}
	/**
	 *  判断对象是否为null
	 * @param str
	 * @return
	 */
	public static Object isNull(Object str){
		if (null==str||"".equals(str)){
			return 0.00;
		} else {
			return str;
		}
		
	}
	
	/**
	 *  判断是否为数字
	 * @param str
	 * @return
	 */
	public static boolean isNum(String str){
		if (!isNull(str)){
			return str.matches("^[0-9]*$");
		} else {
			return false;
		}
		
	}
 
	
	/**
	 *  判断是否为空
	 * @param str
	 * @return
	 */
	public static boolean isNull(String str){
		if(null==str||"".equals(str)){
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 *  判断是否为空,返回某个字符串
	 * @param str
	 * @return
	 */
	public static String isStrNull(String str,String str2){
		if(null==str||"".equals(str)){
			return str2;
		} else {
			return str;
		}
	}
	
	/**
	 *  判断是否为空,返回某个字符串
	 * @param int
	 * @return
	 */
	public static Integer isIntNull(Integer in){
		if(null==in){
			return 0;
		} else {
			return in;
		}
	}
	
	/**
	 *  判断是否为空,返回某个字符串
	 * @param int
	 * @return
	 */
	public static Double isDouNull(Double in){
		if(null==in){
			return new Double(0);
		} else {
			return in;
		}
	}
	
	
	/**
	 * Date类型装换：
	 * @param str
	 * @return yyyymmdd 类型
	 */
	public static String DateToStr(Date data){
		
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");      
        return format.format(data);
	}
	
	/**
	 * Date类型装换：
	 * @param str
	 * @return yyyymmdd 类型
	 * @throws ParseException 
	 */
	public static String StrToStr(String str) throws ParseException{
		if(!isNull(str)){
			Date date = null;
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd"); 
			DateFormat todate = new SimpleDateFormat("yyyy-MM-dd");
			date = todate.parse(str);
	        return format.format(date);
		} else{
			str = "";
			return str;
		}
	}
	
	/**
	 * Date类型装换：
	 * @param str
	 * @return yyyymm 类型
	 * @throws ParseException 
	 */
	public static String StrToStr_ym(String str) throws ParseException{
		
		if(!isNull(str)){
			Date date = null;
			SimpleDateFormat format = new SimpleDateFormat("yyyyMM"); 
			DateFormat todate = new SimpleDateFormat("yyyy-MM");
			date = todate.parse(str);
	        return format.format(date);
		} else{
			str = "";
			return str;
		}
	}
	
	
	public static Date getYear(Date date,int i) {
		  Date date1=null;
		  String str = null;
		  Calendar calendar = Calendar.getInstance();
		  try {
				calendar.setTime(date);
				calendar.add(Calendar.YEAR, i);
				str = getStringYMDate(calendar.getTime());
				str = str+"-01";
				date1 = getDate(str);
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
		  return date1;
		 }
	
	public static Date getMonth(Date date,int i) {
		  Date date1=null;
		  String str = null;
		  Calendar calendar = Calendar.getInstance();
		  try {
				calendar.setTime(date);
				calendar.add(Calendar.MONTH, i);
				str = getStringYMDate(calendar.getTime());
				str = str+"-01";
				date1 = getDate(str);
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
		  return date1;
		 }
	
	/**
	    * 将日期Date类型转换成Integer类型 ,格式为 yyyyMMdd 例：返回20091011
	    * @param date
	    * @return
	    */
	  public static Integer getIntDate(Date date) {
		  String str = getStringDate(date);
		  str = str.replace("-", "").substring(0,8);
		  Integer intDate = Integer.parseInt(str);
		  return intDate;
	 }
	   
	  /**
	    * 将日期Date类型转换成String类型, 格式为 yyyy-MM-dd
	    * @param date
	    * @return
	    */
	   public static String getStringDate(Date date) {
	    if(null==date) {
			return null;
		}
	    	SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(
	    	"yyyy-MM-dd");
	    	String str = localSimpleDateFormat.format(date);
	    	return str;
	 }
	   
	/**
	   * 将String类型时间 转换成 Date类型时间 ，格式为yyyy--MM--dd,例：返回2009-10-10  
	   * @param date
	   * @return
	   * @throws Exception
	   */
	 public static Date getDate(String date) throws Exception {
	  SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	  Date date1 = localSimpleDateFormat.parse(date);
	  return date1;
	 }
	 
	/**
	  * 将Date类型时间 转换成 String类型时间 ，格式为yyyy--MM,例：返回2009-10  
	  * @param date
	  * @return
	  * @throws Exception
	  */
	 public static String getStringYMDate(Date date)  {
	  SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM");
	  String str = localSimpleDateFormat.format(date);
	  return str;
	 }
	   
	   public static String getStringYYDate(Date date)  {
			  SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy");
			  String str = localSimpleDateFormat.format(date);
			  return str;
	   }
	
	/**
	 * Date类型装换：
	 * @param str
	 * @return yyyymmdd 类型
	 * @throws ParseException 
	 */
	public static Date intToDate(Integer intDate) throws ParseException{
		if(null!=intDate){
			Date date = null;
			String strDate= String.valueOf(intDate).substring(0, 4) + "-" +
							String.valueOf(intDate).substring(5, 6) + "-" +
							String.valueOf(intDate).substring(7, 8);
			DateFormat todate = new SimpleDateFormat("yyyy-MM-dd");
			date = todate.parse(strDate);
	        return date;
		}
		return null;
	}
}
