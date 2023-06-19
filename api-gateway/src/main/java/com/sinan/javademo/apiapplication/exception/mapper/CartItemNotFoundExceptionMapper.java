package com.sinan.javademo.apiapplication.exception.mapper;

import com.sinan.javademo.apiapplication.contract.APIErrorResponse;
import com.sinan.javademo.smscore.exception.CartItemNotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

/**
 * A mapper class to map the {@link CartItemNotFoundException} to a response with corresponding {@link APIErrorResponse}.
 *
 * @author Sinan Wannous
 * @see <a href="https://docs.oracle.com/javaee/7/api/javax/ws/rs/ext/ExceptionMapper.html">Interface ExceptionMapper</a>
 * @since 1.0
 */
@Provider
public class CartItemNotFoundExceptionMapper implements ExceptionMapper<CartItemNotFoundException> {

    private final static String description = "This error happened because an item can't be found in the cart, " +
            "please make sure to select valid item identifiers!";

    @Override
    public Response toResponse(CartItemNotFoundException ex) {
        APIErrorResponse apiErrorResponse = new APIErrorResponse(
                ex.getMessage(), description, Response.Status.BAD_REQUEST);
        return apiErrorResponse.generateResponse();
    }
}
