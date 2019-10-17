package com.ycjw.minesecurity.utils;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private static SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 获取一个日期到天
     * @param date
     * @return
     * @throws Exception
     */
    public static Date convert2Day(Date date) throws Exception{
        String str=format.format(date);
        return format.parse(str);
    }

    /**
     * 获取当前日期到天
     * @return
     * @throws Exception
     */
    public static Date convert2Day() throws Exception{
        String str=format.format(new Date());
        return format.parse(str);
    }

}
