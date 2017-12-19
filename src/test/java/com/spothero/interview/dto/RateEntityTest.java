package com.spothero.interview.dto;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.ArrayList;
import java.util.List;

/**
 * RateEntity Tester.
 *
 * @author Brian Slater
 * @version 1.0
 * @since <pre>Dec 17, 2017</pre>
 */
public class RateEntityTest {

    /**
     * Method: getRates()
     */
    @Test
    public void testSetThenGetRates() throws Exception {
        RateEntity rateEntity = new RateEntity();
        Rate rate = new Rate();
        List<Rate> rateList = new ArrayList<>();
        rateList.add(rate);
        rateEntity.setRates(rateList);
        Assert.assertEquals(rateList, rateEntity.getRates());
    }

    /**
     * Method: toString()
     */
    @Test
    public void testToString() throws Exception {
        RateEntity rateEntity = new RateEntity();
        Assert.assertEquals("RateEntity{rates=null}", rateEntity.toString());
    }


} 
