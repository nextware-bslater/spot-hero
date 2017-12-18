package com.spothero.interview.webapp.pojo;

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
     *
     * @param times
     */
    public Interval(String times) {
        buildInterval(times);
    }

    public Interval(String startInterval, String endInterval){
        buildInterval(startInterval, endInterval);
    }

    public Interval() {
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(final LocalTime start) {
        this.start = start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(final LocalTime end) {
        this.end = end;
    }

    public String[] getTimes() {
        return times;
    }

    public void setTimes(final String[] times) {
        this.times = times;
    }

    /**
     * parseTimeHHmm is a helper for parsing time string formatted in HHmm
     * @param time
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
     * @param time
     * @return LocalTime of data string
     * @throws Exception
     */
    public static LocalTime parseTimeISO(String time) throws Exception {

        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        LocalTime localTime = LocalTime.parse(time, formatter);

        return localTime;
    }

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

    private void buildInterval(final String startInterval, final String endInterval) {
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

    public boolean encapsulates(Interval interval){
        boolean startCondition = this.start.isBefore(interval.getStart());
        boolean endCondition = this.end.isAfter(interval.getEnd());
        return startCondition && endCondition;
    }



}
