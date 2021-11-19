package com.glicemap.exception;

public class BaseBusinessException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public BaseBusinessException(String errorMessage) {
        super(errorMessage);
    }
}
