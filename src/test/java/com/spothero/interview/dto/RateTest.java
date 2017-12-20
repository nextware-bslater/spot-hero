package com.spothero.interview.dto;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Rate Tester.
 *
 * @author Brian Slater
 * @version 1.0
 * @since <pre>Dec 17, 2017</pre>
 */
public class RateTest {

    @Before
    public void before() throws Exception {
    }

    /**
     * Method: getDays()
     */
    @Test
    public void testSetThenGetDays() throws Exception {
        Rate rate = new Rate();
        String expectedDays = "mon,tue,wed";
        rate.setDays(expectedDays);
        String actualDays = rate.getDays();
        Assert.assertEquals(expectedDays, actualDays);

    }


    /**
     * Method: getTimes()
     */
    @Test
    public void testSetThenGetTimes() throws Exception {
        Rate rate = new Rate();
        String expectedTimes = "0100-0500";
        rate.setTimes(expectedTimes);
        String actualTimes = rate.getTimes();
        Assert.assertEquals(expectedTimes, actualTimes);

    }


    /**
     * Method: getPrice()
     */
    @Test
    public void testSetThenGetPrice() throws Exception {
        Rate rate = new Rate();
        int expectedPrice = 1500;
        rate.setPrice(expectedPrice);
        int actualPrice = rate.getPrice();
        Assert.assertEquals(expectedPrice, actualPrice);
    }


    /**
     * Method: toString()
     */
    @Test
    public void testToString() throws Exception {
        Rate rate = new Rate();
        rate.setDays("mon");
        rate.setTimes("1500-1800");
        rate.setPrice(500);
        Assert.assertEquals("Rate{days='mon', times='1500-1800', price=500}", rate.toString());

    }


} 
