package com.epam.retail.exception;

import com.epam.retail.util.U;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
class RetailControllerAdvice {

    private static final Logger log = LoggerFactory.getLogger(RetailControllerAdvice.class);

    @ExceptionHandler({RetailException.class, HttpMessageConversionException.class})
    public ResponseEntity<ErrorResponse> handleCustomErrorExceptions(Exception re) {
        log.info("RetailException: ", re);

        HttpStatus status = HttpStatus.BAD_REQUEST;

        return new ResponseEntity<>(
                new ErrorResponse(
                        status,
                        re.getMessage(),
                        U.uuid(),
                        null
                ),
                status
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleOtherExceptions(Exception e) {
        log.error("INTERNAL SERVER ERROR: ", e);

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        return new ResponseEntity<>(
                new ErrorResponse(
                        status,
                        "Please contact the administrator",
                        U.uuid(),
                        null
                ),
                status
        );
    }
}
