package com.spothero.interview.dto;

import com.fasterxml.jackson.annotation.JsonRootName;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * RateEntity class represents list of {@link Rate} objects
 */
@XmlRootElement()
@JsonRootName(value = "rates")
public class RateEntity {

    private List<Rate> rates;

    /**
     * Constructor creates a new empty instance of RateEntity
     */
    public RateEntity() {
    }

    /**
     *Method gets a list of {@link Rate} objects
     * @return list of {@link Rate} objects
     */
    public List<Rate> getRates() {
        return rates;
    }

    /**
     * Method sets a list of {@link Rate} objects
     * @param rates - list of {@link Rate} objects
     */
    public void setRates(final List<Rate> rates) {
        this.rates = rates;
    }

    /**
     * Method overrides defualt Object.toString() method
     * @return legible string
     */
    @Override
    public String toString() {
        return "RateEntity{" +
                "rates=" + rates +
                '}';
    }
}
