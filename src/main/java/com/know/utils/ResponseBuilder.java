package com.know.utils;

import com.know.pojo.ResponseType;
import com.know.pojo.ResponseWrapper;

import javax.ws.rs.core.Response;

public class ResponseBuilder {

    public static Response getResponse(Response.Status status, ResponseType entity) {
        if (status == null) {
            status = Response.Status.OK;
        }

        ResponseWrapper responseWrapper = new ResponseWrapper();
        responseWrapper.setStatus(status.getStatusCode());
        responseWrapper.setResponse(entity);
        return Response.status(status).entity(responseWrapper).build();
    }

}