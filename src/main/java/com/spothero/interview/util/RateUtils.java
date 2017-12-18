package com.spothero.interview.util;

import com.spothero.interview.dto.Rate;
import com.spothero.interview.dto.RateEntity;
import com.spothero.interview.pojo.Day;
import com.spothero.interview.pojo.Interval;
import com.spothero.interview.pojo.RateInterval;

import java.util.*;

public class RateUtils {

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

    public static List<RateInterval> buildRateIntervalListFromOrignalRate(Rate originalRate){

        List<RateInterval> rateIntervals = new ArrayList<>();
        Interval interval = new Interval(originalRate.getTimes());
        RateInterval rateInterval = new RateInterval(interval, originalRate.getPrice());
        rateIntervals.add(rateInterval);

        return rateIntervals;
    }

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
