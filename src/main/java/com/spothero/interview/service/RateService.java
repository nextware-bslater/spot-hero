package com.spothero.interview.service;

import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spothero.interview.dto.RateEntity;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;
import java.io.InputStream;

import static com.spothero.interview.util.RateUtils.findRateforRequestedDateTime;
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
            return findRateforRequestedDateTime(rates, startInterval, endInterval);
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
    @Path("/upload")
    public String post( @FormDataParam("file") InputStream uploadedInputStream,
                       @FormDataParam("startInterval") String startInterval,
                       @FormDataParam("endInterval") String endInterval) throws IOException {

        //TODO add support for client passing time zone
        if (!uploadedInputStream.equals(null) && !startInterval.isEmpty() && !endInterval.isEmpty()) {

            RateEntity rateEntity = null;
                ObjectMapper mapper = new ObjectMapper();

            return findRateforRequestedDateTime(rateEntity, startInterval, endInterval);

        }
        return "Missing FormData necessary to form a valid request";

    }


}
