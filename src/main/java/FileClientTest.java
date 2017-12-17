/*
 *   Copyright 2013-2017 Clockwork Solutions LLC
 *  
 *   All Rights Reserved.
 */

import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;
import org.glassfish.jersey.server.Uri;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.io.File;
import java.io.IOException;
import java.net.URI;

public class FileClientTest {

    public static void main(String[] args) throws IOException {

        final Client client = ClientBuilder.newBuilder()
                .register(MultiPartFeature.class)
                .build();

        final FileDataBodyPart filePart = new FileDataBodyPart("file", new File("/home/devii_b/fun_code/spot-hero/src/test/resources/rates.json"));

        final FormDataMultiPart multipart = new FormDataMultiPart();
        multipart.bodyPart(filePart);
        URI uri = URI.create("http://localhost:8080/spot-hero/rates/upload");

        final WebTarget target = client.target(uri);
        final Response response = target.request()
                .post(Entity.entity(multipart, multipart.getMediaType()));
    }
}
