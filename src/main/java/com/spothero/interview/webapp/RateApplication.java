package com.spothero.interview.webapp;


import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.servlets.AdminServlet;
import com.codahale.metrics.servlets.MetricsServlet;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJsonProvider;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RateApplication extends Application {
    public static final String BASE_URI = "http://" + System.getenv("HOST") +":8080/spot-hero/api/";

    /**
     * RateApplication method.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        try {
            System.out.println("SpotHero Rate Application with Jersey and Grizzly");
            System.out.println("YAY");
            // create a resource config that scans for JAX-RS resources and providers
            // in com.spothero.interview.webapp package
            final ResourceConfig rc = new ResourceConfig().packages("com.spothero.interview", "org.glassfish.jersey.media.multipart", "com.codahale.metrics.servlets")
            .register(MultiPartFeature.class)
            .register(JacksonJsonProvider.class);

            // create and start a new instance of grizzly http server
            // exposing the Jersey application at BASE_URI
            final HttpServer server = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
            Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                @Override
                public void run() {
                    server.shutdownNow();
                }
            }));
            server.start();

            Thread.currentThread().join();
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(RateApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



}
