package com.parserapplication.apikpirozklad.Exception;

/**
 * Not Found Exception
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
