package com.spothero.interview.pojo;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static com.spothero.interview.pojo.Interval.parseTimeHHmm;
import static com.spothero.interview.pojo.Interval.parseTimeISO;

/**
 * Interval Tester.
 *
 * @author Brian Slater
 * @version 1.0
 * @since <pre>Dec 19, 2017</pre>
 */
public class IntervalTest {


    /**
     * Method: getStart() and setStart(final LocalTime start)
     */
    @Test
    public void testSetThenGetStart() throws Exception {
        LocalTime expectedLocalTime = LocalTime.now();
        Interval interval = new Interval();
        interval.setStart(expectedLocalTime);
        Assert.assertEquals(expectedLocalTime, interval.getStart());
    }


    /**
     * Method: getEnd() and setEnd(final LocalTime end)
     */
    @Test
    public void testSetThenGetEnd() throws Exception {
        LocalTime expectedLocalTime = LocalTime.now();
        Interval interval = new Interval();
        interval.setEnd(expectedLocalTime);
        Assert.assertEquals(expectedLocalTime, interval.getEnd());
    }


    /**
     * Method: getTimes() and setTimes(final String[] times)
     */
    @Test
    public void testSetThenGetTimes() throws Exception {
        String[] expectedArray = {"1", "2"};
        Interval interval = new Interval();
        interval.setTimes(expectedArray);
        Assert.assertEquals(expectedArray.toString(),  interval.getTimes().toString());
    }

    /**
     * Method: parseTimeHHmm(String time)
     */
    @Test
    public void testParseTimeHHmm() throws Exception {
        String dateString = "1100";
        LocalTime actualDate = parseTimeHHmm(dateString);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
        LocalTime expectedDate = LocalTime.parse(dateString, formatter);
        Assert.assertEquals("You must have changed the format homie!", expectedDate, actualDate);
    }

    /**
     * Method: parseDateISO(String time)
     */
    @Test
    public void testParseTimeISO() throws Exception {
        String dateString = "2015-07-01T07:00:00Z";
        LocalTime actualDate = parseTimeISO(dateString);
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        LocalTime expectedDate = LocalTime.parse(dateString, formatter);
        Assert.assertEquals("You must have changed the format homie!", expectedDate, actualDate);
    }

    /**
     * Method: parseDateISO(String time)
     */
    @Test(expected = Exception.class)
    public void testParseTimeISOException() throws Exception {
        String dateString = "2015-07-01T07:0000000:00Z";
        parseTimeISO(dateString);
    }

    /**
     * Method: encapsulates(Interval interval)
     * Tests that when a subset tested against a superset returns true
     */
    @Test
    public void testEncapsulates() throws Exception {
        String subsetStart = "2015-07-01T07:00:00Z";
        String subsetEnd = "2015-07-01T08:00:00Z";
        Interval subset = new Interval(subsetStart, subsetEnd);
        String supersetStart = "2015-07-01T06:00:00Z";
        String supersetEnd = "2015-07-01T10:00:00Z";
        Interval superset = new Interval(supersetStart, supersetEnd);
        boolean supersetEncapsulatesSubset = superset.encapsulates(subset);
        Assert.assertEquals(true, supersetEncapsulatesSubset);

    }

    /**
     * Method: encapsulates(Interval interval)
     * Tests that when a "subset" with a lower bound less than the "superset" it is tested against yields false
     */
    @Test
    public void testEncapsulatesSubsetLowerLessThanSuperLower() throws Exception {
        String subsetStart = "2015-07-01T05:00:00Z";
        String subsetEnd = "2015-07-01T08:00:00Z";
        Interval subset = new Interval(subsetStart, subsetEnd);
        String supersetStart = "2015-07-01T06:00:00Z";
        String supersetEnd = "2015-07-01T10:00:00Z";
        Interval superset = new Interval(supersetStart, supersetEnd);
        boolean supersetEncapsulatesSubset = superset.encapsulates(subset);
        Assert.assertEquals(false, supersetEncapsulatesSubset);

    }

    /**
     * Method: encapsulates(Interval interval)
     * Tests that when a "subset" with a lower bound less than the "superset" it is tested against yields false
     */
    @Test
    public void testEncapsulatesSubsetUpperGreaterThanSuperUpper() throws Exception {
        String subsetStart = "2015-07-01T07:00:00Z";
        String subsetEnd = "2015-07-01T11:00:00Z";
        Interval subset = new Interval(subsetStart, subsetEnd);
        String supersetStart = "2015-07-01T06:00:00Z";
        String supersetEnd = "2015-07-01T10:00:00Z";
        Interval superset = new Interval(supersetStart, supersetEnd);
        boolean supersetEncapsulatesSubset = superset.encapsulates(subset);
        Assert.assertEquals(false, supersetEncapsulatesSubset);

    }

