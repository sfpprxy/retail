package com.epam.retail.exception;

import org.springframework.http.HttpStatus;

public class RetailException extends RuntimeException {
    private HttpStatus status = null;

    private Object data = null;

    public RetailException() {
        super();
    }

    public RetailException(String message) {
        super(message);
    }

    public RetailException(HttpStatus status, String message) {
        this(message);
        this.status = status;
    }

    public RetailException(HttpStatus status, String message, Object data) {
        this(status, message);
        this.data = data;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public RetailException setStatus(HttpStatus status) {
        this.status = status;
        return this;
    }

    public Object getData() {
        return data;
    }

    public RetailException setData(Object data) {
        this.data = data;
        return this;
    }
}
