package com.gagful.base;

public class BaseException extends RuntimeException {

    private static final long serialVersionUID = -1814525679916270394L;

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, String type) {
        super(type + message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }
}