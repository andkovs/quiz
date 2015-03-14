package com.univ.service;

import com.univ.dao.QuestionDao;
import com.univ.dao.SubjectDao;
import com.univ.model.Question;
import com.univ.model.Subject;
import example.Rating;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("/test")
public class ServiceTest {
    // The Java method will process HTTP GET requests
//    @GET
//    // The Java method will produce content identified by the MIME Media type "text/plain"
//    @Produces("text/plain")
//    public String getClichedMessage() {
//        // Return some cliched textual content
//        return "Hello World";
//    }

    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("application/json")
    @Path("/subjects")
//    @Consumes("application/json")
    public Subject[] getSubjects() {
        Subject[] subjects = new SubjectDao().loadSubjects();
        return subjects;
    }

    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("application/json")
    @Path("/questions/{id}")
//    @Consumes("application/json")
    public Question[] getQuestionsForSubjects(@PathParam("id") Long id) {
        Question[] questions = new QuestionDao().loadQuestionBySubjectId(id);
        return questions;
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