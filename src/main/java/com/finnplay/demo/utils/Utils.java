package com.finnplay.demo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;

public class Utils {

    public static final String DEFAULT_DATE_FORMAT = "MM/dd/yyyy";

    public static Instant StringToInstantConverter(String dateStr) throws ParseException {
        return new SimpleDateFormat(DEFAULT_DATE_FORMAT).parse(dateStr).toInstant();
    }

    public static String InstantToStringConverter(Instant date) {
        if(date == null) {
            return "";
        }
        String year = date.toString().substring(0, 4);
        String month = date.toString().substring(5, 7);
        String day = date.toString().substring(8, 10);
        return month  + "/" + day + "/" + year;
    }
}
