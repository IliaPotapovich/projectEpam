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
}
