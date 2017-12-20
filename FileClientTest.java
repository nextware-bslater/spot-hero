/*
 *   Copyright 2013-2017 Clockwork Solutions LLC
 *  
 *   All Rights Reserved.
 */

import com.fasterxml.jackson.jaxrs.xml.JacksonJaxbXMLProvider;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJsonProvider;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.net.URI;

public class FileClientTest {

    public static void main(String[] args) throws IOException {



        final Client client = ClientBuilder.newBuilder()
                .register(MultiPartFeature.class)
                .build();

//        final FileDataBodyPart filePart = new FileDataBodyPart("file", new File("/home/devii_b/fun_code/spot-hero/src/test/resources/rates.xml"));
        final final FormDataBodyPart startInterval = new FormDataBodyPart("startInterval", "2015-07-01T07:00:00Z");
        final FormDataBodyPart endInterval = new FormDataBodyPart("endInterval", "2015-07-01T12:00:00Z");


        final FormDataMultiPart multipart = new FormDataMultiPart();
        multipart.bodyPart(filePart);
        multipart.bodyPart(startInterval);
        multipart.bodyPart(endInterval);

        URI uri = URI.create("http://localhost:8080/spot-hero/api/rates/upload");

        final WebTarget target = client.target(uri);
        final Response response = target.request()
                .post(Entity.entity(multipart, multipart.getMediaType()));
    }
}
