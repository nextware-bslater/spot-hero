package com.spothero.interview.pojo;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Class represents a finite interval of time. It consists of a start and end time, as well as the string from which start and end were computed
 */
public class Interval {

    private LocalTime start;
    private LocalTime end;
    private String[] times;

    /**
     *Constructor builds interval that contains data on which the requested interval will be tested against
     * @param times string of time intervals
     */
    public Interval(String times) {
        buildInterval(times);
    }

    /**
     * Constructor builds interval that will later have rate calculated
     * @param startInterval isoformat string which corresponds to the start an {@link Interval}
     * @param endInterval isoformat string which corresponds to the end an {@link Interval}
     */
    public Interval(String startInterval, String endInterval){
        buildInterval(startInterval, endInterval);
    }

    /**
     * Constructor allows for an empty instance of {@link Interval} to be created
     */
    public Interval() {
    }

    /**
     * Method gets the start LocalTime of a given interval
     * @return start LocalTime of given interval
     */
    public LocalTime getStart() {
        return start;
    }

    /**
     *  Method sets the start LocalTime of a given interval
     * @param start LocalTime of given interval
     */
    public void setStart(final LocalTime start) {
        this.start = start;
    }

    /**
     * Method sets the end LocalTime of a given interval
     * @return end LocalTime of given interval
     */
    public LocalTime getEnd() {
        return end;
    }

    /**
     *  Method sets the end LocalTime of a given interval
     * @param end LocalTime of given interval
     */
    public void setEnd(final LocalTime end) {
        this.end = end;
    }

    /**
     * Method gets an array of times, where index 0 is start DateTime and index 1 is end DateTime
     * @return array of values used to generate this interval
     */
    public String[] getTimes() {
        return times;
    }

    /**
     * Method sets an array of times, which represent the initial start and end times
     * @param times array container with DateTime strings in isoformat or time strings in HH:mm format
     */
    public void setTimes(final String[] times) {
        this.times = times;
    }

    /**
     * parseTimeHHmm is a helper for parsing time string formatted in HHmm
     * @param time for which a conversion input string to HH:mm format is necessary
     * @return LocalTime formatted HH:mm
     * @throws Exception
     */
    public static LocalTime parseTimeHHmm(String time) throws Exception {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
        LocalTime localTime = LocalTime.parse(time, formatter);

        return localTime;
    }

    /**
     * parseTimeISO is a helper for parsing a date string into an iso time
     * @param time for which a conversion input string to isoformat is necessary
     * @return LocalTime of data string
     * @throws Exception
     */
    public static LocalTime parseTimeISO(String time) throws Exception {

        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        LocalTime localTime = LocalTime.parse(time, formatter);

        return localTime;
    }

    /**
     * Method tests whether the this interval encapsulates the param interval, i.e. the absolute value of this interval's bounds are greater than the absolute value of the param Interval's bounds
     * @param interval time period that is to be tested
     * @return boolean representing that param interval is a subset of this.Interval, or it is not
     */
    public boolean encapsulates(Interval interval){
        boolean startCondition = this.start.isBefore(interval.getStart());
        boolean endCondition = this.end.isAfter(interval.getEnd());
        return startCondition && endCondition;
    }

    /**
     * Methods validates and populates the {@link Interval} object against which the client typically tests their input Interval
     * @param times string of times in "HHmm-HHmm" format, representing a realizable interval of time
     */
    public void buildInterval(String times) {

        try {
            if(!times.contains("-")) {
                throw new Exception("Times passed in JSON file must be expressed as 2 times formatted HMmm separated by a dash");
            }
            String[] timesArray = times.split("-");
            setTimes(timesArray);
            if(timesArray[0].length() != 4){
                throw new Exception("Times passed in JSON file must be expressed in 24hr format with 4 digits with pattern HHmm");
            }
            setStart(parseTimeHHmm(timesArray[0]));
            if(timesArray[1].length() != 4){
                throw new Exception("Times passed in JSON file must be expressed in 24hr format with 4 digits with pattern HHmm");
            }
            setEnd(parseTimeHHmm(timesArray[1]));
            if(start.isAfter(end)){
                throw new Exception("Start time must be less than end time");
            }
        } catch (Exception e) {
//            TODO add logger
        }
    }

    /**
     * Methods validates and populates the {@link Interval} object which typically represents client input interval
     * @param startInterval isoformat string which corresponds to the start an {@link Interval}
     * @param endInterval isoformat string which corresponds to the end an {@link Interval}
     */
    public void buildInterval(final String startInterval, final String endInterval) {
        try {
            setStart(parseTimeISO(startInterval));
            setEnd(parseTimeISO(endInterval));
            String[] times = {startInterval, endInterval};
            setTimes(times);
            if(start.isAfter(end)){
                throw new Exception("Start time must be less than end time");
            }

        } catch (Exception e) {
//            TODO add logger
        }
    }




}
