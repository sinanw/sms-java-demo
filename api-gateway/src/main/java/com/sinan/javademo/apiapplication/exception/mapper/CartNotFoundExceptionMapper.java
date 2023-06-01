package com.sinan.javademo.apiapplication.exception.mapper;

import com.google.gson.Gson;
import com.sinan.javademo.apiapplication.model.APIError;
import com.sinan.javademo.smscore.exception.CartNotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class CartNotFoundExceptionMapper implements ExceptionMapper<CartNotFoundException> {
    @Override
    public Response toResponse(CartNotFoundException ex) {
        APIError apiError = new APIError(ex.getMessage(),
                "This error happened because cart id can't be found, " +
                        "please make sure to send a valid id!");
        return Response.status(Response.Status.NOT_FOUND).entity(new Gson().toJson(apiError)).build();
    }
}
