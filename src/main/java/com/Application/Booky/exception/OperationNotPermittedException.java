package com.Application.Booky.exception;

public class OperationNotPermittedException extends RuntimeException {

    public OperationNotPermittedException(String msg) {

        super(msg); // calling the RuntimeException class with the same msg

    }

}
