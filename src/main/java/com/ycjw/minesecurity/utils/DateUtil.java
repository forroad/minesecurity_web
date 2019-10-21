package com.ycjw.minesecurity.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private final static  String DAY_REGX="yy-MM-dd";
    private final static  String MINUTE_REGX="yy-MM-dd HH:mm";
    private static SimpleDateFormat dat_format=new SimpleDateFormat(DAY_REGX);
    private static SimpleDateFormat minute_format=new SimpleDateFormat(MINUTE_REGX);


    /**
     * 获取一个日期到天
     * @param date
     * @return
     * @throws Exception
     */
    public static Date convert2Day(Date date) throws Exception{
        String str=dat_format.format(date);
        return dat_format.parse(str);
    }

    /**
     * 获取当前日期到天
     * @return
     * @throws Exception
     */
    public static Date convert2Day() throws Exception{
        String str=dat_format.format(new Date());
        return dat_format.parse(str);
    }

    /**
     * 将字符串转换为日期，精确到天
     * @param str
     * @return
     * @throws Exception
     */
    public static Date strToDate_day(String str) throws Exception{
        return dat_format.parse(str);
    }


    /**
     * 将字符串转换为日期，精确到分钟
     * @param str
     * @return
     * @throws Exception
     */
    public static Date strToDate_minute(String str) throws Exception{
        return minute_format.parse(str);
    }

}
