package com.gagful.util;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtil {
    public static final String DATE_TIME_FORMAT = "dd-mm-yyyy HH:mm:ss";
    public static final String DATE_TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String TIME_FORMAT = "HH:mm:ss";

   /* public static String format(Date date, String pattern)
    {
        if (StringUtils.isEmpty(pattern))
            pattern = DATE_TIME_FORMAT;
        return DateFormatUtils.format(date, pattern);
    }*/

    public static boolean isTimeBetween(Date startTime, Date endTime, Date dateTime)
    {
        return ((((dateTime.equals(startTime)) || (dateTime.after(startTime)))) && (dateTime.before(endTime)));
    }
    public static int getYear(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar.get(1);
    }

    public static int getMonth(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return (calendar.get(2) + 1);
    }

    public static int getDay(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar.get(5);
    }

    public static int getHour(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar.get(10);
    }

    public static Date addDays(Date date,int daysNumber)
    {
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        localDateTime = localDateTime.plusDays(daysNumber);
        Date currentDatePlusOneDay = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        return currentDatePlusOneDay;
    }
    public static Date minusDays(Date date,int daysNumber)
    {
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        localDateTime = localDateTime.minusDays(daysNumber);
        Date currentDatePlusOneDay = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        return currentDatePlusOneDay;
    }
}
