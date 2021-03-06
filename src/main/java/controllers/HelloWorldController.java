package controllers;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Context;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import io.dropwizard.jersey.sessions.Session;

// For a Java class to be eligible to receive ANY requests
// it must be annotated with at least @Path
@Path("")

public class HelloWorldController {

    // You can specify additional @Path steps; they will be relative
    // to the @Path defined at the class level
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/hello")
    public String helloWorld(@Session HttpSession session) {
        return "Hello World " + session.toString();
    }


    @GET
    @Path("/netid")
    public String getNetId() {
        return "nan42";
    }
}
