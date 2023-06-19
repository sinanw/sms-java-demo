package com.sinan.javademo.apiapplication.contract;

import com.google.gson.Gson;
import com.google.gson.annotations.JsonAdapter;
import com.sinan.javademo.apiapplication.adapter.APIErrorResponseJsonAdapter;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

/**
 * A model to wrap the error details of any exception that may happen when calling APIs.
 *
 * @author Sinan Wannous
 * @since 1.0
 */
@JsonAdapter(APIErrorResponseJsonAdapter.class)
public class APIErrorResponse {
    private String title, detail;
    private int status;

    public APIErrorResponse() {
    }

    public APIErrorResponse(String title, String detail, Status status) {
        this(title, detail, status.getStatusCode());
    }

    public APIErrorResponse(String title, String detail, int status) {
        this.title = title;
        this.detail = detail;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setStatus(Status status) {
        this.setStatus(status.getStatusCode());
    }

    public Response generateResponse() {
        return Response.status(status)
                .entity(new Gson().toJson(this))
                .type(MediaType.valueOf("application/problem+json"))
                .build();
    }
}

