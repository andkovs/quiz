package example;

//import com.sun.jersey.api.container.httpserver.HttpServerFactory;
//import com.sun.net.httpserver.HttpServer;
//import com.sun.jersey.api.container.httwpserver.HttpServerFactory;
import com.sun.jersey.api.json.JSONMarshaller;
import com.sun.jersey.core.header.MediaTypes;
import org.json.JSONObject;

import java.io.IOException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by oleg on 2/28/15.
 */
// The Java class will be hosted at the URI path "/helloworld"
@Path("/helloworld")
public class HelloWorld {
    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/plain")
    public String getClichedMessage() {
        // Return some cliched textual content
        return "Hello World";
    }

    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/plain")
    @Path("/path")
    public String getTest(@QueryParam(value = "a") String a) {
        // Return some cliched textual content
        return a;
    }

    @POST
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("application/xml")
    @Path("/testXml")
    @Consumes("application/xml")
    public Response getTestPostXlm(Rating rating) {
        // Return some cliched textual content
        System.out.println(rating);
        return Response.status(200).entity(rating).build();
//        return "asd";
    }

    @POST
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("application/json")
    @Path("/testJson")
    @Consumes("application/json")
    public Response getTestPostJson(Rating rating) {
        // Return some cliched textual content
        System.out.println(rating);
        return Response.status(200).entity(rating).build();
//        return "asd";
    }


    public static void main(String[] args) throws IOException {
//        so
//        HttpServer server = HttpServerFactory.create("http://localhost:9998/");
//        server.start();

        System.out.println("Server running");
        System.out.println("Visit: http://localhost:9998/helloworld");
        System.out.println("Hit return to stop...");
        System.in.read();
        System.out.println("Stopping server");
//        server.stop(0);
        System.out.println("Server stopped");
    }
}
