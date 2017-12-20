package com.spothero.interview.util;

import com.spothero.interview.dto.Rate;
import com.spothero.interview.pojo.RateInterval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Helper class to construct objects associated with the process of computing parking rates
 */
public class RateUtils {

    /**
     *Method is a utility to populate a map that contains a key of day and a list of time intervals as its value
     * @param originalRate rate that requires expansion
     * @param dayMap map to which expanded rates should be placed
     */
    public static Map<String, List<RateInterval>> partitionRatesByDay(Rate originalRate, Map<String, List<RateInterval>> dayMap) throws Exception{
        if(originalRate == null){
            //TODO warn in logger
//            throw new Exception( "Days must be specified for to cre")
        }

        List<String> days =  Arrays.asList(originalRate.getDays().split(","));
        for(String day : days){
            if(day.length() < 3){
                throw new Exception("Days must be of the form mon,tues,wed,thurs,fri,sat,sun");
            }
            day = day.substring(0,3);
            if(dayMap.containsKey(day)){
                dayMap.get(day).add(new RateInterval(originalRate.getTimes(), originalRate.getPrice()));
            }else{
                List<RateInterval> rateIntervals = new ArrayList<>();
                rateIntervals.add(new RateInterval(originalRate.getTimes(), originalRate.getPrice()));
                dayMap.put(day, rateIntervals);
            }
        }

        return dayMap;
    }


}
