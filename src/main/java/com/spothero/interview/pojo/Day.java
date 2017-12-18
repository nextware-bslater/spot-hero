package com.spothero.interview.pojo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * Day is a class used to encapsulate the day and Interval that a user wishes to test against
 */
public class Day {

    private String day;
    private Interval interval;
    private String startInterval;
    private String endInterval;

    /**
     * Constructor creates an instance of a Day and validates that intervals are in the same day
     * @param startInterval string of isoformat DateTime which represents the start DateTime of a time interval
     * @param endInterval string of isoformat DateTime which represents the end DateTime of a time interval
     */
    public Day(String startInterval, String endInterval) {
        setStartInterval(startInterval);
        setEndInterval(endInterval);
        validateDay(startInterval,endInterval);
    }

    /**
     * Method gets the day of the week in short format Locale.US
     * @return day of week in short format (e.g. mon,tue,wed,...)
     */
    public String getDay() {
        return day;
    }

    /**
     * Method sets the day of the week in short format Locale.US
     * @param day day of the week in short format Locale.US
     */
    public void setDay(final String day) {
        this.day = day;
    }

    /**
     * Method gets associated instance of {@link Interval}
     * @return an instance of {@link Interval}
     */
    public Interval getInterval() {
        return interval;
    }

    /**
     * Method set an instance of {@link Interval} to be used for rate computation
     * @param interval interval of time for which a rate is applied
     */
    public void setInterval(final Interval interval) {
        this.interval = interval;
    }

    /**
     * Method gets a string representation of the starting time of a day's {@link Interval} object
     * @return string of Datetime in isoformat where a given day's time interval begins
     */
    public String getStartInterval() {
        return startInterval;
    }

    /**
     * Method sets string represenatation of the starting time of a day's {@link Interval} object
     * @param startInterval isoformat string which corresponds to the start an {@link Interval}
     */
    public void setStartInterval(final String startInterval) {
        this.startInterval = startInterval;
    }

    /**
     * Method gets a string representation of the ending time of a day's {@link Interval} object
     * @return string of Datetime in isoformat where a given day's time interval ends
     */
    public String getEndInterval() {
        return endInterval;
    }

    /**
     * Method sets string represenatation of the ending time of a day's {@link Interval} object
     * @param endInterval isoformat string which corresponds to the end an {@link Interval}
     */
    public void setEndInterval(final String endInterval) {
        this.endInterval = endInterval;
    }
    /**
     * parseTimeISO is a helper for parsing a date string into an iso date
     * @param date
     * @return LocalDate of data string
     * @throws Exception
     */
    public static LocalDate parseTimeISO(String date) throws Exception {

        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        LocalDate localDate = LocalDate.parse(date,formatter);

        return localDate;
    }

    /**
     * Method validates that startInterval and endInterval are in the same day, then set {@link Interval} and day values for the instance
     * @param startInterval isoformat string which corresponds to the start an {@link Interval}
     * @param endInterval isoformat string which corresponds to the end an {@link Interval}
     */
    private void validateDay(final String startInterval, final String endInterval) {

        try {
            LocalDate startDate = parseTimeISO(startInterval);
            LocalDate endDate = parseTimeISO(endInterval);
            if(endDate.equals(startDate)){
                Interval testInterval = new Interval(startInterval, endInterval);
                setInterval(testInterval);
                setDay(startDate.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.US).toLowerCase());
            }else{
                throw new Exception("Input dates must be on the same day");
            }

        } catch (Exception e) {

        }
    }
}
