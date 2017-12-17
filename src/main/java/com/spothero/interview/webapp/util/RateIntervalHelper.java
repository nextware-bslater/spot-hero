package com.spothero.interview.webapp.util;

import com.spothero.interview.webapp.entity.Rate;
import com.spothero.interview.webapp.pojo.Interval;
import com.spothero.interview.webapp.pojo.RateInterval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class RateIntervalHelper {

    public static void partitionRatesByDay(Rate originalRate, Map<String, List<RateInterval>> dayMap){
        if(originalRate == null){
            //TODO warn in logger
//            throw new Exception( "Days must be specified for to cre")
        }

        List<String> days =  Arrays.asList(originalRate.getDays().split(","));
        for(String day : days){
            if(dayMap.containsKey(day)){
                dayMap.get(day).addAll(generateRateIntervalListOfOrignalRate(originalRate));
            }else{
                dayMap.put(day, generateRateIntervalListOfOrignalRate(originalRate));
            }
        }
    }

    public static List<RateInterval> generateRateIntervalListOfOrignalRate(Rate originalRate){

        List<RateInterval> rateIntervals = new ArrayList<>();
        Interval interval = new Interval(originalRate.getTimes());
        RateInterval rateInterval = new RateInterval(interval, originalRate.getPrice());
        rateIntervals.add(rateInterval);

        return rateIntervals;
    }


}
