package com.sinan.javademo.apiapplication.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/**
 * A resource to provide operational APIs. For example, to test the system and make sure it's up and running.
 *
 * @author Sinan Wannous
 * @since 1.0
 */
@Path("/engineering")
@Produces(MediaType.TEXT_PLAIN)
public class EngineeringResource extends SMSResource {

    /**
     * A ping healthcheck endpoint to test if the service is running and accessible.
     *
     * @return a string with 200 OK response indicating the successful reception of the call.
     */
    @GET
    @Path("/ping")
    public String ping() {
        return "pong";
    }

    /**
     * An endpoint to do some tests if necessary (<em>Recommended to be commented out on deployment</em>).
     *
     * @return the test result in a string format.
     */
    @GET
    @Path("/test")
    public String test() {
        return "test";
    }
}
