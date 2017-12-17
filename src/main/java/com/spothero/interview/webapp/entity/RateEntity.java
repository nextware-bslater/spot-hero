package com.spothero.interview.webapp.entity;

import com.fasterxml.jackson.annotation.JsonRootName;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement()
@JsonRootName(value = "rates")
public class RateEntity {

    private List<Rate> rates;

    public RateEntity() {
    }

    public List<Rate> getRates() {
        return rates;
    }

    public void setRates(final List<Rate> rates) {
        this.rates = rates;
    }

    @Override
    public String toString() {
        return "RateEntity{" +
                "rates=" + rates +
                '}';
    }
}
