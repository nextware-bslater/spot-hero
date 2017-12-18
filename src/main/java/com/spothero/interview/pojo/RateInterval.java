package com.spothero.interview.pojo;


/**
 * RateInterval represents an expansion of the {@link com.spothero.interview.webapp.dto.Rate} object based on days on which rates occur
 */
public class RateInterval {

    private Interval interval;
    private int rate;

    /**
     * Constructor builds a fully specified rate to time interval relationship
     * @param interval interval of time during which a rate is actice
     * @param rate rate to applied for a given interval of time
     */
    public RateInterval(Interval interval,int rate) {
        this.interval = interval;
        this.rate = rate;
    }

    /**
     * Methods gets the time interval associated with given rate
     * @return time interval for which a rate is valid
     */
    public Interval getInterval() {
        return interval;
    }

    /**
     * Methods gets the rate associated with given time interval
     * @return rate that is applied for a given time interval
     */
    public int getRate() {
        return rate;
    }
}
