package com.parserapplication.apikpirozklad.Exception;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Handler object handles exceptions
 */
@ControllerAdvice
public class Handler extends ResponseEntityExceptionHandler {

    /**
     * Method returns response message of not found exception
     * @param ex NotFoundException
     * @return response message
     */
    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<ResponseMessage> handleServiceException(NotFoundException ex) {
        ResponseMessage errorMessage = new ResponseMessage("Not Found", 404, ex.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
}