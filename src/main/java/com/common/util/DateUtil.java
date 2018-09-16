package com.common.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author wangzi
 * @date 17/10/19 下午11:25.
 */
public class DateUtil {
    public static final DateFormat haomiaoFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    public static final DateFormat ymdFormat = new SimpleDateFormat("yyyyMMdd");
    public static final DateFormat y_m_dFormat = new SimpleDateFormat("yyyy-MM-dd");
    public static final DateFormat y_m_d_h_m_sFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final DateFormat y_m_d_h_mFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public static final DateFormat ymdhmFormat = new SimpleDateFormat("yyyyMMddHHmm");
    public static final DateFormat ymdhmFormatYmdhms = new SimpleDateFormat("yyyyMMddHHmmss");
    public static final DateFormat y_mFormat = new SimpleDateFormat("yyyy-MM");
    public static final DateFormat yFormat = new SimpleDateFormat("yyyy");
    public static final DateFormat mFormat = new SimpleDateFormat("MM");
    public static final DateFormat h_m_sFormat = new SimpleDateFormat("HH:mm:ss");
    public static final DateFormat h_mFormat = new SimpleDateFormat("HH:mm");
    public static final DateFormat weekFormat = new SimpleDateFormat("EEEE");
    public static final DateFormat ymdwFormat = DateFormat.getDateInstance(DateFormat.FULL);

    public static final long MinutesPerDay = 24 * 60;
    public static final long MillisecondsPerDay = 24 * 60 * 60 * 1000;
    public static final long MillisecondsPerHour = 60 * 60 * 1000;
    public static final long MillisecondsPerMinute = 60 * 1000;

    public DateUtil() {
        super();
    }


    /**
     * 返回当前时间日期 如：20130102
     *
     * @return
     */
    public static String getCurDateStrYmd() {
        return ymdFormat.format(new Date());
    }

    /**
     * 返回当前时间日期 如：2013-01-02
     *
     * @return
     */
    public static String getCurDateStrY_m_d() {
        return y_m_dFormat.format(new Date());
    }

    public static String getDateStrY_m_d(Date date) {
        return y_m_dFormat.format(date);
    }

    /**
     * 如 2013-01-02字符串转换为20130102
     *
     * @return
     */
    public static String getDateStrY_m_dToYmd(String date) {
        return date.replaceAll("-", "");
    }

    /**
     * 如 2013-01-02 01:02:01字符串转换为20130102
     *
     * @return
     */
    public static String getDateStrY_m_d_h_m_sToYmd(String date) {
        return getDateStrY_m_dToYmd(date).substring(0, 8);
    }

    /**
     * 获取当前时间   精确到毫秒  如20130620123407778
     */
    public static String getCurDateStrHaomiao() {
        return haomiaoFormat.format(new Date());
    }

    /**
     * 获取当前时间   精确到毫秒  如2013-06-20 12:34:07 778
     */
    public static String getCurDateStrHaomiao_() {
        return y_m_d_h_m_sFormat.format(new Date());
    }


    /**
     * 获取当前时间   精确到秒  如2013-06-20 12:34:07
     */
    public static String getCurDateStrMiao_() {
        return y_m_d_h_m_sFormat.format(new Date());
    }

    /**
     * 获取当前时间   精确到分钟  如201306201234
     */
    public static String getCurDateStrFenzhong() {
        return ymdhmFormat.format(new Date());
    }

    /**
     * 获取当前时间   精确到秒  如20130620123412
     */
    public static String getCurDateStrMiao() {
        return ymdhmFormatYmdhms.format(new Date());
    }

    /**
     * 字符串转换为指定格式字符串
     */
    public static String getFormartStrFromStr(String format, String datestr) {
        String ymdStr = null;
        if (format == null || "".equals(format.trim())) {
            return null;
        }
        if (datestr == null || "".equals(datestr.trim())) {
            return null;
        }
        try {
            Date date = new SimpleDateFormat(format).parse(datestr);
            ymdStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(date);
        } catch (Exception e) {
            return "";
        }
        return ymdStr;
    }

    /**
     * 字符串转换为指定格式字符串
     */
    public static String getStrFromDate(Date date) {
        String ymdStr = null;
        try {
            ymdStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        } catch (Exception e) {
            return "";
        }
        return ymdStr;
    }

    /**
     * 字符串转换为指定格式字符串
     */
    public static String getStrFromDateFull(Date date) {
        String ymdStr = null;
        try {
            ymdStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(date);
        } catch (Exception e) {
            return "";
        }
        return ymdStr;
    }

    /**
     * 字符串转换为指定格式日期对象
     */
    public static Date getDateFromStr(String format, String datestr) {
        Date date = null;
        if (format == null || "".equals(format.trim())) {
            return null;
        }
        if (datestr == null || "".equals(datestr.trim())) {
            return null;
        }
        try {
            date = new SimpleDateFormat(format).parse(datestr);
        } catch (Exception e) {
            return null;
        }
        return date;
    }

    public static Date getDateFromStr(DateFormat format, String datestr) {
        Date date = null;
        if (format == null) {
            return null;
        }
        if (datestr == null || "".equals(datestr.trim())) {
            return null;
        }
        try {
            date = format.parse(datestr);
        } catch (Exception e) {
            return null;
        }
        return date;
    }

    public static Date getDateFromStrDayAdd1(DateFormat format, String datestr) {
        Date date = null;
        if (format == null) {
            return null;
        }
        if (datestr == null || "".equals(datestr.trim())) {
            return null;
        }
        try {
            Date d = format.parse(datestr);
            date = new Date(d.getTime() + MillisecondsPerDay);
        } catch (Exception e) {
            return null;
        }
        return date;
    }


    /**
     * 获取    指定时间 指定格式    间距多少分钟后的时间  字符串
     *
     * @param datestr
     * @param diff
     * @return
     */
    public static String getDiffMinuteDateStr(String datestr, Integer diff) {
        Date d = getDateFromStr("yyyyMMddHHmm", datestr);
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.MINUTE, now.get(Calendar.MINUTE) + diff);
        Date rd = now.getTime();
        return ymdhmFormat.format(rd);
    }

    /**
     * 当前时间天数+-
     * 传入的参数格式必须和返回的参数格式一样
     *
     * @param format
     * @param datestr
     * @param addDays
     * @return
     */
    public static String getDateStringFromStrDayAdd(DateFormat format, String datestr, Integer addDays) {
        Date date = null;
        if (format == null) {
            return null;
        }
        if (datestr == null || "".equals(datestr.trim())) {
            return null;
        }
        try {
            Date d = format.parse(datestr);
            date = new Date(d.getTime() + MillisecondsPerDay * addDays);
        } catch (Exception e) {
            return null;
        }
        return format.format(date);
    }


    public static String getDateStringByLongTime(Long longStr) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(longStr);
        Date date = c.getTime();
        return y_m_d_h_m_sFormat.format(date);
    }


//	public static void main(String[] args) {
//		System.out.println(getDateStringFromStrDayAdd(DateUtil.y_m_dFormat,DateUtil.getCurDateStrY_m_d(),-3));
//		System.out.println(getDateStringFromStrDayAdd(DateUtil.y_m_d_h_m_sFormat,DateUtil.getCurDateStrMiao_(),3));
//	} 

}
