/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomas.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormatter;

/**
 *
 * @author Antonella
 */
public class Test {

    public static void main(String[] args) throws ParseException {
       
TimeZone.setDefault(TimeZone.getTimeZone("GMT-4"));
        //TimeZone timeZone = TimeZone.getTimeZone("GMT-4");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        //sdf.setTimeZone(timeZone);
        String date2 = sdf.format(new Date());
       // Date date3 = sdf.parse(date2);
       Date date3 = new Date();
        System.out.println(date2);
        System.out.println(date3);
    }

}
