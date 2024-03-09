package com.crisdev.api.storeapi.exception;

public class ShoppingCartEmptyException extends RuntimeException{

    public ShoppingCartEmptyException() {
        super();
    }

    public ShoppingCartEmptyException(String message) {
        super(message);
    }

    public ShoppingCartEmptyException(String message, Throwable cause) {
        super(message, cause);
    }
}
