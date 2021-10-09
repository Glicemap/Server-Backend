package com.glicemap.exception;

public class BaseBusinessException extends RuntimeException {
    public BaseBusinessException(String errorMessage) {
        super(errorMessage);
    }
}
