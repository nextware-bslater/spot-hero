
package com.spothero.interview.dto;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 *Rate class represents an Object that relates a series of days with a time interval and a price for parking given preceding day and time interval
  */
@XmlRootElement
@JsonInclude(Include.NON_EMPTY)
public class Rate {

    private String days;
    private String times;
    private int price;

    /**
     * Constructor creates a new empty instance of Rate
     */
    public Rate() {
    }

    /**
     * Method gets a comma separated list of days that have a given price and time interval
     * @return days - string of comma separated days in short form locale.US
     */
    public String getDays() {
        return days;
    }

    /**
     * Method sets a comma separated list of days that have a given price and time interval
     * @param days string of comma separated days in short form locale.US (e.g. mon, tue, wed,..,etc.)
     */
    public void setDays(final String days) {
        this.days = days;
    }

    /**
     * Method gets a range of times in HHmm format that represent a interval in time at which a price can be applied
     * @return times - string of time interval fomatted "HHmm-HHmm"
     */
    public String getTimes() {
        return times;
    }

    /**
     *Methood sets a range of times in HHmm format that represent a interval in time at which a price can be applied
     * @param times - string of times in "HHmm-HHmm" format, representing a realizable interval of time
     */
    public void setTimes(final String times) {
        this.times = times;
    }

    /**
     * Method gets a price for parking at a imaginary location given a set of days and time interval
     * @return price - price of parking for a given time interval and day
     */
    public int getPrice() {
        return price;
    }

    /**
     * Method sets a price for parking at a imaginary location given a set of days and time interval
     * @return price - pice of parking for a given time interval and day
     */
    public void setPrice(final int price) {
        this.price = price;
    }

    /**
     * Method overrides defualt Object.toString() method
     * @return legible string
     */
    @Override
    public String toString() {
        return "Rate{" +
                "days='" + days + '\'' +
                ", times='" + times + '\'' +
                ", price=" + price +
                '}';
    }


}
