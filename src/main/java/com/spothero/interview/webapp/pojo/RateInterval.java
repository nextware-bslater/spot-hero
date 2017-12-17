package com.spothero.interview.webapp.pojo;

import com.spothero.interview.webapp.entity.Rate;

public class RateInterval extends Rate {

    private Interval interval;
    private int price;

    public RateInterval(final Interval interval, final int price) {
        this.interval = interval;
        this.price = price;
    }


}
