package com.epam.retail.exception;

import org.springframework.http.HttpStatus;

public class InvalidFieldException extends RetailException {
    public InvalidFieldException(String message) {
        super(
                HttpStatus.BAD_REQUEST,
                String.format("Following fields contain null or invalid values: %s", message)
        );
    }
}
