package com.gagful.util;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateTimeUtil {
    private DateTimeUtil(){}
    public static final String DATE_TIME_FORMAT = "dd-mm-yyyy HH:mm:ss";
    public static final String DATE_TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String TIME_FORMAT = "HH:mm:ss";


    public static boolean isTimeBetween(Date startTime, Date endTime, Date dateTime) {
        return ((((dateTime.equals(startTime)) || (dateTime.after(startTime)))) && (dateTime.before(endTime)));
    }

    public static int getYear(LocalDate date) {
        return date.getYear();
    }

    public static int getMonth(LocalDate date) {
        return date.getMonthValue();
    }

    public static int getDay(LocalDate date) {
        return date.getDayOfMonth();
    }

    public static Date addDays(Date date, int daysNumber) {
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        localDateTime = localDateTime.plusDays(daysNumber);
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date minusDays(Date date, int daysNumber) {
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        localDateTime = localDateTime.minusDays(daysNumber);
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}
