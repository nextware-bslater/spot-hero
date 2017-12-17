package com.spothero.interview.webapp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spothero.interview.webapp.entity.Rate;
import com.spothero.interview.webapp.entity.RateEntity;
import com.spothero.interview.webapp.pojo.RateInterval;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;
import java.io.InputStream;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.spothero.interview.webapp.util.RateIntervalHelper.partitionRatesByDay;

@Path("/rates")
public class RateService {

    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public RateEntity post(RateEntity rates ) {
        System.out.println("Request = " + request);
        System.out.println("uriInfo = " + uriInfo);

        for (Rate rate : rates.getRates()){
            System.out.println("rate = " + rate);
        }
        return rates;
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    @Path("/upload")
    public String post(@FormDataParam("file") InputStream uploadedInputStream,
                           @FormDataParam("file") FormDataContentDisposition fileDetails) throws IOException{
        RateEntity rateEntity = null;
        Map<String, List<RateInterval>> dayMap = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        rateEntity = mapper.readValue(uploadedInputStream, RateEntity.class);

        for(Rate rate : rateEntity.getRates()){
            partitionRatesByDay(rate, dayMap);
        }





        return "file";
    }

    @GET
    @Produces("text/plain")
    @Path("/ping")
    public String getRateService() {
        Set<String> allZoneIds = ZoneId.getAvailableZoneIds();
        return "RateService is UP and ready to rock!";
    }



}
