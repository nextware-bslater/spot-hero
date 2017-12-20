package com.spothero.interview.util;

import com.spothero.interview.dto.Rate;
import com.spothero.interview.pojo.RateInterval;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * RateUtils Tester.
 *
 * @author <Authors name>
 * @since <pre>Dec 19, 2017</pre>
 * @version 1.0
 */
public class RateUtilsTest {

    private Map<String, List<RateInterval>> dayMap;
    private Rate rate;

    @Before
    public void before() throws Exception {
        this.dayMap = new HashMap<>();
        this.rate = new Rate();
        rate.setDays("mon,tues");
        rate.setTimes("1000-1100");
        rate.setPrice(1500);
    }


    /**
     *
     * Method: partitionRatesByDay(Rate originalRate, Map<String, List<RateInterval>> dayMap)
     *
     */
    @Test
    public void testPartitionRatesByDay() throws Exception {
        RateUtils.partitionRatesByDay(this.rate, this.dayMap);
        RateInterval expectedRateInterval = new RateInterval("1000-1100",1500);
        Assert.assertEquals(expectedRateInterval, this.dayMap.get("mon").get(0));
        Assert.assertEquals(expectedRateInterval, this.dayMap.get("tue").get(0));
    }

    /**
     *
     * Method: partitionRatesByDay(Rate originalRate, Map<String, List<RateInterval>> dayMap)
     *
     */
    @Test(expected = Exception.class)
    public void testPartitionRatesByDayWithInvalidDay() throws Exception {
        Rate rate = new Rate();
        rate.setPrice(2000);
        rate.setTimes("1200-1300");
        rate.setDays("mo,tu");

        RateUtils.partitionRatesByDay(rate, this.dayMap);
    }


    /**
     *
     * Method: findRateforRequestedDateTime(RateEntity rateEntity, String startInterval, String endInterval)
     *
     */
    @Test
    public void testFindRateforRequestedDateTime() throws Exception {

    }


}
