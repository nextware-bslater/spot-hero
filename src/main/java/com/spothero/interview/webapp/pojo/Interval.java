package com.spothero.interview.webapp.pojo;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Interval {

    private LocalTime start;
    private LocalTime end;
    private String times;

    public Interval(String times) {
        this.times = times;
        buildInterval(times);
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

    public LocalTime parseTime(String time) throws Exception {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
        LocalTime localTime = LocalTime.parse(time, formatter);

        return localTime;
    }

    public void buildInterval(final String times) {

        try {
            if(!times.contains("-")) {
                throw new Exception("Times passed in JSON file must be expressed as 2 times formatted HMmm separated by a dash");
            }
            String[] timesArray = times.split("-");
            if(timesArray[0].length() != 4){
                throw new Exception("Times passed in JSON file must be expressed in 24hr format with 4 digits with pattern HHmm");
            }
            setStart(parseTime(timesArray[0]));
            if(timesArray[1].length() != 4){
                throw new Exception("Times passed in JSON file must be expressed in 24hr format with 4 digits with pattern HHmm");
            }
            setEnd(parseTime(timesArray[1]));
            if(start.isAfter(end)){
                throw new Exception("Start time must be less than end time");
            }
        } catch (Exception e) {
//            TODO add logger
        }
    }

//    LAMBDA comparator http://tutorials.jenkov.com/java/lambda-expressions.html#one-parameter
//    boolean result = myComparator.compare(2, 5)

}
