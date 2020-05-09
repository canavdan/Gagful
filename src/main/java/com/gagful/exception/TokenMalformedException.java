package com.gagful.exception;

import com.gagful.base.BaseException;

public class TokenMalformedException extends BaseException {
    public TokenMalformedException(String message) {
        super(message);
    }
}
