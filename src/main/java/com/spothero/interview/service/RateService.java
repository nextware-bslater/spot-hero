package com.spothero.interview.service;

import com.codahale.metrics.Meter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spothero.interview.dto.Rate;
import com.spothero.interview.dto.RateEntity;
import com.spothero.interview.pojo.Day;
import com.spothero.interview.pojo.Interval;
import com.spothero.interview.pojo.RateInterval;
import com.spothero.interview.util.RateUtils;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.spothero.interview.webapp.RateApplication.METRIC_REGISTRY;

/**
 * Resource allows users to easily calculate parking rates given a desired time interval and a set of constraints
 */
@Path("/rates")
public class RateService {

    @Context
    UriInfo uriInfo;
    @Context
    Request request;

//    private final MetricRegistry metrics = new MetricRegistry();
    private final Meter requests = METRIC_REGISTRY.meter("findRateforRequestedDateTime");

    /**
     * Endpoint allows for users to send a date range and a JSON or XML object from which the date range provided will calculate a rate for parking
     * @param rates a JSON or XML object that contains information about parking rates assocaited with particular day and time intervals
     * @param startInterval time from which you would like to begin your parking interval
     * @param endInterval time at which you would like to end your parking interval
     * @return rate that you can expect to pay given your requested interval
     */
    @POST
    @Produces({MediaType.TEXT_PLAIN})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public String post(RateEntity rates,
                       @QueryParam("startInterval") String startInterval,
                       @QueryParam("endInterval") String endInterval) {

        if (rates.getRates().size() != 0 && !startInterval.isEmpty() && !endInterval.isEmpty()) {
            return findRateForRequestedDateTime(rates, startInterval, endInterval);
        }
        return "Missing FormData necessary to form a valid request";
    }

    /**
     * Endpoint allows for users to send a date range and a JSON file from which the date range provided will calculate a rate for parking
     * @param uploadedInputStream input stream of JSON file that you wish to upload
     * @param startInterval time from which you would like to begin your parking interval
     * @param endInterval time at which you would like to end your parking interval
     * @return rate that you can expect to pay given your requested interval
     * @throws IOException
     */
    @POST
    @Produces({MediaType.TEXT_PLAIN})
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    public String post( @FormDataParam("file") InputStream uploadedInputStream,
                       @QueryParam("startInterval") String startInterval,
                       @QueryParam("endInterval") String endInterval) throws IOException {

        //TODO add support for client passing time zone
        if (!uploadedInputStream.equals(null) && !startInterval.isEmpty() && !endInterval.isEmpty()) {

            ObjectMapper mapper = new ObjectMapper();
            RateEntity rateEntity = mapper.readValue(uploadedInputStream, RateEntity.class);

            return findRateForRequestedDateTime(rateEntity, startInterval, endInterval);

        }
        return "Missing FormData necessary to form a valid request";

    }

    /**
     *Method takes user requested date range input and tests it against the set of rates the user also uploaded
     * @param rateEntity represents list of {@link Rate} objects that user input is to be tested against
     * @param startInterval isoformat string which corresponds to the start an {@link Interval}
     * @param endInterval isoformat string which corresponds to the end an {@link Interval}
     * @return rate or lack there of that a client should expect to pay for parking given a desired time interval and a user defined constraint set
     */
    private String findRateForRequestedDateTime(RateEntity rateEntity, String startInterval, String endInterval)
    {
        Map<String, List<RateInterval>> dayMap = new HashMap<>();

        for (Rate rate : rateEntity.getRates()) {
            try {
                RateUtils.partitionRatesByDay(rate, dayMap);
            } catch (Exception e) {
                //TODO log something
            }
        }

        //Create interval from start and end time
        Day requestDay = new Day(startInterval, endInterval);
        Interval requestInterval = requestDay.getInterval();

        List<RateInterval> rateIntervals;
        if(dayMap.containsKey(requestDay.getDay())) {
            rateIntervals = dayMap.get(requestDay.getDay());
            for(RateInterval rateInterval : rateIntervals){
                Interval checkInterval = rateInterval.getInterval();
                boolean checkIntervalEncapsulatesRequestInterval = checkInterval.encapsulates(requestInterval);
                if(checkIntervalEncapsulatesRequestInterval){
                    return new Integer(rateInterval.getRate()).toString();
                }
            }
            return "unavailable";
        }else{
            return "unavailable";
        }
    }



}
