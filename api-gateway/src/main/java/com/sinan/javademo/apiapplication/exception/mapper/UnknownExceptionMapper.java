package com.sinan.javademo.apiapplication.exception.mapper;

import com.google.gson.Gson;
import com.sinan.javademo.apiapplication.model.APIError;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

/**
 * A mapper class to map the general {@link Exception} to a response with corresponding {@link APIError}.
 * @see <a href="https://docs.oracle.com/javaee/7/api/javax/ws/rs/ext/ExceptionMapper.html">Interface ExceptionMapper</a>
 *
 * @author Sinan Wannous
 * @since 1.0
 */
@Provider
public class UnknownExceptionMapper implements ExceptionMapper<Exception> {
    private final static String message = "This response was generated due to " +
            "an unexpected error that happened when processing your request, " +
            "please try again and if the problem persists please contact the " +
            "server administrator and provide the error message!";

    @Override
    public Response toResponse(Exception ex) {
        APIError apiError = new APIError(String.format("SERVER ERROR (%s)", ex.getMessage()), message);
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new Gson().toJson(apiError)).build();
    }
}
