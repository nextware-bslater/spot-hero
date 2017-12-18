package com.spothero.interview.webapp.pojo;

import com.spothero.interview.webapp.dto.Rate;

public class RateInterval {

    private Interval interval;
    private int rate;

    public RateInterval(Interval interval,int rate) {
        this.interval = interval;
        this.rate = rate;
    }

    public Interval getInterval() {
        return interval;
    }

    public int getRate() {
        return rate;
    }
}
