package com.mail.util;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;


/**
 * @author Hibiki
 * @version 1.0.0
 * @ClassName DateTimeUtil.java
 * @Description TODO
 * @createTime 2018年11月30日 21:25:00
 */
public class DateTimeUtil {

    //str---date
    //date--str
    public static final String STANDARD_FORMAT="yyyy-MM-dd HH:mm:ss";

    public static Date strtoDate(String dateTimeStr, String formatStr){
        DateTimeFormatter dateTimeFormatter=DateTimeFormat.forPattern(formatStr);
        DateTime dateTime=dateTimeFormatter.parseDateTime(dateTimeStr);
        return dateTime.toDate();
    }

    public static String datetostr(Date date,String formatStr){
        if (date==null){
            return StringUtils.EMPTY;
        }
        DateTime dateTime=new DateTime(date);
        return dateTime.toString(formatStr);
    }

    public static Date strtoDate(String dateTimeStr){
        DateTimeFormatter dateTimeFormatter=DateTimeFormat.forPattern(STANDARD_FORMAT);
        DateTime dateTime=dateTimeFormatter.parseDateTime(dateTimeStr);
        return dateTime.toDate();
    }

    public static String datetostr(Date date){
        if (date==null){
            return StringUtils.EMPTY;
        }
        DateTime dateTime=new DateTime(date);
        return dateTime.toString(STANDARD_FORMAT);
    }
}
