package com.sinan.javademo.apiapplication.exception.mapper;

import com.google.gson.Gson;
import com.sinan.javademo.apiapplication.model.APIError;
import com.sinan.javademo.smscore.exception.CartItemNotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

/**
 * A mapper class to map the {@link CartItemNotFoundException} to a response with corresponding {@link APIError}.
 * @see <a href="https://docs.oracle.com/javaee/7/api/javax/ws/rs/ext/ExceptionMapper.html">Interface ExceptionMapper</a>
 *
 * @author Sinan Wannous
 * @since 1.0
 */
@Provider
public class CartItemNotFoundExceptionMapper implements ExceptionMapper<CartItemNotFoundException> {

    @Override
    public Response toResponse(CartItemNotFoundException ex) {
        APIError apiError = new APIError(ex.getMessage(),
                "This error happened because an item can't be found in the cart, " +
                        "please make sure to select valid item identifiers!");
        return Response.status(Response.Status.BAD_REQUEST).entity(new Gson().toJson(apiError)).build();
    }
}
