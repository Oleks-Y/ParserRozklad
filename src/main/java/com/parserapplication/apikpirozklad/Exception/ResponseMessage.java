package com.parserapplication.apikpirozklad.Exception;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonPropertyOrder(value = {"type", "code", "message"})
public class ResponseMessage {
    @JsonProperty("code")
    private int code;

    @JsonProperty("type")
    private String type;

    @JsonProperty("message")
    private String message;

    public ResponseMessage(String type, int code, String message) {
        this.type = type;
        this.code = code;
        this.message = message;
    }

    public int getCode() {return code;}

    public void setCode(int code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
