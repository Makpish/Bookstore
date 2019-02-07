package com.know.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseWrapper {
    @JsonProperty("STATUS")
    private Integer status;

    @JsonProperty("RESPONSE")
    private ResponseType response;

    public ResponseWrapper() {
    }

    public void setResponse(ResponseType response) {
        this.response = response;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}