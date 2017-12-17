package com.spothero.interview.webapp.service;

import com.spothero.interview.webapp.entity.RateEntity;
import com.spothero.interview.webapp.pojo.Rate;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("/rates")
public class RateService {

    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public RateEntity post(RateEntity rates) {
        System.out.println("Request = " + request);
        System.out.println("uriInfo = " + uriInfo);
        for (Rate rate : rates.getRates()){
            System.out.println("rate = " + rate);
        }
        return rates;
    }

    @GET
    @Produces("text/plain")
    @Path("/ping")
    public String getRateService() {
        System.out.println("Request = " + request);
        System.out.println("uriInfo = " + uriInfo);
        return "RateService is UP and ready to rock!";
    }



}
