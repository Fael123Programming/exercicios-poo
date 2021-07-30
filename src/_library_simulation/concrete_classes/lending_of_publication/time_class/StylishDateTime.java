package _library_simulation.concrete_classes.lending_of_publication.time_class;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;//Our dates will have this pattern: dd/MM/yyyy HH:mm:ss
import java.time.Period;

import static java.lang.Math.abs;

public class StylishDateTime {
    private static final DateTimeFormatter FORMATTER=DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    
    private StylishDateTime(){}
    
    /**
     * This method receives an integer number which specifies a day to consider to create a string
     * formatted in format of DateTimeFormatter FORMATTER (it is an attribute of StylishDateTime), based 
     * on system's current date and time.
     * Examples of usages: 0, returns system's current date and time; 5, returns date and time of 5 days
     * after now; -10, returns date and time of 10 days ago.
     * @param dayToConsider 
     * @return formatted string standardized in pattern dd/MM/yyyy HH:mm:ss
     */
    public static String getDateTimeString(int dayToConsider){
        LocalDateTime dateTime=LocalDateTime.now();
        if(dayToConsider==0) return dateTime.format(StylishDateTime.FORMATTER);
        else{
            Period period=Period.ofDays(abs(dayToConsider));
            if(dayToConsider<0) return dateTime.minus(period).format(StylishDateTime.FORMATTER);
            return dateTime.plus(period).format(FORMATTER);
        }
    }
    
    /**
     * This method analyzes whether a string representation of a date with time is greater (that is, 
     * older) than another representation like itself. Note that both of them have to implement the 
     * pattern this class follows: dd/MM/yyyy HH:mm:ss. Anyways, time is not considered in this calculus
     * but just date (year, month and day). 
     * @param firstDateTime  a string representation of a date with time
     * @param secondDateTime  a string representation of a date with time
     * @return a boolean value which says if secondDateTime is greater (older) than firstDateTime
     */
    public static boolean isSecondDateTimeStringGreaterThanFirstDateTimeString(String firstDateTime,String secondDateTime){
        LocalDateTime toFirst=LocalDateTime.parse(firstDateTime,StylishDateTime.FORMATTER);
        LocalDateTime toSecond=LocalDateTime.parse(secondDateTime,StylishDateTime.FORMATTER);
        Period betweenThem=Period.between(toFirst.toLocalDate(),toSecond.toLocalDate());
        return !betweenThem.isNegative();
    }
    
    /**
     * This method receives a string representation of a date with time and a quantity of days
     * to be added into itself. Note that that string must implement the standard notation this
     * class follows.
     * @param dateTimeString  a string representation of a date with time
     * @param quantityOfDays  quantity of days to be added into dateTimeString
     * @return a new date time string with a larger quantity of days (as large as quantityOfDays is)  
     * @see addAmountOfDaysInDateTimeStringBasedOnCurrentTime
     */
    
    public static String addAmountOfDaysInDateTimeString(String dateTimeString,int quantityOfDays){
        LocalDateTime localDateTime=LocalDateTime.parse(dateTimeString,StylishDateTime.FORMATTER);
        return localDateTime.plus(Period.ofDays(quantityOfDays)).format(StylishDateTime.FORMATTER);
    }
    
    /**
     * This method receives a string representation of a date with time and a quantity of days
     * to be added into itself. Unlike addAmountOfDaysInDateTimeString, this method updates the
     * time of that date time string to system's current time. Note that that string must implement
     * the standard notation this class follows.
     * @param dateTimeString a string representation of a date with time
     * @param quantityOfDays quantity of days to be added into dateTimeString
     * @return a new date time string with a larger quantity of days (as large as quantityOfDays is) and
     * updated time (based on system's current time)
     * @see addAmountOfDaysInDateTimeString
     */
    public static String addAmountOfDaysInDateTimeStringBasedOnCurrentTime(String dateTimeString,int quantityOfDays){
        LocalDateTime localDateTime=LocalDateTime.parse(dateTimeString,StylishDateTime.FORMATTER);
        localDateTime=LocalDateTime.of(localDateTime.getYear(),localDateTime.getMonth().getValue(),localDateTime.getDayOfMonth(),
                LocalDateTime.now().getHour(),LocalDateTime.now().getMinute(),LocalDateTime.now().getSecond());
        return localDateTime.plus(Period.ofDays(quantityOfDays)).format(StylishDateTime.FORMATTER);
    }
}
