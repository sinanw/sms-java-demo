package com.sinan.javademo.apiapplication.exception.mapper;

import com.google.gson.Gson;
import com.sinan.javademo.apiapplication.model.APIError;
import com.sinan.javademo.smscore.exception.ItemNotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

/**
 * A mapper class to map the {@link ItemNotFoundException} to a response with corresponding {@link APIError}.
 *
 * @author Sinan Wannous
 * @see <a href="https://docs.oracle.com/javaee/7/api/javax/ws/rs/ext/ExceptionMapper.html">Interface ExceptionMapper</a>
 * @since 1.0
 */
@Provider
public class ItemNotFoundExceptionMapper implements ExceptionMapper<ItemNotFoundException> {

    @Override
    public Response toResponse(ItemNotFoundException ex) {
        APIError apiError = new APIError(ex.getMessage(),
                "This error happened because an item can't be found in our system, " +
                        "please make sure to select valid item identifiers!");
        return Response.status(Response.Status.NOT_FOUND).entity(new Gson().toJson(apiError)).build();
    }
}
