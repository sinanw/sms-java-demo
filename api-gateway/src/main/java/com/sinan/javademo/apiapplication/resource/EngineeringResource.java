package com.sinan.javademo.apiapplication.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/engineering")
@Produces(MediaType.TEXT_PLAIN)
public class EngineeringResource extends SMSResource {

    @GET
    @Path("/ping")
    public String ping() {
        return "pong";
    }

    @GET
    @Path("/test")
    public String test() {
        return "test";
    }
}
