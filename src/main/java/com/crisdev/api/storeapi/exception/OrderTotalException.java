package com.crisdev.api.storeapi.exception;

public class OrderTotalException extends RuntimeException{

    public OrderTotalException() {
        super();
    }

    public OrderTotalException(String message) {
        super(message);
    }

    public OrderTotalException(String message, Throwable cause) {
        super(message, cause);
    }
}
