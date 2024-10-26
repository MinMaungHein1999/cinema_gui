package com.cinema.util;


import java.sql.Date;
import java.sql.SQLData;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateConverter {
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static Date toSqlDate(String dateStr){
        LocalDate date = LocalDate.parse(dateStr, dateFormatter);
        Date sqlData = Date.valueOf(date);
        return sqlData;
    }
}
