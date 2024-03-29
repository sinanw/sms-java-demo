package com.sinan.javademo.apiapplication.exception.mapper;

import com.sinan.javademo.apiapplication.contract.APIErrorResponse;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

/**
 * A mapper class to map the general {@link Exception} to a response with corresponding {@link APIErrorResponse}.
 *
 * @author Sinan Wannous
 * @see <a href="https://docs.oracle.com/javaee/7/api/javax/ws/rs/ext/ExceptionMapper.html">Interface ExceptionMapper</a>
 * @since 1.0
 */
@Provider
public class UnknownExceptionMapper implements ExceptionMapper<Exception> {
    private final static String description = "This response was generated due to " +
            "an unexpected error that happened when processing your request, " +
            "please try again and if the problem persists please contact the " +
            "server administrator and provide the error message!";

    @Override
    public Response toResponse(Exception ex) {
        APIErrorResponse apiErrorResponse = new APIErrorResponse(
                String.format("SERVER ERROR (%s)", ex.getMessage()), description,
                Response.Status.INTERNAL_SERVER_ERROR);
        return apiErrorResponse.generateResponse();
    }
}
