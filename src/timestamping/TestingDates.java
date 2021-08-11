package timestamping;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
//--------------------------------
/*  Most-recommended classes to use with date and time handling
    Instant - a timestamp
    LocalDate - a date without a time, or any reference to an offset or time-zone
    LocalTime - a time without a date, or any reference to an offset or time-zone
    LocalDateTime - combines date and time, but still without any offset or time-zone
    ZonedDateTime - a "full" date-time with time-zone and resolved offset from UTC/Greenwich
Access the following link to get the formal documentation of time API:
https://docs.oracle.com/javase/9/docs/api/java/time/package-summary.html*/

public class TestingDates {
    public static void main(String[] args) throws ParseException {
        //Class Date -> import java.util.Date;
        Date timeExp = new Date();
        System.out.println("Today's date: " + timeExp + " Millisecons: " + timeExp.getTime());
        long oneWeek = 7 * 24 * 60 * 60 * 1000;//One week in milliseconds.
        timeExp.setTime(timeExp.getTime() + oneWeek);
        System.out.println("After a whole week: " + timeExp + " Milliseconds: " + timeExp.getTime());
        Date newDate = new Date();
        DateFormat fmt = DateFormat.getDateInstance(DateFormat.FULL);
        System.out.println(fmt.format(newDate));
        //--------------------------------------------------------------------------
        //Class Calendar -> import java.util.Calendar;
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();//From Calendar we can reach Date class.
        System.out.println(date);
        //calendar.get(Calendar.[constant]); cal.set(Calendar.[constant],[value]); calendar.add(Calendar.[constant],[quantity]) <- Negative to subtract
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int hours = calendar.get(Calendar.HOUR_OF_DAY);//24 hour day.
        int minutes = calendar.get(Calendar.MINUTE);
        int seconds = calendar.get(Calendar.SECOND);
        int milliseconds = calendar.get(Calendar.MILLISECOND);
        System.out.printf("Today's date (Calendar object): %d/%d/%d %d:%d:%d:%d%n", ++month, dayOfMonth, year, hours, minutes, seconds, milliseconds);
        calendar.set(Calendar.YEAR, 2002);
        calendar.set(Calendar.MONTH, 5);//Calendar starts counting the months from zero. Therefore, 5 means June.
        calendar.set(Calendar.DAY_OF_MONTH, 11);
        calendar.set(Calendar.HOUR_OF_DAY, 16);
        calendar.set(Calendar.MINUTE, 30);
        calendar.set(Calendar.SECOND, 15);
        calendar.set(Calendar.MILLISECOND, 377);
        System.out.println("Date I was born: " + calendar.getTime());
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        System.out.println("One week afterwards: " + calendar.getTime());
        //------------------------------------------------------------------------------
        //Date formatting
        //Class SimpleDateFormat -> import java.text.SimpleDateFormat;
        //It transforms strings into Date objects and the opposite way also.
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");//<- code to specify the date pattern to follow
        String formattedDate = dateFormatter.format(Calendar.getInstance().getTime());
        System.out.println(formattedDate);
        calendar.setTime(new Date());//Replacing the information calendar has by another date: the current date.
        System.out.println(calendar.getTime());
        String aDate = "10/12/3567";
        Date parsedADateString = dateFormatter.parse(aDate);
        System.out.println(parsedADateString);
        //---------------------------------------------------------------------------
        //Dates on Java 8
        /*Classes:
            - LocalDate: to handle dates;
            - LocalTime: to handle time;
            - LocalDateTime: to handle both things;
            - Period: represents quantity of years, months and days;
            - Duration: represents quantity of days, hours, minutes, seconds and nanoseconds;
        */
        //Class LocalTime
        LocalTime timeNow = LocalTime.now();
        LocalTime afterAWhile = LocalTime.of(timeNow.getHour() + 2, timeNow.getMinute() + 5, timeNow.getSecond());
        LocalTime parsedTime = LocalTime.parse("15:34:45");
        System.out.println("Time now: " + timeNow);
        System.out.println("After a while: " + afterAWhile);
        System.out.println("Random time I've chosen: " + parsedTime);
        System.out.println(LocalTime.MAX); //23:59:59.999999999
        System.out.println(LocalTime.MIDNIGHT); //00:00
        System.out.println(LocalTime.MIN); //00:00
        System.out.println(LocalTime.NOON); //12:00
        //------------------------------------------------------------------------------
        //Class LocalDate
        LocalDate dateNow = LocalDate.now();//yyyy-mm-dd
        LocalDate afterOneMonth = LocalDate.of(dateNow.getYear(), dateNow.getMonth().getValue() + 1, dateNow.getDayOfMonth());
        LocalDate myBirthday = LocalDate.parse("2002-06-11");//LocalDate starts counting the months from 1, as generally.
        System.out.println("Date now: " + dateNow);
        System.out.println("After one month: " + afterOneMonth);
        System.out.println("My birthday: " + myBirthday);
        //--------------------------------------------------------------------------
        //Class LocalDateTime
        LocalDateTime dateTimeNow = LocalDateTime.now();
        LocalDateTime afterSomeTime = LocalDateTime.of(dateTimeNow.getYear() + 10, dateTimeNow.getMonth().getValue() + 5, dateTimeNow.getDayOfMonth(), dateTimeNow.getHour(), dateTimeNow.getMinute(), dateTimeNow.getSecond());
        LocalDateTime randomDate = LocalDateTime.parse("2002-06-11T16:30:15");//<-- Always using this method pay attention to this pattern.
        System.out.println("Date and time now: " + dateTimeNow);
        System.out.println("After some time: " + afterSomeTime);
        System.out.println("My birthday: " + randomDate);
        //-------------------------------------------------------------------------
        //Class DateTimeFormatter -> import java.time.format.DateTimeFormatter;
        DateTimeFormatter newFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");//<- code to specify the date pattern to follow
        LocalDateTime anotherLDT = LocalDateTime.now();
        String formattedString = anotherLDT.format(newFormatter);
        System.out.println("Formatted string: " + formattedString);
        //Adding time and subtracting time with Period and Duration classes.
        anotherLDT = anotherLDT.plus(Period.ofDays(10));
        anotherLDT = anotherLDT.minus(Duration.ofHours(5));
        System.out.println(anotherLDT);
        //Method between - calculates the difference between two dates/times.
        //Either Period or Duration classes have that method.
        LocalDate ld = LocalDate.now();
        LocalDate tenDaysAgo = ld.minusDays(10);
        System.out.println(ld);
        System.out.println(tenDaysAgo);
        Period prd = Period.between(tenDaysAgo, ld);
        System.out.println("The quantity of days is " + prd.getDays());
        //Class Instant
        Instant momentNow = Instant.now();//UTC time
        Instant parsedString = Instant.parse("2021-07-29T13:27:50Z");
        System.out.println(momentNow);
        System.out.println(parsedString);
        //Class ZonedDateTime
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        LocalDateTime anotherDateTime = LocalDateTime.now();
        ZonedDateTime specificZonedDateTime = ZonedDateTime.of(anotherDateTime, ZoneId.of("America/Sao_Paulo"));
        ZonedDateTime parsedZonedDateTime = ZonedDateTime.parse("2021-07-29T13:27:50-03:00[America/Sao_Paulo]");
        System.out.println(zonedDateTime);
        System.out.println(specificZonedDateTime);
        System.out.println(parsedZonedDateTime);

    }
}
