package com.sinan.javademo.apiapplication.exception.mapper;

import com.google.gson.Gson;
import com.sinan.javademo.apiapplication.exception.RequiredParamException;
import com.sinan.javademo.apiapplication.model.APIError;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

/**
 * A mapper class to map the {@link RequiredParamException} to a response with corresponding {@link APIError}.
 *
 * @author Sinan Wannous
 * @see <a href="https://docs.oracle.com/javaee/7/api/javax/ws/rs/ext/ExceptionMapper.html">Interface ExceptionMapper</a>
 * @since 1.0
 */
@Provider
public class RequiredParamExceptionMapper implements ExceptionMapper<RequiredParamException> {

    private final static String description = "This error happened because a required parameter is not provided. " +
            "please make sure to make a valid API call!";

    @Override
    public Response toResponse(RequiredParamException ex) {
        APIError apiError = new APIError(ex.getMessage(), description);
        return Response.status(Response.Status.BAD_REQUEST).entity(new Gson().toJson(apiError)).build();
    }
}
