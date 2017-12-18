package com.spothero.interview.webapp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spothero.interview.webapp.dto.RateEntity;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;
import java.io.InputStream;

import static com.spothero.interview.webapp.util.RateUtils.findRateforRequestedDateTime;

@Path("/rates")
public class RateService {

    @Context
    UriInfo uriInfo;
    @Context
    Request request;


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

    @POST
    @Produces({MediaType.TEXT_PLAIN})
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    @Path("/upload")
    public String post( @FormDataParam("file") InputStream uploadedInputStream,
//                       @FormDataParam("file") FormDataContentDisposition fileDetails,
                       @FormDataParam("startInterval") String startInterval,
                       @FormDataParam("endInterval") String endInterval) throws IOException {
//                       @FormDataParam("timeZone") String timeZone

        //TODO add support for client passing time zone
        if (!uploadedInputStream.equals(null) && !startInterval.isEmpty() && !endInterval.isEmpty()) {

            RateEntity rateEntity = null;
//            if (fileDetails.getFileName().endsWith(".json")) {
                ObjectMapper mapper = new ObjectMapper();
                rateEntity = mapper.readValue(uploadedInputStream, RateEntity.class);
//            }
            //Given instructions there is not a need for multipart xml file upload
//            else if (fileDetails.getFileName().endsWith(".xml")) {
//                XmlMapper mapper = new XmlMapper();
//                rateEntity = mapper.readValue(uploadedInputStream, RateEntity.class);
//            }

            return findRateforRequestedDateTime(rateEntity, startInterval, endInterval);

        }
        return "Missing FormData necessary to form a valid request";

    }


}
