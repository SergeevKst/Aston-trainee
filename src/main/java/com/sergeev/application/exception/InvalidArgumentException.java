package com.sergeev.application.exception;

/**
 * specific runtime exception for some invalid arguments
 */
public class InvalidArgumentException extends RuntimeException{
    public InvalidArgumentException(String message) {
        super(message);
    }
}
