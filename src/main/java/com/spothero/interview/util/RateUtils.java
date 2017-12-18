package com.spothero.interview.util;

import com.spothero.interview.dto.Rate;
import com.spothero.interview.dto.RateEntity;
import com.spothero.interview.pojo.Day;
import com.spothero.interview.pojo.Interval;
import com.spothero.interview.pojo.RateInterval;

import java.util.*;

/**
 * Helper class to construct objects associated with the process of computing parking rates
 */
public class RateUtils {

    /**
     *Method is a utility to populate a map that contains a key of day and a list of time intervals as its value
     * @param originalRate rate that requires expansion
     * @param dayMap map to which expanded rates should be placed
     */
    public static void partitionRatesByDay(Rate originalRate, Map<String, List<RateInterval>> dayMap){
        if(originalRate == null){
            //TODO warn in logger
//            throw new Exception( "Days must be specified for to cre")
        }

        List<String> days =  Arrays.asList(originalRate.getDays().split(","));
        for(String day : days){
            if(dayMap.containsKey(day)){
                dayMap.get(day).addAll(buildRateIntervalListFromOrignalRate(originalRate));
            }else{
                dayMap.put(day, buildRateIntervalListFromOrignalRate(originalRate));
            }
        }
    }

    /**
     *Method performs a cartesian multiplation of {@link Rate}'s days and interval
     *  * @param originalRate the original {@link Rate} that is to be multiplied
     * @return cartesian product or a {@link Rate}'s days and interval
     */
    public static List<RateInterval> buildRateIntervalListFromOrignalRate(Rate originalRate){

        List<RateInterval> rateIntervals = new ArrayList<>();
        Interval interval = new Interval(originalRate.getTimes());
        RateInterval rateInterval = new RateInterval(interval, originalRate.getPrice());
        rateIntervals.add(rateInterval);

        return rateIntervals;
    }

    /**
     *Method takes user requested date range input and tests it against the set of rates the user also uploaded
     * @param rateEntity represents list of {@link Rate} objects that user input is to be tested against
     * @param startInterval isoformat string which corresponds to the start an {@link Interval}
     * @param endInterval isoformat string which corresponds to the end an {@link Interval}
     * @return rate or lack there of that a client should expect to pay for parking given a desired time interval and a user defined constraint set
     */
    public static String findRateforRequestedDateTime(RateEntity rateEntity, String startInterval, String endInterval)
    {
        Map<String, List<RateInterval>> dayMap = new HashMap<>();

        for (Rate rate : rateEntity.getRates()) {
            partitionRatesByDay(rate, dayMap);
        }

        //Create interval from start and end time
        Day requestDay = new Day(startInterval, endInterval);
        Interval requestInterval = requestDay.getInterval();

        List<RateInterval> rateIntervals;
        if(dayMap.containsKey(requestDay.getDay())) {
            rateIntervals = dayMap.get(requestDay.getDay());
            for(RateInterval rateInterval : rateIntervals){
                Interval checkInterval = rateInterval.getInterval();
                boolean checkIntervalEncapsulatesRequestInterval = checkInterval.encapsulates(requestInterval);
                if(checkIntervalEncapsulatesRequestInterval){
                    return new Integer(rateInterval.getRate()).toString();
                }
            }
            return "unavailable";
        }else{
            return "unavailable";
        }
    }


}
