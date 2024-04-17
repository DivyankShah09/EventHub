package com.eventhub.backend.utils.httpresponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class HttpResponseSuccess<T> {

    @JsonProperty("statusCode")
    private final Integer statusCode;

    @JsonProperty("statusMessage")
    private final String statusMessage;

    @JsonProperty("data")
    private final T data;

    public HttpResponseSuccess(Integer statusCode, String statusMessage, T data) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.data = data;
    }
}
