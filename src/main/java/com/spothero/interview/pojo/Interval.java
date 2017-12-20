package com.spothero.interview.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

/**
 * Class represents a finite interval of time. It consists of a start and end time, as well as the string from which start and end were computed
 */
@JsonInclude
@XmlRootElement
public class Interval {

    private LocalTime start;
    private LocalTime end;
    private String[] times;

    /**
     *Constructor builds interval that contains data on which the requested interval will be tested against
     * @param times string of time intervals
     */
    public Interval(String times) {
        try {
            buildInterval(times);
        } catch (Exception e) {

        }
    }

    /**
     * Constructor builds interval that will later have rate calculated
     * @param startInterval isoformat string which corresponds to the start an {@link Interval}
     * @param endInterval isoformat string which corresponds to the end an {@link Interval}
     */
    public Interval(String startInterval, String endInterval){
        try {
            buildInterval(startInterval, endInterval);
        } catch (Exception e) {

        }
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
        if(localTime == null){
            throw new Exception("Invalid time string passed");
        }

        return localTime;
    }

    /**
     * parseDateISO is a helper for parsing a date string into an iso time
     * @param time for which a conversion input string to isoformat is necessary
     * @return LocalTime of data string
     * @throws Exception
     */
    public static LocalTime parseTimeISO(String time) throws Exception {

        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        LocalTime localTime = LocalTime.parse(time, formatter);
        if(localTime == null){
            throw new Exception("Invalid date string passed");
        }

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
    public void buildInterval(String times) throws Exception {

            if(!times.contains("-")) {
                throw new Exception("Times passed must be expressed as 2 times formatted HMmm separated by a dash");
            }
            String[] timesArray = times.split("-");
            setTimes(timesArray);
            setStart(parseTimeHHmm(timesArray[0]));
            setEnd(parseTimeHHmm(timesArray[1]));
            if(start.isAfter(end)){
                throw new Exception("Start time must be less than end time");
            }

    }

    /**
     * Methods validates and populates the {@link Interval} object which typically represents client input interval
     * @param startInterval isoformat string which corresponds to the start an {@link Interval}
     * @param endInterval isoformat string which corresponds to the end an {@link Interval}
     */
    public void buildInterval(final String startInterval, final String endInterval) throws Exception {
            setStart(parseTimeISO(startInterval));
            setEnd(parseTimeISO(endInterval));
            String[] times = {startInterval, endInterval};
            setTimes(times);
            if(start.isAfter(end)){
                throw new Exception("Start time must be less than end time");
            }

    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Interval interval = (Interval) o;

        if (getStart() != null ? !getStart().equals(interval.getStart()) : interval.getStart() != null) return false;
        if (getEnd() != null ? !getEnd().equals(interval.getEnd()) : interval.getEnd() != null) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(getTimes(), interval.getTimes());
    }

    @Override
    public int hashCode() {
        int result = getStart() != null ? getStart().hashCode() : 0;
        result = 31 * result + (getEnd() != null ? getEnd().hashCode() : 0);
        result = 31 * result + Arrays.hashCode(getTimes());
        return result;
    }
}
