
package com.spothero.interview.webapp.pojo;

import javax.xml.bind.annotation.XmlRootElement;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@XmlRootElement
@JsonInclude(Include.NON_EMPTY)
public class Rate {

    private String days;
    private String times;
    private int price;

    public Rate() {
    }

    public String getDays() {
        return days;
    }

    public void setDays(final String days) {
        this.days = days;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(final String times) {
        this.times = times;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(final int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Rate{" +
                "days='" + days + '\'' +
                ", times='" + times + '\'' +
                ", price=" + price +
                '}';
    }
}
