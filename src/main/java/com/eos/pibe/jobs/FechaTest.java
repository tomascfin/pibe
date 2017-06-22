/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eos.pibe.jobs;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import static org.joda.time.DateTime.now;
import static org.joda.time.DateTime.now;
import static org.joda.time.DateTime.now;
import org.joda.time.DateTimeConstants;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.Weeks;

/**
 *
 * @author Tomas
 */
public class FechaTest {

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
        currentTimestamp.setDate(19);
        java.sql.Timestamp currentTimestamp2 = new java.sql.Timestamp(now.getTime());
        currentTimestamp2.setDate(22);
        currentTimestamp2.setHours(2);
        //currentTimestamp2.setMinutes(30);

        
        Date fecha1 = new Date(currentTimestamp.getTime());
        Date fecha2 = new Date(currentTimestamp2.getTime());
        int numero = currentTimestamp.getDay() - currentTimestamp2.getDay();
        
        
        int diferencia = workdayDiff(fecha1, fecha2);
        System.out.println(fecha1);
        System.out.println(fecha2);
        System.out.println(diferencia);
    }

    public static final int DAYS_PER_WEEKEND = 2;
    public static final int WEEK_START = DateTimeConstants.MONDAY;
    public static final int WEEK_END = DateTimeConstants.FRIDAY;

    public static int workdayDiff(Date d1, Date d2) {
        LocalDate start = LocalDate.fromDateFields(d1);
        LocalDate end = LocalDate.fromDateFields(d2);

        start = toWorkday(start);
        end = toWorkday(end);

        int daysBetween = Days.daysBetween(start, end).getDays();
        int weekendsBetween = Weeks.weeksBetween(start.withDayOfWeek(WEEK_START), end.withDayOfWeek(WEEK_START)).getWeeks();

        return daysBetween - (weekendsBetween * DAYS_PER_WEEKEND);
    }

    public static LocalDate toWorkday(LocalDate d) {
        if (d.getDayOfWeek() > WEEK_END) {
            return d.plusDays(DateTimeConstants.DAYS_PER_WEEK - d.getDayOfWeek() + 1);
        }
        return d;
    }
}
