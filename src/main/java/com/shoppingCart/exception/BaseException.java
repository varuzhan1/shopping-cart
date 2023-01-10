package com.shoppingCart.exception;

public class BaseException extends RuntimeException{

    protected final Error error;

    public BaseException(Error error) {
        this.error = error;
    }
}
