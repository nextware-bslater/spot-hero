package com.spothero.interview.service;

import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJsonProvider;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.io.File;
import java.net.URI;

import static org.junit.Assert.assertEquals;

//import com.sun.jersey.api.client.Client;

public class RateServiceIT extends JerseyTest {


    private Client client;
    private URI uri;
    private String testJson = "{\"rates\": [ { \"days\": \"mon,tues,thurs\", \"times\": \"0900-2100\", \"price\": 1500 }, { \"days\": \"fri,sat,sun\", \"times\": \"0900-2100\", \"price\": 2000 }, { \"days\": \"wed\", \"times\": \"0600-1800\", \"price\": 1750 }, { \"days\": \"mon,wed,sat\", \"times\": \"0100-0500\", \"price\": 1000 }, { \"days\": \"sun,tues\", \"times\": \"0100-0700\", \"price\": 925 } ] }";
    private String testXml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?> <rateEntity> <rates> <days>mon,tues,thurs</days> <price>1500</price> <times>0900-2100</times> </rates> <rates> <days>fri,sat,sun</days> <price>2000</price> <times>0900-2100</times> </rates> <rates> <days>wed</days> <price>1750</price> <times>0600-1800</times> </rates> <rates> <days>mon,wed,sat</days> <price>1000</price> <times>0100-0500</times> </rates> <rates> <days>sun,tues</days> <price>925</price> <times>0100-0700</times> </rates> </rateEntity>";

    static {
        System.setProperty("jersey.config.test.container.port", "8080");
    }

    @Before
    public void before() throws Exception {
        Client client = ClientBuilder.newBuilder()
                .register(MultiPartFeature.class)
                .build();
        this.client = client;
        this.uri = URI.create("http://localhost:8080/spot-hero/api/rates");
    }


    @Override
    protected Application configure() {
        return new ResourceConfig().packages("com.spothero.interview", "org.glassfish.jersey.media.multipart")
                .register(MultiPartFeature.class)
                .register(JacksonJsonProvider.class);
    }

    @Override
    protected URI getBaseUri() {
        return UriBuilder.fromUri(super.getBaseUri()).path("/spot-hero/api/").build();
    }


    @Test
    public void testJsonInRequestBody() {
        testHelper("2015-07-01T07:00:00Z", "2015-07-01T12:00:00Z", MediaType.APPLICATION_JSON, this.testJson, "1750");
    }

    @Test
    public void testXmlInRequestBody() {
        testHelper("2015-07-01T07:00:00Z", "2015-07-01T12:00:00Z", MediaType.APPLICATION_XML, this.testXml, "1750");
    }

    @Test
    public void testJsonFileInFormData() {
        FileDataBodyPart filePart = new FileDataBodyPart("file", new File("/home/devii_b/fun_code/spot-hero/src/test/resources/rates.json"));
        FormDataMultiPart multipart = new FormDataMultiPart();
        multipart.bodyPart(filePart);

        Response response = client.target(URI.create("http://localhost:8080/spot-hero/api/rates/upload"))
                .queryParam("startInterval", "2015-07-01T07:00:00Z")
                .queryParam("endInterval", "2015-07-01T12:00:00Z")
                .request()
                .post(Entity.entity(multipart, MediaType.MULTIPART_FORM_DATA));
        String responseValue = response.readEntity(String.class);

        assertEquals("Should return status 200", 200, response.getStatus());
        assertEquals("Should return rate of 1750", "1750", responseValue);
    }

    @Test
    public void testMondayExpectedAvailableInterval() {
        testHelper("2017-12-18T10:00:00Z", "2017-12-18T11:00:00Z", MediaType.APPLICATION_JSON, this.testJson, "1500");
    }

    @Test
    public void testMondayExpectedUnavailableInterval() {
        testHelper("2017-12-18T07:00:00Z", "2017-12-18T08:00:00Z", MediaType.APPLICATION_JSON, this.testJson, "unavailable");
    }

    @Test
    public void testTuesdayExpectedAvailableInterval() {
        testHelper("2017-12-19T10:00:00Z", "2017-12-19T11:00:00Z", MediaType.APPLICATION_JSON, this.testJson, "1500");
    }

    @Test
    public void testTuesdayExpectedUnavailableInterval() {
        testHelper("2017-12-19T07:00:00Z", "2017-12-19T08:00:00Z", MediaType.APPLICATION_JSON, this.testJson, "unavailable");
    }

    @Test
    public void testWednesdayExpectedAvailableInterval() {
        testHelper("2017-12-20T07:00:00Z", "2017-12-20T07:30:00Z", MediaType.APPLICATION_JSON, this.testJson, "1750");
    }

    @Test
    public void testWednesdayExpectedUnavailableInterval() {
        testHelper("2017-12-20T05:00:00Z", "2017-12-20T05:30:00Z", MediaType.APPLICATION_JSON, this.testJson, "unavailable");
    }

    @Test
    public void testThursdayExpectedAvailableInterval() {
        testHelper("2017-12-21T10:00:00Z", "2017-12-21T11:00:00Z", MediaType.APPLICATION_JSON, this.testJson, "1500");
    }

    @Test
    public void testThursdayExpectedUnavailableInterval() {
        testHelper("2017-12-21T07:00:00Z", "2017-12-21T08:00:00Z", MediaType.APPLICATION_JSON, this.testJson, "unavailable");
    }

    @Test
    public void testFridayExpectedAvailableInterval() {
        testHelper("2017-12-22T10:00:00Z", "2017-12-22T11:00:00Z", MediaType.APPLICATION_JSON, this.testJson, "2000");
    }

    @Test
    public void testFridayExpectedUnavailableInterval() {
        testHelper("2017-12-22T07:00:00Z", "2017-12-22T08:00:00Z", MediaType.APPLICATION_JSON, this.testJson, "unavailable");
    }

    @Test
    public void testSaturdayExpectedAvailableInterval() {
        testHelper("2017-12-23T10:00:00Z", "2017-12-23T11:00:00Z", MediaType.APPLICATION_JSON, this.testJson, "2000");
    }

    @Test
    public void testSaturdayExpectedUnavailableInterval() {
        testHelper("2017-12-23T07:00:00Z", "2017-12-23T08:00:00Z", MediaType.APPLICATION_JSON, this.testJson, "unavailable");
    }

    @Test
    public void testSundayExpectedAvailableInterval() {
        testHelper("2017-12-24T10:00:00Z", "2017-12-24T11:00:00Z", MediaType.APPLICATION_JSON, this.testJson, "2000");
    }

    @Test
    public void testSundayExpectedUnavailableInterval() {
        testHelper("2017-12-24T07:00:00Z", "2017-12-24T08:00:00Z", MediaType.APPLICATION_JSON, this.testJson, "unavailable");
    }

    private void testHelper(String startInterval, String endInterval, String mediaType, String inputData, String expectedValue){
        Response response = client.target(this.uri)
                .queryParam("startInterval", startInterval)
                .queryParam("endInterval", endInterval)
                .request()
                .post(Entity.entity(inputData, mediaType));
        String responseValue = response.readEntity(String.class);

        assertEquals("Should return status 200", 200, response.getStatus());
        assertEquals("Should return rate of " + expectedValue, expectedValue, responseValue);
    }


}
