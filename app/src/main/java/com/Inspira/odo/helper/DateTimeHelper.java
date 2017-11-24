package com.Inspira.odo.helper;

 import android.content.Context;


import java.text.DateFormat;
 import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class DateTimeHelper {
     SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/M/yyyy hh:mm:ss");
    long currentTime;

    public DateTimeHelper(Context context) {


    }


    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public String substractDates(Date date1, Date date2, SimpleDateFormat format) {
        long restDatesinMillis = date1.getTime()-date2.getTime();
        Date restdate = new Date(restDatesinMillis);

//        return format.format(restdate);
        return String.format("%02d d : %02d h : %02d min : %02d sec%n",
                TimeUnit.MILLISECONDS.toDays(restDatesinMillis),
                TimeUnit.MILLISECONDS.toHours(restDatesinMillis) ,
                TimeUnit.MILLISECONDS.toMinutes(restDatesinMillis) ,
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(restDatesinMillis)));
    }



    //1 minute = 60 seconds
//1 hour = 60 x 60 = 3600
//1 day = 3600 x 24 = 86400
    public String printDifference(Date startDate, Date endDate) {
        long different = endDate.getTime() - startDate.getTime();

        System.out.println("startDate : " + startDate);
        System.out.println("endDate : "+ endDate);
        System.out.println("different : " + different);

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;

        System.out.printf(
                "%d days, %d hours, %d minutes, %d seconds%n",
                elapsedDays, elapsedHours, elapsedMinutes, elapsedSeconds);
        return   elapsedDays +" d :"+ elapsedHours +"  h:"+ elapsedMinutes+"  m :"+ elapsedSeconds +" s";


    }

}
