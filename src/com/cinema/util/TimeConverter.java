package com.cinema.util;

import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeConverter {

    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static Time toSqlTime(String timeStr){
        LocalTime localEndTimeObj = LocalTime.parse(timeStr, formatter);
        Time sqlTime = Time.valueOf(localEndTimeObj);
        return sqlTime;
    }

}
