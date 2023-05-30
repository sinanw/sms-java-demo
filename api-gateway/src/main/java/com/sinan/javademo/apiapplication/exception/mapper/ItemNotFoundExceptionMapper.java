package com.sinan.javademo.apiapplication.exception.mapper;

import com.google.gson.Gson;
import com.sinan.javademo.apiapplication.model.APIError;
import com.sinan.javademo.smscore.exception.ItemNotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ItemNotFoundExceptionMapper implements ExceptionMapper<ItemNotFoundException> {

    @Override
    public Response toResponse(ItemNotFoundException ex) {
        APIError apiError = new APIError(ex.getMessage(), "This error happened because cart can't be initialized with no items, please make sure to add at least one item!");
        return Response.status(Response.Status.BAD_REQUEST).entity(new Gson().toJson(apiError)).build();
    }
}
