package com.spothero.interview.webapp;

import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

import static org.junit.Assert.assertEquals;

public class RateApplicationTest extends JerseyTest {


    @Override
    protected Application configure() {
        return new RateApplication();
    }

    @Override
    protected URI getBaseUri() {
        return UriBuilder.fromUri(super.getBaseUri()).path("/spot-hero").build();
    }

    @Test
    public void testClientStringResponse() {
        Response response = target().path("/ping").request().get();

        assertEquals("200", response.getStatus());
    }

}
