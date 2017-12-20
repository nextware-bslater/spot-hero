package com.spothero.interview.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.spothero.interview.pojo.Day.parseDateISO;
import static junit.framework.TestCase.fail;

/**
 * Day Tester.
 *
 * @author Brian Slater
 * @version 1.0
 * @since <pre>Dec 19, 2017</pre>
 */

public class DayTest {


    //TODO add validation for day string value

    /**
     * Method: getDay() and setDay()
     */
    @Test
    public void testSetThenGetDay() throws Exception {
        Day day = new Day();
        String expectedDay = "mon";
        day.setDay(expectedDay);
        Assert.assertEquals(expectedDay, day.getDay());
    }

    /**
     * Method: getInterval() and setInterval()
     */
    @Test
    public void testSetThenGetInterval() throws Exception {
        Day day = new Day();
        String startInterval = "2015-07-01T07:00:00Z";
        String endInterval = "2015-07-01T12:00:00Z";
        Interval expectedInterval = new Interval(startInterval, endInterval);
        day.setInterval(expectedInterval);
        Assert.assertEquals(expectedInterval, day.getInterval());
    }


    /**
     * Method: getStartInterval() and setStartInterval()
     */
    @Test
    public void testSetThenGetStartInterval() throws Exception {
        Day day = new Day();
        String expectedStartInterval = "2015-07-01T07:00:00Z";
        day.setStartInterval(expectedStartInterval);
        Assert.assertEquals(expectedStartInterval, day.getStartInterval());
    }


    /**
     * Method: getEndInterval() and setEndInterval()
     */
    @Test
    public void testSetThenGetEndInterval() throws Exception {
        Day day = new Day();
        String expectedEndInterval = "2015-07-01T07:00:00Z";
        day.setEndInterval(expectedEndInterval);
        Assert.assertEquals(expectedEndInterval, day.getEndInterval());
    }

    /**
     * Method: parseDateISO(String date)
     */
    @Test
    public void testParseDateISO() throws Exception {
        String dateString = "2015-07-01T07:00:00Z";
        LocalDate actualDate = parseDateISO(dateString);
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        LocalDate expectedDate = LocalDate.parse(dateString, formatter);
        Assert.assertEquals("You must have changed the format homie!", expectedDate, actualDate);
    }

    /**
     * Method: parseDateISO(String time)
     */
    @Test(expected = Exception.class)
    public void testParseDateISOException() throws Exception {
        String dateString = "2015-07-01T07:0000000:00Z";
        Day.parseDateISO(dateString);
    }


    /**
     * Method: validateDay(final String startInterval, final String endInterval)
     * Tests behavior under expected conditions
     */
    @Test
    public void testValidateDayEqualStartEndDay() throws Exception {

        Day day = new Day();
        String startInterval = "2015-07-01T07:00:00Z";
        String endInterval = "2015-07-01T12:00:00Z";
        boolean daysEqual = day.validateDay(startInterval, endInterval);
        Assert.assertEquals("Validatioon exoected to return date are the same", true, daysEqual);

    }

    /**
     * Method: validateDay(final String startInterval, final String endInterval)
     * Tests behavior when when and interval does not fall in the same day
     */
    @Test
    public void testValidateDayStartEndDayNotEqual() throws Exception {
        Day day = new Day();
        String startInterval = "2015-07-01T07:00:00Z";
        String endInterval = "2015-07-02T12:00:00Z";
        try {
            boolean daysEqual = day.validateDay(startInterval, endInterval);
            fail("Expected an Exception to be thrown");
        } catch (Exception e) {
            Assert.assertEquals("Input dates must be on the same day", e.getMessage());
        }

    }

}
