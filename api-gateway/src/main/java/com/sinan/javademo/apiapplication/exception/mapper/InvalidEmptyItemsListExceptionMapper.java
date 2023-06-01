package com.sinan.javademo.apiapplication.exception.mapper;

import com.google.gson.Gson;
import com.sinan.javademo.apiapplication.exception.InvalidEmptyItemsListException;
import com.sinan.javademo.apiapplication.model.APIError;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class InvalidEmptyItemsListExceptionMapper implements ExceptionMapper<InvalidEmptyItemsListException> {
    @Override
    public Response toResponse(InvalidEmptyItemsListException ex) {
        APIError apiError = new APIError(ex.getMessage(),
                "This error happened because items list was sent empty, " +
                        "please make sure to add at least one item identifier!");
        return Response.status(Response.Status.BAD_REQUEST).entity(new Gson().toJson(apiError)).build();
    }
}
