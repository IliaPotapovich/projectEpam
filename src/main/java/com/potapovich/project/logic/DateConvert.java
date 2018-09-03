package com.potapovich.project.logic;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateConvert {


    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String dateToString(LocalDateTime date) {
        String stringDate = null;
        if (date != null) {
            stringDate = FORMATTER.format(date);
        }
        return stringDate;
    }

    public static LocalDateTime stringToDate(String stringDate) {
        final int MILLISECONDS_SYMBOLS = 2;
        LocalDateTime date = null;
        if (stringDate != null) {
            date = LocalDateTime.parse(stringDate.substring(0, stringDate.length() - MILLISECONDS_SYMBOLS), FORMATTER);
        }
        return date;
    }

}
