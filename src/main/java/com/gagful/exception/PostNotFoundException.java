package com.gagful.exception;

import com.gagful.base.BaseException;

public class PostNotFoundException extends BaseException {
    public PostNotFoundException(String message) {
        super(message);
    }
}
