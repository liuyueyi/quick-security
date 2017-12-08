package com.hust.hui.quicksecurity.exception;

/**
 * Created by yihui on 2017/12/8.
 */
public class NoPermissionError extends RuntimeException implements IError {

    public NoPermissionError(String message) {
        super(message);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
