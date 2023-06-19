package com.sinan.javademo.apiapplication.exception.mapper;

import com.sinan.javademo.apiapplication.contract.APIErrorResponse;
import com.sinan.javademo.smscore.exception.CartNotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

/**
 * A mapper class to map the {@link CartNotFoundException} to a response with corresponding {@link APIErrorResponse}.
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
        APIErrorResponse apiErrorResponse = new APIErrorResponse(
                ex.getMessage(), description, Response.Status.NOT_FOUND);
        return apiErrorResponse.generateResponse();
    }
}
