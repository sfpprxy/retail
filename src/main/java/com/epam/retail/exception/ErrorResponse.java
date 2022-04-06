package com.epam.retail.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.util.Date;

public class ErrorResponse {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Date timestamp;

    private int code;

    private String status;

    private String message;

    private String errId;

    private Object data;

    public ErrorResponse() {
        timestamp = new Date();
    }

    public ErrorResponse(HttpStatus httpStatus, String message) {
        this();

        this.code = httpStatus.value();
        this.status = httpStatus.name();
        this.message = message;
    }

    public ErrorResponse(HttpStatus httpStatus, String message, String errId) {
        this(httpStatus, message);

        this.errId = errId;
    }

    public ErrorResponse(HttpStatus httpStatus, String message, String errId, Object data) {
        this(httpStatus, message, errId);

        this.data = data;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public ErrorResponse setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public int getCode() {
        return code;
    }

    public ErrorResponse setCode(int code) {
        this.code = code;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public ErrorResponse setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ErrorResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getErrId() {
        return errId;
    }

    public ErrorResponse setErrId(String errId) {
        this.errId = errId;
        return this;
    }

    public Object getData() {
        return data;
    }

    public ErrorResponse setData(Object data) {
        this.data = data;
        return this;
    }
}
