package com.shoppingCart.exception;

public class ResourceNotFoundException extends BaseException  {

    public ResourceNotFoundException(Error error) {
        super(error);
    }
}
