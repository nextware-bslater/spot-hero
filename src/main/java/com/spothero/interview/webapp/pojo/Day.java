package com.spothero.interview.webapp.pojo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * Day is a class used to encapsulate the day and Interval that a user wishes to test against uploaded json file
 */
public class Day {

    private String day;
    private Interval interval;
    private String startInterval;
    private String endInterval;

    public Day(String startInterval, String endInterval) {
        setStartInterval(startInterval);
        setEndInterval(endInterval);
        validateDay(startInterval,endInterval);
    }

    public String getDay() {
        return day;
    }

    public void setDay(final String day) {
        this.day = day;
    }

    public Interval getInterval() {
        return interval;
    }

    public void setTestInterval(final Interval interval) {
        this.interval = interval;
    }

    public String getStartInterval() {
        return startInterval;
    }

    public void setStartInterval(final String startInterval) {
        this.startInterval = startInterval;
    }

    public String getEndInterval() {
        return endInterval;
    }

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

    private void validateDay(final String startInterval, final String endInterval) {

        try {
            LocalDate startDate = parseTimeISO(startInterval);
            LocalDate endDate = parseTimeISO(endInterval);
            if(endDate.equals(startDate)){
                Interval testInterval = new Interval(startInterval, endInterval);
                setTestInterval(testInterval);
                setDay(startDate.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.US).toLowerCase());
            }else{
                throw new Exception("Input dates must be on the same day");
            }

        } catch (Exception e) {

        }
    }
}
