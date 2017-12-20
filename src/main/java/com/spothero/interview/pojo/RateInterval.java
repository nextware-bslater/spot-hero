package com.spothero.interview.pojo;


/**
 * RateInterval represents an expansion of the {@link com.spothero.interview.dto.Rate} object based on days on which rates occur
 */
public class RateInterval {

    private Interval interval;
    private int rate;

    /**
     * Constructor builds a fully specified rate to time interval relationship
     * @param times from which to build interval
     * @param rate rate to applied for a given interval of time
     */
    public RateInterval(String times ,int rate) {
        this.interval = new Interval(times);
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

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final RateInterval that = (RateInterval) o;

        if (getRate() != that.getRate()) return false;
        return getInterval() != null ? getInterval().equals(that.getInterval()) : that.getInterval() == null;
    }

    @Override
    public int hashCode() {
        int result = getInterval() != null ? getInterval().hashCode() : 0;
        result = 31 * result + getRate();
        return result;
    }

    @Override
    public String toString() {
        return "RateInterval{" +
                "interval=" + interval +
                ", rate=" + rate +
                '}';
    }
}
