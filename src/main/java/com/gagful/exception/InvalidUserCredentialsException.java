package com.gagful.exception;

import com.gagful.base.BaseException;

public class InvalidUserCredentialsException extends BaseException {
    public InvalidUserCredentialsException(String message) {
        super(message);
    }
}
