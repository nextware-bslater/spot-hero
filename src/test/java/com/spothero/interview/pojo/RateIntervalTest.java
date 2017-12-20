package com.spothero.interview.pojo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * RateInterval Tester.
 *
 * @author Brian Slater
 * @version 1.0
 * @since <pre>Dec 19, 2017</pre>
 */
public class RateIntervalTest {

    RateInterval rateInterval;

    @Before
    public void before() throws Exception {
        this.rateInterval = new RateInterval("1000-1100", 1000);
    }

    /**
     * Method: getInterval()
     */
    @Test
    public void testGetInterval() throws Exception {
        Interval expectedInterval = new Interval("1000-1100");
        Assert.assertEquals(expectedInterval, this.rateInterval.getInterval());
    }

    /**
     * Method: getRate()
     */
    @Test
    public void testGetRate() throws Exception {
        Assert.assertEquals(1000, this.rateInterval.getRate());
    }


}
