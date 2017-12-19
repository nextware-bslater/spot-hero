package com.spothero.interview.pojo;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.spothero.interview.pojo.Day.parseTimeISO;
import static junit.framework.TestCase.fail;

/**
 * Day Tester.
 *
 * @author <Authors name>
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
     * Method: getStartInterval()
     */
    @Test
    public void testSetThenGetStartInterval() throws Exception {
        Day day = new Day();
        String expectedStartInterval = "2015-07-01T07:00:00Z";
        day.setStartInterval(expectedStartInterval);
        Assert.assertEquals(expectedStartInterval, day.getStartInterval());
    }


    /**
     * Method: getEndInterval()
     */
    @Test
    public void testSetThenGetEndInterval() throws Exception {
        Day day = new Day();
        String expectedEndInterval = "2015-07-01T07:00:00Z";
        day.setEndInterval(expectedEndInterval);
        Assert.assertEquals(expectedEndInterval, day.getEndInterval());
    }

    /**
     * Method: parseTimeISO(String date)
     */
    @Test
    public void testParseTimeISO() throws Exception {
        String dateString = "2015-07-01T07:00:00Z";
        LocalDate actualDate = parseTimeISO(dateString);
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        LocalDate expectedDate = LocalDate.parse(dateString,formatter);
        Assert.assertEquals("You must have changed the format homie!", expectedDate, actualDate);
    }


    /**
     * Method: validateDay(final String startInterval, final String endInterval)
     */
    @Test
    public void testValidateDayEqualStartEndDay() throws Exception {

        Day day = new Day();
        String startInterval = "2015-07-01T07:00:00Z";
        String endInterval = "2015-07-01T12:00:00Z";
        boolean daysEqual = day.validateDay(startInterval, endInterval);
        Assert.assertEquals("Validatioon exoected to return date are the same", true, daysEqual);

    }

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