    /**
     * Method: encapsulates(Interval interval)
     * Tests that when a "subset" is actually a superset of the "superset" being tested the method yields false
     * */
    @Test
    public void testEncapsulatesSubsetIsSupersetOfTestInterval() throws Exception {
        String subsetStart = "2015-07-01T05:00:00Z";
        String subsetEnd = "2015-07-01T11:00:00Z";
        Interval subset = new Interval(subsetStart, subsetEnd);
        String supersetStart = "2015-07-01T06:00:00Z";
        String supersetEnd = "2015-07-01T10:00:00Z";
        Interval superset = new Interval(supersetStart, supersetEnd);
        boolean supersetEncapsulatesSubset = superset.encapsulates(subset);
        Assert.assertEquals(false, supersetEncapsulatesSubset);

    }

    /**
     * Method: encapsulates(Interval interval)
     * Tests that when lower bounds are equal method yields false
     * */
    @Test
    public void testEncapsulatesLowerBoundsEqual() throws Exception {
        String subsetStart = "2015-07-01T05:00:00Z";
        String subsetEnd = "2015-07-01T09:00:00Z";
        Interval subset = new Interval(subsetStart, subsetEnd);
        String supersetStart = "2015-07-01T05:00:00Z";
        String supersetEnd = "2015-07-01T10:00:00Z";
        Interval superset = new Interval(supersetStart, supersetEnd);
        boolean supersetEncapsulatesSubset = superset.encapsulates(subset);
        Assert.assertEquals(false, supersetEncapsulatesSubset);

    }

    /**
     * Method: encapsulates(Interval interval)
     * Tests that when upper bounds are equal method yields false
     * */
    @Test
    public void testEncapsulatesUpperBoundsEqual() throws Exception {
        String subsetStart = "2015-07-01T05:00:00Z";
        String subsetEnd = "2015-07-01T11:00:00Z";
        Interval subset = new Interval(subsetStart, subsetEnd);
        String supersetStart = "2015-07-01T06:00:00Z";
        String supersetEnd = "2015-07-01T11:00:00Z";
        Interval superset = new Interval(supersetStart, supersetEnd);
        boolean supersetEncapsulatesSubset = superset.encapsulates(subset);
        Assert.assertEquals(false, supersetEncapsulatesSubset);

    }

    /**
     * Method: buildInterval(String times)
     * Tests that when a valid time interval string is passed the method properly sets this value
     */
    @Test
    public void testBuildIntervalSetTimes() throws Exception {
        Interval interval = new Interval();
        String times = "0100-0200";
        String[] timesArray = times.split("-");
        interval.buildInterval(times);
        boolean index0Equals = (timesArray[0].equals(interval.getTimes()[0]));
        boolean index1Equals = (timesArray[1].equals(interval.getTimes()[1]));
        boolean lengthEquals = (timesArray.length == interval.getTimes().length);
        boolean equal = index0Equals && index1Equals && lengthEquals;
        Assert.assertEquals(true, equal);
    }

    /**
     * Method: buildInterval(String times)
     * Tests that when an invalid string is passed method throws exception
     * */
    @Test(expected = Exception.class)
    public void testBuildIntervalHandleInvalidTimes() throws Exception {
        Interval interval = new Interval();
        String times = "01000200";
        interval.buildInterval(times);
    }

    /**
     * Method: buildInterval(String times)
     * Tests that when a date range string is passed with a start time greater than end time
     * */
    @Test(expected = Exception.class)
    public void testBuildIntervalHandleStartAfterEnd() throws Exception {
        Interval interval = new Interval();
        String times = "1000-0900";
        interval.buildInterval(times);
    }

    /**
     * Method: buildInterval(final String startInterval, final String endInterval)
     * Tests that when method is given valid inputs it behaves nicely
     */
    @Test
    public void testBuildIntervalForStartIntervalEndIntervalSetTimes() throws Exception {
        Interval interval = new Interval();
        String startInterval = "2015-07-01T05:00:00Z";
        String endInterval = "2015-07-01T09:00:00Z";
        String[] timesArray = {"2015-07-01T05:00:00Z", "2015-07-01T09:00:00Z"};
        interval.buildInterval(startInterval, endInterval);
        boolean index0Equals = (timesArray[0].equals(interval.getTimes()[0]));
        boolean index1Equals = (timesArray[1].equals(interval.getTimes()[1]));
        boolean lengthEquals = (timesArray.length == interval.getTimes().length);
        boolean equal = index0Equals && index1Equals && lengthEquals;
        Assert.assertEquals(true, equal);
    }

    /**
     * Method: buildInterval(final String startInterval, final String endInterval)
     * Tests that when a date range string is passed with a start time greater than end time
     * */
    @Test(expected = Exception.class)
    public void testBuildIntervalForStartIntervalEndIntervalHandleStartAfterEnd() throws Exception {
        Interval interval = new Interval();
        String startInterval = "2015-07-01T10:00:00Z";
        String endInterval = "2015-07-01T09:00:00Z";
        interval.buildInterval(startInterval, endInterval);
    }


}
