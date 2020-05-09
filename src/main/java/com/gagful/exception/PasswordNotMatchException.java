package com.gagful.exception;

import com.gagful.base.BaseException;

public class PasswordNotMatchException extends BaseException {
    public PasswordNotMatchException(String message) {
        super(message);
    }
}
