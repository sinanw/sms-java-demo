package com.sinan.javademo.apiapplication.exception.mapper;

import com.google.gson.Gson;
import com.sinan.javademo.apiapplication.model.APIError;
import com.sinan.javademo.smscore.exception.CartNotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

/**
 * A mapper class to map the {@link CartNotFoundException} to a response with corresponding {@link APIError}.
 *
 * @author Sinan Wannous
 * @see <a href="https://docs.oracle.com/javaee/7/api/javax/ws/rs/ext/ExceptionMapper.html">Interface ExceptionMapper</a>
 * @since 1.0
 */
@Provider
public class CartNotFoundExceptionMapper implements ExceptionMapper<CartNotFoundException> {

    private final static String description = "This error happened because cart id can't be found, " +
            "please make sure to send a valid id!";

    @Override
    public Response toResponse(CartNotFoundException ex) {
        APIError apiError = new APIError(ex.getMessage(), description);
        return Response.status(Response.Status.NOT_FOUND).entity(new Gson().toJson(apiError)).build();
    }
}
