package com.bytenest.mercato_api.infra;

import com.bytenest.mercato_api.exceptions.CustomerNotFound;
import com.bytenest.mercato_api.exceptions.EmailAlreadyExistsException;
import com.bytenest.mercato_api.exceptions.ProductNotFound;
import com.bytenest.mercato_api.exceptions.SaleNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler({
            CustomerNotFound.class,
            ProductNotFound.class,
            SaleNotFound.class
    })
    private ResponseEntity<RestApiError> notFoundHandler(RuntimeException exception) {
        RestApiError restApiError = RestApiError.builder()
                .timeStamp(LocalDateTime.now())
                .code(HttpStatus.NOT_FOUND.value())
                .status(HttpStatus.NOT_FOUND)
                .errors(List.of(exception.getMessage()))
                .build();
        return new ResponseEntity<>(restApiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class
    )
    private ResponseEntity<RestApiError> emailAlreadyExistsHandler(RuntimeException exception){
        RestApiError restApiError = RestApiError.builder()
                .timeStamp(LocalDateTime.now())
                .code(HttpStatus.CONFLICT.value())
                .status(HttpStatus.CONFLICT)
                .errors(List.of(exception.getMessage()))
                .build();
        return new ResponseEntity<>(restApiError, HttpStatus.CONFLICT);
    }


}
