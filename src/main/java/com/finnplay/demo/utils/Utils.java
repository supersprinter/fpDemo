package com.finnplay.demo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;
import java.util.regex.Pattern;

public class Utils {

    public static final String DEFAULT_DATE_FORMAT = "MM/dd/yyyy";


    public static Instant stringToInstantConverter(String dateStr) throws ParseException {
        return new SimpleDateFormat(DEFAULT_DATE_FORMAT).parse(dateStr).toInstant();
    }


    public static String instantToStringConverter(Instant date) {
        if(date == null) {
            return "";
        }
        String year = date.toString().substring(0, 4);
        String month = date.toString().substring(5, 7);
        String day = date.toString().substring(8, 10);
        return month  + "/" + day + "/" + year;
    }


    public static boolean isEmailValid(String email){
        return Pattern.matches("[_a-zA-Z1-9]+(\\.[A-Za-z0-9]*)*@[A-Za-z0-9]+\\.[A-Za-z0-9]+(\\.[A-Za-z0-9]*)*", email);
    }


    public static boolean isDateValid(String dateStr) {
        if(dateStr == null) return false;
        Date date = null;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
            date = sdf.parse(dateStr);
            if (!dateStr.equals(sdf.format(date))) {
                date = null;
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return date != null;
    }
}
