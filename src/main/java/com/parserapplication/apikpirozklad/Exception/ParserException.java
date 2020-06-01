package com.parserapplication.apikpirozklad.Exception;

/**
 * Project own exception
 */
public class ParserException extends RuntimeException{
    public ParserException(String error) {
        super(error);
    }
}
